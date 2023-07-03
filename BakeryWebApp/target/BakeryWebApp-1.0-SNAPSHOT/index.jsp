

<%@page import="com.techgiants.topiefor.model.User"%>
<html lang="en"><head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bakery</title>
        <link rel="stylesheet" href="style.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
        <!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <style>
            @import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css);


            h2 {
                color: #000;
                font-size: 26px;
                font-weight: 300;
                text-align: center;
                text-transform: uppercase;
                position: relative;
                margin: 30px 0 80px;
            }
            h2 b {
                color: #ffc000;
            }
            h2::after {
                content: "";
                width: 100px;
                position: absolute;
                margin: 0 auto;
                height: 4px;
                background: rgba(0, 0, 0, 0.2);
                left: 0;
                right: 0;
                bottom: -20px;
            }


            *{
                margin: 0;
                padding: 0;
                text-decoration: none;
                box-sizing: border-box;
            }
            .navbar{
                position: fixed;
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
            .logo{
                font-size: 40px;
                font-weight: 100;
                font-family: 'Great Vibes',sans-serif;
                color: #222222;
                cursor: pointer;
            }
            .navbar-menu a{
                font-size: 13px;
                font-weight: 500;
                font-family: 'Raleway',sans-serif;
                color: #222222;
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

            #home{
                height: 100vh;
                display: grid;
                grid-template-columns: 1fr 1fr;
                color: #222222;
                /*  background-image: url('Styles/img/lines.jpg');
                   background-position: center;
                  background-size: cover;*/
                background-color: #f5deb3;
                padding: 0 100px;
            }

            .content1{
                display: flex;
                flex-direction: column;
                padding-top: 170px;
            }
            .title h1{
                font-size: 45px;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                font-weight: 900;
                text-transform: uppercase;
                letter-spacing: 3px;
                margin-bottom: 40px;
            }
            .image{
                background-image: url(img2.jpg);
                background-position: center;
                background-size: cover;
                width: 370px;
                height: 270px;
            }
            .content2{
                display: flex;
                flex-direction: column;
                padding-top: 170px;
                padding-left: 20px;
            }
            .content2 .image{
                background-image: url('Styles/img/cupcake.jpg');
            }
            .title p{
                margin-bottom: 0;
                margin-top: 33px;
                font-size: 14px;
                font-family: 'Raleway',sans-serif;
                font-weight: 500;
                letter-spacing: 1px;
                line-height: 25px;
            }
            .content2 button{
                padding: 14px 27px;
                margin-top: 30px;
                background-color: #de6900;
                border: none;
                outline: none;
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                letter-spacing: 2px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
            }
            .social-container{
                position: absolute;
                right: 55px;
                bottom: 30%;
            }
            .social-icons{
                list-style: none;
                position: relative;
            }
            .social-icons li{
                margin: 35px 0;
            }
            .social-icons li i{
                color: #222222;
                font-size: 20px;
            }
            .social-icons li a:hover i{
                transform: scale(1.5);
                color: #de6900;
                transition: all .2s ease-in-out;
            }
            .social-icons::before{
                content: '';
                position: absolute;
                display: block;
                width: 1px;
                height: 190px;
                top: -210px;
                left: 8px;
                background-color: #de6900;
            }
            .social-icons::after{
                content: '';
                position: absolute;
                display: block;
                width: 1px;
                height: 190px;
                bottom: -210px;
                left: 8px;
                background-color: #de6900;
            }
        </style>

    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        
        <nav class="navbar" id="navbar">
            <div class="inner-width">

                <a href="#" class="logo">ToPieFor</a>
                <div class="navbar-menu">
                    <a href="index.jsp">Home</a>
                    <%
                        if (user == null) {
                    %>
                    <a href="login.jsp">Login</a>
                    <a href="sign-up.jsp">Sign Up</a>

                    <%
                    } else {
                        if (user.getRole().getRoleId() == 1) {
                    %>

                    <a href="asAdmin.jsp">As Admin</a>
                    <%
                        }
                    %>
                    <a href="service?service=client&process=all-orders">Orders</a>
                    <a href="service?service=client&process=logout">Logout</a>
                    <%
                        }
                    %>
                    <a href="aboutUs.jsp">About</a>
                    <a href="#">Contact</a>

                </div>
            </div>
        </nav>
        <section id="home">
            <div class="content1">
                <div class="title">                                                                                                        
                    <h1>Products Made With Love</h1>
                </div>
                <div class="image"></div>
            </div>
            <div class="content2">
                <div class="image"></div>
                <div class="title">
                    <p>MNANDILICIOUS PRODUCTS!!!.</p>

                    <form action="service" method="POST">
                        <centre>
                            <input type ="hidden" id = "pro" name="process" value ="view-products">
                            <input type ="hidden" id = "pro" name="service" value ="guest">
                        </centre>

                        <button type="submit" >Order</button>
                    </form>
                </div>
            </div>

            <div class="social-container">
                <ul class="social-icons">
                    <li><a href="#"><i class="fa fa-facebook-f"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                </ul>
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
    </body>
</html>
