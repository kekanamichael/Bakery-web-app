<%-- 
    Document   : view-404
    Created on : Jun 9, 2023, 9:28:58 AM
    Author     : STUDIO 18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!: Error 404</h1>
        
        
        <script>
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
                    request.setAttribute("msg", null);
            %>
                    alert("<%= msg%>");
            <%
                }
            %>
        </script>
    </body>
</html>
