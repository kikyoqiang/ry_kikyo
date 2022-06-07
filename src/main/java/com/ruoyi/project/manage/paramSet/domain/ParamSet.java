package com.ruoyi.project.manage.paramSet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 系统参数对象 k_param_set
 * 
 * @author ruoyi
 * @date 2022-06-07
 */
public class ParamSet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数id */
    private Long paramId;

    /** 参数编码 */
    @Excel(name = "参数编码")
    private String paramCode;

    /** 参数键值 */
    @Excel(name = "参数键值")
    private String paramValue;

    /** 参数类型 */
    @Excel(name = "参数类型")
    private String paramType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setParamId(Long paramId)
    {
        this.paramId = paramId;
    }

    public Long getParamId()
    {
        return paramId;
    }
    public void setParamCode(String paramCode)
    {
        this.paramCode = paramCode;
    }

    public String getParamCode()
    {
        return paramCode;
    }
    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public String getParamValue()
    {
        return paramValue;
    }
    public void setParamType(String paramType)
    {
        this.paramType = paramType;
    }

    public String getParamType()
    {
        return paramType;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paramId", getParamId())
            .append("paramCode", getParamCode())
            .append("paramValue", getParamValue())
            .append("paramType", getParamType())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
