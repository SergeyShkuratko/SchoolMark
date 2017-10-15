package servlets;

import DAO.DTO.TestsDTO;
import service.TestServiceImpl;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@WebServlet("/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //        int id = (int) req.getAttribute("teachedId"); MOCKED FOR TEST
        int id = 30;
        List<TestsDTO> testService = new TestServiceImpl().getWorksForVerifier(id);
        req.setAttribute("tests", testService);
        req.getRequestDispatcher("/controller.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req);
//        System.out.println(req);
//        ServletInputStream inputStream = req.getInputStream();
//        FileInputStream fileInputStream = new FileInputStream()
//        System.out.println(req);
//        System.out.println(req);
    }
}
