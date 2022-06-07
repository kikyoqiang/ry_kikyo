package com.ruoyi.project.manage.paramSet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.manage.paramSet.mapper.ParamSetMapper;
import com.ruoyi.project.manage.paramSet.domain.ParamSet;
import com.ruoyi.project.manage.paramSet.service.IParamSetService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 系统参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-07
 */
@Service
public class ParamSetServiceImpl implements IParamSetService 
{
    @Autowired
    private ParamSetMapper paramSetMapper;

    /**
     * 查询系统参数
     * 
     * @param paramId 系统参数主键
     * @return 系统参数
     */
    @Override
    public ParamSet selectParamSetByParamId(Long paramId)
    {
        return paramSetMapper.selectParamSetByParamId(paramId);
    }

    /**
     * 查询系统参数列表
     * 
     * @param paramSet 系统参数
     * @return 系统参数
     */
    @Override
    public List<ParamSet> selectParamSetList(ParamSet paramSet)
    {
        return paramSetMapper.selectParamSetList(paramSet);
    }

    /**
     * 新增系统参数
     * 
     * @param paramSet 系统参数
     * @return 结果
     */
    @Override
    public int insertParamSet(ParamSet paramSet)
    {
        paramSet.setCreateTime(DateUtils.getNowDate());
        return paramSetMapper.insertParamSet(paramSet);
    }

    /**
     * 修改系统参数
     * 
     * @param paramSet 系统参数
     * @return 结果
     */
    @Override
    public int updateParamSet(ParamSet paramSet)
    {
        return paramSetMapper.updateParamSet(paramSet);
    }

    /**
     * 批量删除系统参数
     * 
     * @param paramIds 需要删除的系统参数主键
     * @return 结果
     */
    @Override
    public int deleteParamSetByParamIds(String paramIds)
    {
        return paramSetMapper.deleteParamSetByParamIds(Convert.toStrArray(paramIds));
    }

    /**
     * 删除系统参数信息
     * 
     * @param paramId 系统参数主键
     * @return 结果
     */
    @Override
    public int deleteParamSetByParamId(Long paramId)
    {
        return paramSetMapper.deleteParamSetByParamId(paramId);
    }
}
