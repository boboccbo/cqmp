package com.airchina.cqmp.core.redis.impl;

import com.airchina.cqmp.core.redis.RedisCacheService;

/**
 * User: 梅海波
 * Date: 2016/11/2.
 * Time: 15:09
 * 说明：
 */
public abstract class AbstractRedisCacheService implements RedisCacheService{

    /**
     *
     * @param arguments
     * @return
     */
    /**
     *@Author 梅海波[meihaibo13186@sinosoft.com.cn]
     *@Date 2016/11/2 15:10
     *说明：
     */
    public String generateCacheKey(Object... arguments) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arguments.length - 1; i++) {
            sb.append(arguments[i]).append(".");
        }
        sb.append(arguments[(arguments.length - 1)]);
        return sb.toString();
    }

}
