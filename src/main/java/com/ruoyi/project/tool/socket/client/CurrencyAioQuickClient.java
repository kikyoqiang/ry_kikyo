package com.ruoyi.project.tool.socket.client;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.extension.plugins.HeartPlugin;
import org.smartboot.socket.extension.processor.AbstractMessageProcessor;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioSession;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CurrencyAioQuickClient extends AbstractMessageProcessor<byte[]> {
    private AioQuickClient aioQuickClient;
    /**
     * Client客户端通用方法接口对象
     */
    private CurrencyClientMethod currencyClientMethod;


    /**
     * 获取AioQuickClient对象，使用默认“END$”作为消息解析结束符；使用默认心跳插件配置心跳每3秒发一次、超时时间30秒、每分钟的生命周期；
     *
     * @param SEVER_IP             连接服务端IP地址
     * @param SERVER_PORT          连接服务端端口号
     * @param currencyClientMethod 通用方法对象
     * @return AioQuickClient
     */
    public AioQuickClient getAioQuickClient(String SEVER_IP, int SERVER_PORT, final CurrencyClientMethod currencyClientMethod) {
        getClient(3, 30, TimeUnit.SECONDS, currencyClientMethod);
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, new CurrencyClientProtocol("END$".getBytes()), this);
        return aioQuickClient;
    }

    public AioQuickClient getAioQuickClientNoHeart(String SEVER_IP, int SERVER_PORT, final CurrencyClientMethod currencyClientMethod1) {
        currencyClientMethod = currencyClientMethod1;
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, new CurrencyClientProtocol("END$".getBytes()), this);
        return aioQuickClient;
    }

    /**
     * 获取AioQuickClient对象，使用默认“END$”作为消息解析结束符
     *
     * @param SEVER_IP             连接服务端IP地址
     * @param SERVER_PORT          连接服务端端口号
     * @param heartRate            自定义心跳插件每多少秒发送一次
     * @param timeout              自定义心跳插件超时时间，必须大于heartRate
     * @param unit                 自定义心跳插件生命周期
     * @param currencyClientMethod 通用方法对象
     * @return AioQuickClient
     */
    public AioQuickClient getAioQuickClient(String SEVER_IP, int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, final CurrencyClientMethod currencyClientMethod) {
        getClient(heartRate, timeout, unit, currencyClientMethod);
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, new CurrencyClientProtocol("END$".getBytes()), this);
        return aioQuickClient;
    }

    /**
     * 获取AioQuickClient对象，可自定义设置单条解析结束符
     *
     * @param SEVER_IP             连接服务端IP地址
     * @param SERVER_PORT          连接服务端端口号
     * @param heartRate            自定义心跳插件每多少秒发送一次
     * @param timeout              自定义心跳插件超时时间，必须大于heartRate
     * @param unit                 自定义心跳插件生命周期
     * @param currencyClientMethod 通用方法对象
     * @param protocolBytes        自定义单条结束符
     * @return AioQuickClient
     */
    public AioQuickClient getAioQuickClient(String SEVER_IP, int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, CurrencyClientMethod currencyClientMethod, byte[] protocolBytes) {
        getClient(heartRate, timeout, unit, currencyClientMethod);
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, new CurrencyClientProtocol(protocolBytes), this);
        return aioQuickClient;
    }

    /**
     * 获取AioQuickClient对象，可自定义设置多条解析结束符
     *
     * @param SEVER_IP             连接服务端IP地址
     * @param SERVER_PORT          连接服务端端口号
     * @param heartRate            自定义心跳插件每多少秒发送一次
     * @param timeout              自定义心跳插件超时时间，必须大于heartRate
     * @param unit                 自定义心跳插件生命周期
     * @param currencyClientMethod 通用方法对象
     * @param protocolBytesList    自定义多条结束符结果集
     * @return AioQuickClient
     */
    public AioQuickClient getAioQuickClient(String SEVER_IP, int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, final CurrencyClientMethod currencyClientMethod, List<byte[]> protocolBytesList) {
        getClient(heartRate, timeout, unit, currencyClientMethod);
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, new CurrencyClientProtocol(protocolBytesList), this);
        return aioQuickClient;
    }

    /**
     * 获取AioQuickClient对象，可自定义Protocol解析类
     *
     * @param SEVER_IP             连接服务端IP地址
     * @param SERVER_PORT          连接服务端端口号
     * @param heartRate            自定义心跳插件每多少秒发送一次
     * @param timeout              自定义心跳插件超时时间，必须大于heartRate
     * @param unit                 自定义心跳插件生命周期
     * @param currencyClientMethod 通用方法对象
     * @param protocol             自定义消息解析类对象
     * @return AioQuickClient
     */
    public AioQuickClient getAioQuickClient(String SEVER_IP, int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, CurrencyClientMethod currencyClientMethod, Protocol<byte[]> protocol) {
        getClient(heartRate, timeout, unit, currencyClientMethod);
        aioQuickClient = new AioQuickClient(SEVER_IP, SERVER_PORT, protocol, this);
        return aioQuickClient;
    }

    /**
     * @param heartRate             自定义心跳插件每多少秒发送一次
     * @param timeout               自定义心跳插件超时时间，必须大于heartRate
     * @param unit                  自定义心跳插件生命周期
     * @param currencyClientMethod1 通用方法对象
     */
    private void getClient(int heartRate, int timeout, TimeUnit unit, CurrencyClientMethod currencyClientMethod1) {
        currencyClientMethod = currencyClientMethod1;
        this.addPlugin(new HeartPlugin<byte[]>(heartRate, timeout, unit) {
            @Override
            public void sendHeartRequest(AioSession aioSession) {
                currencyClientMethod1.sendHeartRequest(aioSession);
            }

            @Override
            public boolean isHeartMessage(AioSession aioSession, byte[] bytes) {
                return currencyClientMethod1.isHeartMessage(aioSession, bytes);
            }
        });
    }

    public void process0(AioSession aioSession, byte[] bytes) {
        currencyClientMethod.process(aioSession, bytes);
    }

    public void stateEvent0(AioSession aioSession, StateMachineEnum stateMachineEnum, Throwable throwable) {
        currencyClientMethod.stateEvent(aioSession, stateMachineEnum, throwable);
    }

    public void start() throws Exception {
        aioQuickClient.start();
    }

    public AioQuickClient setReadBufferSize(int size) {
        aioQuickClient.setReadBufferSize(size);
        return aioQuickClient;
    }

    public void shutdown() {
        this.aioQuickClient.shutdown();
    }
}
