package com.soup.common.service;

/**
 * 缓存服务接口
 *
 * @author zhaoyi
 */
public interface CacheService {

    /**
     * 新增缓存
     *
     * @param cacheName 缓存类别
     * @param key 缓存key
     * @param value 缓存value
     * @return 缓存结果，true缓存成功，false缓存失败
     */
    boolean set(String cacheName, Object key, Object value);

    /**
     * 获取缓存value
     *
     * @param cacheName 缓存类别
     * @param key 缓存key
     * @param <T> 缓存value
     * @return 缓存value对象
     */
    <T> T get(String cacheName, Object key);

    /**
     * 删除缓存
     *
     * @param cacheName 缓存类别
     * @param key 缓存key
     */
    void remove(String cacheName, Object key);
}
