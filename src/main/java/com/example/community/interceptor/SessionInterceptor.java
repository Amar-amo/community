package com.example.community.interceptor;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amar_amo
 * @date 2019/12/8 14:23
 */

@Component
public class SessionInterceptor implements HandlerInterceptor
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("调用拦截器");
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            System.out.println("1");
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null)
                    {
                        System.out.println("验证成功");
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
