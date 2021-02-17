package com.zih.booking.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.BusiClients;
import com.zih.booking.model.KhUser;
import com.zih.booking.model.SysDept;
import com.zih.booking.model.SysUser;
import com.zih.booking.request.BaseRequest;
import com.zih.booking.request.BusiClientsRequest;
import com.zih.booking.request.LoginRequest;
import com.zih.booking.request.RegisterRequest;
import com.zih.booking.response.SysUserResponse;
import com.zih.booking.service.BusiClientsService;
import com.zih.booking.service.KhUserService;
import com.zih.booking.service.SysDeptService;
import com.zih.booking.service.SysUserService;
import com.zih.booking.system.config.rabbitmq.RegisterMq;
import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.shiro.LoginType;
import com.zih.booking.system.shiro.UserToken;
import com.zih.booking.system.token.LoginUser;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author shahy123
 * @since 2020-03-11
 */
@Slf4j
@Api(tags = "客户相关")
@RestController
@RequestMapping("/busiClients")
public class BusiClientsController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BusiClientsService busiClientsService;
    @Autowired
    private KhUserService khUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation(value = "登录", notes = "账号密码登录")
    @PostMapping(value = "/login")
    public ApiResultI18n login(LoginRequest loginRequest) {
        UserToken token = new UserToken(LoginType.USER_PASSWORD, loginRequest.getUserName(), loginRequest.getPassword());
        return shiroLogin(token, loginRequest.getLanguage());
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public ApiResultI18n logout(BaseRequest baseRequest) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ApiResultI18n.success(baseRequest.getLanguage());
    }

    public ApiResultI18n shiroLogin(UserToken token, String language) {
        try {
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                JSON json = new JSONObject();
                ((JSONObject) json).put("token", subject.getSession().getId());
                BusiClients busiClients = (BusiClients) subject.getPrincipal();
                ((JSONObject) json).put("user", busiClients);
                return ApiResultI18n.success(json, language);
            } else {
                return ApiResultI18n.failure(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD, language);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            log.error(e.getMessage(), e);
            return ApiResultI18n.failure(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD, language);
        } catch (DisabledAccountException e) {
            return ApiResultI18n.failure(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD, language);
        } catch (Exception e) {
            return ApiResultI18n.failure(ResultStatusCode.SYSTEM_ERR, language);
        }
    }

    @GetMapping(value = "/getCode")
    @ApiOperation("发送验证码")
    public ApiResultI18n getCode(@RequestParam(value = "phone") String phone) {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        // 发送验证码
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        return ApiResultI18n.success(code, null);
    }
    @GetMapping(value = "/getTjr")
    @ApiOperation("获取推荐人列表")
    public ApiResultI18n getTjr(  ) {
        List<SysUser>users=sysUserService.selectList(new EntityWrapper<SysUser>().eq("reference_type",1).eq("del_flag",0).eq("status",0));
        List<SysUserResponse> responses=new ArrayList<>();
        for(SysUser user:users){
            SysUserResponse response=new SysUserResponse();
            if(sysDeptService.selectList(new EntityWrapper<SysDept>().eq("dept_id",user.getDeptId())).size()!=0){
                response.setClientTjr(sysDeptService.selectList(new EntityWrapper<SysDept>().eq("dept_id",user.getDeptId())).get(0).getDeptName()+"-"+user.getNickName());
            }else{
                response.setClientTjr(user.getNickName());
            }
            response.setClientTjrId(user.getTjrId()+"");
            responses.add(response);
        }
        return ApiResultI18n.success(responses , null);
    }

    @ApiOperation(value = "注册", notes = "手机号验证码密码注册")
    @PostMapping(value = "/register")
    public ApiResultI18n register(RegisterRequest registerRequest) {
        log.info("registerRequest-----------------"+registerRequest);
        if ((busiClientsService.selectList(new EntityWrapper<BusiClients>().eq("client_loginUser", registerRequest.getClientPhone())
                .ne("IsExamline","2").ne("cancelAccount","3").ne("cancelAccount","2")).size() != 0)
                ||( khUserService.selectList(new EntityWrapper<KhUser>().eq("phone",registerRequest.getClientPhone())
                .eq("del_flag","0")).size() != 0)
        ) {
            return ApiResultI18n.failure(1,"该手机号已注册",registerRequest.getLanguage());
        } else {
            BusiClients busiClients = new BusiClients();
            BeanUtils.copyProperties(registerRequest, busiClients);
            busiClients.setClientId(UUIDUtils.getId());
            busiClients.setClientLoginuser(registerRequest.getClientPhone());
            String password = PasswordUtil.randomPassword();
            log.info(password);
            busiClients.setClientLoginpwd(password);
            busiClients.setCreatedate(new Date());
            busiClients.setIsExamline("0");//待审核
            busiClients.setEngChinese("0");
            busiClients.setIsSign(registerRequest.getIsSign());
            busiClients.setPowerType(1);
            //2020-09-01 - 2020-09-26 签订合同
            if("0".equals(registerRequest.getIsSign())){
                busiClients.setPowerType(0);
                busiClients.setSignDate(DateUtils.parseDate(registerRequest.getValidDate().substring(0,10),"yyyy-MM-dd"));
                busiClients.setSignDisableddate(DateUtils.parseDate(registerRequest.getValidDate().substring(13),"yyyy-MM-dd"));
                busiClients.setClientValiditydate(DateUtils.parseDate(registerRequest.getValidDate().substring(0,10),"yyyy-MM-dd"));
                busiClients.setClientDisableddate(DateUtils.parseDate(registerRequest.getValidDate().substring(13),"yyyy-MM-dd"));
                busiClients.setContractTime(String.valueOf(DateUtils.getDistanceOfTwoDate(busiClients.getSignDate(),busiClients.getSignDisableddate()))+"天");
                Calendar c = Calendar.getInstance();
                c.setTime(busiClients.getSignDisableddate());
                c.add(Calendar.DAY_OF_MONTH, -3);
                Date remindTime =c.getTime();
                busiClients.setRemindTime(DateUtils.parseStr(remindTime));
                //合同状态,0正常，1即将到期，2过期
                if(busiClients.getSignDisableddate().before(new Date())){
                    busiClients.setContractStatus(2);
                }else {
                    if(remindTime.before(new Date())){
                        busiClients.setContractStatus(1);
                    }else{
                        busiClients.setContractStatus(0);
                    }
                }
            }
            if(registerRequest.getLanguage().equalsIgnoreCase("en")){
                busiClients.setEngChinese("1");
            }
            busiClientsService.insert(busiClients);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
           // rabbitTemplate.convertAndSend(RegisterMq.REGISTER_TOPIC_EXCHANGE, RegisterMq.REGISTER_ROUTINGKEY, busiClients, correlationData);
            return ApiResultI18n.success(registerRequest.getLanguage());
        }
    }

    @ApiOperation(value = "忘记密码", notes = "忘记密码重置密码")
    @PostMapping(value = "/forget")
    public ApiResultI18n forget(@RequestParam String codeToken,@RequestParam String code, @RequestParam String clientPhone, @RequestParam String clientEmail, String language) {
        String codeRedis = (String) redisTemplate.opsForValue().get(codeToken);
        if (!(StringUtils.isNotEmpty(codeRedis) && codeRedis.equals(code))) {
            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_ERROR, language);
        }else if(busiClientsService.selectList(new EntityWrapper<BusiClients>().eq("client_loginUser",clientPhone)).size()==0){
            return ApiResultI18n.failure(1,"用户不存在",language);
        }else if(!busiClientsService.selectOne(new EntityWrapper<BusiClients>().eq("client_loginUser",clientPhone)).getIsExamline().equals("1")){
            return ApiResultI18n.failure(1,"该账户暂未通过审核",language);
        }else if (busiClientsService.selectList(new EntityWrapper<BusiClients>().eq("client_loginUser",clientPhone).eq("client_Email",clientEmail)).size()==0){
            return ApiResultI18n.failure(1,"邮箱填写错误",language);
        }else{
            BusiClients busiClients = new BusiClients();
            String password = PasswordUtil.randomPassword();
            busiClients.setClientLoginpwd(password);
            busiClients.setClientLoginuser(clientPhone);
            busiClients.setClientEmail(clientEmail);
            busiClientsService.update(busiClients,new EntityWrapper<BusiClients>().eq("client_loginUser",clientPhone));
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(RegisterMq.REGISTER_TOPIC_EXCHANGE, RegisterMq.REGISTER_ROUTINGKEY, busiClients, correlationData);
            //给客户发送邮件
            String subject = "订舱系统个人用户信息";
            String text = "尊敬的客户：<br/>"+"您的账号："+clientPhone + "&nbsp;&nbsp;"  +"<br/>"+"密码："+ password;
            SendPassMailUtil.sendPassMail(clientEmail,subject,text);
            return ApiResultI18n.success(language);
        }
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/modify")
    public ApiResultI18n modify(@RequestParam String oldPassword,@RequestParam String newPassword ,String language) {
        String clientId=tokenService.getClientId(ServletUtils.getRequest());
        if(busiClientsService.selectOne(new EntityWrapper<BusiClients>().eq("client_ID",clientId)).getClientLoginpwd().equalsIgnoreCase(oldPassword)){
            BusiClients busiClients=new BusiClients();
            busiClients.setClientId(clientId);
            busiClients.setClientLoginpwd(newPassword);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(RegisterMq.CLIENT_PW_TOPIC_EXCHANGE, RegisterMq.CLIENT_PW_ROUTINGKEY, busiClients, correlationData);
            return ApiResultI18n.success(busiClientsService.updateById(busiClients),language);
        }else{
            return ApiResultI18n.failure(1,"密码填写错误",language);
        }
    }

    @ApiOperation(value = "公司信息保存", notes = "公司信息保存")
    @PostMapping(value = "/save")
    public ApiResultI18n companySave(@RequestBody BusiClientsRequest busiClientsRequest) {

        BusiClients busiClients = new BusiClients();
        BeanUtils.copyProperties(busiClientsRequest, busiClients);
        busiClientsService.update(busiClients, new EntityWrapper<BusiClients>()
                .eq("client_loginUser", busiClientsRequest.getClientLoginuser()));
        return ApiResultI18n.success(busiClientsRequest.getLanguage());
    }

    @GetMapping(value = "/getInfo")
    @ApiOperation("获取客户信息")
    public ApiResultI18n getInfo(String language) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String clientId = loginUser.getUserType().equals("0") ?loginUser.getBusiClients().getClientId() : loginUser.getKhUser().getClientId();

        BusiClients busiClients = busiClientsService.selectOne(new EntityWrapper<BusiClients>().eq("client_ID", clientId));
        if (loginUser.getUserType().equals("1")) {
            // clientId
            // createuserid
            // createusername
            // clientTel
            // clientId
            // clientUnit
            // clientEmail
            // clientContacts
            // clientTel
            // clientAddress
            // clientTjr
            // clientTjrId
            busiClients.setClientLoginuser(loginUser.getKhUser().getPhone());
            //busiClients.setClientPhone(loginUser.getKhUser().getPhone());
            //busiClients.setClientContacts(loginUser.getKhUser().getUserName());
            busiClients.setClientContacts(loginUser.getKhUser().getOrderContacts());
            busiClients.setClientAddress(loginUser.getKhUser().getOrderContactAddress());
            busiClients.setClientPhone(loginUser.getKhUser().getOrderContactInfo());
            busiClients.setClientEmail(loginUser.getKhUser().getOrderEmail());
            busiClients.setUserinfoId(String.valueOf(loginUser.getKhUser().getId()));
            busiClients.setRemark(loginUser.getKhUser().getRemark());
            busiClients.setKhId(loginUser.getKhUser().getId().toString());
        }else{
            busiClients.setKhId(null);
        }
        return ApiResultI18n.success(busiClients, language);
    }

    /**
     * 验证验证码是否正确
     *
     * @param loginRequest
     * @return
     */
    public ApiResultI18n verifyCode(LoginRequest loginRequest) {
        Object code = redisTemplate.opsForValue().get(loginRequest.getUserName());
        if (ObjectUtils.isEmpty(code)) {
            return ApiResultI18n.success(loginRequest.getLanguage());
        }
        if (!code.equals(loginRequest.getVerifyCode())) {
            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_ERROR, loginRequest.getLanguage());
        }
        return ApiResultI18n.success(loginRequest.getLanguage());
    }


}

