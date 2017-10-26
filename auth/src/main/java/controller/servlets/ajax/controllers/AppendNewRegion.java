package controller.servlets.ajax.controllers;

import org.json.JSONObject;
import services.ServiceInsertNewRegion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/regions/append")
public class AppendNewRegion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region_name = req.getParameter("region-name");
        String region_number = req.getParameter("region-number");
        ServiceInsertNewRegion serviceInsertNewRegion = new ServiceInsertNewRegion();
        Map<String, String> output = serviceInsertNewRegion.insertNewRecord(region_name, Integer.valueOf(region_number));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(output));
        out.flush();
    }
}
