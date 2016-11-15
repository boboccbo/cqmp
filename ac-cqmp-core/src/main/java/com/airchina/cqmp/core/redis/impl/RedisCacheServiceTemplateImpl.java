package com.airchina.cqmp.core.redis.impl;

import com.airchina.cqmp.core.redis.RedisCacheService;

/**
 * User: 梅海波
 * Date: 2016/11/2.
 * Time: 14:05
 * 说明：
 */
public class RedisCacheServiceTemplateImpl extends AbstractRedisCacheService implements RedisCacheService {


    @Override
    public void putCache(String key, Object value) {

    }

    @Override
    public void putCache(String key, Object value, int seconds) {

    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    @Override
    public Object getCache(String key) {
        return null;
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public <T> T getCache(String key, Class<T> clazz) {
        return null;
    }

    /**
     * 确认一个key是否存在
     *
     * @param key
     */
    @Override
    public Boolean exists(String key) {
        return null;
    }

    /**
     * 删除一个key
     *
     * @param keys
     */
    @Override
    public void remove(String keys) {

    }

    /**
     * 清除缓存
     *
     * @param rootContext
     */
    @Override
    public void clear(String rootContext) {

    }
}
