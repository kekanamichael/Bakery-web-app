<%-- 
    Document   : login
    Created on : May 25, 2023, 1:17:42 PM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <style>
            /* Reset some default styles */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Global styles */
            body {
                font-family: Arial, sans-serif;
                line-height: 1.4;
                background: url('Styles/img/alpha.jpg');
                background-size: cover;
                background-position: center;
                filter: blur(0px);
            }

            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }

            /* Header styles */
            .header {
                background-color: #333;
                color: #fff;
                padding: 20px;
            }

            .nav {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .nav_logo {
                color: #fff;
                font-size: 24px;
                text-decoration: none;
            }

            .nav_items {
                list-style: none;
                display: flex;
            }

            .nav_link {
                color: #fff;
                margin-left: 10px;
                text-decoration: none;
            }

            .button {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
            }

            /* Home section styles */
            .home {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form_container {
                background-color: #f5deb3;
                opacity: 0.9;
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

            .checkbox {
                display: inline-flex;
                align-items: center;
                color: #555;
            }

            .checkbox input[type="checkbox"] {
                margin-right: 5px;
            }

            .forgot_pw {
                color: #007bff;
                text-decoration: none;
            }

            .login_signup {
                text-align: center;
                margin-top: 20px;
            }

            .login_signup a {
                color: #007bff;
                text-decoration: none;
            }


        </style>

    </head>
    <body>
        <section class="home">
            <div class="form_container">
                <i class="uil uil-times form_close"></i>
                <!-- Login From -->
                <div class="form login_form container">
                    <form action="service" id="form-open" method="POST">
                        <h2>Login</h2>

                        <div class="input_box">
                            <input type="text"  name="email" placeholder="Enter your email" required />
                            <i class="uil uil-envelope-alt email"></i>
                        </div>
                        <div class="input_box">
                            <input type="password"  name="password" placeholder="Enter your password" required />
                            <i class="uil uil-lock password"></i>
                            <i class="uil uil-eye-slash pw_hide"></i>
                        </div>

                        <div class="option_field">
<!--                            <span class="checkbox">
                                <input type="checkbox" id="check" />
                                <label for="check">Remember me      </label>
                            </span>
                            <a href="#" class="forgot_pw">Forgot password?</a>-->
                        </div>

                        <button class="button" id="login">Login Now</button>
                        <centre>
                            <input type ="hidden"  name="process"value ="login">
                            <input type ="hidden"  name="service"value ="guest">
                        </centre>
                        <div class="login_signup">Don't have an account? <a href="sign-up.jsp" id="signup">SignUp</a></div>
                    </form>
                </div>
            </div>
        </section>

        <script >
            const formOpenBtn = document.querySelector("#form-open"),
                    home = document.querySelector(".home"),
                    formContainer = document.querySelector(".form_container"),
                    formCloseBtn = document.querySelector(".form_close"),
                    signupBtn = document.querySelector("#signup"),
                    loginBtn = document.querySelector("#login"),
                    pwShowHide = document.querySelectorAll(".pw_hide");

            formOpenBtn.addEventListener("click", () => home.classList.add("show"));
            formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

            pwShowHide.forEach((icon) => {
                icon.addEventListener("click", () => {
                    let getPwInput = icon.parentElement.querySelector("input");
                    if (getPwInput.type === "password") {
                        getPwInput.type = "text";
                        icon.classList.replace("uil-eye-slash", "uil-eye");
                    } else {
                        getPwInput.type = "password";
                        icon.classList.replace("uil-eye", "uil-eye-slash");
                    }
                });
            });

//            signupBtn.addEventListener("click", (e) => {
//                e.preventDefault();
//                formContainer.classList.add("active");
//            });
//            loginBtn.addEventListener("click", (e) => {
//                e.preventDefault();
//                formContainer.classList.remove("active");
//            });

        </script>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
                request.setAttribute("msg", null);
        %>
        <script>

            alert('<%= msg%>');

        </script>
        <%
            }
        %>
    </body>
</html>
