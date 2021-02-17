package com.zih.booking.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//提单草单
@Data
public class Lading_Bill implements Serializable
{
     /// Desc:序号
   public String lb_id ;

    /// Desc:发货人
    public String lb_1 ;


    /// Desc:收货人

    public String lb_2 ;


    /// Desc:通知人

    public String lb_3 ;


    /// Desc:提单班列号

    public String lb_4 ;


    /// Desc:发货地

    public String lb_5 ;

    /// Desc:目的地(同lb_7)

    public String lb_6 ;

    /// Desc:收货地(同lb_6)

    public String lb_7 ;

    ///

    public String lb_10 ;

    /// Desc:默认N/M可修改

    public String lb_11 ;

    /// Desc:大件数

    public String lb_12 ;

    /// Desc:13栏货物描述

    public String lb_13_1 ;

    /// Desc:箱号、箱型、铅封号、件数、毛重等

    public String lb_13_2 ;

    /// Desc:毛重

    public String lb_14 ;

    /// Desc:实测体积

    public String lb_15_1 ;

    /// Desc:计费体积

    public String lb_15_2 ;

    /// Desc:提单班列日期

    public String lb_15_3 ;

    /// Desc:到付费用(默认0)

    public String lb_16 ;

    /// Desc:签发地点和日期

    public String lb_19 ;

    /// Desc:拆箱代理信息

    public String lb_20 ;

    /// Desc:备注

    public String lb_remark ;

    /// Desc:状态(0，1草单不完整，2草单完整，3电放保函、电放提单，4正本提单，5草单审核失败，6草单审核成功)

    public int lb_state ;

    /// Desc:是否有站到站分拨1有0否

    public int lb_group1 ;

    /// Desc:是否有站到门分拨1有0否

    public int lb_group2 ;

    /// Desc:箱号

    public String lb_container ;

    /// Desc:仓位号

    public String lb_number ;

    /// Desc:防伪标识

    public String lb_mark ;

    /// Desc:电放保函地址

    public String lb_url1 ;


    /// Desc:完整草单审核失败原因

    public String lb_reason1 ;

    /// Desc:电放保函审核失败原因

    public String lb_reason2 ;

    /// Desc:状态(0电放保函审核失败，1电放保函审核成功)

    public int lb_letterstate ;
    //班列ID
    public String ci_id ;

    /// Desc:往返0西向1东向

    public int lb_eastandwest ;

    private Date ci_startdate;
}
