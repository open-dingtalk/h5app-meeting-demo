package com.aliyun.dingtalk.service.config;

import java.util.Map;

public interface DingTalkConfigService {
    Map<String, Object> getConfig(String url);
}
