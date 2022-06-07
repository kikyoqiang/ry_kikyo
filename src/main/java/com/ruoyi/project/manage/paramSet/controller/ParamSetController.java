package com.ruoyi.project.manage.paramSet.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.manage.paramSet.domain.ParamSet;
import com.ruoyi.project.manage.paramSet.service.IParamSetService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 系统参数Controller
 *
 * @author ruoyi
 * @date 2022-06-07
 */
@Controller
@RequestMapping("/manage/paramSet")
public class ParamSetController extends BaseController {
    private String prefix = "manage/paramSet";

    @Autowired
    private IParamSetService paramSetService;

    @RequiresPermissions("manage:paramSet:view")
    @GetMapping()
    public String paramSet() {
        return prefix + "/paramSet";
    }

    /**
     * 查询系统参数列表
     */
    @RequiresPermissions("manage:paramSet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ParamSet paramSet) {
        startPage();
        List<ParamSet> list = paramSetService.selectParamSetList(paramSet);
        return getDataTable(list);
    }

    /**
     * 导出系统参数列表
     */
    @RequiresPermissions("manage:paramSet:export")
    @Log(title = "系统参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ParamSet paramSet) {
        List<ParamSet> list = paramSetService.selectParamSetList(paramSet);
        ExcelUtil<ParamSet> util = new ExcelUtil<ParamSet>(ParamSet.class);
        return util.exportExcel(list, "系统参数数据");
    }

    /**
     * 新增系统参数
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存系统参数
     */
    @RequiresPermissions("manage:paramSet:add")
    @Log(title = "系统参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ParamSet paramSet) {
        return toAjax(paramSetService.insertParamSet(paramSet));
    }

    /**
     * 修改系统参数
     */
    @RequiresPermissions("manage:paramSet:edit")
    @GetMapping("/edit/{paramId}")
    public String edit(@PathVariable("paramId") Long paramId, ModelMap mmap) {
        ParamSet paramSet = paramSetService.selectParamSetByParamId(paramId);
        mmap.put("paramSet", paramSet);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统参数
     */
    @RequiresPermissions("manage:paramSet:edit")
    @Log(title = "系统参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ParamSet paramSet) {
        return toAjax(paramSetService.updateParamSet(paramSet));
    }

    /**
     * 删除系统参数
     */
    @RequiresPermissions("manage:paramSet:remove")
    @Log(title = "系统参数", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paramSetService.deleteParamSetByParamIds(ids));
    }
}
