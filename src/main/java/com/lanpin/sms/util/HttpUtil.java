package com.lanpin.sms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author  Jello
 * @create  2018/6/21 9:49
 * @desc    http请求工具
 **/
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * POST请求
     * @param url   请求操作的url
     * @param body  发送的数据内容
     * @return
     */
    public static String post(String url, String body){
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();//打开链接
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);//设置链接超时
            connection.setReadTimeout(10000);//设置读取超时
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //提交数据
            out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out.write(body);
            out.flush();

            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = br.readLine()) != null){
                if(firstLine){
                    firstLine = false;
                }else{
                    sb.append(System.lineSeparator());
                }
                sb.append(line);
            }
        } catch (Exception e) {
            logger.debug("post请求异常：" + e.getMessage());
        }finally {
            try {
                if (br!=null) {
                    br.close();
                }
                if (out!=null) {
                    out.close();
                }
            } catch (Exception ee) {
                logger.debug("post请求关闭流异常：" + ee.getMessage());
            }
        }
        return sb.toString();
    }

}