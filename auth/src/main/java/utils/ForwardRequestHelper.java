package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static utils.Settings.ERROR_ATTR;
import static utils.Settings.ERROR_JSP;

public class ForwardRequestHelper {

    public static RequestDispatcher getErrorDispatcher(HttpServletRequest request, String error) {
        request.setAttribute(ERROR_ATTR, error);
        return request.getRequestDispatcher(ERROR_JSP);
    }
}
