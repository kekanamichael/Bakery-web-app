<%-- 
    Document   : card
    Created on : Jun 19, 2023, 8:41:54 AM
    Author     : Train
--%>

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

        <!-- font awesome cdn link  -->
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

        </style>

    </head>
    <body>
        <%
            HashMap<Integer, OrderItem> items = (HashMap<Integer, OrderItem>) session.getAttribute("cart");
        %>
        <nav class="navbar">
            <div class="inner-width">
                <a href="#" class="logo">ToPieFor</a>
                <div class="navbar-menu">
                    <a href="index.jsp">Home</a>
                    <a href="#">Specials</a>
                    <a href="test.jsp">Categories</a>
                    <a href="#">Profile</a>
                    <a href="index.jsp">Logout</a>

                    <a href="#" class="shopping" id="navbar">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="quantity"><%= items != null ? items.size() : 0%></span>
                    </a>

                </div>
            </div>
        </nav>
        <%
            List<Product> prods = (List<Product>) request.getAttribute("prods");
        %>
        <div class="container">

            <h3 class="title"> MNANDILICIOUS PRODUCTS </h3>

            <div class="products-container">

                <%
                    for (Product prod : prods) {
                %>
                <div class="product" onclick="viewProduct(<%= prod.getProductId()%>)" data-name="p-1">
                    <img src="Styles/img/<%=prod.getImage()%>" alt="">
                    <h3><%= prod.getName()%></h3>
                    <div class="price">R<%= prod.getUnitPrice()%></div>
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
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PIE</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(0, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(0, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/alpha.jpg"></div>
                            <div>CAKES</div>
                            <div>120,000</div>
                            <div>
                                <button onclick="changeQuantity(1, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(1, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PRODUCT NAME 4</div>
                            <div>123,000</div>
                            <div>
                                <button onclick="changeQuantity(3, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(3, 2)">+</button>
                            </div>
                        </li>
                        <li>
                            <div><img src="Styles/img/PIE.jpg"></div>
                            <div>PRODUCT NAME 5</div>
                            <div>320,000</div>
                            <div>
                                <button onclick="changeQuantity(4, 0)">-</button>
                                <div class="count">1</div>
                                <button onclick="changeQuantity(4, 2)">+</button>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="cart-actions">
                    <div>
                        <h3>TOTAL PRICE: </h3> <div class="total"><h3>0</h3></div> 
                    </div>
                    <br>

                    <button class="btn btn-clear" onclick="clearCart()">Clear Cart</button>
                    <button class="btn btn-checkout" onclick="checkout()">Checkout</button>
                </div>
            </div>
        </div>

        <script>

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