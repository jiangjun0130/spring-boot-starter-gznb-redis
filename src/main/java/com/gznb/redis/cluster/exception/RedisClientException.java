package com.gznb.redis.cluster.exception;

import com.gznb.redis.cluster.client.RedisClusterTemplate;

/**
 * Base class for exceptions thrown by {@link RedisClusterTemplate} whenever it encounters
 *
 * @author jiangjun
 * @create 2017/9/15
 */
public class RedisClientException extends RuntimeException{

    public RedisClientException(String message) {
        super(message);
    }

    public RedisClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
