package com.duyi.examonline.commo.interceptors;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("reuqest"+request.getRequestURI());
        Object obj = request.getSession().getAttribute("loginTeacher");
        if(obj == null){
//            表示还没有登录，请求不能继续，需要返回登录页面
            request.getRequestDispatcher("/common/timeout.html").forward(request,response);

            return false;
        }
//        有登录信息，请求可以继续进行
        return true;
    }
}
