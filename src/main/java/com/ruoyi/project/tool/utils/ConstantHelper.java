package com.ruoyi.project.tool.utils;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.manage.paramSet.domain.ParamSet;
import com.ruoyi.project.manage.paramSet.mapper.ParamSetMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version: V1.0
 * @author: 纪志强
 * @description: 常量帮助类
 * @date: 2020-11-05
 * @copyright: 北京龙田华远科技有限公司
 */
public class ConstantHelper {
    private static Map<String, String> param = new ConcurrentHashMap<>();
    private static ParamSetMapper ParamSetMapper = null;

    /**
     * @author: 纪志强
     * @description: 获取字典中的值
     * @param: 参数
     * @return: 返回类型
     * @throws:
     */
    public synchronized static String getValue(String code) {
        if (ParamSetMapper == null) {
            ParamSetMapper = SpringUtils.getBean(ParamSetMapper.class);
        }
        if (param.containsKey(code)) {
            return param.get(code);
        }
        ParamSet paramByCode = ParamSetMapper.getParamByCode(code);
        if (paramByCode == null || StringUtils.isEmpty(paramByCode.getParamValue())) {
            return "";
        }
        String value = paramByCode.getParamValue();
        param.put(code, value);
        return value;
    }

    public synchronized static int getIntValue(String code) {
        try {
            return Integer.parseInt(getValue(code));
        } catch (Exception e) {
            return -999999;
        }
    }

    public synchronized static double getDoubleValue(String code) {
        try {
            return Double.parseDouble(getValue(code));
        } catch (Exception e) {
            return -999999;
        }
    }

    public synchronized static String getValueNoCache(String code) {
        if (ParamSetMapper == null) {
            ParamSetMapper = SpringUtils.getBean(ParamSetMapper.class);
        }
        ParamSet paramByCode = ParamSetMapper.getParamByCode(code);
        if (paramByCode == null || StringUtils.isEmpty(paramByCode.getParamValue())) {
            return "";
        }
        return paramByCode.getParamValue();
    }
}
