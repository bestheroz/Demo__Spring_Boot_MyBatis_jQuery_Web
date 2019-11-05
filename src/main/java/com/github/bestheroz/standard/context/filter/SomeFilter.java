package com.github.bestheroz.standard.context.filter;

import com.github.bestheroz.standard.override.RereadableRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SomeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws ServletException, IOException {
        final RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper(httpServletRequest);
        filterChain.doFilter(rereadableRequestWrapper, httpServletResponse);
    }
}
