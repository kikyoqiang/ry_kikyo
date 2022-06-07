package com.ruoyi.project.tool.helper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: V1.0
 * @author: 纪志强
 * @description: 线程池
 * @date: 2020-11-05
 * @copyright: 北京龙田华远科技有限公司
 */

public class ThreadPoolHelper {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    private static ThreadPoolExecutor executor = null;

    static {
        try {
            //使用阿里巴巴推荐的创建线程池的方式
            //通过ThreadPoolExecutor构造函数自定义参数创建
            executor = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                    new ThreadPoolExecutor.CallerRunsPolicy());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
    // ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

    /**
     * executorService
     */
    //private static ExecutorService executorService;

    /**
     * @author: 纪志强
     * @description: 描述
     * @param: 参数
     * @return: 返回类型
     * @throws:
     */
    //static {
    //    executorService = Executors.newFixedThreadPool(50);
    //}

    /**
     * @author: 纪志强
     * @description: 添加线程
     * @param: 参数
     * @return: 返回类型
     * @throws:
     */
    //public static void execute(Runnable runnable) {
    //    executorService.execute(runnable);
    //}
}
