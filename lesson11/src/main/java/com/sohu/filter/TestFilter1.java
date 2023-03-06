package com.sohu.filter;

import com.oppo.tool.ModifyHeaderRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        request.getHeaderNames(); // org.apache.catalina.connector.RequestFacade
        // "/lesson11/v11/getAllEmployees"
        System.out.println(request.getRequestURI());
        ModifyHeaderRequestWrapper headerMapRequestWrapper = new ModifyHeaderRequestWrapper(request);
        headerMapRequestWrapper.addHeader("location-func1", "TestFilter1");
        headerMapRequestWrapper.getHeaderNames();
        filterChain.doFilter(headerMapRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
