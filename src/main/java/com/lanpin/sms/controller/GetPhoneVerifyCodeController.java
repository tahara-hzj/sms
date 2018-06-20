package com.lanpin.sms.controller;

import com.lanpin.sms.adapter.GetPhoneVerifyCodeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author  Jello
 * @create  2018/6/20 11:05
 * @desc    获取短信验证码
 **/

@Controller
public class GetPhoneVerifyCodeController {

    @Autowired
    private GetPhoneVerifyCodeAdapter phoneVerifyCodeAdapter;

    /**
     * 获取短信验证码
     * @param phoneNum 发送手机号
     * @return
     */
    @GetMapping("/getMsgCode/{phoneNum}")
    @ResponseBody
    public Map<String, Object> getMsgCode(@PathVariable("phoneNum") String phoneNum){
        Map<String, Object> map = phoneVerifyCodeAdapter.getMsgCode(phoneNum);
        return map;
    }

}




