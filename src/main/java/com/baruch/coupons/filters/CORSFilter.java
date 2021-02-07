package com.baruch.coupons.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(1)
@Component
public class CORSFilter implements Filter {

    private final String CLIENT_DOMAIN = "http://localhost:3000";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  throws IOException, ServletException {
        HttpServletRequest  httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        httpResponse.addHeader("Access-Control-Allow-Origin", CLIENT_DOMAIN);
        httpResponse.addHeader("Access-Control-Allow-Headers", "*");
        httpResponse.addHeader("Access-Control-Allow-Headers",
                "Authorization, Origin, Accept, x-auth-token, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        if(httpRequest.getMethod().equals("OPTIONS")){
           
            return;
        }

        chain.doFilter(request, response);

    }


}
