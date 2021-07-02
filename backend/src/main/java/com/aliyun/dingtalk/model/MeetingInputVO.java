package com.aliyun.dingtalk.model;

import lombok.Data;

import java.util.List;

@Data
public class MeetingInputVO {
    /**
     * 会议发起人的unionid
     */
    private String userId;

    /**
     * 会议主题，最多不能超20个中文。
     */
    private String confTitle;

    /**
     * 邀请参会人员userId列表 必须是会议发起人的好友或同事。
     */
    private List<String> inviteUserIds;

    /**
     * 邀请参会部门列表
     */
    private List<Long> deptIds;

}
