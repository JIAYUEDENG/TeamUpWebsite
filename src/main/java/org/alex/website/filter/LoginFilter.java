package org.alex.website.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.Result;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * check if user is already logged in
 */

@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        String requestURI = httpServletRequest.getRequestURI();

        String[] urls = new String[]{
                "/user/login"
        };
        boolean check = check(urls, requestURI);

        if (!check){
            log.info("This request {} doesn't need to process",requestURI);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (httpServletRequest.getSession().getAttribute("user") != null){
            log.info("User logged in，user id is：{}",httpServletRequest.getSession().getAttribute("user"));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        log.info("User is not logged in");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error("Not logged in yet")));
        return;

    }

    private boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) return true;
        }
        return false;
    }
}
