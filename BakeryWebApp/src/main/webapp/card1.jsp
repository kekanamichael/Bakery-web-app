<%--
    Document   : card
    Created on : Jun 19, 2023, 8:41:54 AM
    Author     : Train
--%>

<%@page import="com.techgiants.topiefor.model.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.techgiants.topiefor.model.OrderItem"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.techgiants.topiefor.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Catalog</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="Styles/css/style.css">

        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">


        <style>
            @import url('https://fonts.googleapis.com/css2?family=Nunito:wght@200;300;400;500;600&display=swap');

            *{
                font-family: 'Nunito', sans-serif;
                margin:0;
                padding:0;
                box-sizing: border-box;
                outline: none;
                border:none;
                text-decoration: none;
                transition: all .2s linear;
                text-transform: capitalize;
            }

            html{
                font-size: 62.5%;
                overflow-x: hidden;
            }

            body{
                background: #eee;
            }

            .container{
                max-width: 1200px;
                margin:0 auto;
                padding:3rem 2rem;
            }

            .container .title{
                font-size: 3.5rem;
                color:#444;
                margin-bottom: 3rem;
                text-transform: uppercase;
                text-align: center;
            }

            .container .products-container{
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(30rem, 1fr));
                gap:2rem;
            }

            .container .products-container .product{
                text-align: center;
                padding:3rem 2rem;
                background: #fff;
                box-shadow: 0 .5rem 1rem rgba(0,0,0,.1);
                outline: .1rem solid #ccc;
                outline-offset: -1.5rem;
                cursor: pointer;
                padding-bottom: 50px;
            }

            .container .products-container .product:hover{
                outline: .2rem solid #222;
                outline-offset: 0;
            }

            .container .products-container .product img{
                height: 20rem;
                width: 100%;
            }

            .container .products-container .product:hover img{
                transform: scale(.9);
            }

            .container .products-container .product h3{
                padding:.5rem 0;
                font-size: 2rem;
                color:#444;
            }

            .container .products-container .product:hover h3{
                color:#27ae60;
            }

            .container .products-container .product .price{
                font-size: 2rem;
                color:#444;
            }

            .products-preview{
                position: fixed;
                top:0;
                left:0;
                min-height: 100vh;
                width: 100%;
                background: rgba(0,0,0,.8);
                display: none;
                align-items: center;
                justify-content: center;
            }

            .products-preview .preview{
                display: none;
                padding:2rem;
                text-align: center;
                background: #fff;
                position: relative;
                margin:2rem;
                width: 40rem;
            }

            .products-preview .preview.active{
                display: inline-block;
            }

            .products-preview .preview img{
                height: 30rem;
            }

            .products-preview .preview .fa-times{
                position: absolute;
                top:1rem;
                right:1.5rem;
                cursor: pointer;
                color:#444;
                font-size: 4rem;
            }

            .products-preview .preview .fa-times:hover{
                transform: rotate(90deg);
            }

            .products-preview .preview h3{
                color:#444;
                padding:.5rem 0;
                font-size: 2.5rem;
            }

            .products-preview .preview p{
                line-height: 1.5;
                padding:1rem 0;
                font-size: 1.6rem;
                color:#777;
            }

            .products-preview .preview .price{
                padding:1rem 0;
                font-size: 2.5rem;
                color:#27ae60;
            }

            .products-preview .preview .buttons{
                display: flex;
                gap:1.5rem;
                flex-wrap: wrap;
                margin-top: 1rem;
            }

            .products-preview .preview .buttons a{
                flex:1 1 16rem;
                padding:1rem;
                font-size: 1.8rem;
                color:#444;
                border:.1rem solid #444;
            }

            .products-preview .preview .buttons a.cart{
                background: #444;
                color:#fff;
            }

            .products-preview .preview .buttons a.cart:hover{
                background: #111;
            }

            .products-preview .preview .buttons a.buy:hover{
                background: #444;
                color:#fff;
            }


            @media (max-width:991px){

                html{
                    font-size: 55%;
                }

            }

            @media (max-width:768px){

                .products-preview .preview img{
                    height: 25rem;
                }

            }

            @media (max-width:450px){

                html{
                    font-size: 50%;
                }

            }
            .buttons .cart{
                background: #de6900;
                color:#fff;
                width: 100%;
                padding: 10px;
            }


            /* Add your CSS styles here */
            .popup-slider {
                position: fixed;
                top: 0;
                right: 0;
                width: 350px;
                height: 100vh;
                background-color: white;
                transform: translateX(100%);
                transition: transform 0.3s ease-in-out;
                overflow-y: auto;
                padding: 20px;
            }

            .popup-slider.open {
                transform: translateX(0%);
            }

            .popup-slider-close {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 20px;
                cursor: pointer;
            }


            .listCard {
                height: 350px; /* Adjust the height according to your needs */
                overflow-y: auto;
            }
            .listCard li{
                display: grid;
                grid-template-columns: 100px repeat(3, 1fr);
                color: #000000;
                row-gap: 10px;
            }
            .listCard li div{
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .listCard li img{
                width: 90%;
                padding: 10px;
            }
            .listCard li button{
                background-color: #fff5;
                border: none;
            }
            .listCard .count{
                margin: 0 10px;
            }

            .btn-clear{
                padding: 14px 27px;
                height: 35px;
                background-color: #de6900;
                color: #fff;
                font-size: 11px;
                font-weight: 700;
                letter-spacing: 1px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
                /*    margin-bottom: 100px;*/
            }
            .btn-checkout{
                padding: 14px 27px;
                height: 35px;
                background-color: #de6900;
                color: #fff;
                font-size: 11px;
                font-weight: 700;
                letter-spacing: 1px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
            }

            #image-container{
                width:50%;
            }
            #image{
                width:100%;
                height: 100%;
            }
            #prdDetails{
                background-color: #fff;
                width: 50%;
                text-align: left;
                padding-left: 25px;
                font-size: 18px;
                /*                padding: 20px;*/
            }


            .cart-actions{
                /*                margin-bottom: 200px;*/
                padding-top: 50px;

            }
            .nutr{
                font-size: 17px;
                font-weight: bold;
            }
            .warn{
                font-size: 17px;
                font-weight: bold;
            }
            .line{
                width: 80px;
                background-color: orangered;
                height: 5px;
                margin-bottom: 20px;
                margin-top: 0px;

            }
            .close {
                position: relative;
                top: 10px;
                right: 10px;
                cursor: pointer;
                font-size: 24px;
            }
            .close:hover{
                color:red;
                font-weight: bold;
                font-size: 28px;
            }
            .popup-content {
                margin-top: 50px;
                margin: 20px;
                width: 95%;
                animation: popupAnimation 0.7s ease;
            }
            .detail-container{
                display: flex;
                width: 95%;
                box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.4);
            }
            .popup {
                display: none;
                position: fixed;
                z-index: 9999;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.3);
            }

            .popup.active {
                display: block;
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
            .top{
                display: flex;
                justify-content: space-between
            }
            .buttonss .cartt{
                background: #de6900;
                color:#fff;
                width: 30%;
                padding: 10px;
                font-size: 20px;
                border: none;
                margin-bottom: 40px;
            }
            .pricee{
                font-size: 20px;
                color: brown;
                margin-top: 20px;
                margin-bottom: 5px;
            }
            p.nutr{
                font-size: 20px;
            }
            #prod-name{
                font-family: 'Raleway',sans-serif;
            }
        </style>

    </head>

    <body>

        <%
            HashMap<Integer, OrderItem> items = (HashMap<Integer, OrderItem>) session.getAttribute("cart");
            User user = (User) session.getAttribute("user");
            List<Product> prods = (List<Product>) request.getAttribute("prods");
            double totAmountDue = 0;
        %>

        <div id="wrapper" class="wrapper">
            <div id="popup" class="popup">
                <div class="popup-content">
                    <!--<span class="close" onclick="closePopup()">&times;</span>-->
                    <div class="detail-container">
                        <div id="image-container" class="image-container">
                            <img id="image" src="assets\blue-cupcake.jpg">
                        </div>
                        <div id="prdDetails" class="prodDetails">
                            <div class="top"><h1 id="prod-name">BLUEBERRY MUFFIN </h1><span class="close" onclick="closePopup()">&times;</span></div>
                            <h3 id="pro-cat">Muffins</h3>
                            <br>
                            <label for="nutr">Nutrient information:</label>
                            <p id="nutrient-info" class="nutrient-info"><p class="nutr"></p></p>
                            <br>
                            <label for="warn">Warnings: </label>
                            <p id="warnings" class="warnings"><p class="warn"></p></p>
                            <br>
                            <p id="price" class="pricee">R120.00</p>
                            <div class="line"></div>
                            <form method="POST" action="service">
                                <input hidden="hidden" value="add-new-item" name="sub-process">
                                <input hidden="hidden" value="update-cart" name="process">
                                <input hidden="hidden" value="guest" name="service">
                                <input hidden="hidden" value="prodId" name="prod-id">
                                <div class="buttonss">
                                    <input class="cartt" type="submit" value="Add to Cart">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <nav class="navbar">
            <div class="inner-width">
                <a href="index.jsp" class="logo">ToPieFor</a>
                <div class="navbar-menu">
                    <a href="index.jsp">Home</a>
                    <%
                        if (user == null) {
                    %>
                    <a href="login.jsp">Login</a>

                    <%
                    } else {
                    %>

                    <a href="user-profile.jsp">Profile</a>
                    
                    <a href="service?service=client&process=all-orders">orders</a>
                    <%
                        if (user.getRole().getRoleId() == 1) {
                    %>

                    <a href="asAdmin.jsp">As Admin</a>
                    <%
                        }
                    %>
                    <a href="service?service=client&process=logout">Logout</a>
                    <%
                        }
                    %>
                    <a href="#" class="shopping" id="navbar">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="quantity"><%= items != null ? items.size() : 0%></span>
                    </a>
                </div>
            </div>
        </nav>


        <div class="container">

            <h3 class="title"> MNANDILICIOUS PRODUCTS </h3>

            <div class="products-container">

                <%
                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    for (Product prod : prods) {
                %>
                <div class="product" id="pro-id" data-name="p-1" onclick="openPopup('Styles/img/<%= prod.getImage()%>', '<%= prod.getUnitPrice()%>', '<%= prod.getNutrientInfo()%>', '<%= prod.getWarnings()%>', '<%= prod.getName()%>', '<%= prod.getCategory().getName()%>')">
                                                    <!--<img src="Styles/img/BROWNIE.jpg" onclick="viewProduct(<%= prod.getProductId()%>)" alt="">-->
                    <img src="Styles/img/<%=prod.getImage()%>" style="width:"auto" alt="">
                    <h3 id="prod-name"><%= prod.getName()%></h3>
                    <div id="price" class="price">R<%= df.format(prod.getUnitPrice()) %></div>
                    <br>

                    <form method="POST" action="service">
                        <input hidden="hidden" value="add-new-item" name="sub-process">
                        <input hidden="hidden" value="update-cart" name="process">
                        <input hidden="hidden" value="guest" name="service">
                        <input hidden="hidden" value="<%= prod.getProductId()%>" name="prod-id">

                        <div class="buttons">
                            <input class="cart" type="submit" value="add to cart">
                        </div>
                    </form>


                </div>
                <%
                    }
                %>


            </div>

        </div>


        <div class="card">
            <div class="popup-slider" id="cartSlider">
                <span class="popup-slider-close" onclick="closeCartSlider()">&times;</span>
                <h1>Cart</h1>
                <div class="card-container">
                    <ul class="listCard">

                        <%
                            if (items != null) {
                                Iterator<Integer> iter = items.keySet().iterator();
                                OrderItem it;
                                int ProdId;
                                while (iter.hasNext()) {
                                    ProdId = iter.next();
                                    it = items.get(ProdId);
                                    totAmountDue += it.getProduct().getUnitPrice() * it.getQuantity();
                        %>
                        <li>
                            <div><img src="Styles/img/<%= it.getProduct().getImage()%>"></div>
                            <div><%= it.getProduct().getName()%></div>
                            <div>R<%= df.format(it.getProduct().getUnitPrice()) %></div>
                            <div>
                                <form action="service" method="POST">
                                    <input hidden="hidden" value="update-item-qty" name="sub-process">
                                    <input hidden="hidden" value="update-cart" name="process">
                                    <input hidden="hidden" value="guest" name="service">
                                    <input hidden="hidden" value="-" name="operation">
                                    <input hidden="hidden" value="<%= ProdId%>" name="prod-id">
                                    <button type="submit"">-</button>
                                </form>
                                <div class="count"><%= it.getQuantity()%></div>
                                <form action="service" method="POST">
                                    <input hidden="hidden" value="update-item-qty" name="sub-process">
                                    <input hidden="hidden" value="update-cart" name="process">
                                    <input hidden="hidden" value="+" name="operation">
                                    <input hidden="hidden" value="guest" name="service">
                                    <input hidden="hidden" value="<%= ProdId%>" name="prod-id">

                                    <button type="submit"">+</button>
                                </form>
                            </div>
                        </li>
                        <%
                                }
                            }
                        %>

                    </ul>
                </div>
                <div class="cart-actions">
                    <div>
                        <h3>TOTAL PRICE: </h3> <div class="total"><h3><%= df.format(totAmountDue)%></h3></div> 
                    </div >
                    <br>
                    <div style=" width:fit-content;height: fit-content; display: flex">
                        <form style="margin:10px" action="service" method="POST">
                            <input hidden="hidden" value="clear-cart" name="process">
                            <input hidden="hidden" value="guest" name="service">
                            <input type="submit" class="btn btn-clear" value="Clear Cart">
                        </form>
                        <form style="margin:10px" action="#">
                            <input hidden="hidden" value="check-out" name="process">
                            <input hidden="hidden" value="client" name="service">
                            <input type="submit" class="btn btn-clear" value="Checkout">
                        </form>
                        </di>
                    </div>
                </div>
            </div>

            <script>
                let cartItems = [];

                function addToCart(item) {
                    cartItems.push(item);
                    updateCartIcon();
                }

                function updateCartIcon() {
                    const cartQuantity = document.querySelector('.quantity');
                    cartQuantity.textContent = cartItems.length;
                }

                const cartIcon = document.getElementById('navbar');
                const cartPopup = document.getElementById('cartSlider');

                cartIcon.addEventListener('click', () => {
                    openCartSlider();
                });

                function openCartSlider() {
                    cartPopup.classList.add('open');
                }

                function closeCartSlider() {
                    cartPopup.classList.remove('open');
                }

                // Close the slider if clicked outside
                window.addEventListener('click', (event) => {
                    if (event.target !== cartIcon && !cartPopup.contains(event.target)) {
                        closeCartSlider();
                    }
                });

                // Close the slider on ESC key press
                document.addEventListener('keydown', (event) => {
                    if (event.key === 'Escape') {
                        closeCartSlider();
                    }
                });

                function clearCart() {
                    cartItems = [];
                    updateCartIcon();
                }

                function checkout() {
                    // Implement your checkout logic here
                }

                function openPopup(proImage, proPrice, nutrientInfo, proWarnings, proName, proCategory) {
                    var popup = document.getElementById("popup");
                    popup.classList.add("active");

                    document.getElementById("image").src = proImage;
                    document.getElementById("nutrient-info").textContent = nutrientInfo;
                    document.getElementById("prod-name").textContent = proName;
                    document.getElementById("pro-cat").textContent = proCategory;
                    document.getElementById("price").textContent = proPrice;
                    document.getElementById("warnings").textContent = proWarnings;
                }

                function closePopup() {
                    var popup = document.getElementById("popup");
                    popup.classList.remove("active");
                }

            </script>

            <script>
                function viewProduct(prodName) {
                    alert(++prodName);
                }

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
