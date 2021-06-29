package com.aliyun.dingtalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.model.MeetingInputVO;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.service.meeting.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 实现了创建会议的功能
 */
@Slf4j
@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    /**
     * 创建会议
     */
    @PostMapping("/meeting")
    public ServiceResult createMeeting(@RequestBody MeetingInputVO meetingInputVO) {

        log.info("MeetingController#createMeeting params: {}", JSONObject.toJSON(meetingInputVO));

        return ServiceResult.success(meetingService.createMeeting(meetingInputVO));
    }

}


