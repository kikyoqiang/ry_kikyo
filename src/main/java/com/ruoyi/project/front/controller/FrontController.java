package com.ruoyi.project.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version: V1.0
 * @author: 纪志强
 * @description: 前台控制器
 * @date: 2020-12-05
 * @copyright: 北京龙田华远科技有限公司
 */
@Controller
@RequestMapping("/front")
public class FrontController {
    private String prefix = "front";

    @RequestMapping("")
    public String index(Model model) {
        return prefix + "/index";
    }
}
