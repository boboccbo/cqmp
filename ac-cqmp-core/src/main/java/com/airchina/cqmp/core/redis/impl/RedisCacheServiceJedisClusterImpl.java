package com.airchina.cqmp.core.redis.impl;



import java.util.Set;

import com.airchina.cqmp.core.redis.RedisCacheService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Service
public class RedisCacheServiceJedisClusterImpl extends AbstractRedisCacheService implements RedisCacheService {

	//ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-redis.xml");
	private JedisCluster jedisCluster =null; // (JedisCluster)context.getBean("jedisCluster");
	


	public void putCache(String key, Object value) {
		try {
			String realPath = this.getClass().getClassLoader().getResource("/").getPath();
			String[] array = realPath.split("/");
			jedisCluster.sadd(array[array.length-3], key);
			jedisCluster.set(key, JSON.toJSONString(value, SerializerFeature.WriteClassName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void putCache(String key, Object value, int seconds) {
		try {
			String realPath = this.getClass().getClassLoader().getResource("/").getPath();
			String[] array = realPath.split("/");
			jedisCluster.sadd(array[array.length-3], key);
			jedisCluster.setex(key, seconds, JSON.toJSONString(value, SerializerFeature.WriteClassName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {
		Object result = null;
		try {
			result = JSON.parse(jedisCluster.get(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public <T> T getCache(String key, Class<T> clazz) {
		T t = null;
		try {
			t = JSON.parseObject(jedisCluster.get(key), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 确认一个key是否存在
	 */

	public Boolean exists(String key) {
		Boolean result = false;
		try {
			result = jedisCluster.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除一个key
	 */

	public void remove(String keys) {
		try {
			jedisCluster.del(keys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 清空redis缓存
	 */
	public void clear(String rootContext) {
		try {
			Set<String> set = jedisCluster.smembers(rootContext);
			for (String key : set) {
				jedisCluster.del(key);
				jedisCluster.srem("keys", key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

}
