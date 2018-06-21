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
 * @create  2018/6/21 10:18
 * @desc    获得开发者账号信息
 **/

@Service
public class DeveloperAccInfoAdapter {
    Logger logger = LoggerFactory.getLogger(DeveloperAccInfoAdapter.class);

    /**
     * 获得开发者账号信息
     * @return
     */
    public String getDeveloperAccInfo(){
        //TODO

        String sendUrl = Config.BASE_URL + "/query/accountInfo";
        logger.info("开始获取开发者账号信息");
        String content = SendMsgUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        logger.info("发送获取开发者账号信息请求数据为：" + content);

        String resultStr = HttpUtil.post(sendUrl, content);

        JSONObject jsonObject = JSONObject.fromObject(resultStr.toString());
        logger.info("发送开发者账号信息请求成功，短信平台接口返回的数据为：" + jsonObject);
        return jsonObject.toString();
    }
}
