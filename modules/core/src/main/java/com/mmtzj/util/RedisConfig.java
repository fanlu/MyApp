package com.mmtzj.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-27
 * Time: 上午11:55
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@PropertySource("classpath:/jdbc.properties")
public class RedisConfig {

    @Value("${redis.status}")
    private String status;

    public String getStatus() {
        return status;
    }
}
