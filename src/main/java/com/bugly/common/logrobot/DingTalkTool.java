package com.bugly.common.logrobot;

import net.sf.json.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 钉钉机器人消息发送工具
 *
 */
public class DingTalkTool {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkTool.class);

    private static final int CODE_OK = 200;

    private static OkHttpClient HTTP_CLIENT = instance();

    private static OkHttpClient instance() {
        if (HTTP_CLIENT == null) {
            HTTP_CLIENT = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
        }
        return HTTP_CLIENT;
    }

    /**
     * 异步发送消息
     *
     * @param message 消息
     */
    public static void send(BaseMessage message) {
        message.setAtAll(true);
        CompletableFuture.completedFuture(message)
                         .thenAcceptAsync(DingTalkTool::sendSync);
    }

    /**
     * 异步发送消息
     *
     * @param message 消息
     */
    public static void sendCommon(BaseMessage message) {
        CompletableFuture.completedFuture(message)
                .thenAcceptAsync(DingTalkTool::sendSyncCommon);
    }

    /**
     * 同步发送消息
     *
     * @param message 消息
     */
    private static void sendSync(BaseMessage message) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, message.toRequestBody());
        if (message.getUrl() == null || message.getUrl().isEmpty()) {
            return;
        }

        Request request = new Request.Builder()
                .url(message.getUrl())
                .post(requestBody)
                .build();

        HTTP_CLIENT.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call c, IOException e) {
//                logger.error("发送消息失败，请查看异常信息", e);
            }

            @Override
            public void onResponse(Call c, Response r) throws IOException {
                int code = r.code();
                if (code != CODE_OK) {
//                    logger.error("发送消息失败，code={}", code);
                    return;
                }

                ResponseBody responseBody = r.body();
                if (responseBody != null) {
                    try {
                        JSONObject body = JSONObject.fromObject(responseBody.string());
                        Object errCode = body.get("errcode");
                        if (errCode == null || (int) errCode != 0) {
                            if (body.containsKey("errmsg")) {
                                String errMsg = body.getString("errmsg");
                                logger.error("发送消息出现错误，errCode={}, errMsg={}", errCode, errMsg);
                            }
                        }
                    } catch (Exception e) {
//                        logger.error("发送消息出现错误，errMsg={}", e);
                    } finally {
                        if (null != responseBody) {
                            responseBody.close();
                        }
                    }
                }
            }
        });
    }

    /**
     * 同步发送消息
     *
     * @param message 消息
     */
    private static void sendSyncCommon(BaseMessage message) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, message.toCommonRequestBody());
        System.out.println(message.toCommonRequestBody());
        if (message.getUrl() == null || message.getUrl().isEmpty()) {
            return;
        }

        Request request = new Request.Builder()
                .url(message.getUrl())
                .post(requestBody)
                .build();

        HTTP_CLIENT.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call c, IOException e) {
//                logger.error("发送消息失败，请查看异常信息", e);
            }

            @Override
            public void onResponse(Call c, Response r) throws IOException {
                int code = r.code();
                if (code != CODE_OK) {
//                    logger.error("发送消息失败，code={}", code);
                    return;
                }

                ResponseBody responseBody = r.body();
                if (responseBody != null) {
                    try {
                        JSONObject body = JSONObject.fromObject(responseBody.string());
                        Object errCode = body.get("errcode");
                        if (errCode == null || (int) errCode != 0) {
                            if (body.containsKey("errmsg")) {
                                String errMsg = body.getString("errmsg");
                                logger.error("发送消息出现错误，errCode={}, errMsg={}", errCode, errMsg);
                            }
                        }
                    } catch (Exception e) {
//                        logger.error("发送消息出现错误，errMsg={}", e);
                    } finally {
                        if (null != responseBody) {
                            responseBody.close();
                        }
                    }
                }
            }
        });
    }
}
