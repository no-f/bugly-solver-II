package com.bugly;

import cn.hutool.crypto.digest.DigestUtil;
import com.bugly.common.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuglyBootApplicationTests {

    @Test
    public void uuidTest() {

//        String replace = UUID.randomUUID().toString().replace("-", "");
//        System.out.println(replace);
//        String replace1 = UUID.randomUUID().toString().replace("-", "");
//        System.out.println(replace1);


        String s = DigestUtil.sha256Hex("123");
        System.out.println(s);
        String encode = new BCryptPasswordEncoder().encode(s);

        System.out.println(new BCryptPasswordEncoder().matches("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",encode));

    }

    @Test
    public void generateUUID(){
        for (int i = 0; i <10 ; i++) {
            String sixteenUUID = UUIDUtils.getUUID();
            System.out.println(sixteenUUID);
        }
    }

    @Test
    private void data() {

        Map<String,String> contentMap = new HashMap<>();
        contentMap.put("currentCluster","blue");
        contentMap.put("serviceName","facepp");
        contentMap.put("machineAddress","facep/10.12.16.134");
        contentMap.put("triggerTime", String.valueOf(new Date()));
        contentMap.put("threadId", "1");
        contentMap.put("level", "warn");
        contentMap.put("errorLocation","com.manniu.sns.core.service.XiaoMiPush.【行号=】");
        contentMap.put("errorMessage","ParameterizedMessage[messagePattern=xiaomi push error messageId: {}, errorCode: {},errorReason: {}, token: {}, deviceType: {}, stringArgs=[Xcm61103593568309021n4, com.xiaomi.push.sdk.ErrorCode@54b851ac, No valid targets!, t04XvYI5TBKuBDMoZMi35uDR5gqkkNmvmhn95Z9/Fsx20tBeeTuTvJVIq7hlvPqh, 7], throwable=null]");
        contentMap.put("errorException","java.io.IOException: Connection reset by peer\n" +
                "  at sun.nio.ch.FileDispatcherImpl.read0(Native Method)\n" +
                "  at sun.nio.ch.SocketDispatcher.read(SocketDispatcher.java:39)\n" +
                "  at sun.nio.ch.IOUtil.readIntoNativeBuffer(IOUtil.java:223)\n" +
                "  at sun.nio.ch.IOUtil.read(IOUtil.java:192)\n" +
                "  at sun.nio.ch.SocketChannelImpl.read(SocketChannelImpl.java:380)\n" +
                "  at io.netty.buffer.PooledUnsafeDirectByteBuf.setBytes(PooledUnsafeDirectByteBuf.java:288)\n" +
                "  at io.netty.buffer.AbstractByteBuf.writeBytes(AbstractByteBuf.java:1108)\n" +
                "  at io.netty.channel.socket.nio.NioSocketChannel.doReadBytes(NioSocketChannel.java:345)\n" +
                "  at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:131)\n" +
                "  at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:645)\n" +
                "  at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:580)\n" +
                "  at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:497)\n" +
                "  at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:459)\n" +
                "  at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)\n" +
                "  at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)\n" +
                "  at java.lang.Thread.run(Thread.java:748)");

        System.out.println(JSONObject.fromObject(contentMap).toString());


    }

}
