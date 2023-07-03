<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login & Registration Form</title>


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
            }


            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                background-color: transparent;
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
                /*background-image: url('styles/img/alpha.jpg');*/
                background-color: #f5deb3;
                opacity: 0.9;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
        <!-- Header -->
        <!--    <header class="header">
              <nav class="nav">
                <a href="#" class="nav_logo">ToPieFor</a>
        
                <ul class="nav_items">
                  <li class="nav_item">
                    <a href="#" class="nav_link">Home</a>
                    <a href="#" class="nav_link">Contact</a>
                  </li>
                </ul>
        
                <button class="button" id="form-open">Login</button>
              </nav>
            </header>-->

        <!-- Home -->
        <section class="home">
            <div class="form_container">
                <i class="uil uil-times form_close"></i>

                <!-- Signup From -->
                <div class="form signup_form">
                    <form action="service" method="POST">
                        <h2>Sign Up</h2>

                        <div class="input_box">
                            <input type="text"  name="name" placeholder="Enter your name" required />
                            <i class="uil uil-envelope-alt email"></i>
                        </div>

                        <div class="input_box">
                            <input type="text"  name="lastname" placeholder="Enter your lastname" required />
                            <i class="uil uil-envelope-alt email"></i>
                        </div>

                        <div class="input_box">
                            <input type="text"  name="email" placeholder="Enter your email" required />
                            <i class="uil uil-envelope-alt email"></i>
                        </div>

                        <div class="input_box">
                            <input type="text"  name="mobileNum" placeholder="Enter your mobile number" required />
                            <i class="uil uil-envelope-alt email"></i>
                        </div>

                        <div class="input-box address">
                            <h1>Address</hl>
                                <input type="text"  name="strName" placeholder="Enter street address" required />
                                <input type="text"  name="city" placeholder="Enter your city" required />

                                <div class="column">
                                    <input type="text"  name="suburb" placeholder="Enter your suburb" required />
                                    <input type="number"  name="zipCode" placeholder="Enter postal code" required />
                                    <input type="text"  name="country" placeholder="Enter your country" required />
                                </div>
                        </div>

                        <div class="input_box">
                            <input type="password"  name="password" placeholder="Create password" required />


                        </div>
                        <div class="input_box">
                            <input type="password"  name="password" placeholder="Confirm password" required />    
                        </div>

                        <centre>
                            <input type ="hidden" id = "pro" name="process"value ="create-account">
                            <input type ="hidden" id = "pro" name="service"value ="guest">
                        </centre>

                        <button class="button">SignUp Now</button>

                        <div class="login_signup">Already have an account? <a href="login.jsp" id="login">Login</a></div>
                    </form>
                </div>
            </div>
        </section>

        <script src="script.js">
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

            signupBtn.addEventListener("click", (e) => {
                e.preventDefault();
                formContainer.classList.add("active");
            });
            loginBtn.addEventListener("click", (e) => {
                e.preventDefault();
                formContainer.classList.remove("active");
            });

        </script>

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