package com.lanpin.sms.adapter;

import com.lanpin.sms.common.Config;
import com.lanpin.sms.util.HttpUtil;
import com.lanpin.sms.util.SendMsgUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  Jello
 * @create  2018/6/20 13:59
 * @desc    获取短信验证码
 **/

@Service
public class PhoneVerifyCodeAdapter {

    Logger logger = LoggerFactory.getLogger(PhoneVerifyCodeAdapter.class);

    /**
     * 获取短信验证码
     * @param phoneNum 接收方手机号
     * @return
     */
    public Map<String,Object> getMsgCode(String phoneNum){
        String sendUrl = Config.BASE_URL + "/industrySMS/sendSMS";
        Map<String,Object> resultMap = new HashMap<String,Object>(16);
        try {
            String smsContent = URLEncoder.encode(Config.smsContent, "UTF-8");
            logger.info("开始获取短信验证码");
            String content = SendMsgUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN, smsContent, phoneNum);
            logger.info("发送获取短信验证码请求数据为：" + content);

            String resultStr = HttpUtil.post(sendUrl, content);

            JSONObject jsonObject = JSONObject.fromObject(resultStr.toString());
            logger.info("发送短信请求成功，短信平台接口返回的数据为：" + jsonObject);
            Object respCode = jsonObject.get("respCode");
            Object respDesc = jsonObject.get("respDesc");
            resultMap.put("respCode",respCode);
            resultMap.put("respDesc",respDesc);

            if (respCode.equals("00000")) {
                resultMap.put("verifyCode",Config.randNum);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}


