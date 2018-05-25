package com.jerry.girl.config.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * K 指以hash结构操作时 键类型
 * T 为数据实体 应实现序列化接口,并定义serialVersionUID * RedisTemplate 提供了五种数据结构操作类型 hash / list / set / zset / value
 * 方法命名格式为 数据操作类型 + 操作 如 hashPut 指以hash结构(也就是map)想key添加键值对 * Created by hp on 2018/3/1.
 */
public interface RedisDBHelper<HK, T> {
    /**
     * Hash结构 添加元素 * @param key key * @param hashKey hashKey * @param domain 元素
     */
    void hashPut(String key, HK hashKey, T domain);

    /**
     * Hash结构 获取指定key所有键值对 * @param key * @return
     */
    Map<HK, T> hashFindAll(String key);

    /**
     * Hash结构 获取单个元素 * @param key * @param hashKey * @return
     */
    T hashGet(String key, HK hashKey);

    void hashRemove(String key, HK hashKey);

    /**
     * List结构 向尾部(Right)添加元素 * @param key * @param domain * @return
     */
    Long listPush(String key, T domain);

    /**
     * List结构 向头部(Left)添加元素 * @param key * @param domain * @return
     */
    Long listUnshift(String key, T domain);

    /**
     * List结构 获取所有元素 * @param key * @return
     */
    List<T> listFindAll(String key);

    /**
     * List结构 移除并获取数组第一个元素 * @param key * @return
     */
    T listLPop(String key);

    void remove(String key);

    /**
     * 设置过期时间 * @param key 键 * @param timeout 时间 * @param timeUnit 时间单位
     */
    boolean expirse(String key, long timeout, TimeUnit timeUnit);
}

