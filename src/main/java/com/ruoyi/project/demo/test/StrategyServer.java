package com.ruoyi.project.demo.test;

import com.ruoyi.project.tool.socket.server.CurrencyAioQuickServer;
import com.ruoyi.project.tool.socket.server.CurrencyServerMethod;
import com.ruoyi.project.tool.utils.ConstantHelper;
import com.ruoyi.project.tool.utils.LogbackUtil;
import org.slf4j.Logger;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class StrategyServer implements CurrencyServerMethod {
    private static Logger log = LogbackUtil.getTestLogger();
    private static Logger errorLog = LogbackUtil.getErrorLogger();
    private static String socketName = StrategyServer.class.getSimpleName() + " ";
    public static CurrencyAioQuickServer quickServer;
    public static AioSession aioSession;
    private static SimpleDateFormat sdfSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static SimpleDateFormat sdfss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdfdd = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        openServer();
    }

    public static void openServer() {
        try {
            quickServer = new CurrencyAioQuickServer();
            quickServer.getAioQuickServer(7015, 3, 10, TimeUnit.SECONDS, new StrategyServer(), "END$".getBytes());
            quickServer.setReadBufferSize(2048);
            quickServer.start();
            log.info(socketName + "::openServer - start");
        } catch (Exception e) {
            errorLog.error(socketName + "::openServer - Address already in use (0x07070001)", e);
            quickServer.shutdown();
            e.printStackTrace();
        }
    }

    @Override
    public void process(AioSession aioSession, byte[] bytes) {
        try {
            processData(aioSession, bytes);
        } catch (Exception e) {
            errorLog.error(socketName + "数据处理异常", e);
        }
    }

    private void processData(AioSession aioSession, byte[] bytes) {
        String data = new String(bytes);
        System.out.println(socketName + data);
    }

    @Override
    public void stateEvent(AioSession aioSession1, StateMachineEnum stateMachineEnum, Throwable throwable) {
        switch (stateMachineEnum) {
            case NEW_SESSION:
                aioSession = aioSession1;
                System.out.println(socketName + "NEW_SESSION state: " + stateMachineEnum);
                log.info(socketName + "::stateEvent - " + stateMachineEnum.toString() + "");
                break;
            case SESSION_CLOSED:
                aioSession = null;
                System.out.println(socketName + "服务端SESSION_CLOSED state: " + stateMachineEnum);
                log.warn(socketName + "::stateEvent - " + stateMachineEnum.toString() + "(0x07060002)");
                break;
            default:
                aioSession = null;
                System.out.println(socketName + "other state: " + stateMachineEnum + " " + throwable.toString());
                log.warn(socketName + "::stateEvent - " + stateMachineEnum.toString() + "(0x07060002)");
                break;
        }
    }

    @Override
    public void sendHeartRequest(AioSession aioSession) {

    }

    @Override
    public boolean isHeartMessage(AioSession aioSession, byte[] bytes) {
        return false;
    }
}
