package com.ruoyi.project.tool.helper;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SchedulerHelper {
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
        SchedulerHelper helper1 = SchedulerHelper.getNew();
        helper1.scheduleAtFixedRate(() -> {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            System.out.println(sdf.format(new java.util.Date()));
        }, 1000);
    }

    public static SchedulerHelper getNew() {
        return new SchedulerHelper();
    }

    /**
     * 在一定延迟之后周期性的执行某个任务
     * 执行任务的间隔是固定的
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long period) {
        return executor.scheduleAtFixedRate(command, 0, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 在一定延迟之后周期性的执行某个任务
     * 执行任务的间隔是固定的
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period) {
        return executor.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 在一定延迟之后周期性的执行某个任务
     * 执行时间间隔是不固定的
     * 其会在周期任务的上一个任务执行完成之后才开始计时
     * 并在指定时间间隔之后才开始执行任务
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay) {
        return executor.scheduleAtFixedRate(command, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 在一定延迟之后只执行一次某个任务
     */
    public ScheduledFuture<?> schedule(Runnable command, long delay) {
        return executor.schedule(command, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 在一定延迟之后只执行一次某个任务
     */
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay) {
        return executor.schedule(callable, delay, TimeUnit.MILLISECONDS);
    }
}
