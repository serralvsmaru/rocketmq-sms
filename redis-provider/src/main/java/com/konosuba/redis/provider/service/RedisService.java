package com.konosuba.redis.provider.service;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 说明：操作 redis 的工具类
 *
 * @author konosuba
 */
public interface RedisService {
    // key 的操作

    /**
     * 删除 key
     *
     * @param key 要删除的 key
     * @return 成功返回 true
     */
    Boolean delete(String key);

    /**
     * 批量删除 key
     *
     * @param keys 要删除的 key 的 Collection<String>集合
     * @return 删除成功数
     */
    Long delete(Collection<String> keys);

    /**
     * 序列化 key
     *
     * @param key 要序列化的 key
     * @return byte[] 数组
     */
    byte[] dump(String key);

    /**
     * key 是否存在
     *
     * @param key key
     * @return 存在则 true，否则 false
     */
    Boolean hasKey(String key);

    /**
     * 设置过期时间
     *
     * @param key      设置过期时间的 key
     * @param timeout  过期时间
     * @param timeUnit TimeUnit 枚举类对象，单位
     * @return 成功返回 true
     */
    Boolean expire(String key, long timeout, TimeUnit timeUnit);

    /**
     * 设置过期时间
     *
     * @param key  设置过期时间的 key
     * @param date 过期时间的日期，Date 对象
     * @return 成功返回 true
     */
    Boolean expireAt(String key, Date date);

    /**
     * 查找匹配的 key
     *
     * @param pattern 条件
     * @return Set<String>
     */
    Set<String> keys(String pattern);

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     *
     * @param key     移动的 key
     * @param dbIndex 目标数据库
     * @return 成功返回 true
     */
    Boolean move(String key, int dbIndex);

    /**
     * 移除 key 的过期时间，key 将持久保持
     *
     * @param key 移除的 key
     * @return 成功返回 true
     */
    Boolean persist(String key);

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key      查看的 key
     * @param timeUnit TimeUnit 枚举类，单位
     * @return 返回时间
     */
    Long getExpire(String key, TimeUnit timeUnit);

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key key
     * @return 剩余过期时间
     */
    Long getExpire(String key);

    /**
     * 从当前数据库中随机返回一个 key
     *
     * @return 返回的 key
     */
    String randomKey();

    /**
     * 修改 key 的名称
     *
     * @param oldKey key 原来的名称
     * @param newKey 新的名称
     */
    void rename(String oldKey, String newKey);

    /**
     * 仅当 newKey 不存在时，将 oldKey 改名为 newKey
     *
     * @param oldKey key 原来的名称
     * @param newKey 新的名称
     * @return 成功返回 true
     */
    Boolean renameIfAbsent(String oldKey, String newKey);

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key key
     * @return key 的类型
     */
    DataType type(String key);

    // string 相关操作

    /**
     * 设置指定 key 的值
     *
     * @param key   key
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 获取指定 key 的值
     *
     * @param key key
     * @return key 的值
     */
    Object get(String key);

    /**
     * 返回 key 中字符串值的子字符
     *
     * @param key   key
     * @param start 截取的初始索引
     * @param end   截取的末索引
     * @return 子字符串
     */
    Object get(String key, long start, long end);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     *
     * @param key   key
     * @param value 要设定的新值
     * @return key 原来的值
     */
    Object getAndSet(String key, String value);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     *
     * @param key    key
     * @param offset 偏移量
     * @return 成功返回 true
     */
    Boolean getBit(String key, long offset);

    /**
     * 批量获取
     *
     * @param keys key 集合
     * @return key 对应的值的集合
     */
    List<Object> multiGet(Collection<String> keys);

    /**
     * 设置 ASCII 码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第 offset 位值变为value
     *
     * @param key    key
     * @param offset 位置，二进制的位数
     * @param value  值，true为 1，false为 0
     * @return 成功返回 true
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key      key
     * @param value    值
     * @param timeout  过期时间
     * @param timeUnit 时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                 秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    void set(String key, String value, long timeout, TimeUnit timeUnit);

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key   key
     * @param value 值
     * @return 之前已经存在返回false, 不存在返回true
     */
    Boolean setIfAbsent(String key, String value);

    /**
     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     *
     * @param key    key
     * @param value  复写的参数值
     * @param offset 从指定位置开始覆写
     */
    void set(String key, String value, long offset);

    /**
     * 获取字符串的长度
     *
     * @param key key
     * @return key对应值的长度
     */
    Long size(String key);

    /**
     * 批量添加
     *
     * @param maps 添加的字符串的 map
     */
    void multiSet(Map<String, String> maps);

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     *
     * @param maps 添加的字符串的 map
     * @return 之前已经存在返回 false, 不存在返回 true
     */
    Boolean multiSetIfAbsent(Map<String, String> maps);

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key       key
     * @param increment 整型，自增的 值
     * @return 自增后的值
     */
    Long incrBy(String key, long increment);

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key       key
     * @param increment double，自增的值
     * @return 自增后的值
     */
    Double incrByDouble(String key, double increment);

    /**
     * 追加到末尾
     *
     * @param key   key
     * @param value 追加的值
     * @return key 的长度
     */
    Integer append(String key, String value);

    // hash相关操作

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key   key
     * @param field hash的键
     * @return 键对应的值
     */
    Object hGet(String key, String field);

    /**
     * 获取所有给定 key hash 的值
     *
     * @param key key
     * @return key 对应的所有的键值对 map
     */
    Map<String, Object> hGetAll(String key);

    /**
     * 获取所有给定字段的值
     *
     * @param key    key
     * @param fields key 对应的键的集合
     * @return 键对应的值的集合
     */
    List<Object> hMultiGet(String key, List<String> fields);

    /**
     * 给名为 key 的 hash 增加 hashKey-value 键值对
     *
     * @param key     key
     * @param hashKey hash对应的键
     * @param value   键对应的值
     */
    void hPut(String key, String hashKey, Object value);

    /**
     * 给名为 key 的 hash 增加 maps 所有的键值对
     *
     * @param key  键
     * @param maps 要增加的 map
     */
    void hPutAll(String key, Map<String, String> maps);

    /**
     * 仅当 hashKey不存在时才设置
     *
     * @param key     key hash
     * @param hashKey hash 对应的键
     * @param value   设置的值
     * @return 成功返回 true
     */
    Boolean hPutIfAbsent(String key, String hashKey, Object value);

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key    hash
     * @param fields 删除的键
     * @return 删除的个数
     */
    Long hDelete(String key, Object... fields);

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key   hash
     * @param field 要判断的键
     * @return 存在返回 true
     */
    boolean hHasKey(String key, String field);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key       hash
     * @param field     hash 对应的键
     * @param increment 自增的值，负数为减
     * @return 增加后的值
     */
    Long hIncrBy(String key, String field, long increment);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key       hash
     * @param field     hash 对应的键
     * @param increment 自增的值，负数为减
     * @return 增加后的值
     */
    Double hIncrByDouble(String key, String field, Double increment);

    /**
     * 获取所有哈希表中的字段
     *
     * @param key hash
     * @return hash 所有的键的 Set 集合
     */
    Set<String> hKeys(String key);

    /**
     * 获取哈希表中字段的数量
     *
     * @param key key
     * @return 键的数量
     */
    Long hSize(String key);

    /**
     * 获取哈希表中所有值
     *
     * @param key hash
     * @return hash 所有的值的 List 集合
     */
    List<Object> hValues(String key);

    /**
     * 迭代哈希表中的键值对
     *
     * @param key     hash
     * @param options 迭代的条件
     * @return 连接，如果报错，可以手动关闭，调用 .close()
     */
    Cursor<Map.Entry<String, Object>> hScan(String key, ScanOptions options);

    // list相关操作

    /**
     * 通过索引获取列表中的元素
     *
     * @param key   list 对应的 key
     * @param index 索引
     * @return 对应的值
     */
    Object lIndex(String key, long index);

    /**
     * 获取列表指定范围内的元素
     *
     * @param key   key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return 元素的 集合
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 存储在list头部
     *
     * @param key   key
     * @param value 值
     * @return 集合的长度
     */
    Long lLeftPush(String key, Object value);

    /**
     * 将值全部存储在头部
     *
     * @param key   key
     * @param value 值
     * @return 集合的长度
     */
    Long lLeftPushAll(String key, Object... value);

    /**
     * 将集合里面的值全部存储在头部
     *
     * @param key   key
     * @param value Collection<String>集合
     * @return 集合的长度
     */
    Long lLeftPushAll(String key, Collection<Object> value);

    /**
     * 当list存在的时候才加入
     *
     * @param key   key
     * @param value 值
     * @return 集合的长度
     */
    Long lLeftPushIfPresent(String key, Object value);

    /**
     * 如果 pivot存在,再 pivot前面添加
     *
     * @param key   key
     * @param pivot 是否存在的值
     * @param value pivot 存在则在其前面添加的值
     * @return 集合的长度，失败返回 -1
     */
    Long lLeftPush(String key, Object pivot, Object value);

    /**
     * list 尾部添加
     *
     * @param key   key
     * @param value 值
     * @return 集合的长度
     */
    Long lRightPush(String key, Object value);

    /**
     * 尾部添加所有
     *
     * @param key   key
     * @param value 添加的值
     * @return 集合的长度
     */
    Long lRightPushAll(String key, Object... value);

    /**
     * 尾部添加所有
     *
     * @param key   list key
     * @param value 要添加的 Collection<String> 集合
     * @return 集合的长度
     */
    Long lRightPushAll(String key, Collection<Object> value);

    /**
     * 为已存在的 list 列表添加值
     *
     * @param key   key
     * @param value 要添加的值
     * @return 集合的长度
     */
    Long lRightPushIfPresent(String key, Object value);

    /**
     * 在 pivot 元素的右边添加值
     *
     * @param key   list
     * @param pivot list 存在的元素，在其右边添加
     * @param value 添加的值
     * @return 集合的长度
     */
    Long lRightPush(String key, Object pivot, Object value);

    /**
     * 通过索引设置列表元素的值
     *
     * @param key   list
     * @param index 位置
     * @param value 添加的值
     */
    void lSet(String key, long index, String value);

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key key
     * @return 删除的元素
     */
    Object lLeftPop(String key);

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key      key
     * @param timeout  等待时间
     * @param timeUnit 时间单位
     * @return 移除的元素
     */
    Object lLeftPop(String key, long timeout, TimeUnit timeUnit);

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key key
     * @return 删除的元素
     */
    Object lRightPop(String key);

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key      key
     * @param timeout  等待时间
     * @param timeUnit 时间单位
     * @return 移除的元素
     */
    Object lRightPop(String key, long timeout, TimeUnit timeUnit);

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey      源 list
     * @param destinationKey 目标 list
     * @return 源 list 移除的元素
     */
    Object lRightPopAndLeftPush(String sourceKey, String destinationKey);

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param sourceKey      源 list
     * @param destinationKey 目标 list
     * @param timeout        超时时间
     * @param timeUnit       TimeUnit 枚举类对象，单位
     * @return 源 list 移除的元素
     */
    Object lRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit timeUnit);

    /**
     * 删除集合中值等于value得元素
     *
     * @param key   key
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value 要删除的值
     * @return 删除的个数
     */
    Long lRemove(String key, long index, String value);

    /**
     * 裁剪list
     *
     * @param key   key
     * @param start 裁剪的起始索引
     * @param end   裁剪的末尾索引
     */
    void lTrim(String key, long start, long end);

    /**
     * 获取列表长度
     *
     * @param key key
     * @return list 长度
     */
    Long lSize(String key);

    // set相关操作

    /**
     * set添加元素
     *
     * @param key    key
     * @param values 添加的元素
     * @return 成功个数
     */
    Long sAdd(String key, Object... values);

    /**
     * set移除元素
     *
     * @param key    key
     * @param values 删除的元素
     * @return 删除成功个数
     */
    Long sRemove(String key, Object... values);

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key set 集合 key
     * @return 移除的元素
     */
    Object sPop(String key);

    /**
     * 将元素value从一个集合移到另一个集合
     *
     * @param key     源 set
     * @param value   移动的 值
     * @param destKey 目标 set
     * @return 成功返回 true
     */
    Boolean sMove(String key, String value, String destKey);

    /**
     * 获取集合的大小
     *
     * @param key set 集合
     * @return 集合大小
     */
    Long sSize(String key);

    /**
     * 判断集合是否包含value
     *
     * @param key   set 集合
     * @param value 是否包含的值
     * @return 存在返回 true
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取两个集合的交集
     *
     * @param key      集合
     * @param otherKey 集合
     * @return 两个集合的交集
     */
    Set<Object> sIntersect(String key, String otherKey);

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key       集合
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @return 交集
     */
    Set<Object> sIntersect(String key, Collection<String> otherKeys);

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     *
     * @param key      求交集的集合
     * @param otherKey 求交集的集合
     * @param destKey  交集存储的目标集合
     * @return 交集元素个数
     */
    Long sIntersectAndStore(String key, String otherKey, String destKey);

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key       集合 key
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @param destKey   交集存储的目标集合
     * @return 添加到 destKey 交集的个数
     */
    Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 获取两个集合的并集
     *
     * @param key       集合
     * @param otherKeys 集合
     * @return 并集
     */
    Set<Object> sUnion(String key, String otherKeys);

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key       集合
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @return 并集
     */
    Set<Object> sUnion(String key, Collection<String> otherKeys);

    /**
     * key集合与otherKey集合的并集存储到destKey中
     *
     * @param key      求并集的集合
     * @param otherKey 求并集的集合
     * @param destKey  并集添加到的目标集合
     * @return 添加到目标集合的值的个数
     */
    Long sUnionAndStore(String key, String otherKey, String destKey);

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key       集合
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @param destKey   并集添加到的目标集合
     * @return 添加到目标集合的值的个数
     */
    Long sUnionAndStore(String key, Collection<String> otherKeys,
                        String destKey);

    /**
     * 获取两个集合的差集
     *
     * @param key      集合
     * @param otherKey 集合
     * @return 差集
     */
    Set<Object> sDifference(String key, String otherKey);

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key       集合
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @return 差集
     */
    Set<Object> sDifference(String key, Collection<String> otherKeys);

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * @param key      求差集的集合
     * @param otherKey 求差集的集合
     * @param destKey  差集添加到的目标集合
     * @return 添加到目标集合的值的个数
     */
    Long sDifference(String key, String otherKey, String destKey);

    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key       求差集的集合
     * @param otherKeys 包含多个集合 key 的集合 Collection<String>
     * @param destKey   差集添加到的目标集合
     * @return 添加到目标集合的值的个数
     */
    Long sDifference(String key, Collection<String> otherKeys,
                     String destKey);

    /**
     * 获取集合所有元素
     *
     * @param key 集合
     * @return 集合的所有元素
     */
    Set<Object> sMembers(String key);

    /**
     * 随机获取集合中的一个元素
     *
     * @param key 集合
     * @return 随机返回的元素
     */
    Object sRandomMember(String key);

    /**
     * 随机获取集合中count个元素，可能获取同一个元素多次
     *
     * @param key   集合
     * @param count 获取的个数
     * @return 获取到的元素的集合
     */
    List<Object> sRandomMembers(String key, long count);

    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key   集合
     * @param count 随机获取的元素个数
     * @return 元素的集合
     */
    Set<Object> sDistinctRandomMembers(String key, long count);

    /**
     * 迭代 set 集合
     *
     * @param key     要迭代的集合
     * @param options 迭代的条件
     * @return 连接，如果报错，可以手动关闭，调用 .close()
     */
    Cursor<Object> sScan(String key, ScanOptions options);

    // zSet相关操作

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key   集合
     * @param value 添加的元素
     * @param score 元素对应的分数
     * @return 成功返回 true
     */
    Boolean zAdd(String key, Object value, double score);

    /**
     * 将 values 集合的元素添加的 key 集合
     *
     * @param key    集合 key
     * @param values Set<ZSetOperations.TypedTuple<Object>> 集合
     * @return 添加的个数
     */
    Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> values);

    /**
     * 删除元素
     *
     * @param key    集合
     * @param values 要删除的元素，可变参数
     * @return 删除的个数
     */
    Long zRemove(String key, Object... values);

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key   集合
     * @param value 要添加 score 值的元素
     * @param delta 添加的 score 的值
     * @return 增加后的值
     */
    Double zIncrementScore(String key, Object value, double delta);

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key   集合
     * @param value 要返回排名的值
     * @return 0表示第一位
     */
    Long zRank(String key, Object value);

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key   集合
     * @param value 要返回排名的值
     * @return 0表示第一位
     */
    Long zReverseRank(String key, Object value);

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param key   集合
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 元素的集合
     */
    Set<Object> zRange(String key, long start, long end);

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key   集合
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 元素的集合
     */
    Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key, long start, long end);

    /**
     * 根据Score值查询集合元素
     *
     * @param key 集合
     * @param min 最小值
     * @param max 最大值
     * @return 元素的集合
     */
    Set<Object> zRangeByScore(String key, double min, double max);

    /**
     * 根据Score值查询集合元素, 从小到大排序
     *
     * @param key 集合
     * @param min 最小值
     * @param max 最大值
     * @return 元素的集合
     */
    Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key, double min, double max);

    /**
     * 获取key集合中索引在 [start, end] 的分数在 [min, max] 范围内的所有元素及其分数
     *
     * @param key   集合
     * @param min   score 最小值
     * @param max   score 最大值
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 获取到的元素集合
     */
    Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key, double min,
                                                                   double max, long start, long end);

    /**
     * 获取集合的元素, 从大到小排序
     *
     * @param key   集合
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 获取到的元素集合
     */
    Set<Object> zReverseRange(String key, long start, long end);

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     *
     * @param key   集合
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 元素的集合
     */
    Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScores(String key, long start, long end);

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key 集合
     * @param min score 最小值
     * @param max score 最大值
     * @return 元素集合
     */
    Set<Object> zReverseRangeByScore(String key, double min, double max);

    /**
     * 根据Score值查询集合元素及其分数, 从大到小排序
     *
     * @param key 集合
     * @param min score 最小值
     * @param max score 最大值
     * @return 元素集合
     */
    Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(String key, double min, double max);

    /**
     * 获取key集合中索引在 [start, end] 的分数在 [min, max] 范围内的所有元素
     *
     * @param key   key 集合
     * @param min   score 最小值
     * @param max   score 最大值
     * @param start 起始位置
     * @param end   末尾位置
     * @return 元素集合
     */
    Set<Object> zReverseRangeByScore(String key, double min, double max, long start, long end);

    /**
     * 根据score值获取集合元素个数
     *
     * @param key 集合
     * @param min score 最小值
     * @param max score 最大值
     * @return 元素个数
     */
    Long zCount(String key, double min, double max);

    /**
     * 获取集合大小
     *
     * @param key 集合
     * @return 包含的元素的个数
     */
    Long zSize(String key);

    /**
     * 获取集合大小
     *
     * @param key 集合
     * @return 集合大小
     */
    Long zCard(String key);

    /**
     * 获取集合中value元素的score值
     *
     * @param key   集合
     * @param value 要获取 score 值的元素
     * @return score 值
     */
    Double zScore(String key, Object value);

    /**
     * 移除指定索引位置的成员
     *
     * @param key   集合
     * @param start 起始位置
     * @param end   结束位置
     * @return 移除的个数
     */
    Long zRemoveRange(String key, long start, long end);

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key 集合
     * @param min score 最小值
     * @param max score 最大值
     * @return 移除的个数
     */
    Long zRemoveRangeByScore(String key, double min, double max);

    /**
     * 获取 key和 otherKey的并集并存储在destKey中
     *
     * @param key      求并集的集合
     * @param otherKey 求并集的集合
     * @param destKey  并集存储的目标集合
     * @return 添加到目标集合的个数
     */
    Long zUnionAndStore(String key, String otherKey, String destKey);

    /**
     * 获取 key和 otherKey 的并集并存储在destKey中
     *
     * @param key       集合 key
     * @param otherKeys 多个集合 key 的 Collection<String> 集合
     * @param destKey   并集添加到的目标集合
     * @return 添加到目标集合的元素个数
     */
    Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 获取 key和 otherKey 的交集并存储在destKey中
     *
     * @param key      集合
     * @param otherKey 集合
     * @param destKey  交集添加到的目标集合
     * @return 添加到目标集合的元素个数
     */
    Long zIntersectAndStore(String key, String otherKey, String destKey);

    /**
     * 获取 key和 otherKey 的交集并存储在destKey中
     *
     * @param key       求交集的集合
     * @param otherKeys 求交集的集合
     * @param destKey   交集添加到的目标集合
     * @return 添加到目标集合的元素个数
     */
    Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 迭代集合
     *
     * @param key     要迭代的集合
     * @param options 迭代的条件
     * @return 连接，如果报错，调用 .close() 方法关闭
     */
    Cursor<ZSetOperations.TypedTuple<Object>> zScan(String key, ScanOptions options);

}

