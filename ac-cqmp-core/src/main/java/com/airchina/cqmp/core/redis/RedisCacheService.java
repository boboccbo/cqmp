package com.airchina.cqmp.core.redis;

public interface RedisCacheService {
	
	public String generateCacheKey(Object... arguments);

	public void putCache(String key, Object value);

	public void putCache(String key, Object value, int seconds);

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key);

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public <T> T getCache(String key, Class<T> clazz);

	/**
	 * 确认一个key是否存在
	 */

	public Boolean exists(String key);

	/**
	 * 删除一个key
	 */

	public void remove(String keys);

	/**
	 * 清除缓存
	 */
	public void clear(String rootContext);
}
