package com.lanpin.sms.util;

import java.util.Random;

/**
 * @author  Jello
 * @create  2018/6/20 9:55
 * @desc    生成随机数
 **/
public class RandUtil {

    public static String getRandNum(){
        String randNum = new Random().nextInt(1000000) + "";
        if(6 != randNum.length()){//如果生成的不是6位数随机数则返回该方法继续生成
            return getRandNum();
        }
        return randNum;
    }

}


