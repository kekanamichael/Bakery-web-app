<%-- 
    Document   : myOrders
    Created on : Jun 21, 2023, 1:23:20 AM
    Author     : Admin
--%>

<%@page import="com.techgiants.topiefor.model.Delivery"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.techgiants.topiefor.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>

        <link rel="stylesheet" href="Styles/css/style.css">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style>
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

            .close {
                position: absolute;
                top: 10px;
                right: 10px;
                cursor: pointer;
            }
            #pop-form {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
            }

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
            .grid-container {
                display: grid;
                grid-template-columns: auto auto;
                background-color: #DE6900;
                padding: 10px;
            }
            .grid-item {
                background-color: rgba(255, 255, 255, 0.8);
                border: 1px solid rgba(0, 0, 0, 0.8);
                padding: 10px;
                font-size: 15px;
                text-align: center;
            }
            .navbar{
                position: flex;
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

            /*SEARCH BAR STYLES*/
            .search-box {
                position: relative;
                float: left;
                padding-bottom: 20px;
            }
            .search-box input {
                height: 34px;
                border-radius: 20px;
                padding-left: 35px;
                border-color: #ddd;
                box-shadow: none;
            }
            .search-box input:focus {
                border-color: #3FBAE4;
            }
            .search-box i {
                color: #a0a5b1;
                position: absolute;
                font-size: 19px;
                top: 8px;
                left: 10px;
            }

            .date-input {
                display: inline-block;
                margin-right: 20px;
            }

        </style>
    </head>
    <body>
        <nav class="navbar" id="navbar">
            <div class="inner-width">

                <a href="#" class="logo">ToPieFor</a>
                <div class="navbar-menu">
                    <a href="index.jsp">Home</a>
                    <a href="card.jsp">Products</a>
                    <a href="login.jsp">LogOut</a>

                </div>
            </div>
        </nav>

        <div id="popup" class="popup">
            <div class="popup-content">
                <span class="close" onclick="closePopup()">&times;</span>
                <h3>Ordered Items</h3>
                <form id="pop-form" action="#" method="POST">

                    <div class="grid-container">
                        <div class="grid-item">CARROT CAKE: 2</div>
                        <div class="grid-item">BLUEBERRY MUFFIN: 5</div>
                        <div class="grid-item">BROWN BREAD: 1</div>
                    </div>

                    <label for="total-cost">Total Cost:</label>
                    <input type="text" id="order-id" name="total-cost" required>

                </form>
            </div>
        </div>
        <h1>Order details</h1>

        <!--        SEARCH BAR-->

        <!--        <div class="search-box">
                    <i class="material-icons">&#xE8B6;</i>
                    <input type="text" class="form-control" placeholder="Search&hellip;">
                </div>-->

        <!--END OF SEARCH BAR-->
        <form action="#" method="POST">
            <div>
                <div class="date-input">
                    <label for="start-date">Start Date:</label>
                    <input type="date" id="start-date" name="date">
                </div>

                <div class="date-input">
                    <label for="end-date">End Date:</label>
                    <input type="date" id="end-date" name="date">
                </div>
                <button type="submit">Filter Results</button>
            </div>

        </form>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Order No#</th>
                    <th>Order Status</th>
                    <th>Total Amount</th>
                    <th>Order Date</th>
                    <th>Delivery Date</th>
                    <th>Order Items</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Order> orders = (List<Order>) request.getAttribute("my-orders");
                    DecimalFormat df = new DecimalFormat("00000"),dfMoney = new DecimalFormat("#.00");
                    
                    if (orders != null) {
                        Delivery dD;
                        for (Order ord : orders) {
                             dD = ord.getDelivery();
                %>

                <tr>
                    <td>#<%= df.format(ord.getOrderId()) %></td>
                    <td><%= ord.getStatus().name() %></td>
                    <td>R<%= dfMoney.format(ord.getTotalAmount()) %></td>
                    <td><%= ord.getOrderDate().toLocalDate().toString() %></td>
                    <td><%= dD.getDeliveryDate()==null? "NOT YET DELIVERED":dD.getDeliveryDate().toLocalDate().toString() %></td>
                    
                    <td><button onclick="openPopup()">View</button>
                        <a href="service?service=client&process=get-invoice&invoice-id=<%= ord.getInvoice().getInvoiceId() %>">
                            <button >Get Invoice</button>
                        </a></td>
                    
                </tr>
                <%
                        }
                    }
                %>

            </tbody>
        </table>

        <script>
            function openPopup() {
                var popup = document.getElementById("popup");
                popup.classList.add("active");
            }

            function closePopup() {
                var popup = document.getElementById("popup");
                popup.classList.remove("active");
            }
        </script>
    </body>
</html>
