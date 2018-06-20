package com.lanpin.sms.adapter;

import com.lanpin.sms.util.SendMsgUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
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
    private OutputStreamWriter out;

    public Map<String,Object> getMsgCode(String phoneNum){
        logger.info("开始获取短信验证码");
        OutputStreamWriter ut = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        Map<String,Object> resultMap = new HashMap<String,Object>(16);

        String args = SendMsgUtil.sendMsgData(SendMsgUtil.ACCOUNT_SID, SendMsgUtil.AUTH_TOKEN, SendMsgUtil.smsContent, phoneNum);
        logger.info("发送获取短信验证码请求数据为：" + args);
        try {
            URL url = new URL(SendMsgUtil.BASE_URL);
            URLConnection connection = url.openConnection();//打开链接
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);//设置链接超时
            connection.setReadTimeout(10000);//设置读取超时

            //提交数据
            out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out.write(args);
            out.flush();

            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null){
                sb.append(line);
            }

        } catch (Exception e) {
            logger.debug("获取短信验证码异常：" + e.getMessage());
        }finally {
            try {
                if (br!=null) {
                    br.close();
                }
                if (out!=null) {
                    out.close();
                }
            } catch (Exception ee) {
                logger.debug("获取短信验证码关闭流异常：" + ee.getMessage());
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
        logger.info("发送短信请求成功，短信平台接口返回的数据为：" + jsonObject);
        Object respCode = jsonObject.get("respCode");
        Object respDesc = jsonObject.get("respDesc");
        resultMap.put("respCode",respCode);
        resultMap.put("respDesc",respDesc);

        if (!respCode.equals("00000")) {
            return resultMap;
        }else{
            resultMap.put("verifyCode",SendMsgUtil.randNum);
            return resultMap;
        }
    }
}


