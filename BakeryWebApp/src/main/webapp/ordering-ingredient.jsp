<%-- 
    Document   : ordering-ingredient
    Created on : Jun 11, 2023, 6:28:20 PM
    Author     : VM JELE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="service" method="GET">
            <centre>
                <input type ="hidden" id = "pro" name="process" value ="ordering-ingredient">
                <input type ="hidden" id = "pro" name="service"value ="admin">
            </centre>
            
            <input type="submit" value="Order Ingredient"/>
        </form>
        
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
