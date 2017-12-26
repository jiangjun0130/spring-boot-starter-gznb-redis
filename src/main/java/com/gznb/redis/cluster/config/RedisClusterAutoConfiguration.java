package com.gznb.redis.cluster.config;

import com.gznb.redis.cluster.client.RedisClusterTemplate;
import com.gznb.redis.cluster.utils.IntrospectorUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * redis cluster自动装配
 *
 * @author jiangjun
 * @create 2017/9/14
 */
@Configuration
@ConditionalOnClass(JedisCluster.class)
//@ConditionalOnProperty(name = {"gznb.redis.cluster.nodes","gznb.redis.pool.maxTotal"},matchIfMissing = false)
@EnableConfigurationProperties({RedisClusterProperties.class, RedisPoolProperties.class})
public class RedisClusterAutoConfiguration {

    @Bean
    public JedisCluster jedisCluster(RedisClusterProperties clusterProperties, RedisPoolProperties poolProperties) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        // 初始化clusterProperties
        if (clusterProperties == null || IntrospectorUtils.entityIsNull(clusterProperties)) {
            throw new RuntimeException("init jedis cluster failed! redis cluster nodes is null");
        }
        Set<HostAndPort> nodes = new HashSet<>(clusterProperties.getNodes().size());
        clusterProperties.getNodes().forEach(node -> {
            String[] ipPortPair = node.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        });
        // 初始化poolProperties
        if (poolProperties == null || IntrospectorUtils.entityIsNull(poolProperties)) {
            throw new RuntimeException("init jedis pool failed! redis pool config is null");
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(poolProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(poolProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(poolProperties.getMaxWaitMillis());
        jedisPoolConfig.setTestOnBorrow(poolProperties.getTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(poolProperties.getTestOnReturn());
        return new JedisCluster(nodes, clusterProperties.getConnectionTimeout(), clusterProperties.getSoTimeout(),
                clusterProperties.getMaxAttempts(), jedisPoolConfig);
    }

    @Bean
    public RedisClusterTemplate redisClusterTemplate(RedisClusterProperties clusterProperties, RedisPoolProperties poolProperties) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        JedisCluster jedisCluster = jedisCluster(clusterProperties,poolProperties);
        return new RedisClusterTemplate(jedisCluster);
    }
}
