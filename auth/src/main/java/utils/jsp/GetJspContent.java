package servlets.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetJspContent {

    private static CharArrayWriterResponse charArrayWriterResponse;
    private static HttpServletRequest req;
    private static HttpServletResponse resp;

    public GetJspContent(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.charArrayWriterResponse = new CharArrayWriterResponse(GetJspContent.resp);
    }

    public String getContent(String jspPath) throws ServletException, IOException {
        this.req.getRequestDispatcher("/WEB-INF/views/regions/list.jsp")
                .forward(req, this.charArrayWriterResponse);
        return this.charArrayWriterResponse.getOutput();
    }

}
