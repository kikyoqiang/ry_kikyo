package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils.other;
//
//
//import org.apache.commons.lang3.time.StopWatch;
//
///**
// * Created by Administrator on 2021/3/18.
// */
//public class StopWatchUtil {
//    private static StopWatch stopWatch = new StopWatch();
//    private static boolean flag=false;
//
//    public static void start(){
//        if (flag){
//            return;
//        }
//        stopWatch.reset();
//        stopWatch.start();
//        flag=true;
//    }
//
//    public static void stop(){
//        stopWatch.stop();
//
//        flag = false;
//    }
//
//    public static void split(){
//        stopWatch.split();
//        flag = false;
//    }
//
//    public static long getTime(){
//       return stopWatch.getTime();
//    }
//
//    public static long getSplitTime(){
//        return stopWatch.getStartTime();
//    }
//}
