package com.ruoyi.project.tool.utils;

/**
 * @version: V1.0
 * @author: 纪志强
 * @description: 常量类
 * @date: 2020-11-05
 * @copyright: 北京龙田华远科技有限公司
 */
public class Constant {
    static {
        try {
            COAL = ConstantHelper.getDoubleValue("COAL");
            GANGUE = ConstantHelper.getDoubleValue("GANGUE");
            density_min = ConstantHelper.getDoubleValue("density_min");
            density_max = ConstantHelper.getDoubleValue("density_max");
            gangue_min = ConstantHelper.getDoubleValue("gangue_min");
            gangue_max = ConstantHelper.getDoubleValue("gangue_max");
            lidar_min = ConstantHelper.getIntValue("lidar_min");
            lidar_max = ConstantHelper.getIntValue("lidar_max");
            MOR_time = ConstantHelper.getValue("MOR_time");
            AFT_time = ConstantHelper.getValue("AFT_time");
            NIG_time = ConstantHelper.getValue("NIG_time");
            int a1 = 0;
        } catch (Exception e) {
            LogbackUtil.getErrorLogger().error("Constant", e);
        }
    }
    //==========【 煤炭 参数 】==========================================================================================

    /**
     * 煤炭密度
     */
    public static Double COAL;
    /**
     * 矸石密度
     */
    public static Double GANGUE;
    /**
     * 最大密度 1.4
     */
    public static Double density_max;
    /**
     * 最小密度 1.3
     */
    public static Double density_min;
    /**
     * 最大含矸率 19.27
     */
    public static Double gangue_max;
    /**
     * 最小含矸率 0.0006
     */
    public static Double gangue_min;


    //==========【 雷达 参数 】==========================================================================================

    /**
     * 雷达数据最大边界值
     */
    public static Integer lidar_max;
    /**
     * 雷达数据最小边界值
     */
    public static Integer lidar_min;

    //==========【 三班 参数 】==========================================================================================

    /**
     * 早班时间
     */
    public static String MOR_time;

    /**
     * 中班时间
     */
    public static String AFT_time;

    /**
     * 晚班时间
     */
    public static String NIG_time;

    //==========【 文件路径 】==========================================================================================
    /**
     * 文件根目录
     **/
    public static String rootPath = "D:/bjlthy/tcc";
    /**
     * 皮带数据路径
     **/
    public static String BeltPath = rootPath + "/Realtime/Belt";
    /**
     * 皮带电子秤数据路径
     **/
    public static String BeltBalancePath = rootPath + "/Realtime/BeltBalance";
    /**
     * 皮带雷达数据路径
     **/
    public static String BeltLidarPath = rootPath + "/Realtime/BeltLidar";
    /**
     * 刮板历史数据路径
     **/
    public static String ScraperPath = rootPath + "/Realtime/Scraper";
    /**
     * 皮带雷达过滤数据路径
     **/
    public static String BeltLidarFilterPath = rootPath + "/Realtime/BeltLidarFilter";
    /**
     * 测试路径
     **/
    public static String TestPath = rootPath + "/Test";


    /**
     * 皮带历史数据路径
     **/
    public static String BeltHisPath = rootPath + "/History/Belt";
    /**
     * 皮带电子秤历史数据路径
     **/
    public static String BeltBalanceHisPath = rootPath + "/History/BeltBalance";
    /**
     * 皮带雷达历史数据路径
     **/
    public static String BeltLidarHisPath = rootPath + "/History/BeltLidar";
    /**
     * 刮板历史数据路径
     **/
    public static String ScraperHisPath = rootPath + "/History/Scraper";
    /**
     * 接收下位机异常消息
     */
    public static String errorPath = rootPath + "/ErrorInfo";


    /**
     * wav音频波形声音存储目录1
     */
    public static String wavDir1Path = rootPath + "/wav_1";
    /**
     * wav音频波形声音存储目录2
     */
    public static String wavDir2Path = rootPath + "/wav_2";
    /**
     * pcm音频波形声音存储目录
     */
    public static String pcmDir = rootPath + "/pcm_1s";
    /**
     * 日志根目录
     */
    public static String logRootPath = rootPath + "/logs";

    /**
     * 开始时间用于拼接sql查询
     */
    public static String startTime;

    //==========【 IP 】==========================================================================================
    ///**
    // * 刮板机 IP
    // */
    //public static String scraperIp = "192.168.1.8";
    ////public static String scraperIp = "127.0.0.1";
    ///**
    // * 皮带机 IP
    // */
    //public static String beltIp = "192.168.1.61";
    ////public static String beltIp = "127.0.0.1";
    ///**
    // * 皮带雷达 IP
    // */
    //public static String beltLidarIp = "192.168.1.206";
    ////public static String beltLidarIp = "127.0.0.1";
    ///**
    // * 拾音器配置 IP
    // */
    ////public static String pickupConfigIp = "192.168.2.64";
    //public static String pickupConfigIp = "127.0.0.1";
    ///**
    // * 其它配置 IP
    // */
    //public static String otherConfigIp = "192.168.1.61";
    ////public static String otherConfigIp = "127.0.0.1";
    ///**
    // * 放顶煤策略配置 IP
    // */
    ////public static String StrategySendIp = "192.168.1.19";
    //public static String StrategySendIp = "127.0.0.1";
    ///**
    // * 后刮板预警配置 IP
    // */
    ////public static String ScraperWarnSendIp = "192.168.1.19";
    //public static String ScraperWarnSendIp = "127.0.0.1";


    //==========【 端口 】==========================================================================================


    ///**
    // * 接收 拾音器 实时数据 端口
    // */
    //public  static Integer pickupRealtimePort = 7001;
    ///**
    // * 接收 刮板机 实时数据 端口
    // */
    //public  static Integer ScraperRealtimePort = 7003;
    ///**
    // * 接收 皮带机 实时数据 端口
    // */
    //public  static Integer BeltRealtimePort = 7005;
    ///**
    // * 接收 皮带 电子秤 实时数据 端口
    // */
    //public  static Integer BeltBalanceRealtimePort = 7007;
    ///**
    // * 接收 皮带 雷达 实时数据 端口
    // */
    //public  static Integer BeltLidarRealtimePort = 7009;
    ///**
    // * 接收 皮带 雷达 原始数据 端口
    // */
    //public  static Integer BeltLidarOriginalPort = 7011;
    ///**
    // * 接收 皮带 雷达 外设数据 端口
    // */
    //public  static Integer BeltLidarDevicelPort = 2111;
    ///**
    // * 发送 拾音器 配置 端口
    // */
    //public  static Integer PickupConfigPort = 7020;
    ///**
    // * 发送 其它 配置 端口
    // */
    //public  static Integer OtherConfigPort = 7060;
    //
    ///**
    // * 接收 放顶煤策略  端口
    // */
    //public  static Integer StrategyReceivePort = 7015;
    ///**
    // * 发送 放顶煤策略 配置 端口
    // */
    //public  static Integer StrategySendPort = 7030;
    ///**
    // * 接收 调速预警策略  端口
    // */
    //public  static Integer ScraperWarnReceivePort = 7016;
    ///**
    // * 发送 调速预警策略 配置 端口
    // */
    //public  static Integer ScraperWarnSendPort = 7050;


}
