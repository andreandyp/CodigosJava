package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class frames extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String frame = request.getParameter("frame");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet frames</title>");            
            out.println("</head>");
            switch(frame){
                case "unu":
                    out.println("<frameset cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"leftFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "du":
                    out.println("<frameset cols=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"rightFrame\" scrolling=\"NO\" noresize title=\"rightFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "tri":
                    out.println("<frameset rows=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "kvar":
                    out.println("<frameset rows=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "kvin":
                    out.println("<frameset rows=\"*,80\" cols=\"*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frameset cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"leftFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  </frameset>\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "ses":
                    out.println("<frameset rows=\"*,80\" cols=\"*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frameset cols=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"rightFrame\" scrolling=\"NO\" noresize title=\"rightFrame\">\n" +
"  </frameset>\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "sept":
                    out.println("<frameset rows=\"*\" cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"leftFrame\">\n" +
"  <frameset rows=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  </frameset>\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "ok":
                    out.println("<frameset rows=\"*\" cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"leftFrame\">\n" +
"  <frameset rows=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"  </frameset>\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "nau":
                    out.println("<frameset cols=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frameset rows=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"  </frameset>\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"rightFrame\" scrolling=\"NO\" noresize title=\"rightFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "dek":
                    out.println("<frameset cols=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frameset rows=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  </frameset>\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"rightFrame\" scrolling=\"NO\" noresize title=\"rightFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "dekunu":
                    out.println("<frameset rows=\"80,*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"bottomFrame\" scrolling=\"NO\" noresize title=\"bottomFrame\">\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "dekdu":
                    out.println("<frameset rows=\"80,*\" cols=\"*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"  <frameset cols=\"80,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"leftFrame\" scrolling=\"NO\" noresize title=\"leftFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"  </frameset>\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
                case "dektri":
                    out.println("<frameset rows=\"80,*\" cols=\"*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"  <frame src=\"http://localhost:8080/Practica18/Servlet1\" name=\"topFrame\" scrolling=\"NO\" noresize title=\"topFrame\">\n" +
"  <frameset cols=\"*,80\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet2\" name=\"mainFrame\" title=\"mainFrame\">\n" +
"    <frame src=\"http://localhost:8080/Practica18/Servlet3\" name=\"rightFrame\" scrolling=\"NO\" noresize title=\"rightFrame\">\n" +
"  </frameset>\n" +
"</frameset>\n" +
"<noframes><body>\n" +
"</body></noframes>");
                    break;
            }
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}