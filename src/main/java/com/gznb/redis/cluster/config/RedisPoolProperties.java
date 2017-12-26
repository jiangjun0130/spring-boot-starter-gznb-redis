package com.gznb.redis.cluster.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis连接池配置
 *
 * @author jiangjun
 * @create 2017/9/14
 */
@ConfigurationProperties(prefix = "gznb.redis.pool")
public class RedisPoolProperties {

    private Integer maxTotal;

    private Integer maxIdle;

    private Long maxWaitMillis;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    @Override
    public String toString() {
        return "RedisPoolProperties{" +
                "maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", maxWaitMillis=" + maxWaitMillis +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                '}';
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
