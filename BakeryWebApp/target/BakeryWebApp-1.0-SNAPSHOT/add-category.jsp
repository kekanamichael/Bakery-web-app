<%-- 
    Document   : addCategory
    Created on : May 29, 2023, 8:46:30 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

        <style>
            @import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css);

            body {
                margin: 0;
                padding: 0;
            }
            body {
                /* background-color: #f8f2e7;*/
                background-image: url('Styles/img/alpha.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                backdrop-filter: blur(3px);
            }
            *{
                margin: 0;
                padding: 0;
                text-decoration: none;
                box-sizing: border-box;
            }

            .logo{
                font-size: 40px;
                font-weight: 100;
                font-family: 'Great Vibes',sans-serif;
                color: #222222;
                cursor: pointer;
            }
            .home {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 90vh;
            }

            .form_container {
                background-color: #f5deb3;
                background-position: center;
                opacity: 0.8;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 500px;
                height: 350px;
            }

            .form_close {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 24px;
                color: #333;
                cursor: pointer;
            }

            .form h2 {
                font-size: 24px;
                margin-bottom: 20px;
                text-align: center;
            }

            .input_box {
                position: relative;
                margin-bottom: 20px;
            }

            .input_box input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .uil {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                right: 10px;
                font-size: 20px;
                color: #999;
            }

            .option_field {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .navbar {
                position: fixed;
                top: 0;
                width: 100%;
                background-color: transparent;
                z-index: 100;
            }

            body {
                padding-top: 60px;
            }
            .navbar{
                /*  position: fixed;*/
                background-color: transparent;
                width: 100%;
                padding: 30px 0;
                top: 0;
                z-index: 100;
                line-height: 20px;
            }

            .inner-width{
                max-width: 1300px;
                margin: auto;
                padding: 0 60px;
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .navbar-menu a{
                font-size: 13px;
                font-weight: 500;
                font-family: 'Raleway',sans-serif;
                color: #733635;
                text-transform: uppercase;
                letter-spacing: 2px;
                margin-left: 35px;
                display: inline-block;
            }

            .navbar-menu a::after{
                content: '';
                display: block;
                width: 0;
                height: 1.8px;
                background-color: #de6900;
                transition: width .3s;
            }

            .navbar-menu a:hover::after{
                width: 100%;
            }
        </style>

    </head>
    <body>
        <div class="background-container"></div>

        <nav class="navbar">
            <div class="inner-width">
                <a href="#" class="logo">ToPieFor</a>
                <div class="navbar-menu">
                    <a href="index.jsp">Home</a>
                    <a href="index.jsp">Logout</a>
                </div>
            </div>
        </nav>
        <section class="home">
            <div class="form_container">
                <i class="uil uil-times form_close"></i>
                <!-- Login From -->
                <div class="form test_form">
                    <form action="service" method="post">
                        <h2>Adding A Category</h2>

                        <div class="input_box">
                            <input type="text"  name="name" placeholder="Enter Category Name" required />
                        </div>
                        <centre>
                            <input type ="hidden" id = "pro" name="process"value ="add-category">
                            <input type ="hidden" id = "pro" name="service"value ="admin">
                        </centre>

                        <div class="input_box">
                            <input type="text"  name="desc" placeholder="Enter Description" required />         
                        </div>

                        <div>
                            <input type="submit" name="name" value="ADD">
                        </div>
                    </form>
                </div>
            </div>
        </section>


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
        <!-- Rest of your page content -->
    </body>

</html>
