package controllers.ajax;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import services.CityService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class AppendNewCity  {
    private static Logger logger = Logger.getLogger(AppendNewRegion.class);
	private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
    @RequestMapping(value = "/admin/cities/append", method = RequestMethod.POST)
    public void doPost(@RequestParam("city-name") String pCityName,
                          @RequestParam("region-number") String pRegionNumber,
                          HttpServletResponse resp){
        
        Map<String, String> output = cityService.insertNewRecord(pCityName, Integer.valueOf(pRegionNumber));
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
