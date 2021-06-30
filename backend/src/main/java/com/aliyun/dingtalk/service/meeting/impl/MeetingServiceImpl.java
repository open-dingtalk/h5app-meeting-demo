package com.aliyun.dingtalk.service.meeting.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.model.MeetingInputVO;
import com.aliyun.dingtalk.service.meeting.MeetingService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.aliyun.dingtalkconference_1_0.Client;
import com.aliyun.dingtalkconference_1_0.models.CloseVideoConferenceHeaders;
import com.aliyun.dingtalkconference_1_0.models.CloseVideoConferenceRequest;
import com.aliyun.dingtalkconference_1_0.models.CloseVideoConferenceResponse;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceHeaders;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceRequest;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceResponse;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 物品领用流程
 */
@Slf4j
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private AppConfig appConfig;

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

            CreateVideoConferenceHeaders createVideoConferenceHeaders = new CreateVideoConferenceHeaders();
            createVideoConferenceHeaders.xAcsDingtalkAccessToken = AccessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());

            CreateVideoConferenceRequest createVideoConferenceRequest = new CreateVideoConferenceRequest()
                    .setUserId(meetingInputVO.getUserId())
                    .setConfTitle(meetingInputVO.getConfTitle())
                    .setInviteUserIds(meetingInputVO.getInviteUserIds());

            // 创建视频会议
            log.info("invoke dingTalk createVideoConferenceWithOptions params request: {}", JSONObject.toJSON(createVideoConferenceHeaders));

            CreateVideoConferenceResponse createVideoConferenceResponse = client.createVideoConferenceWithOptions(createVideoConferenceRequest, createVideoConferenceHeaders, new RuntimeOptions());

            log.info("invoke createVideoConferenceWithOptions response: {}", JSONObject.toJSON(createVideoConferenceResponse));

            if (createVideoConferenceResponse != null) {
                CreateVideoConferenceResponseBody body = createVideoConferenceResponse.getBody();
                return body;
            } else {
                log.error("createVideoConferenceWithOptions fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 关闭会议
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

        } catch (TeaException err) {
            if (!Common.empty(err.code) && !Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!Common.empty(err.code) && !Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
        return "success";
    }

}
