package com.airchina.cqmp.web.controllers;

import com.airchina.cqmp.interfaces.TestDubbo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: 梅海波
 * Date: 2016/11/14.
 * Time: 15:19
 * 说明：
 */
@Controller
@RequestMapping("/test")
public class TestDubboController {

    @Reference
    private TestDubbo testDubbo;

    @RequestMapping("/test.do")
    public void sayHello(){
        testDubbo.getUser();
    }
}
