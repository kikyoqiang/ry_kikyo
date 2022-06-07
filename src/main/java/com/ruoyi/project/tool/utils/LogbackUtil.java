package com.ruoyi.project.tool.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogbackUtil {
    public static void main(String[] args) {
        Logger errorLog = getErrorLogger();
        errorLog.debug("============debug");
        errorLog.info("============info");
        errorLog.warn("============warn");
        errorLog.error("============error");
    }

    public static Logger getErrorLogger() {
        return LogbackUtil.getLogger(LogName.ErrorDirName);
    }

    public static Logger getTestLogger() {
        return LogbackUtil.getLogger(LogName.ErrorDirName);
    }

    public static Logger getLogger(LogName logName) {
        try {
            Logger logger = LoggerBuilder.getLogger(logName.dirName, logName.fileNamePrefix);
            return logger;
        } catch (Exception e) {
            e.printStackTrace();
            Logger errorLog = getErrorLogger();
            errorLog.error("getLogger", e);
            return errorLog;
        }
    }

    private static String getLogRootPath() {
        String logRootPath2 = "/home/kikyo/logs";
        return logRootPath2;
    }

    public enum LogName {
        TestName("Test", "test"), ErrorDirName("Error", "error");
        private final String dirName;
        private final String fileNamePrefix;

        LogName(String dirName, String filePrefix) {
            this.dirName = dirName;
            this.fileNamePrefix = filePrefix;
        }
    }

    private static class LoggerBuilder {
        private static final Map<String, Logger> maps = new ConcurrentHashMap<>();

        public static Logger getLogger(String dirName, String fileNamePrefix) {
            if (maps.containsKey(dirName)) {
                return maps.get(dirName);
            }
            synchronized (LoggerBuilder.class) {
                Logger build = build(dirName, fileNamePrefix);
                maps.put(dirName, build);
                return build;
            }
        }

        private static Logger build(String dirName, String fileNamePrefix) {
            RollingFileAppender errorAppender = Appender.getAppender(dirName, fileNamePrefix, Level.ERROR);
            RollingFileAppender infoAppender = Appender.getAppender(dirName, fileNamePrefix, Level.INFO);
            RollingFileAppender warnAppender = Appender.getAppender(dirName, fileNamePrefix, Level.WARN);
            RollingFileAppender debugAppender = Appender.getAppender(dirName, fileNamePrefix, Level.DEBUG);
            LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
            Logger logger = context.getLogger("FILE-" + dirName);

            //设置不向上级打印信息
            logger.setAdditive(false);
            logger.addAppender(errorAppender);
            logger.addAppender(infoAppender);
            logger.addAppender(warnAppender);
            logger.addAppender(debugAppender);

            return logger;
        }
    }

    private static class Appender {
        public static RollingFileAppender getAppender(String dirName, String fileNamePrefix, Level level) {
            // DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
            LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
            //这里是可以用来设置appender的，在xml配置文件里面，是这种形式：
            // <appender dirName="error" class="ch.qos.logback.core.rolling.RollingFileAppender">
            RollingFileAppender appender = new RollingFileAppender();
            //ConsoleAppender consoleAppender = new ConsoleAppender();

            //这里设置级别过滤器
            LevelFilter levelFilter = getLevelFilter(level);
            levelFilter.start();
            appender.addFilter(levelFilter);

            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            appender.setContext(context);
            //appender的name属性
            appender.setName("FILE-" + dirName);
            //设置文件名
            appender.setAppend(true);

            appender.setPrudent(false);

            //设置文件创建时间及大小的类
            TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();

            /*日志路径*/
            String fp = OptionHelper.substVars(getLogRootPath() + "/" + dirName + "/" + fileNamePrefix + " " + "%d{yyyy_MM_dd} .%i. " + level.levelStr.toLowerCase() + ".txt", context);
            policy.setFileNamePattern(fp);

            /*设置滚动*/
            SizeAndTimeBasedFNATP triggeringPolicy = new SizeAndTimeBasedFNATP();
            triggeringPolicy.setMaxFileSize(FileSize.valueOf("1MB"));

            policy.setTimeBasedFileNamingAndTriggeringPolicy(triggeringPolicy);

            /*最大保存日期*/
            policy.setMaxHistory(60);

            //设置父节点是appender
            policy.setParent(appender);
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            policy.setContext(context);
            policy.start();

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            //设置上下文，每个logger都关联到logger上下文，默认上下文名称为default。
            // 但可以使用<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
            encoder.setContext(context);
            //设置格式
            encoder.setPattern("%d %p %thread (%file:%line\\)- %m%n");
            encoder.start();

            //加入下面两个节点
            appender.setRollingPolicy(policy);
            appender.setEncoder(encoder);
            appender.start();
            return appender;
        }

        private static LevelFilter getLevelFilter(Level level) {
            LevelFilter levelFilter = new LevelFilter();
            levelFilter.setLevel(level);
            levelFilter.setOnMatch(FilterReply.ACCEPT);
            levelFilter.setOnMismatch(FilterReply.DENY);
            return levelFilter;
        }
    }

    private static String getLogRootPath2() {
        try {
            Map<String, String> map = System.getenv();
            String userName = map.get("USERNAME");// 获取用户名
            String logRootPath2 = "C:/Users/" + userName + "/Desktop/雷达数据";
            return logRootPath2;
        } catch (Exception e) {
            e.printStackTrace();
            LogbackUtil.getErrorLogger().error("init", e);
            return "";
        }
    }
}
