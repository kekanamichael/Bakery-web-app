<%-- 
    Document   : menu
    Created on : Jun 8, 2023, 11:03:51 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-AU-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device,initial scale=1.0">

        <title>JSP Page</title>
        <link rel="stylesheet" href="Styles/css/style.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <!--     <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">-->

        <style>
            h2 {
                color: #000;
                font-size: 26px;
                font-weight: 300;
                text-align: center;
                text-transform: uppercase;
                position: relative;
                margin: 30px 0 80px;
            }
            h2 b {
                color: #ffc000;
            }
            h2::after {
                content: "";
                width: 100px;
                position: absolute;
                margin: 0 auto;
                height: 4px;
                background: rgba(0, 0, 0, 0.2);
                left: 0;
                right: 0;
                bottom: -20px;
            }

            button.total{
                padding: 14px 27px;
                margin-top: 30px;
                background-color: #de6900;
                /*    border: none;
                    outline: none;*/
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                letter-spacing: 2px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
            }

            button.closeShopping{
                padding: 14px 27px;
                height: 60px;
                background-color: #de6900;
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                letter-spacing: 2px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
            }
            button.proceed{

                padding: 14px 27px;
                height: 60px;
                background-color: #de6900;
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                letter-spacing: 2px;
                font-family: 'Raleway',sans-serif;
                cursor: pointer;
                text-transform: uppercase;
                transition: 0.3s;
            }
            /*        .card-container{
                 height: 350px;  Adjust the height according to your needs 
                 overflow-y: auto;
                 background-color: #E8BC0E;
                    }*/
            .bod{
                padding: 0;
                margin: 0;
            }
            b{
                font-family: 'Raleway',sans-serif;
            }

        </style>

    </head>
    <body class="bod">
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
                        <span class="quantity">0</span>
                    </a>

                </div>
            </div>
        </nav>


        <div class="container">
            <header>
                <h2><b>Products</b></h2>
            </header>

            <div class="list">

            </div>
        </div>

        <div class="card">

            <h1>Cart</h1>
            <div class="card-container">
                <ul class="listCard">
                </ul>
            </div>

            <div class="checkOut">

                <h1>TOTAL PRICE:</h1> <div class="total">0</div>
                <a href="pay.jsp" > <button  class="proceed">PROCEED TO CHECKOUT</button></a>

                <button class="closeShopping">Close</button>
                <!--            <div class="closeShopping">Close</div>-->
            </div>
        </div>


        <script >
            let openShopping = document.querySelector('.shopping');
            let closeShopping = document.querySelector('.closeShopping');
            let list = document.querySelector('.list');
            let listCard = document.querySelector('.listCard');
            let body = document.querySelector('body');
            let total = document.querySelector('.total');
            let quantity = document.querySelector('.quantity');

            openShopping.addEventListener('click', () => {
                body.classList.add('active');
            });
            closeShopping.addEventListener('click', () => {
                body.classList.remove('active');
            });

            let products = [
                {
                    id: 1,
                    name: 'PIE',
                    image: 'PIE.jpg',
                    price: 120000
                },
                {
                    id: 2,
                    name: 'CAKES',
                    image: 'alpha.jpg',
                    price: 120000
                },
                {
                    id: 3,
                    name: 'BREADS',
                    image: 'BREAD.jpg',
                    price: 220000
                },
                {
                    id: 4,
                    name: 'PRODUCT NAME 4',
                    image: 'PIE.jpg',
                    price: 123000
                },
                {
                    id: 5,
                    name: 'PRODUCT NAME 5',
                    image: 'PIE.jpg',
                    price: 320000
                },
                {
                    id: 6,
                    name: 'PRODUCT NAME 6',
                    image: 'PIE.jpg',
                    price: 120000
                }
            ];
            let listCards = [];

            function initApp() {
                products.forEach((value, key) => {
                    let newDiv = document.createElement('div');
                    newDiv.classList.add('item');
                    newDiv.innerHTML = `
                <img src="Styles/img/${value.image}">
                <div class="title">${value.name}</div>
                <div class="price">${value.price.toLocaleString()}</div>
                <button onclick="addToCard(${key})">Add To Card</button>`;
                    list.appendChild(newDiv);
                });
            }
            initApp();

            function addToCard(key) {
                if (listCards[key] == null) {
                    // copy product from list to list card
                    listCards[key] = JSON.parse(JSON.stringify(products[key]));
                    listCards[key].quantity = 1;
                }
                reloadCard();
            }

            function reloadCard() {
                listCard.innerHTML = '';
                let count = 0;
                let totalPrice = 0;
                listCards.forEach((value, key) => {
                    totalPrice = totalPrice + value.price;
                    count = count + value.quantity;
                    if (value != null) {
                        let newDiv = document.createElement('li');
                        newDiv.innerHTML = `
                    <div><img src="Styles/img/${value.image}"/></div>
                    <div>${value.name}</div>
                    <div>${value.price.toLocaleString()}</div>
                    <div>
                        <button onclick="changeQuantity(${key}, ${value.quantity - 1})">-</button>
                        <div class="count">${value.quantity}</div>
                        <button onclick="changeQuantity(${key}, ${value.quantity + 1})">+</button>
                    </div>`;
                        listCard.appendChild(newDiv);
                    }
                });
                total.innerText = totalPrice.toLocaleString();
                quantity.innerText = count;
            }

            function changeQuantity(key, quantity) {
                if (quantity == 0) {
                    delete listCards[key];
                } else {
                    listCards[key].quantity = quantity;
                    listCards[key].price = quantity * products[key].price;
                }
                reloadCard();
            }



        </script>
    </body>
</html>



