<%-- 
    Document   : product
    Created on : Jun 14, 2023, 3:26:02 PM
    Author     : Train
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.techgiants.topiefor.model.User"%>
<%@page import="com.techgiants.topiefor.model.Unit"%>
<%@page import="com.techgiants.topiefor.model.Ingredient"%>
<%@page import="com.techgiants.topiefor.model.Category"%>
<%@page import="com.techgiants.topiefor.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Product</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

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

        <!-- Google Web Fonts -->

        <style>
            body {
                background-color: #ECECEC;
                font-family: 'Nunito', sans-serif;
            }

            form {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1{
                font-family: 'Nunito', sans-serif;
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }

            label {
                display: block;
                margin-bottom: 10px;
                color: #333;
            }

            input[type="text"],
            textarea,
            select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
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
                max-width: 1000px;
                margin: 100px auto;
                padding: 20px;
                border-radius: 5px;
                position: relative;
                opacity: 0;
                transform: translateY(-20px);
                transition: opacity 0.3s ease, transform 0.3s ease;
                animation: popupAnimation 0.7s ease;
                height: 400px; /* Adjust the height as needed */
                overflow-y: scroll;
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
                max-width: 1000px;
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
                max-width: 900px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

            }

            h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;

            }

            label {
                display: block;
                margin-bottom: 10px;
                color: #333;
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

    </head>

    <body>

        <%
            List<Category> categories = (List<Category>) request.getAttribute("categories");
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");

        %>
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

                <%                    List<Product> prods = (List<Product>) request.getAttribute("products");
                %>
                <div id="popup" class="popup">
                    <div class="popup-content">
                        <span class="close" onclick="closePopup()">&times;</span>
                        <h2>Update Product</h2>
                        <form action="service" method="POST">

                            <centre>
                                <input type ="hidden" id = "pro" name="process"value ="update-product">
                                <input type ="hidden" id = "pro" name="service"value ="admin">
                            </centre>

                            <input type="hidden" id="prodId" name="id" />

                            <label for="product-name">Product Name:</label>
                            <input type="text" id="product-name" name="product-name" required />

                            <label for="product-name">Product Price:</label>
                            <input type="number" id="pro-price" name="pro-price" step="any" required />


                            <label for="nutrient-info">Nutrient Information:</label>
                            <input type="text" id="nutrient-info" name="nutrient-info">

                            <label for="warnings">Warnings:</label>
                            <input type="text" id="warnings" name="warnings" >
                            
                            <input type="hidden" id="act" name="active" />


                            <!--<input type="hidden" name="prodId" id="prodId" value="">
                                            <label for="product-name">Name:</label>
                                            <input type="text" name="product-name" id="product-name" required><br>
                                            <label for="pro-price">Price:</label>
                                            <input type="number" name="pro-price" id="pro-price" required><br>
                                            <label for="nutrient-info">Nutrient Info:</label>
                                            <textarea name="nutrient-info" id="nutrient-info" required></textarea><br>
                                            <label for="warnings">Warnings:</label>
                                            <textarea name="warnings" id="warnings" required></textarea><br>
                                            <button type="submit">Save</button>
                                            <button type="button" onclick="closePopup()">Cancel</button>-->

                            <!--                            <label for="ingredients">Ingredients:</label>
                                                        <div id="ingredient-list">
                                                            <div class="ingredient-item">
                                                                <select name="ingredient[]" required>
                                                                    <option value="" disabled selected>Select Ingredient</option>
                                                                    <option value="ingredient1">Ingredient 1</option>
                                                                    <option value="ingredient2">Ingredient 2</option>
                                                                     Add more options for ingredients 
                                                                </select>
                                                                <select name="unit[]" required>
                                                                    <option value="" disabled selected>Select Unit</option>
                                                                    <option value="gram">Gram</option>
                                                                    <option value="kilogram">Kilogram</option>
                                                                     Add more options for units 
                                                                </select>
                                                                <input type="number" name="quantity[]" placeholder="quantity">                
                                                            </div>
                                                        </div>
                            
                                                        <button type="button" id="add-product">Add Product</button>-->

                            <input type="submit" value="Update Product">
                        </form>
                    </div>
                </div>

                <h1>Product details</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Warnings</th>
                            <th>Category</th>
                            <th>Recipe Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            DecimalFormat df = new DecimalFormat("#,##0.00");
                            for (Product prod : prods) {
                        %>
                        <tr>
                            <td><%= prod.getProductId()%></td>
                            <td><%= prod.getName()%></td>
                            <td>R<%= df.format(prod.getUnitPrice()) %></td>
                            <td><%= prod.getWarnings()%></td>
                            <td><%= prod.getCategory().getName()%></td>
                            <td><%= prod.getRecipe().getName()%></td>
                            <td>
                                <button onclick="openPopup('<%= prod.getProductId()%>', '<%= prod.getName()%>', '<%= prod.getUnitPrice()%>', '<%= prod.getNutrientInfo()%>', '<%= prod.getWarnings()%>', '<%= prod.isIsActive()%>')">Edit</button>
                                <% if (prod.isIsActive()) {%>
                                <a href="service?service=admin&process=activate-product&productId=<%= prod.getProductId()%>&active=false">
                                    <button>Deactivate</button>
                                </a>
                                <% } else {%>
                                <a href="service?service=admin&process=activate-product&productId=<%= prod.getProductId()%>&active=true" >
                                    <button>Activate</button>
                                </a>
                                <% } %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <br>

                <div id="popup1" class="popup1">
                    <div class="popup-content1">
                        <span class="close" onclick="closePopup1()">&times;</span>

                        <div class="form_container"
                             style="display: flex">
                            <i class="uil uil-times form_close"></i>
                            <form action="service" method="post" style="display:flex;">
                                <div class="form test_form"
                                     style="height: fit-content">


                                    <h1>Add Product</h1>
                                    <centre>
                                        <input type ="hidden" id = "pro" name="process" value ="add-product">
                                        <input type ="hidden" id = "pro" name="service"value ="admin">
                                    </centre>
                                    <div class="input_box">
                                        <input type="text"  name="pro-name" placeholder="Enter The Product name" required />
                                    </div>

                                    <div class="input_box">
                                        <input type="text"  name="pro-desc" placeholder="Enter Product Description" required />
                                    </div>

                                    <div class="input_box">
                                        <input type="number" step="any" min="0"  name="pro-price" placeholder="Enter Product Price" required />
                                    </div>

                                    <div class="input_box">
                                        <input type="text"  name="pro-nutro-info" placeholder="Enter Nutrient Information" required />
                                    </div>

                                    <div class="input_box">
                                        <input type="text"  name="pro-warnings" placeholder="Product Warnings" required />
                                    </div>
                                    <div class="input_box">
                                        <select id="category" name="cat-id">
                                            <% for (Category cat : categories) {%>
                                            <option value="<%= cat.getCategoryId()%>"><%= cat.getName()%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div>
                                        <h for="product_pic">Product Image</h>
                                        <input type="file" name="image" id="file"/>
                                    </div>

                                    <br>
                                </div>

                                <div style="width: fit-content;height: fit-content;">

                                    <div class="form test_form1"
                                         style="width: 450px;height: fit-content">

                                        <h1>Recipe Info:</h1>   

                                        <div class="input_box">
                                            <input type="text"  name="res-name" placeholder="Enter Recipe Name" required />
                                        </div>

                                        <div class="input_box">
                                            <input type="text"  name="res-desc" placeholder="Enter Recipe Description" required />
                                        </div>
                                        <div  class="ingredientRow" style="display:flex;">
                                            <select id="ingred">
                                                <% for (Ingredient ingr : ingredients) {%>
                                                <option value="<%= ingr.getIngredientId()%>"><%= ingr.getName()%></option>
                                                <% }%>
                                            </select>

                                            </select>
                                            <select id="unit" name="unit">
                                                <% int cnt = 0;
                                                    for (Unit unit : Unit.values()) {%>
                                                <option value="<%= cnt%>"><%= unit.name()%></option>
                                                <% cnt++;
                                                    }%>
                                            </select>

                                            <input type="text" id="qty" placeholder="Quantity" required>
                                            <!-- <button type="button" onclick="removeIngredientRow(this)">Remove</button> -->
                                        </div>
                                        <div id="ingredientContainer" class="scrollable-div">

                                            <br>
                                        </div>
                                        <br>

                                        <div>
                                            <button type="button" onclick="addIngredientRow()">Add Ingredient</button>
                                        </div>

                                        <br>
                                        <button type="submit" class="addproduct">Add Product</button>
                                    </div>  
                                    <br>



                                </div>
                            </form>                     
                        </div>
                    </div>
                </div>
                <button style="margin-left: 400px" onclick="openPopup1()">Add New Product</button>

            </div>


            <script>
                function openPopup(productId, productName, proPrice, nutrientInfo, warnings, isActive) {
                    var popup = document.getElementById("popup");
                    popup.classList.add("active");

                    document.getElementById("prodId").value = productId;
                    document.getElementById("product-name").value = productName;
                    document.getElementById("pro-price").value = proPrice;
                    document.getElementById("nutrient-info").value = nutrientInfo;
                    document.getElementById("warnings").value = warnings;
                    document.getElementById("act").value = isActive;

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

                    const unitInput = document.createElement("input");
                    unitInput.type = "number";
                    unitInput.name = "unit[]";
                    unitInput.required = true;
                    unitInput.hidden = true;
                    unitInput.value = document.getElementById("unit").value;

                    const recipeIngred = document.createElement("p");
                    var ingredSelect = document.getElementById("ingred");
                    var unitSelect = document.getElementById("unit");
                    recipeIngred.textContent = ingredSelect.options[ingredSelect.selectedIndex].textContent + ' : '
                            + document.getElementById("qty").value + ' ('
                            + unitSelect.options[unitSelect.selectedIndex].textContent + ') ';

                    const removeButton = document.createElement("button");
                    removeButton.type = "button";
                    removeButton.textContent = "Remove";
                    removeButton.classList.add("removeButton");
                    removeButton.onclick = function () {
                        removeIngredientRow(this);
                    };

                    newRow.appendChild(ingredientInput);
                    newRow.appendChild(unitInput);
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

</html>