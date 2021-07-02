package com.aliyun.dingtalk.service.meeting.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
import com.aliyun.dingtalk.model.MeetingInputVO;
import com.aliyun.dingtalk.service.meeting.MeetingService;
import com.aliyun.dingtalk.service.user.DingTalkUserService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.aliyun.dingtalkconference_1_0.Client;
import com.aliyun.dingtalkconference_1_0.models.CloseVideoConferenceHeaders;
import com.aliyun.dingtalkconference_1_0.models.CloseVideoConferenceRequest;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceHeaders;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceRequest;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceResponse;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 物品领用流程
 */
@Slf4j
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private DingTalkUserService dingTalkUserService;

    private Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new Client(config);
    }

    /**
     * 创建会议
     *
     * @param meetingInputVO
     * @return
     */
    public CreateVideoConferenceResponseBody createMeeting(MeetingInputVO meetingInputVO) {


        try {
            // 创建client
            Client client = createClient();

            String accessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

            CreateVideoConferenceHeaders createVideoConferenceHeaders = new CreateVideoConferenceHeaders();
            createVideoConferenceHeaders.xAcsDingtalkAccessToken = accessToken;

            List<String> unionIdList = new ArrayList<>();
            // 部门ID列表
            List<Long> deptIds = meetingInputVO.getDeptIds();
            if (!CollectionUtils.isEmpty(deptIds)) {
                // 获取部门下所有员工unionId
                List<String> deptUserUnionIdList = getUnionIdByDeptIds(deptIds);
                unionIdList.addAll(deptUserUnionIdList);
            }

            List<String> inviteUserIds = meetingInputVO.getInviteUserIds();

            if (!CollectionUtils.isEmpty(inviteUserIds)) {
                // 获取员工列表的unionIds
                List<String> unionIdsByUserIDs = getUnionIdsByUserIDs(inviteUserIds, accessToken);
                unionIdList.addAll(unionIdsByUserIDs);
            }

            if (CollectionUtils.isEmpty(unionIdList)) {
                throw new RuntimeException("inviteUser is empty");
            }

            CreateVideoConferenceRequest createVideoConferenceRequest = new CreateVideoConferenceRequest()
                    .setUserId(meetingInputVO.getUserId())
                    .setConfTitle(meetingInputVO.getConfTitle())
                    .setInviteUserIds(unionIdList.stream().distinct().collect(Collectors.toList()));

            // 创建视频会议
            log.info("invoke dingTalk createVideoConferenceWithOptions params request: {}", JSONObject.toJSON(createVideoConferenceHeaders));

            CreateVideoConferenceResponse createVideoConferenceResponse = client.createVideoConferenceWithOptions(createVideoConferenceRequest, createVideoConferenceHeaders, new RuntimeOptions());

            log.info("invoke createVideoConferenceWithOptions response: {}", JSONObject.toJSON(createVideoConferenceResponse));

            CreateVideoConferenceResponseBody body = createVideoConferenceResponse.getBody();
            return body;
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("createMeeting error");
        }

    }

    private List<String> getUnionIdsByUserIDs(List<String> inviteUserIds, String accessToken) {


        return inviteUserIds.stream().map(userId ->
                // 根据用户ID列表获取用户的unionId
                dingTalkUserService.getOapiV2UserGetResponseByUserId(userId, accessToken).getUnionid()
        ).collect(Collectors.toList());
    }

    /**
     * 根据部门获取员工unionId
     *
     * @param deptIds
     * @return
     */
    private List<String> getUnionIdByDeptIds(List<Long> deptIds) {

        List<OapiV2UserListResponse.ListUserResponse> users = dingTalkUserService.getUsersByDeptIds(deptIds);

        List<String> unionIds = users.stream().map(OapiV2UserListResponse.ListUserResponse::getUnionid).collect(Collectors.toList());

        return unionIds;
    }

    /**
     * 关闭会议
     *
     * @param conferenceId
     * @param unionId
     * @return
     */
    @Override
    public String closeMeeting(String conferenceId, String unionId) {
        try {
            Client client = createClient();
            CloseVideoConferenceHeaders closeVideoConferenceHeaders = new CloseVideoConferenceHeaders();
            closeVideoConferenceHeaders.xAcsDingtalkAccessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
            CloseVideoConferenceRequest closeVideoConferenceRequest = new CloseVideoConferenceRequest()
                    .setUnionId(unionId);
            // 关闭会议
            client.closeVideoConferenceWithOptions(conferenceId, closeVideoConferenceRequest, closeVideoConferenceHeaders, new RuntimeOptions());
            return "success";
        } catch (TeaException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("closeMeeting error");
        }
    }

}
