<%-- 
    Document   : all-view
    Created on : Jun 12, 2023, 10:49:44 AM
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
        <h1>ALL View</h1>
        <form action="service" method="POST">
            <centre>
                <input type ="hidden" id = "pro" name="process" value ="all-products">
                <input type ="hidden" id = "pro" name="service" value ="admin">
            </centre>

            <input type="submit" value="View Product"/>
        </form>
        <form action="service" method="POST">
            <centre>
                <input type ="hidden" id = "pro" name="process" value ="all-categories">
                <input type ="hidden" id = "pro" name="service" value ="admin">
            </centre>

            <input type="submit" value="View Categories"/>
        </form>
        <form action="service" method="POST">
            <centre>
                <input type ="hidden" id = "pro" name="process" value ="all-ingredients">
                <input type ="hidden" id = "pro" name="service" value ="admin">
            </centre>

            <input type="submit" value="View Ingredients"/>
        </form>
        <form action="service" method="POST">
            <centre>
                <input type ="hidden" id = "pro" name="process" value ="view-products">
                <input type ="hidden" id = "pro" name="service" value ="guest">
            </centre>

            <input type="submit" value="Guest View Prods"/>
        </form>

        <form action="service" method="POST">
            <centre>
                <input type ="hidden" id = "pro" name="process"value ="all-ingredient-orders">
                <input type ="hidden" id = "pro" name="service"value ="admin">
            </centre>

            <input type="submit" value="View Ingredient Orders"/>
        </form>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
                request.setAttribute("msg", null);
        %>

        <script>
            alert("<%= msg%>");
        </script>
        <%
            }
        %>
    </body>
</html>
