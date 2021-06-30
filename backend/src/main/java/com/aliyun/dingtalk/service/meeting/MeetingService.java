package com.aliyun.dingtalk.service.meeting;

import com.aliyun.dingtalk.model.MeetingInputVO;
import com.aliyun.dingtalkconference_1_0.models.CreateVideoConferenceResponseBody;

public interface MeetingService {

    CreateVideoConferenceResponseBody createMeeting(MeetingInputVO meetingInputVO);

    String closeMeeting(String conferenceId, String unionId);
}
