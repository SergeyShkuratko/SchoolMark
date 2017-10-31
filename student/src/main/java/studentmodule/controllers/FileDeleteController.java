package studentmodule.controllers;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studentmodule.service.WorkService;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileDeleteController {

    private static final Logger logger = Logger.getLogger(FileDeleteController.class);
    private final WorkService workService;
    private final ServletContext servletContext;

    @Autowired
    public FileDeleteController(WorkService workService, ServletContext servletContext) {
        this.workService = workService;
        this.servletContext = servletContext;
    }


    @PostMapping("/workload/delete")
    public String handleFileDelete(
            @RequestParam("fileId") String fileId,
            ModelMap model) {
        Map<String, String> output = new HashMap<>();
        try {
            String fname =
                    workService.getStudentFileUrlByIdAndDeleteRecord(Integer.valueOf(fileId)).getFile();
            String path = servletContext.getRealPath(fname);
            File file = new File(path);
            if (!file.delete()) {
                throw new IOException("Не удалось удалить файл!");
            }
            output.put("result", "OK");
            output.put("message", "Файл успешно удален!");
        } catch (Exception e) {
            output.put("result", "error");
            output.put("message", "Во время удаления файла произошла ошибка! " + e.getMessage());
            logger.error("Во время удаления файла произошла ошибка! " + e.getMessage());
        }
        model.addAttribute("json_text", new JSONObject(output));
        return "WEB-INF/pages/student/_json";
    }

}
