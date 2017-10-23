package servlets.ajax.controllers;

import org.json.JSONObject;
import services.ServiceInsertNewRegion;
import services.ServiceRemoveRegion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/admin/regions/remove" })
public class RemoveRegionById extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region_id = req.getParameter("region_id");

        ServiceRemoveRegion serviceRemoveRegion = new ServiceRemoveRegion();
        Map<String, String> output = serviceRemoveRegion.removeRecordById(Integer.valueOf(region_id));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject(output));
        out.flush();
    }
}
