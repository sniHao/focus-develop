package nh.focus.common.limiter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nh.focus.common.constant.ResultCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Description:限流
 * @Author:xph
 * @Date: 2024/10/20 15:22
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAspect {

    private final RedisTemplate redisTemplate;
    private final RedisScript<Long> limitScript;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        String combineKey = getCombineKey(rateLimiter, point);
        List<Object> keys = Collections.singletonList(combineKey);
        Long number = (Long) redisTemplate.execute(limitScript, keys, count, time);
        if (number.intValue() > count) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, ResultCode.SYSTEM_OFTEN.tips());
        }
    }

    /**
     * @return String
     * @Description:获取IP或者其他限流方式
     * @author: xph
     * @param[1] rateLimiter
     * @param[2] point
     * @Date: 2024/10/20 15:45
     */
    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuilder stringBuilder = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType() == LimiterType.IP) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String ip = getIp(attributes.getRequest());
            if (!Objects.isNull(ip)) stringBuilder.append(ip);
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuilder.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuilder.toString();
    }

    /**
     * 获取IP
     *
     * @param request 请求
     * @return IP
     */
    public String getIp(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                String localIp = "127.0.0.1";
                if (ipAddress.equals(localIp)) return localIp;
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
