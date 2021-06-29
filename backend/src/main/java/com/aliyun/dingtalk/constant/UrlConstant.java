package com.aliyun.dingtalk.constant;

public class UrlConstant {
    /**
     * 钉钉网关gettoken地址
     */
    public static final String GET_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";


    /**
     * 通过免登授权码获取用户信息 url
     */
    public static final String GET_USER_INFO_URL = "https://oapi.dingtalk.com/topapi/v2/user/getuserinfo";

    /**
     * 根据用户id获取用户详情 url
     */
    public static final String USER_GET_URL = "https://oapi.dingtalk.com/topapi/v2/user/get";

    /**
     * 获取部门成员信息
     */
    public static final String GET_USER_LIST_BY_DEPT_URL = "https://oapi.dingtalk.com/topapi/v2/user/list";

}
