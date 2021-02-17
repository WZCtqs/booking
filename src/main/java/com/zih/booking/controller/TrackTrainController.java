package com.zih.booking.controller;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.dao.TrackTrainMapper;
import com.zih.booking.model.*;
import com.zih.booking.service.*;
import com.zih.booking.system.config.rabbitmq.TrainEmailMq;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.DateUtils;
import com.zih.booking.utils.SendMailUtil;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.EmaiMqVo;
import com.zih.booking.vo.SendEmailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 运踪_班列站到站 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/trackTrain")
@Api(tags = "班列跟踪中心")
public class TrackTrainController {
    @Autowired
    private TrackTrainService trackTrainService;
    @Resource
    private TrackTrainMapper trackTrainMapper;
    @Autowired
    private TrackAbnormalBoxService trackAbnormalBoxService;
    @Autowired
    private TrackAddressService trackAddressService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private EmailRecordService emailRecordService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BusiClientsService busiClientsService;
    @Autowired
    private TrackTwoLevelService trackTwoLevelService;
    @Autowired
    private BusiShippingorderService busiShippingorderService;
    @Autowired
    private TrackGoodsStatusService trackGoodsStatusService;
    @Autowired
    private TrackAbroadService trackAbroadService;


    private String getEnAddress(String address) {
        String enAddress = address;
        if (trackAddressService.selectList(new EntityWrapper<TrackAddress>().eq("name_ch", address)).size() != 0) {
            enAddress = trackAddressService.selectList(new EntityWrapper<TrackAddress>().eq("name_ch", address)).get(0).getNameEn();
        }
        return enAddress;
    }

    @GetMapping("/classTrack")
    @ApiOperation("班列运踪")
    public Result classTrack(String orderId,String number,String language,String inORout) {
        BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
        Map<String, Object> map = new HashMap<>();
        map.put("abroad",0);
        if("2".equals(shippingOrder.getLineTypeid())&&"1".equals(shippingOrder.getClassEastandwest())){
            map.put("abroad",2);
        }
        String trackTime =null;
        //根据orderId获取实际班列日期
        List<TrackGoodsStatus> acDateList=new ArrayList<>();
        if(StringUtils.isNotEmpty(number)&&number.startsWith("ZIH")){
            acDateList=trackGoodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id", orderId).eq("del_flag",0));
        }else{
            acDateList=trackGoodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id", orderId).eq("box_num",number).eq("del_flag",0));
        }
        List<List<TrackTrain>> trackTrainsList=new ArrayList<>();
        List<List<TrackAbroad>> trackAbroadList=new ArrayList<>();
        if(acDateList==null||acDateList.size()==0){
            map.put("trackTrainsList",trackTrainsList);
            map.put("trackAbroadList",trackAbroadList);
            return new Result(map);
        }
        String actualDate=null;
       // String batchTime=null;
        //循环获取实际班列日期
        for(int j=0;j<acDateList.size();j++){
            actualDate=acDateList.get(j).getActualClassDate();
            List<TrackTrain> trackTrains=new ArrayList<>();
            //实际班列日期为空班列运踪为空
            if(actualDate!=null&&!"".equals(actualDate)&&!"/".equals(actualDate)){
                //带x,显示这个仓位号和箱号在改为10.22p-x之前10.22p所有的班列运踪+改之后10.22p-x的班列运踪，获取修改成X的时间
               /* if(actualDate.contains("X")||actualDate.contains("x")){
                    Date batTime= acDateList.get(0).getBatchTime();
                    batchTime=DateUtils.parseStr(batTime,"yyyy-MM-dd HH:mm:ss");
                }*/
                //不带x,显示不带x的所有班列运踪
                //trackTrains = trackTrainMapper.selectTtList(acDateList.get(j).getActualClassId(),actualDate,batchTime);
                trackTrains=trackTrainMapper.selectTrainListByTgs(acDateList.get(j));
                //中亚境外
                if("2".equals(shippingOrder.getLineTypeid())&&"0".equals(shippingOrder.getClassEastandwest())){
                    //查询出入境时间
                /*    if(trackTrains!=null&&trackTrains.size()!=0) {
                        String gocome = "1";//"0"到达"1"驶离"2"在
                        String chPort = trackTrains.get(0).getCreateBy();
                        String enPort = trackTrains.get(0).getUpdateBy();
                        for (int i = 0; i < trackTrains.size(); i++) {//state "0"到达"1"驶离"2"在
                            if (StringUtils.isEmpty(chPort)) {
                                chPort = "0";
                            }
                            if (StringUtils.isEmpty(enPort)) {
                                enPort = "0";
                            }
                            if ((chPort.contains(StringUtils.isNotEmpty(trackTrains.get(i).getCurrentLocation()) ? trackTrains.get(i).getCurrentLocation().toLowerCase() : "") ||
                                    enPort.contains(StringUtils.isNotEmpty(trackTrains.get(i).getCurrentLocation()) ? trackTrains.get(i).getCurrentLocation().toLowerCase() : ""))
                                    && gocome.equals(trackTrains.get(i).getStateValue())) {
                                trackTime = trackTrains.get(i).getTrackTime();
                            }
                        }
                    }*/

                    List<TrackAbroad> abroadList = trackAbroadService.selectList(new EntityWrapper<TrackAbroad>().eq("order_id", orderId)
                            .eq("box_num",acDateList.get(j).getBoxNum()).eq("del_flag",0).orderBy("update_time",false));
                    if (abroadList != null && abroadList.size() != 0) {
                        /*for (int n = 0; n < abroadList.size(); n++) {
                            abroadList.get(n).setRemark(StringUtils.isNotEmpty(trackTime)?trackTime.substring(0,10):"");
                        }*/
                        trackAbroadList.add(abroadList);
                    }
                    map.put("abroad",1);
                }
            }


            //英文
            if(language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())){
                for(int i=0;i<trackTrains.size();i++){
                    if(trackTrains.get(i).getCurrentLocation().matches("[\u4E00-\u9FA5]+")){
                        trackTrains.get(i).setCurrentLocation(trackAddressService.selectOne(new EntityWrapper<TrackAddress>()
                                .eq("name_ch",trackTrains.get(i).getCurrentLocation())).getNameEn());
                    }
                    if(!"".equals(trackTrains.get(i).getStationOneName())&&null!=trackTrains.get(i).getStationOneName()){
                        trackTrains.get(i).setStationOneName(getEnStation(trackTrains.get(i).getStationOneName()));
                    }
                    if(!"".equals(trackTrains.get(i).getStationTwoName())&&null!=trackTrains.get(i).getStationTwoName()){
                        trackTrains.get(i).setStationTwoName(getEnStation(trackTrains.get(i).getStationTwoName()));
                    }
                    if(!"".equals(trackTrains.get(i).getStationThrName())&&null!=trackTrains.get(i).getStationThrName()){
                        trackTrains.get(i).setStationThrName(getEnStation(trackTrains.get(i).getStationThrName()));
                    }
                    if(!"".equals(trackTrains.get(i).getStationFouName())&&null!=trackTrains.get(i).getStationFouName()){
                        trackTrains.get(i).setStationFouName(getEnStation(trackTrains.get(i).getStationFouName()));
                    }
                }
            }
            for(int i=0;i<trackTrains.size();i++){
                trackTrains.get(i).setActualClassDate(acDateList.get(j).getBoxNum());
                //到站时间 07-30/08-01 2222-11-08
                if(trackTrains.get(i).getExceptEarlyTime()!=null&&!"".equals(trackTrains.get(i).getExceptEarlyTime())
                        &&trackTrains.get(i).getExceptLastTime()!=null&&!"".equals(trackTrains.get(i).getExceptLastTime())){
                    trackTrains.get(i).setExceptEarlyTime(trackTrains.get(i).getExceptEarlyTime().substring(8,10)+" "+getEnMonth(trackTrains.get(i).getExceptEarlyTime().substring(5,7))+
                            "/"+trackTrains.get(i).getExceptLastTime().substring(8,10)+" "+getEnMonth(trackTrains.get(i).getExceptLastTime().substring(5,7)));
                }
            }

            //查询是否异常箱
            /*List<TrackGoodsStatus> tgsList=trackGoodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id", orderId).isNotNull("abnormal_time").
                    eq("del_flag",0).eq("is_normal",1).eq("box_num",acDateList.get(j).getBoxNum()).orderBy("abnormal_time",true));
            if(tgsList!=null&&tgsList.size()>0){
                List<TrackTrain> abList=new ArrayList<>();
                for (int g = 0; g < trackTrains.size(); g++) {//异常箱之后运踪不显示
                    if(trackTrains.get(g).getUpdateTime().before(tgsList.get(0).getAbnormalTime())){
                        abList.add(trackTrains.get(g));
                    }
                }
                trackTrains=abList;
            }*/

            //欧线去程班列上有多个下货站的货物，需要判断一下到布列斯特（布列斯特，莫斯科，明斯克，圣彼得堡）、马拉（马拉，华沙，布拉格，布达佩斯）下货的货物，之后的运踪不再显示
            if("0".equals(shippingOrder.getLineTypeid())&&"0".equals(shippingOrder.getClassEastandwest())){
                List<TrackTrain> actualList=new ArrayList<>();
                List<String> emptyList=new ArrayList<>();
                emptyList.add("0");
                emptyList.add("/");
                List<String> brestUnLoadsite=new ArrayList<>();
                brestUnLoadsite.add("布列斯特");
                brestUnLoadsite.add("莫斯科");
                brestUnLoadsite.add("明斯克");
                brestUnLoadsite.add("圣彼得堡");
                //布列斯特下货
                if(brestUnLoadsite.contains(shippingOrder.getOrderUnloadsite())){
                    //已经经过或者在布列斯特,布列斯特之后运踪不显示
                    if(emptyList.contains(trackTrains.get(0).getStationTwoDistance())){
                        int n=0;
                        for(int i=0;i<trackTrains.size();i++){
                            if("0".equals(trackTrains.get(i).getStateValue())&&(trackTrains.get(i).getCurrentLocation().contains("布列斯特")||trackTrains.get(i).getCurrentLocation().contains("Brest")
                                    ||trackTrains.get(i).getCurrentLocation().contains("brest"))){
                                n=i;
                                break;
                            }
                        }
                        //从n到size-1
                        for(int m=n;m<trackTrains.size();m++){
                            actualList.add(trackTrains.get(m));
                        }
                        trackTrains=actualList;
                    }
                }
                List<String> malaUnLoadsite=new ArrayList<>();
                malaUnLoadsite.add("马拉");
                malaUnLoadsite.add("华沙");
                malaUnLoadsite.add("布拉格");
                malaUnLoadsite.add("布达佩斯");
                //马拉下货
                if(malaUnLoadsite.contains(shippingOrder.getOrderUnloadsite())){
                    //已经经过或者在马拉,马拉之后运踪不显示
                    if(emptyList.contains(trackTrains.get(0).getStationThrDistance())){
                        int n=0;
                        for(int i=0;i<trackTrains.size();i++){
                            if("0".equals(trackTrains.get(i).getStateValue())&&(trackTrains.get(i).getCurrentLocation().contains("马拉")||trackTrains.get(i).getCurrentLocation().contains("Mala")
                                    ||trackTrains.get(i).getCurrentLocation().contains("mala"))){
                                n=i;
                                break;
                            }
                        }
                        //从n到size-1
                        for(int m=n;m<trackTrains.size();m++){
                            actualList.add(trackTrains.get(m));
                        }
                        trackTrains=actualList;
                    }
                }
            }
            if(trackTrains!=null&&trackTrains.size()>0){
                trackTrainsList.add(trackTrains);
            }
        }
        if("in".equals(inORout)){
            if(trackTrainsList!=null&&trackTrainsList.size()>0){
                map.put("trackTrainsList",trackTrainsList.get(0));
            }else{
                map.put("trackTrainsList",new ArrayList<>());
            }
            if(trackAbroadList!=null&&trackAbroadList.size()>0){
                map.put("trackAbroadList",trackAbroadList.get(0));
            }else{
                map.put("trackAbroadList",new ArrayList<>());
            }
            return new Result(map);
        }
        map.put("trackAbroadList",trackAbroadList);
        map.put("trackTrainsList",trackTrainsList);
        return new Result(map);
    }

    @GetMapping("/classErrorTrack")
    @ApiOperation("异常运踪")
    public Result classErrorTrack(String orderId) {
        List<TrackAbnormalBox> trackTrains = trackAbnormalBoxService.selectList(new EntityWrapper<TrackAbnormalBox>().eq("order_id", orderId).orderBy("input_time", false));
        return new Result(trackTrains);
    }

    @PostMapping("/sendMail")
    @ApiOperation("运踪发送邮件")
    public Result sendMail(@RequestBody SendEmailVo sendEmailVo) {
        BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_number",sendEmailVo.getOrderNumber()));
        //获取发送邮件和密码,线路，去回程，整拼箱
        EmaiMqVo emaiMqVo= new EmaiMqVo();
        //中欧中越
        if("0".equals(shippingOrder.getLineTypeid())||"3".equals(shippingOrder.getLineTypeid())){
            emaiMqVo.setLineType(0);
            emaiMqVo.setDelFlag(0);
            emaiMqVo.setIsCustom(0);
            emaiMqVo.setGoCome(Integer.valueOf(shippingOrder.getClassEastandwest()));
            emaiMqVo.setConsolidationType(Integer.valueOf(shippingOrder.getIsConsolidation()));
        }else{
            emaiMqVo.setDelFlag(0);
            emaiMqVo.setLineType(1);
        }
        emaiMqVo = trackTrainMapper.selectEmail(emaiMqVo);

        String clientId= tokenService.getClientId(ServletUtils.getRequest());
        String name=busiClientsService.selectById(clientId).getClientUnit();
        String text = "<p>尊敬的客户，您好/Dear customer：</p>\n";
        //List<TrackTrain> trackTrains=trackTrainService.selectList(new EntityWrapper<TrackTrain>().eq("class_id",classId).orderBy("track_time",false));
        for (int i = 0; i < sendEmailVo.getId().length; i++) {
            TrackTrain trackTrain = trackTrainService.selectById(sendEmailVo.getId()[i]);
            //"0"到达"1"驶离"2"在
            String stateValue = trackTrain.getStateValue();
            if ("0".equals(stateValue)) {
                stateValue = "到达/arrive in";
            } else if ("1".equals(stateValue)) {
                stateValue = "驶离/depart from";
            } else if ("2".equals(stateValue)) {
                stateValue = "在/in";
            }

            String one = "";
            String two = "";
            String thr = "";
            String fou = "";
            if (!StringUtils.isEmpty(trackTrain.getStationOneDistance())&&!"/".equals(trackTrain.getStationOneDistance())) {
                one= "<p>距离" + trackTrain.getStationOneName() + "/distance until " + getEnAddress(trackTrain.getStationOneName()) + ":" + trackTrain.getStationOneDistance() + "km；</p>\n" ;
            }
            if (!StringUtils.isEmpty(trackTrain.getStationTwoDistance())&&!"/".equals(trackTrain.getStationTwoDistance())) {
                two="<p>距离" + trackTrain.getStationTwoName() + "/distance until " + getEnAddress(trackTrain.getStationTwoName()) + ":" + trackTrain.getStationTwoDistance() + "km；</p>\n";
            }
            if (!StringUtils.isEmpty(trackTrain.getStationThrDistance())&&!"/".equals(trackTrain.getStationThrDistance())) {
                thr = "<p>距离" + trackTrain.getStationThrName() + "/distance until " + getEnAddress(trackTrain.getStationThrName()) + ":" + trackTrain.getStationThrDistance() + "km；</p>\n";
            }
            if (!StringUtils.isEmpty(trackTrain.getStationFouDistance())&&!"/".equals(trackTrain.getStationFouDistance())) {
                fou = "<p>距离" + trackTrain.getStationFouName() + "/distance until " + getEnAddress(trackTrain.getStationFouName()) + ":" + trackTrain.getStationFouDistance() + "km；</p>\n";
            }
            String remark = "";
            if (!StringUtils.isEmpty(trackTrain.getRemark())) {
                remark="<p>备注/Remark:" + trackTrain.getRemark() + "</p>\n" ;
            }
            text = text +
                    "<p>班列日期/Train date：" + trackTrain.getActualClassDate() + "；</p>\n" +
                    "<p>舱位编号/Booking number：" + sendEmailVo.getOrderNumber() + "；</p>\n" +
                    "<p>运踪时间/Tracing time： " + trackTrain.getTrackTime() + "；</p>\n" +
                    "<p>状态/Status: "+stateValue+" "+trackTrain.getCurrentLocation()+"；</p>\n"+
                    one +
                    two +
                    thr +
                    fou +
                    "<p>预计到港时间/ETA time:" + trackTrain.getExceptEarlyTime() + "/" + trackTrain.getExceptLastTime() + "；</p>\n" +
                    remark +
                    "<p>我司会根据班列的实时运行情况对ETA进行调整，请知悉。/we will adjust the ETA according to the real-time operation of the train, please be informed.</p>\n"
                    + "<br />";
        }
        String subject = "Tracing info of West-Bound ZZ-train"+new Date().toLocaleString();//邮件主题   *
        emaiMqVo.setSubject(subject);
        emaiMqVo.setContent(text);
        String[] arr =sendEmailVo.getTo().split(";");
        for(int i=0;i<arr.length;i++){
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            EmailRecord emailRecord=new EmailRecord();
            emailRecord.setOrderNumber(sendEmailVo.getOrderNumber());
            emailRecord.setSendEmail(arr[i]);
            emailRecord.setSendName(name);
            emailRecord.setSendTime(new Date());
            emailRecordService.insert(emailRecord);
            emaiMqVo.setReceiveEmail(arr[i]);
            rabbitTemplate.convertAndSend(TrainEmailMq.TRAIN_EMAIL_TOPIC_EXCHANGE, TrainEmailMq.TRAIN_EMAIL_ROUTINGKEY, emaiMqVo, correlationData);
            //String[] email = {arr[i]};
            //SendMailUtil.sendTrackMail(emaiMqVo.getAccount(), emaiMqVo.getPassword(),email,emaiMqVo.getSubject(),emaiMqVo.getContent());
        }
            return new Result(true);
    }

    @GetMapping("/sendErrorMail")
    @ApiOperation("异常箱发送邮件")
    public Result sendErrorMail(String[] to, String id) {
        TrackAbnormalBox trackAbnormalBox = trackAbnormalBoxService.selectById(id);
        String four = "";

        String text = "Dear customer,\n" +
                "Nice day to you!\n" +
                "CICU8108488：ZIHEB200108LHX08\n" +
                "异常原因/Abnormal cause：" + trackAbnormalBox.getUnloadReason() + "\n" +
                "下货地点/Unloading point：" + trackAbnormalBox.getUnloadSite() + "/" + getEnAddress(trackAbnormalBox.getUnloadSite()) + "\n" +
                "运踪信息/Tracing info：\n" +
                trackAbnormalBox.getAbnormalInformation() + " .\n" +
                "So sorry for the inconvenience to you, please understand.";
        String subject = "Tracing info of West-Bound ZZ-train2020-01-08";//邮件主题   *
        SendMailUtil.sendHtmlMail(to, text, subject);
        return new Result(true);
    }

    @GetMapping("/allTwoNode")
    @ApiOperation("根据订单id获取所有二级运踪")
    public Result allTwoNode(String orderId,String language) {
        List<TrackTwoLevel>  ttlList=trackTwoLevelService.selectList(new EntityWrapper<TrackTwoLevel>().eq("order_id", orderId).eq("is_custom","0").orderBy("sort"));
       //中亚查询去程班列运踪之后不要，回程已订舱-班列运踪
        BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
        //中亚去程只显示到班列运踪
        if("0".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            for(int i=0;i<ttlList.size();i++){
                if(ttlList.get(i).getSort()>23){
                    ttlList.remove(i);
                }
            }
        }else if("1".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            for(int i=0;i<ttlList.size();i++){
                if(ttlList.get(i).getSort()>7&&ttlList.get(i).getSort()<18){
                    ttlList.remove(i);
                }
            }
        }
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            for (int i=0;i<ttlList.size();i++) {
                ttlList.get(i).setNameZh(ttlList.get(i).getNameEn());
                ttlList.get(i).setRemark(ttlList.get(i).getRemarkEn());
            }
        }
        return new Result(ttlList);
    }

    //获取中文名对应的英文名
    public String getEnStation(String chStation){
        Map<String, String> nameMap=new HashMap();
        nameMap.put("山口","Alashankou");
        nameMap.put("布列斯特","BREST");
        nameMap.put("马拉","Malaszewicze");
        nameMap.put("列日","Liege");
        nameMap.put("绥芬河","Suifenhe");
        nameMap.put("霍尔果斯","Khorgos");
        nameMap.put("满洲里","Manzhouli");
        nameMap.put("郑州圃田","Putian.Zhengzhou");
        nameMap.put("郑州","Putian.Zhengzhou");
        nameMap.put("二连","Erenhot");
        nameMap.put("扎门乌德","Zamyn-Uud");
        nameMap.put("格罗捷克沃","Grodekovo");
        nameMap.put("沃尔西诺","Vorsino");
        nameMap.put("后贝加尔斯克","Zabaikalsk");
        nameMap.put("河内","Hanoi");
        nameMap.put("凭祥","Pinxiang");
        nameMap.put("阿拉木图","Almaty");
        nameMap.put("塔什干","Tashkent");
        nameMap.put("丘库尔赛","Chukursay");
        nameMap.put("谢尔盖利","Sergeli");
        nameMap.put("汉堡","Hamburg");
        nameMap.put("慕尼黑","Munich");
        nameMap.put("丘诺亚尔","Chunoyal");
        nameMap.put("克拉斯诺亚尔斯克","Krasnoyarsk");
        nameMap.put("伊尔库茨克","Irkutsk");
        nameMap.put("赤塔","Chita");
        nameMap.put("亨克","Genk");
        return nameMap.get(chStation);
    }

    //获取数字对应英文名
    public String getEnMonth(String chMonth){
        Map<String, String> nameMap=new HashMap();
        nameMap.put("01","Jan.");
        nameMap.put("02","Feb.");
        nameMap.put("03","Mar.");
        nameMap.put("04","Apr.");
        nameMap.put("05","May.");
        nameMap.put("06","Jun.");
        nameMap.put("07","Jul.");
        nameMap.put("08","Aug.");
        nameMap.put("09","Sep.");
        nameMap.put("10","Oct.");
        nameMap.put("11","Nov.");
        nameMap.put("12","Dec.");
        return nameMap.get(chMonth);
    }

}

