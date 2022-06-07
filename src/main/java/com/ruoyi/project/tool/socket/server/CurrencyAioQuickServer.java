package com.ruoyi.project.tool.socket.server;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.extension.plugins.HeartPlugin;
import org.smartboot.socket.extension.processor.AbstractMessageProcessor;
import org.smartboot.socket.transport.AioQuickServer;
import org.smartboot.socket.transport.AioSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CurrencyAioQuickServer extends AbstractMessageProcessor<byte[]> {
    private AioQuickServer aioQuickServer;
    private CurrencyServerMethod currencyServerMethod;

    public CurrencyAioQuickServer() {
    }

    public AioQuickServer setReadBufferSize(int size) {
        aioQuickServer.setReadBufferSize(size);
        return aioQuickServer;
    }

    public void start() throws IOException {
        aioQuickServer.start();
    }

    public void shutdown() {
        aioQuickServer.shutdown();
    }

    /**
     * 获取AioQuickServer对象，使用默认“END$”作为消息解析结束符
     *
     * @param SERVER_PORT           服务端服务端口
     * @param currencyServerMethod1 通用方法对象
     * @return AioQuickServer
     */
    public AioQuickServer getAioQuickServer(int SERVER_PORT, CurrencyServerMethod currencyServerMethod1) {
        getServer(3, 30, TimeUnit.SECONDS, currencyServerMethod1);
        aioQuickServer = new AioQuickServer(SERVER_PORT, new CurrencyServerProtocol("END$".getBytes()), this);
        return aioQuickServer;
    }

    /**
     * 获取AioQuickServer对象，可自定义设置单条解析结束符
     *
     * @param SERVER_PORT          服务端服务端口
     * @param currencyServerMethod 通用方法对象
     * @param protocolBytes        自定义单条结束符
     * @return AioQuickServer
     */
    public AioQuickServer getAioQuickServer(int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, CurrencyServerMethod currencyServerMethod, byte[] protocolBytes) {
        getServer(heartRate, timeout, unit, currencyServerMethod);
        aioQuickServer = new AioQuickServer(SERVER_PORT, new CurrencyServerProtocol(protocolBytes), this);
        return aioQuickServer;
    }

    /**
     * 获取AioQuickServer对象，可自定义设置多条解析结束符
     *
     * @param SERVER_PORT          服务端服务端口
     * @param currencyServerMethod 通用方法对象
     * @param protocolBytesList    自定义多条结束符结果集
     * @return AioQuickServer
     */
    public AioQuickServer getAioQuickServer(int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, final CurrencyServerMethod currencyServerMethod, List<byte[]> protocolBytesList) {
        getServer(heartRate, timeout, unit, currencyServerMethod);
        aioQuickServer = new AioQuickServer(SERVER_PORT, new CurrencyServerProtocol(protocolBytesList), this);
        return aioQuickServer;
    }

    /**
     * 获取AioQuickServer对象，可自定义Protocol解析类
     *
     * @param SERVER_PORT          服务端服务端口
     * @param currencyServerMethod 通用方法对象
     * @param protocol             自定义消息解析类对象
     * @return AioQuickServer
     */
    public AioQuickServer getAioQuickServer(int SERVER_PORT, int heartRate, int timeout, TimeUnit unit, CurrencyServerMethod currencyServerMethod, Protocol<byte[]> protocol) {
        getServer(heartRate, timeout, unit, currencyServerMethod);
        aioQuickServer = new AioQuickServer(SERVER_PORT, protocol, this);
        return aioQuickServer;
    }

    private void getServer(int heartRate, int timeout, TimeUnit unit, CurrencyServerMethod currencyServerMethod1) {
        currencyServerMethod = currencyServerMethod1;
        this.addPlugin(new HeartPlugin<byte[]>(heartRate, timeout, unit) {
            @Override
            public void sendHeartRequest(AioSession aioSession) {
                currencyServerMethod.sendHeartRequest(aioSession);
            }

            @Override
            public boolean isHeartMessage(AioSession aioSession, byte[] bytes) {
                return currencyServerMethod.isHeartMessage(aioSession, bytes);
            }
        });
    }

    @Override
    public void process0(AioSession aioSession, byte[] bytes) {
        currencyServerMethod.process(aioSession, bytes);
    }

    @Override
    public void stateEvent0(AioSession aioSession, StateMachineEnum stateMachineEnum, Throwable throwable) {
        currencyServerMethod.stateEvent(aioSession, stateMachineEnum, throwable);
    }
}
