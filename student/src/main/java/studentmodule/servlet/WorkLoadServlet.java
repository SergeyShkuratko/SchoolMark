package studentmodule.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import studentmodule.exception.DAOStudentWorkException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import studentmodule.service.WorkService;
import javax.servlet.ServletContext;

import static studentmodule.constants.ControllersConstants.RESPONSE_ERROR_MESSAGE;

@Controller
public class WorkLoadServlet {

    private static final Logger logger = Logger.getLogger(WorkLoadServlet.class);
    private final WorkService workService;
    private final ServletContext servletContext;

    @Autowired
    public WorkLoadServlet(WorkService workService, ServletContext servletContext) {
        this.workService = workService;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/workload", method = RequestMethod.GET)
    public String showWorkload(
            @RequestParam(name = "id") String inpId, ModelMap model
    ) {
        try {
            int id = Integer.valueOf(inpId);
            int variant_id = workService.getWorkById(id).getVariantId();
            model.addAttribute("work", workService.getWorkById(id));
            model.addAttribute("questions",
                    workService.getQuestionListByVariantId(variant_id));
            model.addAttribute("files", workService.getStudentFilesByWorkId(id));
            model.addAttribute("teacher_files",
                    workService.getVerificationFilesByVerificationId(id));
        } catch (Exception e) {
            logger.error(RESPONSE_ERROR_MESSAGE + e.getMessage());
            model.addAttribute("whatHappened", RESPONSE_ERROR_MESSAGE + e.getMessage());
            return "errpages/500-error";
        }
        return "student/work_load";
    }

//    @RequestMapping(value = "/workload/delphoto", method = RequestMethod.POST)
//    public String deletePhoto(
//            @RequestParam(name = "referer") String referer,
//            @RequestParam(name = "filename") String filename,
//            @RequestParam(name = "fileId") String fileId,
//            ModelMap model
//    ) {
//        try {
//            return deletePhoto(referer, filename, Integer.valueOf(fileId), model);
//        } catch(NumberFormatException nfe) {
//            logger.error(errMessage + nfe.getMessage());
//            model.addAttribute("whatHappened", errMessage + nfe.getMessage());
//            return "errpages/500-error";
//        }
//    }
//
//    private String deletePhoto(String referer, String fileName, Integer fileId, ModelMap model) {
//        String errorMessage = "Во время удаления файла произошла непредвиденная ошибка. ";
//        try {
//            if (workService.delStudentFile(fileId)) {
//                String path = servletContext.getRealPath(fileName);
//                File file = new File(path);
//                if (!file.delete()) {
//                    logger.error(errorMessage);
//                    throw new DAOStudentWorkException("Не удалось удалить файл!");
//                }
//            }
//        } catch (DAOStudentWorkException e) {
//            model.addAttribute("whatHappened", errorMessage + e.getMessage());
//            return "errpages/500-error";
//        }
//        return "redirect:" + referer;
//    }
//
//
//    private ModelAndView loadPhoto(String referer) {
//        //проверяем является ли полученный запрос multipart/form-data
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if (!isMultipart) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        // Создаём класс фабрику
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        // Максимальный буфера данных в байтах,
//        // при его привышении данные начнут записываться на диск во временную директорию
//        // устанавливаем один мегабайт
//        factory.setSizeThreshold(1024 * 1024);
//
//        // устанавливаем временную директорию
//        File tempDir = (File) servletContext.getAttribute("javax.studentmodule.servlet.context.tempdir");
//        factory.setRepository(tempDir);
//
//        //Создаём сам загрузчик
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        //максимальный размер данных который разрешено загружать в байтах
//        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
//        upload.setSizeMax(1024 * 1024 * 10);
//        try {
//            List items = upload.parseRequest(request);
//            Iterator iter = items.iterator();
//            int work_id = 0;
//            while (iter.hasNext()) {
//                FileItem item = (FileItem) iter.next();
//                if (item.isFormField()) {
//                    String fildName = item.getFieldName();
//                    if ("workId".equals(fildName)) {
//                        work_id = Integer.decode(item.getString());
//                    }
//                    //если принимаемая часть данных является полем формы
//                    processFormField(item);
//                } else {
//                    //в противном случае рассматриваем как файл
//
//                    WorkService.addStudentFileToBD(work_id, processUploadedFile(item));
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.sendRedirect(referer);
//        }
//        return new ModelAndView("redirect:" + referer);
//    }
//
//    /**
//     * Сохраняет файл на сервере, в папке upload.
//     * Сама папка должна быть уже создана.
//     *
//     * @param item
//     * @throws Exception
//     */
//    private String processUploadedFile(FileItem item) throws Exception {
//        File uploadetFile = null;
//        //выбираем файлу имя пока не найдём свободное
//        do {
//            String path = servletContext.getRealPath("/img/" + random.nextInt() + item.getName());
//            uploadetFile = new File(path);
//        } while (uploadetFile.exists());
//
//        //создаём файл
//        uploadetFile.createNewFile();
//        //записываем в него данные
//        item.write(uploadetFile);
//        return "img/" + uploadetFile.getName();
//    }

//    @RequestMapping(value = "/workload", method = RequestMethod.POST)
//    protected void sendCommand(
//            @RequestParam(name = "command") String command,
//            @RequestParam(name = "referer", required = false) String referer,
//            @RequestParam(name = "file", required = false) String file,
//            @RequestParam(name = "file_id", required = false) String fileId,
//            @RequestParam(name = "workId", required = false) String workId,
//            @RequestParam(name = "work_id", required = false) String work_Id
//            ) {
//        if (command == null) command = "";
//        switch (command) {
//            case "send_work":
//                sendWork(Integer.valueOf(workId));
//                break;
//            case "recheck":
//                sendToRecheck(Integer.valueOf(work_Id));
//                break;
//            case "del_photo":
//                //TODO need catch NumberFormatException
//                delPhoto(referer, file, Integer.valueOf(fileId));
//                break;
//            default:
//                loadPhoto(referer);
//        }
//    }

    /**
     * Выводит на консоль имя параметра и значение
     *
     * @param item
     */
//    private void processFormField(FileItem item) {
//        System.out.println(item.getFieldName() + "=" + item.getString());
//    }
}
