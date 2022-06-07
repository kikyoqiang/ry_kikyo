package com.ruoyi.project.demo.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
    private String prefix = "demo";

    @RequestMapping("")
    public String index() {
        String res = prefix + "/test/test_layer";
        return res;
    }

    @RequestMapping("/layer")
    public String layer() {
        String res = prefix + "/test/test_layer";
        return res;
    }

    @RequestMapping("/openWindow")
    public String openWindow() {
        String res = prefix + "/test/test_openWindow";
        return res;
    }
}
