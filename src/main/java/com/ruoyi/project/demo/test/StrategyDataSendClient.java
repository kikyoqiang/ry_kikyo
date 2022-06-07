package com.ruoyi.project.demo.test;

import com.ruoyi.project.tool.socket.client.CurrencyAioQuickClient;
import com.ruoyi.project.tool.socket.client.CurrencyClientMethod;
import com.ruoyi.project.tool.utils.LogbackUtil;
import org.slf4j.Logger;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;
import org.smartboot.socket.transport.WriteBuffer;

import java.text.SimpleDateFormat;
import java.util.*;

public class StrategyDataSendClient implements CurrencyClientMethod {
    private static Logger log = LogbackUtil.getTestLogger();
    private static Logger logError = LogbackUtil.getErrorLogger();
    private static String socketName = StrategyDataSendClient.class.getSimpleName() + " ";
    private static CurrencyAioQuickClient quickClient;
    private static AioSession aioSession;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Integer index = 0;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        openClient();
        sendDataTest();
    }

    private static void sendDataTest() {
        List<String> list = getHolder6_10();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (index > (list.size() - 1)) {
                        index = 0;
                    }
                    sendData(list.get(index));
                    System.out.println("sendTestData = " + list.get(index));
                    ++index;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }

    private static List<String> getDataTest() {
        List<String> list = new ArrayList<>();  //0是放煤  1是放矸  2托辊好  3托辊坏  4环境声音
        for (int i = 1; i <= 5; i++) {
            list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:0 PickupSupportsNumber:7 PickupSupportStatus:0 A2 END$");
        }
        for (int i = 1; i <= 2; i++) {
            list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:7 PickupSupportStatus:0 A2 END$");
        }
        list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:2 PickupSupportsNumber:7 PickupSupportStatus:1 A2 END$");
        list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:2 PickupSupportsNumber:7 PickupSupportStatus:1 A2 END$");
        return list;
    }

    private static List<String> getHolder6_10() {
        //0是放煤  1是放矸  2托辊好  3托辊坏  4环境声音
        int coalTime = 5;
        List<String> list = new ArrayList<>();
        for (int i = 6; i <= 10; i++) {
            if (i == 6) {
                for (int a = 1; a <= coalTime; a++) {
                    list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:0 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                }
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:1 A2 END$");
            }
            if (i == 7 || i == 8) {
                for (int a = 1; a <= coalTime; a++) {
                    list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:0 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                }
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:1 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:1 A2 END$");
            }
            if (i == 9 || i == 10) {
                for (int a = 1; a <= coalTime; a++) {
                    list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:2 PickupState:0 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                }
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:2 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:2 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:0 A2 END$");
                list.add("2021-01-07 16:05:23.365 $PICKUP PickupNumber:2 PickupState:1 PickupSupportsNumber:" + i + " PickupSupportStatus:1 A2 END$");
            }
        }
        return list;
    }

    public static void openClient() {
        try {
            quickClient = new CurrencyAioQuickClient();
            quickClient.getAioQuickClient("127.0.0.1", 7015, new StrategyDataSendClient());
            quickClient.setReadBufferSize(2048);
            quickClient.start();
            log.info(socketName + "::PythonClient - start");
        } catch (Exception e) {
            logError.error(socketName + "::PythonClient - ");
            quickClient.shutdown();
        }
    }

    @Override
    public void process(AioSession aioSession, byte[] bytes) {
        try {
            processData(aioSession, bytes);
        } catch (Exception e) {
            logError.error(socketName + "数据处理异常", e);
            e.printStackTrace();
        }
    }

    private void processData(AioSession aioSession, byte[] bytes) throws Exception {
        String data = new String(bytes);
        System.out.println("========= processData " + data);
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
                System.out.println(socketName + "SESSION_CLOSED state: " + stateMachineEnum);
                log.warn(socketName + "::stateEvent - " + stateMachineEnum.toString() + "(0x07080002)");
                openClient();
                break;
            default:
                aioSession = null;
                System.out.println(socketName + "other state: " + stateMachineEnum + " " + throwable.toString());
                log.info(socketName + "::stateEvent - " + stateMachineEnum.toString() + "(0x07080002)");
                // openClient();
                break;
        }
    }

    @Override
    public void sendHeartRequest(AioSession aioSession) {
        try {
            //2021-01-07 16:05:23.365 $HEART RESPONSE END$
            String msg = sdf.format(new Date()) + " $HEART REQUEST END$";
            sendData(msg);
        } catch (Exception e) {
            logError.error(socketName + "::sendHeartRequest ", e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean isHeartMessage(AioSession aioSession, byte[] bytes) {
        try {
            //2021-01-07 16:05:23.365 $HEART RESPONSE END$
            String heart = new String(bytes);
            if (heart.contains("$HEART") && heart.contains("REQUEST")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logError.error(socketName + "::isHeartMessage ", e);
            e.printStackTrace();
            return false;
        }
    }

    public static void sendData(String msg) throws Exception {
        if (aioSession != null) {
            WriteBuffer writeBuffer = aioSession.writeBuffer();
            writeBuffer.write(msg.getBytes());
            writeBuffer.flush();
        }
    }
}
