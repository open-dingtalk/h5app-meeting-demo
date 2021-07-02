package com.aliyun.dingtalk.service.user;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;

import java.util.List;


public interface DingTalkUserService {
    OapiV2UserGetResponse.UserGetResponse getUserInfo(String authCode);

    List<OapiV2UserListResponse.ListUserResponse> getUsersByDeptIds(List<Long> deptIds);

    OapiV2UserGetResponse.UserGetResponse getOapiV2UserGetResponseByUserId(String userId, String accessToken);
}
