package com.aliyun.dingtalk.service.config.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.service.config.DingTalkConfigService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.aliyun.dingtalk.util.DdConfigSign;
import com.aliyun.dingtalk.util.JsApiTicketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DingTalkConfigServiceImpl implements DingTalkConfigService {

    // 钉钉鉴权参数 自定义固定字符串。
    private static final String NONCE_STR = "meetingtest";

    @Autowired
    private AppConfig appConfig;

    /**
     * 获取钉钉鉴权参数
     * @param url
     * @return
     */
    public Map<String, Object> getConfig(String url) {

        Map<String, Object> configMap = new HashMap<>();

        Long agentId = appConfig.getAgentId();

        String nonceStr = NONCE_STR;

        String corpId = appConfig.getCorpId();

        Long timeStamp = Instant.now().toEpochMilli() / 1000;

        // 获取企业accessTo
        String accessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

        // 获取jsApiTicket
        String jsApiTicket = JsApiTicketUtil.getJsApiTicket(accessToken);

        // 计算签名
        String signature = DdConfigSign.sign(jsApiTicket, nonceStr, timeStamp, url);

        configMap.put("agentId", agentId);
        configMap.put("corpId", corpId);
        configMap.put("timeStamp", timeStamp);
        configMap.put("signature", signature);
        configMap.put("nonceStr", nonceStr);
        log.info("" + JSONObject.toJSON(configMap));
        return configMap;
    }
}
