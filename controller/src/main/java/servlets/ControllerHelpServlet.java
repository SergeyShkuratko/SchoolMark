package servlets;

import classes.Work;
import com.google.common.base.Strings;
import service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("ALL")
@WebServlet("/getHelp")
public class ControllerHelpServlet extends HttpServlet {
    private static String RESPONSE1 = "{\n" +
            "  \"_id\": 0,\n" +
            "  \"description\": \"Sit nulla deserunt do nostrud fugiat pariatur incididunt qui. Enim qui sit non nisi veniam nisi proident deserunt veniam et laboris. Laborum irure non dolor elit fugiat incididunt.\\r\\n\",\n" +
            "  \"works\": [\n" +
            "    {\n" +
            "      \"workid\": 1,\n" +
            "      \"firstname\": \"Isabella \",\n" +
            "      \"surname\": \"Everett\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 2,\n" +
            "      \"firstname\": \"Ray \",\n" +
            "      \"surname\": \"Owen\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 3,\n" +
            "      \"firstname\": \"Phyllis \",\n" +
            "      \"surname\": \"Bond\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static String RESPONSE2 = "{\n" +
            "  \"_id\": 1,\n" +
            "  \"description\": \"Nisi proident exercitation commodo nisi anim sint ea cupidatat consequat officia. Commodo elit sint pariatur duis aliquip exercitation. Velit aliqua enim qui commodo ex. Velit laborum laborum nisi anim labore nisi ut qui id. Duis id consectetur sunt in ea. Proident ad commodo excepteur reprehenderit fugiat sint voluptate irure ea amet laboris.\\r\\n\",\n" +
            "  \"works\": [\n" +
            "    {\n" +
            "      \"workid\": 1,\n" +
            "      \"firstname\": \"Stacy \",\n" +
            "      \"surname\": \"Haynes\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 2,\n" +
            "      \"firstname\": \"Barton \",\n" +
            "      \"surname\": \"Holt\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 3,\n" +
            "      \"firstname\": \"Howe \",\n" +
            "      \"surname\": \"Jones\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static String RESPONSE3 = "{\n" +
            "  \"_id\": 2,\n" +
            "  \"description\": \"Tempor ullamco tempor duis est occaecat ea eu cupidatat mollit pariatur cillum proident quis nulla. Consectetur incididunt occaecat ea minim consectetur proident officia enim ex culpa id magna. Sit dolor esse commodo minim laborum laboris non irure occaecat incididunt occaecat velit. Dolor ea consequat est nostrud commodo fugiat.\\r\\n\",\n" +
            "  \"works\": [\n" +
            "    {\n" +
            "      \"workid\": 1,\n" +
            "      \"firstname\": \"Thompson \",\n" +
            "      \"surname\": \"Barber\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 2,\n" +
            "      \"firstname\": \"Walton \",\n" +
            "      \"surname\": \"Spears\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workid\": 3,\n" +
            "      \"firstname\": \"Farmer \",\n" +
            "      \"surname\": \"Casey\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static String IMAGE_RESPONSE1 = "[\n" +
            "  {\n" +
            "    \"workImageId\": 1,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work1.1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"workImageId\": 2,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work1.2\"\n" +
            "  }\n" +
            "]";
    private static String IMAGE_RESPONSE2 = "[\n" +
            "  {\n" +
            "    \"workImageId\": 1,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work2.1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"workImageId\": 2,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work2.2\"\n" +
            "  }\n" +
            "]";
    private static String IMAGE_RESPONSE3 = "[\n" +
            "  {\n" +
            "    \"workImageId\": 1,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work3.1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"workImageId\": 2,\n" +
            "    \"picture\": \"http://placehold.it/180x180?text=Work3.2\"\n" +
            "  }\n" +
            "]";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Work> testService = new TestService().getWorks();
        String getWorkForTest = req.getParameter("getWorksForTest");
        doWork(resp, getWorkForTest);

        String imageForWork = req.getParameter("getImageForWork");
        if (!Strings.isNullOrEmpty(imageForWork)) {
            if (imageForWork.equals("1")) {
                resp.getWriter().print(IMAGE_RESPONSE1);
            }
            if (imageForWork.equals("2")) {
                resp.getWriter().print(IMAGE_RESPONSE2);
            }
            if (imageForWork.equals("3")) {
                resp.getWriter().print(IMAGE_RESPONSE3);
            }
        }
//        super.doGet(req, resp);
    }

    private void doWork(HttpServletResponse resp, String getWorkForTest) throws IOException {
        if (!Strings.isNullOrEmpty(getWorkForTest)) {
            if (getWorkForTest.equals("1")) {
                resp.getWriter().print(RESPONSE1);
            }

            if (getWorkForTest.equals("2")) {
                resp.getWriter().print(RESPONSE2);
            }

            if (getWorkForTest.equals("3")) {
                resp.getWriter().print(RESPONSE3);
            }
        }
    }
}
