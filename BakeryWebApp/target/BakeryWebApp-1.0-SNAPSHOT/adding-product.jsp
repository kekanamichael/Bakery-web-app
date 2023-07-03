<%-- 
    Document   : adding-product
    Created on : Jun 9, 2023, 2:37:38 PM
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
        <h1>Hello World!</h1>
        <form action="service" method="GET">
            <centre>
                <input type ="hidden" id = "pro" name="process"value ="adding-product">
                <input type ="hidden" id = "pro" name="service"value ="admin">
            </centre>
            
            <input type="submit" value="Add Product"/>
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
