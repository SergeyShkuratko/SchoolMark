package studentmodule.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
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
import studentmodule.service.RandomString;
import studentmodule.service.WorkService;
import studentmodule.storage.StorageFileNotFoundException;
import studentmodule.storage.StorageProperties;
import studentmodule.storage.StorageService;
import studentmodule.utils.FileExtension;


@ControllerAdvice
@Controller
public class FileUploadController {

    private static final Logger logger = Logger.getLogger(FileUploadController.class);

    private final StorageService storageService;
    private final StorageProperties storageProperties;
    private final RandomString randomString;
    private final FileExtension fileExtension;
    private final WorkService workService;

    @Autowired
    public FileUploadController(
            StorageService storageService,
            StorageProperties storageProperties,
            RandomString randomString,
            FileExtension fileExtension,
            WorkService workService) {
        this.storageService = storageService;
        this.storageProperties = storageProperties;
        this.randomString = randomString;
        this.fileExtension = fileExtension;
        this.workService = workService;
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
    }

    @PostMapping("/workload/uploadfile")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("workId") String workId,
            ModelMap model) {
        Map<String, String> output = new HashMap<>();
        try {
            storageService.store(file);
            String randomFilename = this.randomString.getSaltString() +
                    fileExtension.getFileExtention(file.getOriginalFilename());
            Path source = storageService.load(file.getOriginalFilename());
            Files.move(source, source.resolveSibling(randomFilename));
            String fileUri = storageProperties.getRelativeLocation() +
                             File.separator +
                             randomFilename;
            int fileId = workService.addStudentFileToBD(Integer.valueOf(workId), fileUri);
            output.put("result", "OK");
            output.put("path",
                    storageProperties.getOffsetUri() +
                    storageProperties.getRelativeLocation() +
                    File.separator +
                    randomFilename);
            output.put("message", "Файл успешно сохранен!");
            output.put("workId", workId);
            output.put("fileId", String.valueOf(fileId));
        } catch (Exception e) {
            output.put("result", "error");
            output.put("message", "Во время сохранения файла произошла ошибка! " + e.getMessage());
            logger.error("Во время сохранения файла произошла ошибка! " + e.getMessage());
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
