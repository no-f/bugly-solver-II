package com.bugly.common.logrobot;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文本消息
 *
 */
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private final String content;

    private final String localtion;

    private final String serviceName;

    private final String currentCluste;

//    private final String buglyHttpUrl;

    private final String environment;

    public TextMessage(JSONObject content) {
        super();
//        this.buglyHttpUrl = (String) content.get("buglyHttpUrl");
        this.environment = (String) content.get("environment");
        if (!content.containsKey("common")) {
            this.content = createDingTemplate(content);
            this.localtion =  !content.containsKey("errorLocation") ? "" : (String) content.get("errorLocation");
            this.serviceName = !content.containsKey("serviceName") ?  "" : (String) content.get("serviceName");
            this.currentCluste = !content.containsKey("currentCluster") ?  "" :(String) content.get("currentCluster");
        } else {
            this.content = "请及时处理 ～" + "\n" + environment + "/bugly/login" + "\n" ;
            this.localtion =  "";
            this.serviceName = "";
            this.currentCluste = "";
        }
    }

    @Override
    public String toRequestBody() {
        List<Btns> btns = new ArrayList<>(2);

        Btns btns1 = new Btns();
        btns1.setTitle("去解决");
        btns1.setActionURL(environment + "/bugly/bugly/exception/out_solve?localtion=" + localtion);
        btns.add(btns1);

        Btns btns2 = new Btns();
        btns2.setTitle("详细信息");
        btns2.setActionURL(environment + "/bugly/bugly/exception/out_detail?localtion=" + localtion
                + "&serviceName="+ serviceName +"&currentCluster=" + currentCluste);

        btns.add(btns2);

        Map<String, Object> actionCardMap = new HashMap<>(2);
        actionCardMap.put("title", serviceName);
        actionCardMap.put("text", content);
        actionCardMap.put("btnOrientation", 1);
        actionCardMap.put("btns", btns);

        Map<String, Object> newReqMap = new HashMap<>(3);
        newReqMap.put("msgtype", "actionCard");
        newReqMap.put("actionCard", actionCardMap);

        JSONObject obj = JSONObject.fromObject(newReqMap);
        return obj.toString();
    }


    @Override
    public String toCommonRequestBody() {
        Map<String, String> contentMap = new HashMap<>(1);
        contentMap.put("content", content);

        Map<String, Object> atMap = new HashMap<>(2);
        if (null != getAtMobiles() && getAtMobiles().size() > 0) {
            atMap.put("atMobiles", getAtMobiles());
            atMap.put("isAtAll", false);
        } else {
            atMap.put("atMobiles", getAtMobiles());
            atMap.put("isAtAll", true);
        }

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
            content.append("当前集群：").append(jsonObject.get("currentCluster")).append("\n").append("\n");
        }
        if (jsonObject.containsKey("serviceName")) {
            content.append("服务名称：").append(jsonObject.get("serviceName")).append("\n").append("\n");
        }
        if (jsonObject.containsKey("machineAddress")) {
            content.append("机器地址：").append(jsonObject.get("machineAddress")).append("\n").append("\n");
        }
        if (jsonObject.containsKey("triggerTime")) {
            String triggerTime = (String) jsonObject.get("triggerTime");
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            content.append("触发时间：").append(sf.format(Long.valueOf(triggerTime))).append("\n").append("\n");
        }

        if (jsonObject.containsKey("num")) {
            content.append("最近24h异常次数：").append(jsonObject.get("num")).append("\n").append("\n");
        }

        if (jsonObject.containsKey("threadId")) {
            content.append("线程ID：").append(jsonObject.get("threadId")).append("\n").append("\n");
        }
        if (jsonObject.containsKey("level")) {
            content.append("级别：").append(jsonObject.get("level")).append("\n").append("\n");
        }

        if (jsonObject.containsKey("errorLocation")) {
            content.append("位置：").append(jsonObject.get("errorLocation")).append("\n").append("\n");
        }

        if (jsonObject.containsKey("errorMessage")) {
            String meaages = (String) jsonObject.get("errorMessage");
            if (meaages.length() > 100) {
                content.append("信息：").append(meaages.substring(0,100) + "...").append("\n").append("\n");
            } else {
                content.append("信息：").append(meaages).append("\n").append("\n");
            }

        }
        if (jsonObject.containsKey("errorException")) {
            String errorException = (String) jsonObject.get("errorException");
            if (errorException.length() > 100) {
                content.append("异常：").append(errorException.substring(0,100) + "...").append("\n").append("\n");
            } else {
                content.append("异常：").append(errorException).append("\n").append("\n");
            }
        }

        return content.toString();
    }

}
