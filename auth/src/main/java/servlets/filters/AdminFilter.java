package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.AUTH_ATTRIBUTE;
import static utils.Settings.AUTH_PAGE;
import static utils.Settings.DEPLOY_PATH;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isAuth = false;
        Object authParam = ((HttpServletRequest)request).getSession().getAttribute(AUTH_ATTRIBUTE);
        if (authParam != null) {
            isAuth = (Boolean) authParam;
            if (isAuth) {
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
