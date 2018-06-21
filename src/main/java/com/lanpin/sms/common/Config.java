package com.lanpin.sms.common;

import com.lanpin.sms.util.SendMsgUtil;

/**
 * @author  Jello
 * @create  2018/6/21 9:33
 * @desc    系统常量
 **/
public class Config {
    /**
     * 开发者注册后系统自动生成的账号(用户ID)，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "276c3c2429e341e9ac1ce459784e5423";//这里填写你在平台里的ACOUNT_SID

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "0df6df38046d46208e14980bae79b8ff";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";//请求地址是固定的不用改

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";

    /**
     * 验证码
     */
    public static final String randNum = SendMsgUtil.getRandNum();

    /**
     * 时间约束
     */
    public static final String time = "2";

    /**
     * 短信内容
     */
    public static String smsContent = "【倾城出行】您的验证码为"+ randNum +"，请于"+ time +"分钟内正确输入，如非本人操作，请忽略此短信。";

    /**
     * 会员营销短信内容
     */
    public static String affMarkName = "优惠券";
    public static String affMarkUrl = "https://my.csdn.net/";
    public static String affMarkSMS = "【倾城出行】您的"+ affMarkName +"就快过期啦！不想白白浪费，就快来使用吧！戳："+ affMarkUrl +"使用！回TD退订。";

}





