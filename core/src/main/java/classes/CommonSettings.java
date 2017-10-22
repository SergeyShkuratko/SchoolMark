package classes;

import exceptions.CityDAOException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommonSettings {
    public static final String AUTH_USER_ATTRIBUTE = "userId";
    public static final String AUTH_ROLE_ATTRIBUTE = "userRoleId";

    public static final String TEACHER_CABINET = "/mainmenu.jsp";
    public static final String STUDENT_CABINET = "/mainmenu.jsp";
    public static final String DIRECTOR_CABINET = "/mainmenu.jsp";
    public static final String ADMIN_CABINET = "/admin/cabinet";

    public static final String AUTH_URL = "/auth";


}
