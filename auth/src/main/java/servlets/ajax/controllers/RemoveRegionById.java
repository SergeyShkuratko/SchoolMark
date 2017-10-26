package servlets.ajax.controllers;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ServiceRemoveRegion;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.log4j.Logger;

@Controller
public class RemoveRegionById {
    private static Logger logger = Logger.getLogger(RemoveRegionById.class);

    @RequestMapping(value = "/admin/regions/remove", method = RequestMethod.POST)
    public void doPost(@RequestParam("id") String pRegionId, HttpServletResponse resp){
        ServiceRemoveRegion serviceRemoveRegion = new ServiceRemoveRegion();
        Map<String, String> output = serviceRemoveRegion.removeRecordById(Integer.valueOf(pRegionId));
        resp.setContentType("application/json; charset=UTF-8");
        try{
            PrintWriter out = resp.getWriter();
            out.print(new JSONObject(output));
            out.flush();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
