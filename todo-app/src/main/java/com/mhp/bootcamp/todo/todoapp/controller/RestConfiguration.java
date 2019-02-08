package com.mhp.bootcamp.todo.todoapp.controller;


import com.sun.management.jmx.TraceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configuration
public class RestConfiguration {

    @Bean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    protected static class TraceFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    String uuid = HttpServletRequest.class.cast(request).getHeader("X-Trace-Id");

            if (null == uuid || uuid.isEmpty()) {
                    uuid = UUID.randomUUID().toString();
                    }

                    HttpServletResponse.class.cast(response).setHeader("X-Trace-Id", uuid);
        chain.doFilter(request, response);

        }
    }

}
