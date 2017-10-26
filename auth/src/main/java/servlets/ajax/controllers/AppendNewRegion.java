package servlets.ajax.controllers;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ServiceInsertNewRegion;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class AppendNewRegion {
    private static Logger logger = Logger.getLogger(AppendNewRegion.class);

    @RequestMapping(value = "/admin/regions/append", method = RequestMethod.POST)
    public void doPost(@RequestParam("region-name") String pRegionName,
                          @RequestParam("region-number") String pRegionNumber,
                          HttpServletResponse resp) {
        ServiceInsertNewRegion serviceInsertNewRegion = new ServiceInsertNewRegion();
        Map<String, String> output = serviceInsertNewRegion.insertNewRecord(pRegionName, Integer.valueOf(pRegionNumber));
        resp.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter out = resp.getWriter();
            out.print(new JSONObject(output));
            out.flush();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
