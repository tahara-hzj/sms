package com.lanpin.sms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  Jello
 * @create  2018/6/20 10:45
 * @desc    发送验证码
 **/
public class SendMsgUtil {

    /**
     * 用户ID
     */
    public static final String ACCOUNT_SID = "276c3c2429e341e9ac1ce459784e5423";//这里填写你在平台里的ACOUNT_SID

    /**
     * 密钥
     */
    public static final String AUTH_TOKEN = "0df6df38046d46208e14980bae79b8ff";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//请求地址是固定的不用改

    /**
     * 验证码
     */
    public static final String randNum = RandUtil.getRandNum();

    /**
     * 短信内容
     */
    public static String smsContent = "【倾城出行】您的验证码为"+ randNum +"，请于"+2+"分钟内正确输入，如非本人操作，请忽略此短信。";

    /**
     * 获取时间戳
     * @return
     */
    public static String getTimestamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * MD5加密
     * @param args
     * @return
     */
    public static String MD5(String... args){ //动态参数
        StringBuffer result = new StringBuffer();
        if(args == null || args.length == 0){
            return "";
        }else{
            StringBuffer sb = new StringBuffer();
            for (String str : args){
                sb.append(str);
            }

            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(sb.toString().getBytes());
                for (byte b : bytes){
                    String hex = Integer.toHexString(b & 0xff);
                    if(hex.length() == 1){
                        result.append("0" + hex);
                    }else{
                        result.append(hex);
                    }
                }
            }catch (NoSuchAlgorithmException e){

            }
        }
        return result.toString();
    }

    /**
     * 获取短信验证码请求数据
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     * @param smsContent
     * @param to
     * @return
     */
    public static String sendMsgData(String ACCOUNT_SID,String AUTH_TOKEN, String smsContent,String to){
        String timestamp = getTimestamp(); //时间戳
        String sig =  MD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);//签名认证
        String str = "accountSid="+ACCOUNT_SID+"&smsContent="+
                smsContent+"&to="+to+"&timestamp="+timestamp+"&sig="+sig+"&respDataType=JSON";
        return str;
    }

}



