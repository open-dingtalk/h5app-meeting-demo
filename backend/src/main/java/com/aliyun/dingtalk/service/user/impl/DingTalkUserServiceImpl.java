package com.aliyun.dingtalk.service.user.impl;

import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
import com.aliyun.dingtalk.service.user.DingTalkUserService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.request.OapiV2UserListRequest;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 用户管理
 */
@Slf4j
@Service
public class DingTalkUserServiceImpl implements DingTalkUserService {

    private static final Long SIZE = 100L;

    @Autowired
    private AppConfig appConfig;

    public OapiV2UserGetResponse.UserGetResponse getUserInfo(String authCode) {

        // 1. 获取access_token
        String accessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

        // 2. 获取用户ID
        String userId = getUserId(authCode, accessToken);

        // 2. 根据用户ID获取用户详情
        return getOapiV2UserGetResponseByUserId(userId, accessToken);

    }

    /**
     * 获取部门列表的员工详情
     *
     * @param deptIds
     * @return
     */
    @Override
    public List<OapiV2UserListResponse.ListUserResponse> getUsersByDeptIds(List<Long> deptIds) {

        String accessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

        List<OapiV2UserListResponse.ListUserResponse> users = new ArrayList<>();

        deptIds.forEach(deptId ->
                getUsersByDeptId(0L, deptId, accessToken, users)
        );

        return users;
    }

    /**
     * 递归调用 根据部门获取员工详情
     *
     * @param cursor
     * @param deptId
     * @param accessToken
     * @param users
     */
    public void getUsersByDeptId(Long cursor, Long deptId, String accessToken, List<OapiV2UserListResponse.ListUserResponse> users) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_LIST_BY_DEPT_URL);
        OapiV2UserListRequest req = new OapiV2UserListRequest();
        req.setDeptId(deptId);
        req.setCursor(cursor);
        req.setSize(SIZE);
        req.setOrderField("modify_desc");
        req.setContainAccessLimit(false);
        req.setLanguage("zh_CN");
        try {
            OapiV2UserListResponse rsp = client.execute(req, accessToken);
            if (rsp.isSuccess()) {
                OapiV2UserListResponse.PageResult result = rsp.getResult();
                List<OapiV2UserListResponse.ListUserResponse> list = result.getList();
                users.addAll(list);
                if (result.getHasMore()) {
                    getUsersByDeptId(cursor + 1, deptId, accessToken, users);
                }
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }

    }

    /**
     * 根据authCode获取用户ID
     *
     * @param authCode
     * @param accessToken
     * @return
     */
    private String getUserId(String authCode, String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_INFO_URL);
        OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
        req.setCode(authCode);
        OapiV2UserGetuserinfoResponse oapiV2UserGetuserinfoResponse;
        try {
            oapiV2UserGetuserinfoResponse = client.execute(req, accessToken);
            if (oapiV2UserGetuserinfoResponse.isSuccess()) {
                OapiV2UserGetuserinfoResponse.UserGetByCodeResponse userGetByCodeResponse = oapiV2UserGetuserinfoResponse.getResult();
                return userGetByCodeResponse.getUserid();
            } else {
                throw new InvokeDingTalkException(oapiV2UserGetuserinfoResponse.getErrorCode(), oapiV2UserGetuserinfoResponse.getErrmsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 根据用户ID获取用户详情
     *
     * @param userId
     * @param accessToken
     * @return
     */
    public OapiV2UserGetResponse.UserGetResponse getOapiV2UserGetResponseByUserId(String userId, String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.USER_GET_URL);
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userId);
        req.setLanguage("zh_CN");

        try {
            OapiV2UserGetResponse oapiV2UserGetResponse = client.execute(req, accessToken);
            if (oapiV2UserGetResponse.isSuccess()) {
                return oapiV2UserGetResponse.getResult();
            } else {
                throw new InvokeDingTalkException(oapiV2UserGetResponse.getErrorCode(), oapiV2UserGetResponse.getMsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }
}
