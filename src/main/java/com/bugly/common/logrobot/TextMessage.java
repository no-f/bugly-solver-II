package com.bugly.common.logrobot;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 文本消息
 *
 */
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private final String content;

    public TextMessage(CharSequence content) {
        super();
        Objects.requireNonNull(content, "The text to send can't be null");
        this.content = content.toString();
    }

    @Override
    public String toRequestBody() {
        Map<String, String> contentMap = new HashMap<>(1);
        contentMap.put("content", content);

        Map<String, Object> atMap = new HashMap<>(2);
        atMap.put("isAtAll", isAtAll());
        atMap.put("atMobiles", getAtMobiles());

        Map<String, Object> reqMap = new HashMap<>(3);
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);
        JSONObject obj = JSONObject.fromObject(reqMap);
        return obj.toString();
    }

}
