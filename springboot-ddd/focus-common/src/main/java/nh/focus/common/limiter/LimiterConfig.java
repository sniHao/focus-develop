package nh.focus.common.limiter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @Description:限流配置类
 * @Author:xph
 * @Date: 2024/10/20 15:20
 */
@Configuration
public class LimiterConfig {
    @Bean
    public DefaultRedisScript<Long> limiterScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText("""
                local key = KEYS[1]
                local count = tonumber(ARGV[1])
                local time = tonumber(ARGV[2])
                local current = redis.call('get', key)
                if current and tonumber(current) > count then
                    return tonumber(current)
                end
                current = redis.call('incr', key)
                if tonumber(current) == 1 then
                    redis.call('expire', key, time)
                end
                return tonumber(current)
                                """);
        redisScript.setResultType(Long.class);
        return redisScript;
    }

}
