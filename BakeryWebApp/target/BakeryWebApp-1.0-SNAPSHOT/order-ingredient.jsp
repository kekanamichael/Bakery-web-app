<%-- 
    Document   : orderIngredient
    Created on : Jun 7, 2023, 10:36:34 AM
    Author     : Train
--%>

<%@page import="com.techgiants.topiefor.model.Ingredient"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <%
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");
        %>

        <div class="background-container"></div>

        <form method="POST" action="service">

            <div style="width: fit-content;height: fit-content">

                <div class="form test_form"
                     style="width: 450px;height: fit-content;padding-left: 100px">

                    <h1>Ordering Ingredients</h1> 
                    <centre>
                        <input type ="hidden" id = "pro" name="process" value ="order-ingredient">
                        <input type ="hidden" id = "pro" name="service"value ="admin">
                    </centre>

                    <div id="ingredientContainer">
                        <div class="ingredientRow">
                            <select id="ingred" >
                                <% for (Ingredient ingr : ingredients) {%>
                                <option value="<%= ingr.getIngredientId()%>"><%= ingr.getName()%></option>
                                <% } %>
                            </select>
                            <input type="text" id="qty" placeholder="Quantity" required>
                            <!-- <button type="button" onclick="removeIngredientRow(this)">Remove</button> -->
                        </div>
                        <br>
                    </div>
                    <br>

                    <div>
                        <button type="button" onclick="addIngredientRow()">Add Ingredient</button>
                    </div>
                    <br>
                    <button type="submit" class="addproduct">Submit Order</button>
                </div>  
                <br>

            </div>         
        </form>

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
                 var ingredSelect = document.getElementById("ingred");
                recipeIngred.textContent = ingredSelect.options[ingredSelect.selectedIndex].textContent + " : " + document.getElementById("qty").value;

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
