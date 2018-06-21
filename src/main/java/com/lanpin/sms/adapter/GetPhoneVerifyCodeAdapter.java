package com.lanpin.sms.adapter;

import com.lanpin.sms.common.Config;
import com.lanpin.sms.util.HttpUtil;
import com.lanpin.sms.util.SendMsgUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Jello
 * @create  2018/6/20 13:59
 * @desc    获取短信验证码
 **/

@Service
public class GetPhoneVerifyCodeAdapter {

    Logger logger = LoggerFactory.getLogger(GetPhoneVerifyCodeAdapter.class);

    public Map<String,Object> getMsgCode(String phoneNum){
        logger.info("开始获取短信验证码");
        Map<String,Object> resultMap = new HashMap<String,Object>(16);
        String content = SendMsgUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN, Config.smsContent, phoneNum);
        logger.info("发送获取短信验证码请求数据为：" + content);

        String resultStr = HttpUtil.post(Config.BASE_URL, content);

        JSONObject jsonObject = JSONObject.fromObject(resultStr.toString());
        logger.info("发送短信请求成功，短信平台接口返回的数据为：" + jsonObject);
        Object respCode = jsonObject.get("respCode");
        Object respDesc = jsonObject.get("respDesc");
        resultMap.put("respCode",respCode);
        resultMap.put("respDesc",respDesc);

        if (!respCode.equals("00000")) {
            return resultMap;
        }else{
            resultMap.put("verifyCode",Config.randNum);
            return resultMap;
        }
    }
}


