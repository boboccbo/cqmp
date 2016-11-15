package com.airchina.cqmp.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 梅海波
 * Date: 2016/11/9.
 * Time: 15:16
 * 说明：所有controller的父类
 */
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {
    }

    @InitBinder({"page"})
    public void initBinderPage(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("page.");
    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
        logger.error(ex.getMessage(), ex);
    }

}
