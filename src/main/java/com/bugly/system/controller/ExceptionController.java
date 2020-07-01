package com.bugly.system.controller;

import com.bugly.common.utils.SecurityUtils;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.entity.SysUser;
import com.bugly.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author no_f
 * @create 2020-06-16 17:46
 */
@Controller
@RequestMapping("/bugly/exception")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionController {

    private final SysUserService sysUserService;

    @GetMapping("/list")
    public String index(){
        return "module/bugly/bugly";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = sysUserService.findByName(username);
        model.addAttribute("exceptionTypeId", id);
        model.addAttribute("sysUser", sysUser);
        return "module/bugly/updaterBugly";
    }

    @GetMapping("/detail")
    public String detail(String id, Model model){
        model.addAttribute("exceptionTypeId", id);
        return "module/bugly/detail";
    }

    @GetMapping("/detail_show")
    public String detailShow(String exception, Model model){
        ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
        serviceExceptionBo.setErrorException("org.apache.dubbo.rpc.RpcException: Failed to invoke the method searchFace in the service com.bullyun.fpp.api.FaceService. Tried 1 times of the providers [10.244.7.41:20880] (1/1) from the registry zookeeper.bullyun-green.svc.cluster.local:2181 on the consumer 10.244.7.46 using the dubbo version 2.7.5.11-SNAPSHOT. Last error is: Invoke remote method timeout. method: searchFace, provider: dubbo://10.244.7.41:20880/com.bullyun.fpp.api.FaceService?application=rest-service&await=false&await.time=6000000&check=false&deprecated=false&dubbo=2.0.2&init=false&interface=com.bullyun.fpp.api.FaceService&pid=16&qos.enable=false&register.ip=10.244.7.46&release=2.7.5.11-SNAPSHOT&remote.application=facepp-proxy-service&retries=0&side=consumer&sticky=false&threadpool=cached&timeout=10000&timestamp=1591582378098, cause: org.apache.dubbo.remoting.TimeoutException: Waiting server-side response timeout by scan timer. start time: 2020-06-29 08:39:36.326, end time: 2020-06-29 08:39:46.347, client elapsed: 0 ms, server elapsed: 10021 ms, timeout: 10000 ms, request: Request [id=18620456, version=2.0.2, twoway=true, event=false, broken=false, data=null], channel: /10.244.7.46:37740 -> /10.244.7.41:20880\n" +
                "  at org.apache.dubbo.rpc.cluster.support.FailoverClusterInvoker.doInvoke(FailoverClusterInvoker.java:129)\n" +
                "  at org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker.invoke(AbstractClusterInvoker.java:255)\n" +
                "  at org.apache.dubbo.rpc.cluster.interceptor.ClusterInterceptor.intercept(ClusterInterceptor.java:47)\n" +
                "  at org.apache.dubbo.rpc.cluster.support.wrapper.AbstractCluster$InterceptorInvokerNode.invoke(AbstractCluster.java:92)\n" +
                "  at org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.invoke(MockClusterInvoker.java:78)\n" +
                "  at org.apache.dubbo.rpc.proxy.InvokerInvocationHandler.invoke(InvokerInvocationHandler.java:60)\n" +
                "  at org.apache.dubbo.common.bytecode.proxy101.searchFace(proxy101.java)\n" +
                "  at com.manniu.service.impl.FaceServiceImpl.faceCompare(FaceServiceImpl.java:950)\n" +
                "  at com.manniu.controller.FaceController.lambda$faceCompare$16(FaceController.java:308)\n" +
                "  at org.springframework.web.context.request.async.WebAsyncManager.lambda$startCallableProcessing$4(WebAsyncManager.java:326)\n" +
                "  at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)\n" +
                "  at java.util.concurrent.FutureTask.run(FutureTask.java:266)\n" +
                "  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" +
                "  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" +
                "  at java.lang.Thread.run(Thread.java:748)\n" +
                "Caused by: java.util.concurrent.ExecutionException: org.apache.dubbo.remoting.TimeoutException: Waiting server-side response timeout by scan timer. start time: 2020-06-29 08:39:36.326, end time: 2020-06-29 08:39:46.347, client elapsed: 0 ms, server elapsed: 10021 ms, timeout: 10000 ms, request: Request [id=18620456, version=2.0.2, twoway=true, event=false, broken=false, data=null], channel: /10.244.7.46:37740 -> /10.244.7.41:20880\n" +
                "  at java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:357)\n" +
                "  at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1915)\n" +
                "  at org.apache.dubbo.rpc.AsyncRpcResult.get(AsyncRpcResult.java:177)\n" +
                "  at org.apache.dubbo.rpc.protocol.AsyncToSyncInvoker.invoke(AsyncToSyncInvoker.java:61)\n" +
                "  at org.apache.dubbo.monitor.support.MonitorFilter.invoke$original$c9i7Twx7(MonitorFilter.java:89)\n" +
                "  at org.apache.dubbo.monitor.support.MonitorFilter.invoke$original$c9i7Twx7$accessor$DWZzYFjD(MonitorFilter.java)\n" +
                "  at org.apache.dubbo.monitor.support.MonitorFilter$auxiliary$6SZUcpXZ.call(Unknown Source)\n" +
                "  at org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstMethodsInter.intercept(InstMethodsInter.java:96)\n" +
                "  at org.apache.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java)\n" +
                "  at org.apache.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:81)\n" +
                "  at org.apache.dubbo.rpc.protocol.dubbo.filter.FutureFilter.invoke(FutureFilter.java:49)\n" +
                "  at org.apache.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:81)\n" +
                "  at org.apache.dubbo.rpc.filter.ConsumerContextFilter.invoke(ConsumerContextFilter.java:55)\n" +
                "  at org.apache.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:81)\n" +
                "  at org.apache.dubbo.rpc.listener.ListenerInvokerWrapper.invoke(ListenerInvokerWrapper.java:78)\n" +
                "  at org.apache.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:56)\n" +
                "  at org.apache.dubbo.rpc.cluster.support.FailoverClusterInvoker.doInvoke(FailoverClusterInvoker.java:85)\n" +
                "  ... 14 more\n" +
                "Caused by: org.apache.dubbo.remoting.TimeoutException: Waiting server-side response timeout by scan timer. start time: 2020-06-29 08:39:36.326, end time: 2020-06-29 08:39:46.347, client elapsed: 0 ms, server elapsed: 10021 ms, timeout: 10000 ms, request: Request [id=18620456, version=2.0.2, twoway=true, event=false, broken=false, data=null], channel: /10.244.7.46:37740 -> /10.244.7.41:20880\n" +
                "  at org.apache.dubbo.remoting.exchange.support.DefaultFuture.doReceived(DefaultFuture.java:210)\n" +
                "  at org.apache.dubbo.remoting.exchange.support.DefaultFuture.received(DefaultFuture.java:175)\n" +
                "  at org.apache.dubbo.remoting.exchange.support.DefaultFuture$TimeoutCheckTask.notifyTimeout(DefaultFuture.java:297)\n" +
                "  at org.apache.dubbo.remoting.exchange.support.DefaultFuture$TimeoutCheckTask.lambda$run$0(DefaultFuture.java:284)\n" +
                "  at org.apache.dubbo.common.threadpool.ThreadlessExecutor.waitAndDrain(ThreadlessExecutor.java:77)\n" +
                "  at org.apache.dubbo.rpc.AsyncRpcResult.get(AsyncRpcResult.java:175)\n" +
                "  ... 28 more");
        model.addAttribute("serviceException", serviceExceptionBo.getErrorException());
        return "module/bugly/detailShow";
    }
}
