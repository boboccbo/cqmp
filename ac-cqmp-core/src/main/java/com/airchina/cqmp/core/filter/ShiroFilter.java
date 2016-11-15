package com.airchina.cqmp.core.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * User: 梅海波
 * Date: 2016/11/3.
 * Time: 11:15
 * 说明：系统自定义shiro过滤器
 */
public class ShiroFilter extends AuthorizationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(ShiroFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {

        LOG.debug("执行了自定义授权过滤器");
        Subject subject = getSubject(servletRequest, servletResponse);
        //获取权限名称列表
        String[] perms= (String[])mappedValue;//得到的是shiro配置文件中perms[]中的内容
        if(perms==null || perms.length==0){
            return true;
        }
        //遍历权限
        for(String p:perms){
            if(subject.isPermitted(p)){
                return true;
            }
        }
        return false;
    }
}
