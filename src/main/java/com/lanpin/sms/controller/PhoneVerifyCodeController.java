package com.lanpin.sms.controller;

import com.lanpin.sms.adapter.AffMarkSMSAdapter;
import com.lanpin.sms.adapter.DeveloperAccInfoAdapter;
import com.lanpin.sms.adapter.PhoneVerifyCodeAdapter;
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
public class PhoneVerifyCodeController {

    @Autowired
    private PhoneVerifyCodeAdapter phoneVerifyCodeAdapter;

    @Autowired
    private DeveloperAccInfoAdapter developerAccInfoAdapter;

    @Autowired
    private AffMarkSMSAdapter affMarkSMSAdapter;

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

    /**
     * 获取开发者账号信息
     * @return
     */
    @GetMapping("/getDeveloperAccInfo")
    @ResponseBody
    public String getDeveloperAccInfo(){
        return developerAccInfoAdapter.getDeveloperAccInfo();
    }

    /**
     * 获取会员营销短信
     * @param phoneNum 发送手机号
     * @return
     */
    @GetMapping("/getAffMarkSMS/{phoneNum}/{playTimes}")
    @ResponseBody
    public String getAffMarkSMS(@PathVariable("phoneNum") String phoneNum, @PathVariable("playTimes") String playTimes){
        return affMarkSMSAdapter.getAffMarkSMS(phoneNum,playTimes);
    }

}




