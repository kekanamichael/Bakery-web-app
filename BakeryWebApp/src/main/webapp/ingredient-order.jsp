<%-- 
    Document   : order-ingredient
    Created on : Jun 14, 2023, 3:26:02 PM
    Author     : Train
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.techgiants.topiefor.model.User"%>
<%@page import="com.techgiants.topiefor.model.Ingredient"%>
<%@page import="com.techgiants.topiefor.model.Ingredient_Order"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Order Ingredient</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="Styles/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="Styles/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="Styles/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="Styles/css/style.css" rel="stylesheet">
    </head>

    <style>
        body {
            background-color: #ECECEC;
            font-family: 'Nunito', sans-serif;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .popup {
            display: none;
            position: fixed;
            z-index: 9999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            opacity: 0;
            transition: opacity 0.3s ease;
        }

        .popup.active {
            display: block;
            opacity: 1;
        }

        .popup-content {
            background-color: #fff;
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            border-radius: 5px;
            position: relative;
            opacity: 0;
            transform: translateY(-20px);
            transition: opacity 0.3s ease, transform 0.3s ease;
            animation: popupAnimation 0.7s ease;
        }

        .popup.active .popup-content {
            opacity: 1;
            transform: translateY(0);
        }
        .popup1 {
            display: none;
            position: fixed;
            z-index: 9999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            opacity: 0;
            transition: opacity 0.3s ease;
        }

        .popup1.active {
            display: block;
            opacity: 1;
        }

        .popup-content1 {
            background-color: #fff;
            max-width: 800px;
            margin: 100px auto;
            padding: 20px;
            border-radius: 5px;
            position: relative;
            opacity: 0;
            transform: translateY(-20px);
            transition: opacity 0.3s ease, transform 0.3s ease;
            animation: popupAnimation 0.7s ease;
        }

        .popup1.active .popup-content1 {
            opacity: 1;
            transform: translateY(0);
        }

        .close {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            margin-top: 5px;
            font-weight: bold;
        }

        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            /*margin-top: 20px;*/
        }

        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #de6900;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        @keyframes popupAnimation {
            0% {
                opacity: 0;
                transform: scale(0.3);
            }
            100% {
                opacity: 1;
                transform: scale(1);
            }
        }
        .scrollable-div {
            height: 100px; /* Adjust the height as needed */
            overflow-y: scroll;
        }
        .removeButton{
            height: 30px;
        }
    </style>

    <body>
        <div class="container-fluid position-relative d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->


            <!-- Sidebar Start -->
            <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-secondary navbar-dark">
                    <a href="#" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary"><i class="fa fa-user-edit me-2"></i>Admin</h3>
                    </a>



                    <div class="navbar-nav w-100">
                        <a href="asAdmin.jsp" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>


                        <a href="service?service=admin&process=all-products" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Products</a>

                        <a href="service?service=admin&process=all-categories" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Categories</a>

                        <a href="service?service=admin&process=all-orders" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Orders</a>

                        <a href="service?service=admin&process=all-ingredients" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Ingredients</a>

                        <a href="service?service=admin&process=all-ingredient-orders" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Ingredient Order</a>


                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">

                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-secondary navbar-dark sticky-top px-4 py-0">
                    <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                        <h2 class="text-primary mb-0"><i class="fa fa-user-edit"></i></h2>
                    </a>


                    <div class="navbar-nav align-items-center ms-auto">


                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <img   alt="" style="width: 40px; height: 40px;">
                                <%
                                    User user = (User) session.getAttribute("user");
                                %>
                                <span class="d-none d-lg-inline-flex"><%= user != null ? user.getName() + " " + user.getSurname() : "No Name"%></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
                                <a href="service?service=client&process=logout" class="dropdown-item">Log Out</a>
                                <a href="index.jsp" class="dropdown-item">As Customer</a>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->


                <!-- Sale & Revenue Start -->
<!--                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fa fa-chart-line fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Today Sale</p>
                                    <h6 class="mb-0">R1,234</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fa fa-chart-bar fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Total Sale</p>
                                    <h6 class="mb-0">R1,234</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fa fa-chart-area fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Today Revenue</p>
                                    <h6 class="mb-0">R1,234</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fa fa-chart-pie fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Total Revenue</p>
                                    <h6 class="mb-0">R1,234</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
                <!-- Sale & Revenue End -->

                <div id="popup" class="popup">
                    <div class="popup-content">
                        <span class="close" onclick="closePopup()">&times;</span>
                        <h3>Update Ingredient Order</h3>
                        <form action="#" method="POST">

                            <label for="status">Is Delivered?:</label>
                            <select id="status" name="status">
                                <option value="pending">PENDING</option>
                                <option value="preparing">NOT_DELIVERED</option>
                                <option value="delivered">DELIVERED</option>


                            </select>

                            <input type="submit" value="Update Status">
                        </form>
                    </div>
                </div>


                <div id="popup1" class="popup1">
                    <div class="popup-content1">
                        <span class="close" onclick="closePopup1()">&times;</span>
                        <h3>Order New Ingredients</h3>



                        <form method="POST" action="service">
                            <centre>
                                <input type ="hidden" id = "pro" name="process" value ="order-ingredient">
                                <input type ="hidden" id = "pro" name="service"value ="admin">
                            </centre>

                            <div style="width: fit-content;height: fit-content">

                                <div class="form test_form"
                                     style="width: 450px;height: fit-content;padding-left: 100px">

                                    <h1>Ordering Ingredients</h1>   
                                    <%
                                        List<Ingredient> ingredients = (List<Ingredient>) request
                                                .getAttribute("ingredients");
                                    %>

                                    <div class="ingredientRow">
                                        <select id="ingred">
                                            <% for (Ingredient ingr : ingredients) {%>
                                            <option value="<%= ingr.getIngredientId()%>"><%= ingr.getName()%></option>
                                            <% } %>
                                        </select>
                                        <input type="text" id="qty" placeholder="Quantity" required>
                                        <!-- <button type="button" onclick="removeIngredientRow(this)">Remove</button> -->
                                    </div>
                                    <br>

                                    <div id="ingredientContainer" class="scrollable-div">

                                        <br>
                                    </div>

                                    <div>
                                        <button type="button" onclick="addIngredientRow()">Add Ingredient</button>
                                    </div>
                                    <br>
                                    <button type="submit" class="addproduct">Submit Order</button>
                                </div>  
                                <br>

                            </div> 

                        </form>
                    </div>
                </div>
                <h1>Ingredient Order details</h1>

                <%
                    List<Ingredient_Order> orders = (List<Ingredient_Order>) request.getAttribute("ingredient-orders");
                %>

                <table>
                    <thead>


                        <tr>
                            <th>IngrOrderId</th>
                            <th>Quantity</th>
                            <th>Date</th>
                            <th>Delivered</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
                            for (Ingredient_Order order : orders) {
                        %>
                        <tr>
                            <td><%= order.getId()%></td>
                            <td><%= order.getOrderItems().size()%></td>
                            <td><%= order.getDeliveryDate() != null ? dtf.format(order.getDeliveryDate()) : "Not Delivered"%></td>
                            <td>
                                <%= order.isDelivered() == true ? "Yes" : "No"%>
                            </td>


                            <td>

                                <%= order.isDelivered() == false ? "<a href=\"service?service=admin&process=confirm-order&ingredOrderId=" + order.getId() + "\"><button >Confirm Order</button></a>" : "Order confirmed"%>
                            </td>



                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <br>
                <button style="margin-left: 400px" onclick="openPopup1()" >Order New Ingredients</button>

                <script>
                    function openPopup() {
                        var popup = document.getElementById("popup");
                        popup.classList.add("active");
                    }

                    function closePopup() {
                        var popup = document.getElementById("popup");
                        popup.classList.remove("active");
                    }
                    function openPopup1() {
                        var popup = document.getElementById("popup1");
                        popup.classList.add("active");
                    }

                    function closePopup1() {
                        var popup = document.getElementById("popup1");
                        popup.classList.remove("active");
                    }
                </script>
                <script>
                    function addIngredientRow() {
                        const ingredientContainer = document.getElementById("ingredientContainer");

                        const newRow = document.createElement("div");
                        newRow.classList.add("ingredientRow");
                        newRow.style.display = "flex";

                        const ingredientInput = document.createElement("input");
                        ingredientInput.type = "text";
                        ingredientInput.name = "ingredient[]";
                        ingredientInput.required = true;
                        ingredientInput.hidden = true;

                        var selectElement = document.getElementById("ingred");
                        var selectedValue = selectElement.value;
                        ingredientInput.value = selectedValue;

                        const quantityInput = document.createElement("input");
                        quantityInput.type = "number";
                        quantityInput.name = "quantity[]";
                        quantityInput.required = true;
                        quantityInput.hidden = true;
                        quantityInput.value = document.getElementById("qty").value;

                        const recipeIngred = document.createElement("p");
                        recipeIngred.textContent = selectElement.options[selectElement.selectedIndex].textContent+ " : " + document.getElementById("qty").value;

                        const removeButton = document.createElement("button");
                        removeButton.type = "button";
                        removeButton.textContent = "Remove";
                        removeButton.classList.add("removeButton");
                        removeButton.onclick = function () {
                            removeIngredientRow(this);
                        };

                        newRow.appendChild(ingredientInput);
                        newRow.appendChild(quantityInput);
                        newRow.appendChild(recipeIngred);
                        newRow.appendChild(removeButton);

                        ingredientContainer.appendChild(newRow);
                    }

                    function removeIngredientRow(button) {
                        const row = button.parentNode;
                        const container = row.parentNode;
                        container.removeChild(row);
                    }
                </script>


                <%
                    String msg = (String) session.getAttribute("msg");
                    if (!(msg == null || msg.isEmpty())) {

                %>

                <script>
                    alert("<%= msg%>")
                </script>

                <%
                        session.setAttribute("msg", null);
                    }
                %>



                <!-- JavaScript Libraries -->
                <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="Styles/lib/chart/chart.min.js"></script>
                <script src="Styles/lib/easing/easing.min.js"></script>
                <script src="Styles/lib/waypoints/waypoints.min.js"></script>
                <script src="Styles/lib/owlcarousel/owl.carousel.min.js"></script>
                <script src="Styles/lib/tempusdominus/js/moment.min.js"></script>
                <script src="Styles/lib/tempusdominus/js/moment-timezone.min.js"></script>
                <script src="Styles/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

                <!-- Template Javascript -->
                <script src="Styles/js/main.js"></script>
                </body>
