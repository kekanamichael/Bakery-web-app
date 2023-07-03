<%-- 
    Document   : product-details
    Created on : Jun 20, 2023, 9:31:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

        <style>
            body{
                background-color: #f5deb3;
            }
            #wrapper{
                /*                display: flex;
                                width: 95%;
                                box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.4);*/
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
                /*                padding: 20px;*/
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
        </style>
    </head>
    <body>
    <center>
        <h1>Product Details</h1>
        <button onclick="openPopup()">Open Popup</button>

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
                            <h3>Muffins</h3>
                            <hr>
                            <p id="nutrient-info" class="nutrient-info"><p class="nutr">Nutrient information:</p> Calories: Approximately 200-250 calories per muffin
                            Sodium: 150-300 milligrams</p>
                            <hr>
                            <p id="warnings" class="warnings"><p class="warn">Warnings: </p> Blueberry muffins can be high in calories and sugar due to the presence of refined flour, added sugars, and sometimes even streusel toppings.</p>
                            <hr>
                            <p id="price" class="price">R120.00</p>
                            <div class="line"></div>
                            <form method="POST" action="service">
                                <input hidden="hidden" value="add-new-item" name="sub-process">
                                <input hidden="hidden" value="update-cart" name="process">
                                <input hidden="hidden" value="guest" name="service">
                                <input hidden="hidden" value="prodId" name="prod-id">
                                <div class="buttons">
                                    <input class="cart" type="submit" value="Add to Cart">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </center>
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
