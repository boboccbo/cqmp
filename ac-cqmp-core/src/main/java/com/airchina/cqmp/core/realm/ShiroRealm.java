package com.airchina.cqmp.core.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * User: 梅海波
 * Date: 2016/11/3.
 * Time: 11:33
 * 说明：
 */
public class ShiroRealm extends AuthorizingRealm {


    /**
     *@Author 梅海波[meihaibo13186@sinosoft.com.cn]
     *@Date 2016/11/3 11:34
     *说明：认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *@Author 梅海波[meihaibo13186@sinosoft.com.cn]
     *@Date 2016/11/3 11:35
     *说明：授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
