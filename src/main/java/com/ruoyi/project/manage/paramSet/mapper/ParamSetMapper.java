package com.ruoyi.project.manage.paramSet.mapper;

import java.util.List;

import com.ruoyi.project.manage.paramSet.domain.ParamSet;

/**
 * 系统参数Mapper接口
 *
 * @author ruoyi
 * @date 2022-06-07
 */
public interface ParamSetMapper {
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
     * 删除系统参数
     *
     * @param paramId 系统参数主键
     * @return 结果
     */
    public int deleteParamSetByParamId(Long paramId);

    /**
     * 批量删除系统参数
     *
     * @param paramIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParamSetByParamIds(String[] paramIds);

    public ParamSet getParamByCode(String paramCode);
}
