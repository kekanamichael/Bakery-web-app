<%-- 
    Document   : test
    Created on : May 30, 2023, 8:55:45 AM
    Author     : Train
--%>

<%@page import="com.techgiants.topiefor.model.Unit"%>
<%@page import="com.techgiants.topiefor.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.techgiants.topiefor.model.Ingredient"%>
<%@page import="java.util.ArrayList"%>
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
                padding: 10px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 900px;
                display: flex;
                /*  align-items: flex-start;*/
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
            .form h1 {
                font-size: 24px;
                margin-bottom: 20px;
                text-align: center;
            }

            .input_box {
                position: relative;
                margin-bottom: 15px;
            }

            .input_box input {
                width: 90%;
                padding: 5px;
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
            .form test_form input,
            .form test_form textarea,
            .form test_form select{
                display: block;
                width: 20%;
                margin-bottom: 10px;
            }
            .addproduct{
                colour: #f5deb3;
                height: 35px;

                /*                padding-top: 100px;*/
            }
            .scrollable-div {
                height: 100px; /* Adjust the height as needed */
                overflow-y: scroll;
            }



        </style>

    </head>
    <body>
        <%
            List<Category> categories = (List<Category>) request.getAttribute("categories");
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");

        %>

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
        <div class="home">
            <div class="form_container"
                 style="display: flex">
                <i class="uil uil-times form_close"></i>
                <form action="service" method="post" style="display:flex;">
                    <div class="form test_form"
                         style="height: fit-content">


                        <h1>Adding A Product</h1>
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
                            <input type="text"  name="pro-price" placeholder="Enter Product Price" required />
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
                            <input type="file" id="file"/>
                        </div>

                        <br>
                    </div>

                    <div style="width: fit-content;height: fit-content;">

                        <div class="form test_form1"
                             style="width: 450px;height: fit-content">

                            <h1>Adding A Recipe</h1>   

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
                                    <% } %>
                                </select>
                                    <select id="unit" name="unit">
                                        <% int cnt = 0;
                                    for (Unit unit : Unit.values()) {%>
                                        <option value="<%= cnt%>"><%= unit.name()%></option>
                                        <% cnt++;
                                    } %>
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
                            
                        </div>  
                        <br>



                    </div>
                      <button type="submit" class="addproduct">Add Product</button>              
                </form>                     
            </div>

        </div>

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
                        +unitSelect.options[unitSelect.selectedIndex].textContent+') ' ;

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
            ;

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
