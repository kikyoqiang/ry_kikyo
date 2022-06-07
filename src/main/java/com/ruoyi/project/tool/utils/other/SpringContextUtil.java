package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @version: V1.0
// * @author: 纪志强
// * @description: 描述
// * @date: 2020-12-05
// * @copyright: 北京龙田华远科技有限公司
// */
//@Component
//public class SpringContextUtil implements ApplicationContextAware {
//    private static ApplicationContext applicationContext;
//
//    /**
//     * @author: 纪志强
//     * @description: setApplicationContext
//     * @param: 参数
//     * @return: 返回类型
//     * @throws:
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SpringContextUtil.applicationContext = applicationContext;
//    }
//
//    /**
//     * @author: 纪志强
//     * @description: 获取applicationContext
//     * @param: 参数
//     * @return: 返回类型
//     * @throws:
//     */
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    /**
//     * @author: 纪志强
//     * @description: 通过name获取 Bean.
//     * @param: 参数
//     * @return: 返回类型
//     * @throws:
//     */
//    public static Object getBean(String name) {
//        return getApplicationContext().getBean(name);
//    }
//
//    /**
//     * @author: 纪志强
//     * @description: 通过class获取Bean.
//     * @param: 参数
//     * @return: 返回类型
//     * @throws:
//     */
//    public static <T> T getBean(Class<T> clazz) {
//        return getApplicationContext().getBean(clazz);
//    }
//
//    /**
//     * @author: 纪志强
//     * @description: 通过name, 以及Clazz返回指定的Bean
//     * @param: 参数
//     * @return: 返回类型
//     * @throws:
//     */
//    public static <T> T getBean(String name, Class<T> clazz) {
//        return getApplicationContext().getBean(name, clazz);
//    }
//}
