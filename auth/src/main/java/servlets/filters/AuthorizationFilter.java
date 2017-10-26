package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.AUTH_ROLE_ATTRIBUTE;
import static classes.CommonSettings.AUTH_USER_ATTRIBUTE;
import static utils.Settings.AUTH_PAGE;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.equals("/") || path.equals("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        Object userAttribute = httpRequest.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        Object roleAttribute = httpRequest.getSession().getAttribute(AUTH_ROLE_ATTRIBUTE);

        if (userAttribute != null && roleAttribute != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + AUTH_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
}
