package com.airchina.cqmp.core.demo.activiti;/**
 * Created by zhang on 2016/11/7 0007.
 */

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Activiti流程引擎Demo
 *
 * @Author zhang
 * @Create 2016/11/7 0007
 */
public class HumanResourceExamListener implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(HumanResourceExamListener.class.getName());

    @Override
    public void execute(DelegateExecution execute) throws Exception {
        logger.info("检查该考试是否通过开发知识考试....");
        Map<String, Object> variables = execute.getVariables();
        String reuslt = variables.get("result").toString();
        logger.info("开发知识面试结果" + reuslt);
        logger.info("开始人事面试了....");
        execute.setVariable("result", "该考生开发知识面试通过了....");
        logger.info("人事面试完毕....等候通知....");
    }
}
