
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            sesion.invalidate();
            sesion = request.getSession(true);
            sesion.setAttribute("usuario", "andre");
            sesion.setAttribute("tipo", "profesor");
        %>
        <h1>Hello World!</h1>
    </body>
</html>
