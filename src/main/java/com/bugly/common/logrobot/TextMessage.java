package com.bugly.common.logrobot;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.*;

import static org.apache.commons.lang3.CharUtils.LF;

/**
 * 文本消息
 *
 */
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private final String content;

    public TextMessage(JSONObject content) {
        super();
        Objects.requireNonNull(content, "The text to send can't be null");
        this.content = createDingTemplate(content);
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

    /**
     * 生成钉钉通知模版
     * @param jsonObject
     * @return
     */
    public static String createDingTemplate(JSONObject jsonObject) {
        StringBuilder content = new StringBuilder(1024);
        if (jsonObject.containsKey("currentCluster")) {
            content.append("当前集群：").append(jsonObject.get("currentCluster")).append(LF);
        }
        if (jsonObject.containsKey("serviceName")) {
            content.append("服务名称：").append(jsonObject.get("serviceName")).append(LF);
        }
        if (jsonObject.containsKey("machineAddress")) {
            content.append("机器地址：").append(jsonObject.get("machineAddress")).append(LF);
        }
        if (jsonObject.containsKey("triggerTime")) {
            String triggerTime = (String) jsonObject.get("triggerTime");
            content.append("触发时间：").append(new Date(Long.valueOf(triggerTime))).append(LF);
        }

        if (jsonObject.containsKey("threadId")) {
            content.append("线程ID：").append(jsonObject.get("threadId")).append(LF);
        }
        if (jsonObject.containsKey("level")) {
            content.append("级别：").append(jsonObject.get("level")).append(LF);
        }

        if (jsonObject.containsKey("errorLocation")) {
            content.append("位置：").append(jsonObject.get("errorLocation")).append(LF);
        }

        if (jsonObject.containsKey("errorMessage")) {
            content.append("信息：").append(jsonObject.get("errorMessage")).append(LF);
        }
        if (jsonObject.containsKey("errorException")) {
            content.append("异常：").append(jsonObject.get("errorException"));
        }

        return content.toString();
    }

}
