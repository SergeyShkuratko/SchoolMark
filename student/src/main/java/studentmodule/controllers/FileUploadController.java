package student.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import student.storage.StorageFileNotFoundException;
import student.storage.StorageService;


@ControllerAdvice
@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        //return "student/_json";
    }

    @PostMapping("/workload/uploadfile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, ModelMap model) {
        Map<String, String> output = new HashMap<>();
        try {
            storageService.store(file);
            output.put("result", "OK");
            output.put("path",
                    MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                            "serveFile", storageService.load(file.getOriginalFilename()).toString()).build().toString());
            output.put("message", "Файл успешно сохранен!");
        } catch (Exception e) {
            output.put("result", "error");
            output.put("message", "Во время сохранения файла произошла ошибка! " + e.getMessage());
        }
        model.addAttribute("json_text", new JSONObject(output));
        return "student/_json";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxUploadFileSize(MaxUploadSizeExceededException exc) {
        Map<String, String> output = new HashMap<>();
        output.put("result", "error");
        output.put("message", "Размер файла превышает максимально допустимый настройками сервера!" + exc.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject("json_text", new JSONObject(output));
        model.setViewName("student/_json");
        return model;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ModelAndView handleStorageFileNotFound(StorageFileNotFoundException exc) {
        Map<String, String> output = new HashMap<>();
        output.put("result", "error");
        output.put("message", "Файл не найден! " + exc.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject("json_text", new JSONObject(output));
        model.setViewName("student/_json");
        return model;
    }

}
