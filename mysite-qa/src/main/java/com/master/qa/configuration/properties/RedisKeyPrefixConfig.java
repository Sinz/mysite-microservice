package com.master.qa.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis.key")
@Data
public class RedisKeyPrefixConfig {

    private Prefix prefix;

    private Expire expire;

    @Data
    public static class Prefix {
        private String otpCode;

    }

    @Data
    public static class Expire {

        private Long otpCode;

    }

}
