<%-- 
    Document   : asAdmin
    Created on : Jun 14, 2023, 3:26:02 PM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

     Google Web Fonts 
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 
    
     Icon Font Stylesheet 
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

     Libraries Stylesheet 
    <link href="Styles/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="Styles/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

     Customized Bootstrap Stylesheet 
    <link href="Styles/css/bootstrap.min.css" rel="stylesheet">

     Template Stylesheet 
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
                max-width: 400px;
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
                    
<!--                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-th me-2"></i>Products</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="addProduct.jsp" class="dropdown-item">Add New Product</a>
                            <a href="view-products.jsp" class="dropdown-item">View Products</a>
                        </div>
                    </div>-->
                    
                     <a href="product.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Products</a>
                     
                     <a href="category.jsp" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Categories</a>
                    
                     <a href="order.jsp" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Orders</a>
                    
                    <a href="ingredient.jsp" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Ingredients</a>
                    
                    <a href="ingredient-order.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Ingredient Order</a>
                    
                    <a href="#" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Report</a>
                    
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
                            <span class="d-none d-lg-inline-flex">Admin Name</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
                            <a href="login.jsp" class="dropdown-item">Log Out</a>
                            <a href="card.jsp" class="dropdown-item">As Customer</a>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->


            <!-- Sale & Revenue Start -->
            <div class="container-fluid pt-4 px-4">
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
            </div>
            <!-- Sale & Revenue End -->


               

        </div>
        <!-- Content End -->

    </div>
    
    
    

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