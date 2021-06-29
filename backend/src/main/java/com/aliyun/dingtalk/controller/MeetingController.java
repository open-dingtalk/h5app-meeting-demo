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
 * 企业 E应用审批解决方案示例代码
 * 实现了审批的基础功能
 */
@Slf4j
@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;



    /**
     * 发起审批
     */
    @PostMapping("/meeting")
    public ServiceResult startProcessInstance(@RequestBody MeetingInputVO meetingInputVO) {

        log.info("ProcessInstanceController#startProcessInstance params: {}", JSONObject.toJSON(meetingInputVO));

        return ServiceResult.success(meetingService.createMeeting(meetingInputVO));
    }

}


