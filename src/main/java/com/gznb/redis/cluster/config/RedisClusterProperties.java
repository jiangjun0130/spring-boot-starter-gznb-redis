package com.gznb.redis.cluster.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * redis cluster配置类
 *
 * @author jiangjun
 * @create 2017/9/14
 */
@ConfigurationProperties(prefix = "gznb.redis.cluster")
public class RedisClusterProperties {

    private List<String> nodes;

    private Integer connectionTimeout;

    /**
     * 返回值的超时时间
     */
    private Integer soTimeout;

    private Integer maxAttempts;

    @Override
    public String toString() {
        return "RedisClusterProperties{" +
                "nodes=" + nodes +
                ", connectionTimeout=" + connectionTimeout +
                ", soTimeout=" + soTimeout +
                ", maxAttempts=" + maxAttempts +
                '}';
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
