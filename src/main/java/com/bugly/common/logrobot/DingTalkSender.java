package com.bugly.common.logrobot;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * @author bbb
 * @since 2020-05-26
 */
public class DingTalkSender {

    /**
     * 通用接口
     * @param content ：消息内容
     * @param webHookUrl 钉钉报警群群地址
     *
     */
    public static void sendDingTalk(JSONObject content, String webHookUrl) {
        if (localVerify(content, webHookUrl)) {
            return;
        }
        TextMessage message = new TextMessage(content);
        message.setUrl(webHookUrl);
        DingTalkTool.send(message);
    }

    public static void sendCommonDingTalk(JSONObject content, String webHookUrl, List<String> mobiels) {
        if (null == webHookUrl || webHookUrl.isEmpty()) {
            return;
        }
        content.put("common", true);
        TextMessage message = new TextMessage(content);
        message.setUrl(webHookUrl);
        if (null == mobiels || mobiels.size() == 0) {
            message.setAtAll(true);
        } else {
            message.setAtAll(false);
            mobiels.forEach(phone -> message.addAtMobile(phone));
        }
        DingTalkTool.sendCommon(message);
    }



    private static Boolean localVerify(JSONObject content, String webHookUrl) {
        if (null == webHookUrl || webHookUrl.isEmpty()) {
            return true;
        }
        if (null == content || content.isEmpty()) {
            return true;
        }
        return false;
    }


}
