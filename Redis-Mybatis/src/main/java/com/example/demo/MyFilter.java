package com.example.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest srequest = (HttpServletRequest) request;
        System.out.println("this is MyFilter,url :"+srequest.getRequestURI());
        chain.doFilter(request, response);
    }


    public void destory() {
        
    }
    

    public void init() {
        
    }
}
