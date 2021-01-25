package com.dao.homework.web.filters;

import com.dao.homework.lib.Injector;
import com.dao.homework.service.DriverService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    public static final Set<String> test = new HashSet<>();
    private static final String DRIVER_ID = "drivers_id";
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void init(FilterConfig filterConfig) {
        test.add("/login");
        test.add("/drivers/add");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getServletPath();
        if (test.contains(url)) {
            filterChain.doFilter(req, resp);
            return;
        }

        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (driverId == null || driverService.get(driverId) == null) {
            resp.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
