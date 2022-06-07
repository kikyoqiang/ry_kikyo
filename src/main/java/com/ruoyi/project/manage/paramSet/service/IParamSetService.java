package com.ruoyi.project.manage.paramSet.service;

import java.util.List;
import com.ruoyi.project.manage.paramSet.domain.ParamSet;

/**
 * 系统参数Service接口
 * 
 * @author ruoyi
 * @date 2022-06-07
 */
public interface IParamSetService 
{
    /**
     * 查询系统参数
     * 
     * @param paramId 系统参数主键
     * @return 系统参数
     */
    public ParamSet selectParamSetByParamId(Long paramId);

    /**
     * 查询系统参数列表
     * 
     * @param paramSet 系统参数
     * @return 系统参数集合
     */
    public List<ParamSet> selectParamSetList(ParamSet paramSet);

    /**
     * 新增系统参数
     * 
     * @param paramSet 系统参数
     * @return 结果
     */
    public int insertParamSet(ParamSet paramSet);

    /**
     * 修改系统参数
     * 
     * @param paramSet 系统参数
     * @return 结果
     */
    public int updateParamSet(ParamSet paramSet);

    /**
     * 批量删除系统参数
     * 
     * @param paramIds 需要删除的系统参数主键集合
     * @return 结果
     */
    public int deleteParamSetByParamIds(String paramIds);

    /**
     * 删除系统参数信息
     * 
     * @param paramId 系统参数主键
     * @return 结果
     */
    public int deleteParamSetByParamId(Long paramId);
}
