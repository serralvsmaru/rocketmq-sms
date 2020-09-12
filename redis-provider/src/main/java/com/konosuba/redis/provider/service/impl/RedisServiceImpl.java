package com.konosuba.redis.provider.service.impl;


import com.konosuba.redis.provider.service.RedisService;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 说明：redis工具类的实现
 *
 * @author konosuba
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    @Override
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    @Override
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    @Override
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    @Override
    public Object get(String key) {
        return valueOperations.get(key);
    }

    @Override
    public Object get(String key, long start, long end) {
        return valueOperations.get(key, start, end);
    }

    @Override
    public Object getAndSet(String key, String value) {
        return valueOperations.getAndSet(key, value);
    }

    @Override
    public Boolean getBit(String key, long offset) {
        return valueOperations.getBit(key, offset);
    }

    @Override
    public List<Object> multiGet(Collection<String> keys) {
        return valueOperations.multiGet(keys);
    }

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        return valueOperations.setBit(key, offset, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        valueOperations.set(key, value, timeout, timeUnit);
    }

    @Override
    public Boolean setIfAbsent(String key, String value) {
        return valueOperations.setIfAbsent(key, value);
    }

    @Override
    public void set(String key, String value, long offset) {
        valueOperations.set(key, value, offset);
    }

    @Override
    public Long size(String key) {
        return valueOperations.size(key);
    }

    @Override
    public void multiSet(Map<String, String> maps) {
        valueOperations.multiSet(maps);
    }

    @Override
    public Boolean multiSetIfAbsent(Map<String, String> maps) {
        return valueOperations.multiSetIfAbsent(maps);
    }

    @Override
    public Long incrBy(String key, long increment) {
        return valueOperations.increment(key, increment);
    }

    @Override
    public Double incrByDouble(String key, double increment) {
        return valueOperations.increment(key, increment);
    }

    @Override
    public Integer append(String key, String value) {
        return valueOperations.append(key, value);
    }

    @Override
    public Object hGet(String key, String field) {
        return hashOperations.get(key, field);
    }

    @Override
    public Map<String, Object> hGetAll(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public List<Object> hMultiGet(String key, List<String> fields) {
        return hashOperations.multiGet(key, fields);
    }

    @Override
    public void hPut(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    @Override
    public void hPutAll(String key, Map<String, String> maps) {
        hashOperations.putAll(key, maps);
    }

    @Override
    public Boolean hPutIfAbsent(String key, String hashKey, Object value) {
        return hashOperations.putIfAbsent(key, hashKey, value);
    }

    @Override
    public Long hDelete(String key, Object... fields) {
        return hashOperations.delete(key, fields);
    }

    @Override
    public boolean hHasKey(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public Long hIncrBy(String key, String field, long increment) {
        return hashOperations.increment(key, field, increment);
    }

    @Override
    public Double hIncrByDouble(String key, String field, Double increment) {
        return hashOperations.increment(key, field, increment);
    }

    @Override
    public Set<String> hKeys(String key) {
        return hashOperations.keys(key);
    }

    @Override
    public Long hSize(String key) {
        return hashOperations.size(key);
    }

    @Override
    public List<Object> hValues(String key) {
        return hashOperations.values(key);
    }

    @Override
    public Cursor<Map.Entry<String, Object>> hScan(String key, ScanOptions options) {
        return hashOperations.scan(key, options);
    }

    @Override
    public Object lIndex(String key, long index) {
        return listOperations.index(key, index);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    @Override
    public Long lLeftPush(String key, Object value) {
        return listOperations.leftPush(key, value);
    }

    @Override
    public Long lLeftPushAll(String key, Object... value) {
        return listOperations.leftPushAll(key, value);
    }

    @Override
    public Long lLeftPushAll(String key, Collection<Object> value) {
        return listOperations.leftPushAll(key, value);
    }

    @Override
    public Long lLeftPushIfPresent(String key, Object value) {
        return listOperations.leftPushIfPresent(key, value);
    }

    @Override
    public Long lLeftPush(String key, Object pivot, Object value) {
        return listOperations.leftPush(key, pivot, value);
    }

    @Override
    public Long lRightPush(String key, Object value) {
        return listOperations.rightPush(key, value);
    }

    @Override
    public Long lRightPushAll(String key, Object... value) {
        return listOperations.rightPushAll(key, value);
    }

    @Override
    public Long lRightPushAll(String key, Collection<Object> value) {
        return listOperations.rightPushAll(key, value);
    }

    @Override
    public Long lRightPushIfPresent(String key, Object value) {
        return listOperations.rightPushIfPresent(key, value);
    }

    @Override
    public Long lRightPush(String key, Object pivot, Object value) {
        return listOperations.rightPush(key, pivot, value);
    }

    @Override
    public void lSet(String key, long index, String value) {
        listOperations.set(key, index, value);
    }

    @Override
    public Object lLeftPop(String key) {
        return listOperations.leftPop(key);
    }

    @Override
    public Object lLeftPop(String key, long timeout, TimeUnit timeUnit) {
        return listOperations.leftPop(key, timeout, timeUnit);
    }

    @Override
    public Object lRightPop(String key) {
        return listOperations.rightPop(key);
    }

    @Override
    public Object lRightPop(String key, long timeout, TimeUnit timeUnit) {
        return listOperations.rightPop(key, timeout, timeUnit);
    }

    @Override
    public Object lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return listOperations.rightPopAndLeftPush(sourceKey, destinationKey);
    }

    @Override
    public Object lRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit timeUnit) {
        return listOperations.rightPopAndLeftPush(sourceKey, destinationKey, timeout, timeUnit);
    }

    @Override
    public Long lRemove(String key, long index, String value) {
        return listOperations.remove(key, index, value);
    }

    @Override
    public void lTrim(String key, long start, long end) {
        listOperations.trim(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return listOperations.size(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return setOperations.add(key, values);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return setOperations.remove(key, values);
    }

    @Override
    public Object sPop(String key) {
        return setOperations.pop(key);
    }

    @Override
    public Boolean sMove(String key, String value, String destKey) {
        return setOperations.move(key, value, destKey);
    }

    @Override
    public Long sSize(String key) {
        return setOperations.size(key);
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    @Override
    public Set<Object> sIntersect(String key, String otherKey) {
        return setOperations.intersect(key, otherKey);
    }

    @Override
    public Set<Object> sIntersect(String key, Collection<String> otherKeys) {
        return setOperations.intersect(key, otherKeys);
    }

    @Override
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return setOperations.intersectAndStore(key, otherKey, destKey);
    }

    @Override
    public Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return setOperations.intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<Object> sUnion(String key, String otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    @Override
    public Set<Object> sUnion(String key, Collection<String> otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    @Override
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return setOperations.unionAndStore(key, otherKey, destKey);
    }

    @Override
    public Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return setOperations.unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<Object> sDifference(String key, String otherKey) {
        return setOperations.difference(key, otherKey);
    }

    @Override
    public Set<Object> sDifference(String key, Collection<String> otherKeys) {
        return setOperations.difference(key, otherKeys);
    }

    @Override
    public Long sDifference(String key, String otherKey, String destKey) {
        return setOperations.differenceAndStore(key, otherKey, destKey);
    }

    @Override
    public Long sDifference(String key, Collection<String> otherKeys, String destKey) {
        return setOperations.differenceAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<Object> sMembers(String key) {
        return setOperations.members(key);
    }

    @Override
    public Object sRandomMember(String key) {
        return setOperations.randomMember(key);
    }

    @Override
    public List<Object> sRandomMembers(String key, long count) {
        return setOperations.randomMembers(key, count);
    }

    @Override
    public Set<Object> sDistinctRandomMembers(String key, long count) {
        return setOperations.distinctRandomMembers(key, count);
    }

    @Override
    public Cursor<Object> sScan(String key, ScanOptions options) {
        return setOperations.scan(key, options);
    }

    @Override
    public Boolean zAdd(String key, Object value, double score) {
        return zSetOperations.add(key, value, score);
    }

    @Override
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> values) {
        return zSetOperations.add(key, values);
    }

    @Override
    public Long zRemove(String key, Object... values) {
        return zSetOperations.remove(key, values);
    }

    @Override
    public Double zIncrementScore(String key, Object value, double delta) {
        return zSetOperations.incrementScore(key, value, delta);
    }

    @Override
    public Long zRank(String key, Object value) {
        return zSetOperations.rank(key, value);
    }

    @Override
    public Long zReverseRank(String key, Object value) {
        return zSetOperations.reverseRank(key, value);
    }

    @Override
    public Set<Object> zRange(String key, long start, long end) {
        return zSetOperations.range(key, start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key, long start, long end) {
        return zSetOperations.rangeWithScores(key, start, end);
    }

    @Override
    public Set<Object> zRangeByScore(String key, double min, double max) {
        return zSetOperations.rangeByScore(key, min, max);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key, double min, double max) {
        return zSetOperations.rangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return zSetOperations.rangeByScoreWithScores(key, min, max, start, end);
    }

    @Override
    public Set<Object> zReverseRange(String key, long start, long end) {
        return zSetOperations.reverseRange(key, start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScores(String key, long start, long end) {
        return zSetOperations.reverseRangeWithScores(key, start, end);
    }

    @Override
    public Set<Object> zReverseRangeByScore(String key, double min, double max) {
        return zSetOperations.reverseRangeByScore(key, min, max);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return zSetOperations.reverseRangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Object> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return zSetOperations.reverseRangeByScore(key, min, max, start, end);
    }

    @Override
    public Long zCount(String key, double min, double max) {
        return zSetOperations.count(key, min, max);
    }

    @Override
    public Long zSize(String key) {
        return zSetOperations.size(key);
    }

    @Override
    public Long zCard(String key) {
        return zSetOperations.zCard(key);
    }

    @Override
    public Double zScore(String key, Object value) {
        return zSetOperations.score(key, value);
    }

    @Override
    public Long zRemoveRange(String key, long start, long end) {
        return zSetOperations.removeRange(key, start, end);
    }

    @Override
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return zSetOperations.removeRangeByScore(key, min, max);
    }

    @Override
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return zSetOperations.unionAndStore(key, otherKey, destKey);
    }

    @Override
    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return zSetOperations.unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return zSetOperations.intersectAndStore(key, otherKey, destKey);
    }

    @Override
    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return zSetOperations.intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Cursor<ZSetOperations.TypedTuple<Object>> zScan(String key, ScanOptions options) {
        return zSetOperations.scan(key, options);
    }
}

