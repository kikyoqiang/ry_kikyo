package com.ruoyi.project.front;

import com.ruoyi.project.demo.test.StrategyDataSendClient;
import com.ruoyi.project.demo.test.StrategyServer;
import com.ruoyi.project.tool.utils.LogbackUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 纪志强
 * @version V1.0
 * @description 初始化
 * @date 2022年03月16日
 */
@Component
public class InitCommandLineRunner implements CommandLineRunner {
    private static boolean flag = false;

    @Override
    public void run(String... args) {
        try {
            if (flag) return;
            flag = true;
            init();
        } catch (Exception e) {
            e.printStackTrace();
            LogbackUtil.getErrorLogger().error("run", e);
        }
    }

    private void init() throws Exception {
        //initTest();

    }

    private void initTest() throws Exception {
        StrategyServer.openServer();
        Thread.sleep(1000 * 2);
        StrategyDataSendClient.openClient();
    }
}