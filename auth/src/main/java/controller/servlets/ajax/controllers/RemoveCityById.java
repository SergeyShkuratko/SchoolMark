package controller.servlets.ajax.controllers;

import org.json.JSONObject;
import services.ServiceRemoveCity;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String city_id = req.getParameter("id");
        ServiceRemoveCity serviceRemoveCity = new ServiceRemoveCity();
        Map<String, String> output = serviceRemoveCity.removeRecordById(Integer.valueOf(city_id));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(output));
        out.flush();
    }
}
