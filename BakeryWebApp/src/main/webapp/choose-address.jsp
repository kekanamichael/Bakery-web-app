<%-- 
    Document   : choose-address
    Created on : Jun 21, 2023, 3:25:05 AM
    Author     : Admin
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.techgiants.topiefor.model.Address"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <title>Delivery Address</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            padding: 20px;
        }

        h1 {
            color: #ff6600;
            text-align: center;
            margin-top: 0;
        }

        .address-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
            width: 800px;
            margin: auto;
        }

        .address-list {
            list-style-type: none;
            padding: 0;
        }

        .address-list li {
            margin-bottom: 10px;
        }

        .add-address {
            background-color: #ff6600;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .add-address:hover {
            background-color: #e65c00;
        }

        .form-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
            display: none;
            width: 500px;
            margin: auto;

        }

        .form-container label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .form-container input[type="text"],
        .form-container input[type="radio"] {
            width: 100%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            margin-bottom: 10px;
        }

        .form-container .submit-button {
            background-color: #ff6600;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-container .submit-button:hover {
            background-color: #e65c00;
        }

    </style>
</head>
<body>

    <%
        HashMap<Integer, Address> addresses = (HashMap<Integer, Address>) session.getAttribute("addresses");
    %>

    <h1>Delivery Address</h1>

    <div class="address-container">
        <form action="service" method="post">
            <h2>Choose an Address</h2>
            <ul class="address-list">
                <%
                    if (addresses != null) {
                        Iterator<Integer> userAddrIds = (Iterator<Integer>) addresses.keySet().iterator();
                        Address addr;
                        int userAddrId;
                        while (userAddrIds.hasNext()) {
                            userAddrId = userAddrIds.next();
                            addr = addresses.get(userAddrId);
                %>
                <li>
                    <input hidden="hidden" value="adding-address" name="process">
                    <input hidden="hidden" value="client" name="service">
                    <input type="radio" name="user-address-id" id="address1" value="<%= userAddrId%>">
                    <label for="address1"><%= addr.getStreetName()%>, <%= addr.getSuburb()%>, <%= addr.getCity()%>
                        <br><%= addr.getProvince()%>, <%= addr.getCountry()%>, <%= addr.getPostalCode()%></label>
                </li>
                <%
                        }
                    }
                %>

            </ul>

            <button type="submit" class="add-address">Proceed To Payment</button>
        </form>
        <br>
        <button class="add-address" onclick="showAddAddressForm()">Add New Address</button>



    </div>
    <br>
    <div class="form-container" id="addAddressForm">
        <h2>Add New Address</h2>
        <form action="service" method="POST">

            <input type="text"  name="strName" placeholder="Enter street address" required />
            <input type="text"  name="city" placeholder="Enter your city" required />

            <div class="column">
                <input type="text"  name="suburb" placeholder="Enter your suburb" required />
                <input type="text"  name="province" placeholder="Enter your province" required />
                <input type="number"  name="zipcode" placeholder="Enter postal code" required />
                <input type="text"  name="country" placeholder="Enter your country" required />
                <input hidden="hidden" value="add-new-address" name="process">
                <input hidden="hidden" value="client" name="service">

                <button class="submit-button" type="submit">Save Address</button>

            </div>
        </form>
    </div>
    <script>
        function showAddAddressForm() {
            var formContainer = document.getElementById('addAddressForm');
            formContainer.style.display = 'block';
        }
    </script>

    <script>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null && !msg.isEmpty()) {
                request.setAttribute("msg", null);
        %>
        alert("<%= msg%>");
        <%
            }
        %>
    </script>

</body>

</html>
