package com.aliyun.dingtalk.util;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.response.OapiGetJsapiTicketResponse;
import com.taobao.api.ApiException;

/**
 * 获取jsapiticket
 */
public class JsApiTicketUtil {


    public static String getJsApiTicket(String accessToken) {

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_JSAPI_TICKET);
        OapiGetJsapiTicketRequest req = new OapiGetJsapiTicketRequest();
        req.setHttpMethod("GET");
        try {
            OapiGetJsapiTicketResponse rsp = client.execute(req, accessToken);

            if (rsp.isSuccess()) {
                return rsp.getTicket();
            } else {
                throw new InvokeDingTalkException(rsp.getErrorCode(), rsp.getErrmsg());
            }

        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }
    }
}
