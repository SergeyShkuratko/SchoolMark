package servlets.ajax.controllers;

import org.json.JSONObject;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.CityService;
import services.impl.CityServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/cities/remove")
public class RemoveCityById extends HttpServlet {
    private CityService cityService;

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cityId = req.getParameter("id");
        Map<String, String> output = cityService.removeRecordById(Integer.valueOf(cityId));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(output));
        out.flush();
    }
}
