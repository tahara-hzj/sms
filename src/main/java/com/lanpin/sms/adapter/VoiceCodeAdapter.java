package com.lanpin.sms.adapter;

import com.lanpin.sms.common.Config;
import com.lanpin.sms.util.HttpUtil;
import com.lanpin.sms.util.SendMsgUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author  Jello
 * @create  2018/6/21 11:52
 * @desc    语音验证码
 **/

@Service
public class VoiceCodeAdapter {
    Logger logger = LoggerFactory.getLogger(DeveloperAccInfoAdapter.class);

    /**
     * 获取语音验证码
     * @return
     */
    public String getVoiceCode(){
        String sendUrl = Config.BASE_URL + "/call/voiceCode";
        logger.info("开始获取语音验证码");
        String content = SendMsgUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        logger.info("发送获取语音验证码请求数据为：" + content);

        String resultStr = HttpUtil.post(sendUrl, content);

        JSONObject jsonObject = JSONObject.fromObject(resultStr.toString());
        logger.info("发送语音验证码请求成功，短信平台接口返回的数据为：" + jsonObject);
        return jsonObject.toString();
    }
}
