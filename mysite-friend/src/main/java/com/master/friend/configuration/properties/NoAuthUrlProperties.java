package com.master.friend.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;

/**
 * @author ：杨过
 * @date ：Created in 2020/2/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 拦截器配置项
 **/
@Data
@ConfigurationProperties(prefix = "member.auth")
public class NoAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}
