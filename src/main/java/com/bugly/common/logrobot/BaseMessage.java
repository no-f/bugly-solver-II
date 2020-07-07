package com.bugly.common.logrobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 消息类型
 *
 * @date 2020/05/21
 */
public abstract class BaseMessage {

    private String url;

    private List<String> atMobiles;

    private boolean atAll;

    public abstract String toRequestBody();

    public abstract String toCommonRequestBody();

    public void addAtMobile(String atMobile) {
        if (atMobiles == null) {
            atMobiles = new ArrayList<>(1);
        }

        atMobiles.add(atMobile);
    }

    public void setAtAll(boolean atAll) {
        this.atAll = atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles != null ? atMobiles : Collections.emptyList();
    }

    public boolean isAtAll() {
        return atAll;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
