package servlets.ajax.controllers;

import org.json.JSONObject;
import services.ServiceInsertNewCity;
import services.ServiceInsertNewRegion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/cities/append")
public class AppendNewCity extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String city_name = req.getParameter("city-name");
        String region_number = req.getParameter("region-number");
        ServiceInsertNewCity serviceInsertNewCity = new ServiceInsertNewCity();
        Map<String, String> output = serviceInsertNewCity.insertNewRecord(city_name, Integer.valueOf(region_number));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(output));
        out.flush();
    }
}
