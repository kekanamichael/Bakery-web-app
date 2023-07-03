<%-- 
    Document   : pay
    Created on : Jun 14, 2023, 9:30:25 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href=" https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js" rel="stylesheet">
        <link href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" rel="stylesheet">

        <style>
            body{
                background: #f5f5f5
            }
            .rounded{
                border-radius: 1rem
            }
            .nav-pills .nav-link{
                color: #555
            }
            .nav-pills .nav-link.active{
                color: white
            }
            input[type="radio"]{
                margin-right: 5px
            }
            .bold{
                font-weight:bold
            }

            .btn-primary{
                background-color: #ff6600;
                color: #fff;
                border: none;
                border-radius: 3px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
                background-color: #ff6600;
            }
            .card {
                position: relative;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #ECECEC;
                background-clip: border-box;
                border: 1px solid rgba(0,0,0,.125);
                border-radius: .25rem;
            }
            h1 {
                color: #ff6600;
                text-align: center;
                margin-top: 0;
            }
            .btn-primary:hover {
                background-color: #ff6600;
            }

        </style>
    </head>
    <body>

        <div class="container py-5">

            <div class="row mb-4">
                <div class="col-lg-8 mx-auto text-center">
                    <h1 class="display-6">Payment Information</h1>
                </div>
            </div> <!-- End -->
            <div class="row">
                <div class="col-lg-6 mx-auto">
                    <div class="card ">
                        <div class="card-header">
                            <div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
                                <!-- Credit card form tabs -->
                                <ul role="tablist" class="nav bg-light nav-pills rounded nav-fill mb-3">
                                    <li class="nav-item"> <a data-toggle="pill" href="#credit-card" class="nav-link active "> <i class="fas fa-credit-card mr-2"></i> Credit Card </a> </li>
                                    <li class="nav-item"> <a data-toggle="pill" href="#paypal" class="nav-link "> <i class="fab fa-paypal mr-2"></i> Paypal </a> </li>
                                </ul>
                            </div> <!-- End -->
                            <!-- Credit card form content -->
                            <div class="tab-content">
                                <!-- credit card info-->
                                <div id="credit-card" class="tab-pane fade show active pt-3">

                                    <div class="form-group"> <label for="username">
                                            <h6>Card Owner</h6>
                                        </label> <input type="text" name="username" placeholder="Card Owner Name" required class="form-control "> </div>
                                    <div class="form-group"> <label for="cardNumber">
                                            <h6>Card number</h6>
                                        </label>
                                        <div class="input-group"> <input type="text" name="cardNumber" placeholder="Valid card number" class="form-control " required>
                                            <div class="input-group-append"> <span class="input-group-text text-muted"> <i class="fab fa-cc-visa mx-1"></i> <i class="fab fa-cc-mastercard mx-1"></i> <i class="fab fa-cc-amex mx-1"></i> </span> </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <div class="form-group"> <label><span class="hidden-xs">
                                                        <h6>Expiration Date</h6>
                                                    </span></label>
                                                <div class="input-group"> <input type="number" placeholder="MM" name="" class="form-control" required> <input type="number" placeholder="YY" name="" class="form-control" required> </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group mb-4"> <label data-toggle="tooltip" title="Three digit CV code on the back of your card">
                                                    <h6>CVV <i class="fa fa-question-circle d-inline"></i></h6>
                                                </label> <input type="text" required class="form-control"> </div>
                                        </div>
                                    </div>
                                    <div class="card-footer"> 
                                        <form action="service" method="GET">
                                            <input hidden="hidden" value="process-order" name="process">
                                            <input hidden="hidden" value="client" name="service">
                                            <button type="submit" class="subscribe btn btn-primary btn-block shadow-sm"> Confirm Payment </button>  
                                        </form>

                                    </div>
                                </div> <!-- End -->
                                <!-- Paypal info -->
                                <div id="paypal" class="tab-pane fade pt-3">
                                    <p> <button type="button" class="btn btn-primary" onclick="redirectToPayPal()"><i class="fab fa-paypal mr-2"></i> Log into my PayPal</button> </p>
                                    <p class="text-muted"> Note: After clicking on the button, you will be directed to a secure gateway for payment. After completing the payment process, you will be redirected back to the website to view details of your order. </p>
                                </div> <!-- End -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

            <script>
                                        $(function () {
                                            $('[data-toggle="tooltip"]').tooltip();
                                        });

                                        function redirectToPayPal() {
                                            // Redirect to PayPal login page or implement your own logic here
                                            console.log("Redirecting to PayPal...");
                                        }
            </script>

    </body>
</html>
