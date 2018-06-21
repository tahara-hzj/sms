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

/**
 * @author  Jello
 * @create  2018/6/21 11:27
 * @desc    会员营销短信
 **/

@Service
public class AffMarkSMSAdapter {
    Logger logger = LoggerFactory.getLogger(DeveloperAccInfoAdapter.class);

    /**
     * 会员营销短信
     * @param phoneNum 接收方手机号
     * @return
     */
    public String getAffMarkSMS(String phoneNum, String playTimes){
        String sendUrl = Config.BASE_URL + "/affMarkSMS/sendSMS";
        JSONObject jsonObject = null;
        try {
            logger.info("开始获取会员营销短信信息");
            String content = SendMsgUtil.createVoiceParam(Config.ACCOUNT_SID, phoneNum, Config.AUTH_TOKEN, playTimes);
            logger.info("发送获取会员营销短信请求数据为：" + content);

            String resultStr = HttpUtil.post(sendUrl, content);

            jsonObject = JSONObject.fromObject(resultStr.toString());
            logger.info("发送会员营销短信请求成功，短信平台接口返回的数据为：" + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}