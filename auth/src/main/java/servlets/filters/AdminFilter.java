package servlets.filters;

import classes.CommonSettings;
import classes.Role;
import sun.nio.cs.ext.IBM037;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.AUTH_ROLE_ATTRIBUTE;
import static classes.CommonSettings.AUTH_USER_ATTRIBUTE;
import static utils.Settings.AUTH_PAGE;
import static utils.Settings.DEPLOY_PATH;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isAuth = false;
        Object user = ((HttpServletRequest)request).getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        Role role = (Role) ((HttpServletRequest)request).getSession().getAttribute(AUTH_ROLE_ATTRIBUTE);
        if (user != null && role != null) {
            int userId = (int) user;
            if (role == Role.admin) {
                isAuth = true;
                chain.doFilter(request, response);
            }
        }
        if (!isAuth) {
            ((HttpServletResponse) response).sendRedirect(DEPLOY_PATH + AUTH_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
}
