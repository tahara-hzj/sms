package com.lanpin.sms.util;

import com.lanpin.sms.common.Config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author  Jello
 * @create  2018/6/20 10:45
 * @desc    发送验证码
 **/
public class SendMsgUtil {

    /**
     * 生成随机数
     * @return
     */
    public static String getRandNum(){
        String randNum = new Random().nextInt(1000000) + "";
        if(6 != randNum.length()){//如果生成的不是6位数随机数则返回该方法继续生成
            return getRandNum();
        }
        return randNum;
    }

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
    public static String createCommonParam(String ACCOUNT_SID,String AUTH_TOKEN, String smsContent,String to){
        String timestamp = getTimestamp(); //时间戳
        String sig =  MD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);//签名认证
        String str = "accountSid=" + ACCOUNT_SID + "&smsContent=" +
                smsContent + "&to=" + to + "&timestamp=" + timestamp + "&sig=" + sig +
                "&respDataType=" + Config.RESP_DATA_TYPE;
        return str;
    }

    /**
     * 获取开发者账号信息请求数据
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     * @return
     */
    public static String createCommonParam(String ACCOUNT_SID,String AUTH_TOKEN){
        String timestamp = getTimestamp(); //时间戳
        String sig =  MD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);//签名认证
        String str = "accountSid=" + ACCOUNT_SID + "&timestamp=" + timestamp + "&sig=" + sig +
                "&respDataType=" + Config.RESP_DATA_TYPE;
        return str;
    }

    /**
     * 获取语音验证码请求数据
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     * @return
     */
    public static String createVoiceParam(String ACCOUNT_SID, String called, String AUTH_TOKEN, String playTimes){
        String timestamp = getTimestamp(); //时间戳
        String sig =  MD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);//签名认证
        String str = "accountSid=" + ACCOUNT_SID + "&verifyCode=" + getRandNum() + "&called=" + called +
                "&playTimes=" + playTimes + "&timestamp=" + timestamp + "&sig=" + sig +
                "&respDataType=" + Config.RESP_DATA_TYPE;
        return str;
    }
}

