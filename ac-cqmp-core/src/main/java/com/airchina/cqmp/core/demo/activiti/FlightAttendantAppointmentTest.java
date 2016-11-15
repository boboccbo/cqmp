package com.airchina.cqmp.core.demo.activiti;/**
 * Created by zhang on 2016/11/7 0007.
 */


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Activiti流程引擎测试Demo
 *
 * @Author zhang
 * @Create 2016/11/7 0007
 */
public class FlightAttendantAppointmentTest implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(FlightAttendantAppointmentTest.class.getName());

    @Override
    public void execute(DelegateExecution execute) throws Exception {
        // TODO Auto-generated method stub
        logger.info("开始开发知识面试了....");
        Map<String, Object> variables = execute.getVariables();
        Set<Map.Entry<String, Object>> infos = variables.entrySet();
        for (Map.Entry<String, Object> entry : infos) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }
        logger.info("开始开发知识面试了....");
        execute.setVariable("result", "该考生开发知识面试通过了....");

    }
}
