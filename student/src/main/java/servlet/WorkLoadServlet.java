package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import service.WorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


@WebServlet("/workload")
public class WorkLoadServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(WorkLoadServlet.class);
    private Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.decode(req.getParameter("id"));
            req.setAttribute("work", WorkService.getWorkById(id - 1));
            req.setAttribute("questions", WorkService.getQuestionListByWorkId(id));
            req.setAttribute("files", WorkService.getStudentFilesByWorkId(id));
            req.setAttribute("teacher_files", WorkService.getTeacherFilesByWorkId(id));
            req.getRequestDispatcher("/work_load.jsp").forward(req, resp);
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            ((HttpServletResponse) resp).sendRedirect("/student");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) command = "";
        switch (command) {
            case "send_work":
                sendWork(request, response);
                break;
            case "recheck":
                sendToRecheck(request, response);
                break;
            case "del_photo":
                delPhoto(request, response);
                break;
            default:
                loadPhoto(request, response);
        }
    }

    private void delPhoto(HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("referer");
        String fileName = request.getParameter("file");
        WorkService.fileList.remove(fileName);
        String path = getServletContext().getRealPath(fileName);
        File file = new File(path);
        if (!file.delete()) logger.error("Файл изображения не удален.");
        try {
            response.sendRedirect(referer);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendWork(HttpServletRequest request, HttpServletResponse response) {
        String context = getServletContext().getContextPath();
        try {
            //TODO тут что то должно произойти со статусом и дальше отправить на назначение
            response.sendRedirect(context + "/testlist");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendToRecheck(HttpServletRequest request, HttpServletResponse response) {
        String context = getServletContext().getContextPath();
        try {
            //TODO тут что то должно произойти со статусом и дальше отправить на перепроверку
            response.sendRedirect(context + "/testlist");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void loadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //проверяем является ли полученный запрос multipart/form-data
        String referer = request.getHeader("referer");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создаём класс фабрику
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы
                    processFormField(item);
                } else {
                    //в противном случае рассматриваем как файл
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect(referer);
        }
        response.sendRedirect(referer);
    }

    /**
     * Сохраняет файл на сервере, в папке upload.
     * Сама папка должна быть уже создана.
     *
     * @param item
     * @throws Exception
     */
    private void processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        //выбираем файлу имя пока не найдём свободное
        do {
            String path = getServletContext().getRealPath("/img/" + random.nextInt() + item.getName());
            uploadetFile = new File(path);
        } while (uploadetFile.exists());

        //создаём файл
        uploadetFile.createNewFile();
        //записываем в него данные
        item.write(uploadetFile);
        WorkService.fileList.add("img/" + uploadetFile.getName());
    }

    /**
     * Выводит на консоль имя параметра и значение
     *
     * @param item
     */
    private void processFormField(FileItem item) {
        System.out.println(item.getFieldName() + "=" + item.getString());
    }
}
