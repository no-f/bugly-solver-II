package com.bugly.common.logrobot;

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
    public static void sendDingTalk(String content, String webHookUrl) {
        if (localVerify(webHookUrl, content)) {
            return;
        }
        TextMessage message = new TextMessage(content);
        message.setUrl(webHookUrl);
        DingTalkTool.send(message);
    }

    private static Boolean localVerify(String content, String webHookUrl) {
        if (null == webHookUrl || webHookUrl.isEmpty()) {
            return true;
        }
        if (null == content || content.isEmpty()) {
            return true;
        }
        return false;
    }


}
