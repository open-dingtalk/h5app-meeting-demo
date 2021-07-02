package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.service.config.DingTalkConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DingTalkConfigController {


    @Autowired
    private DingTalkConfigService dingTalkConfigService;


    /**
     * 获取钉钉配置
     * @return
     */
    @GetMapping("/config")
    public ServiceResult<Map> getDingTalkConfig(@RequestParam String url) {

        return ServiceResult.getSuccessResult(dingTalkConfigService.getConfig(url));
    }

}
