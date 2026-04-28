package org.example.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.util.JwtUtil;
import org.example.util.ThreadLocalUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        try{
            //去除Bearer前缀（如果存在）
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            if(stringRedisTemplate.opsForValue().get(token)==null){
                throw new RuntimeException("jwt令牌失效");
            }


            //验证JWT
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
            //将用户信息存储在ThreadLocal中
            ThreadLocalUtil.set(stringObjectMap);

            return true;


        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();}
}
