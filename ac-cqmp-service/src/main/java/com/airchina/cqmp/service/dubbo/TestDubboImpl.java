package com.airchina.cqmp.service.dubbo;

import com.airchina.cqmp.interfaces.TestDubbo;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * User: 梅海波
 * Date: 2016/11/14.
 * Time: 15:16
 * 说明：
 */
@Service
public class TestDubboImpl implements TestDubbo{
    @Override
    public String getUser() {

        System.out.print("Hello World");

        return "helloWorld";
    }
}
