<%-- 
    Document   : viewIngredientOrder
    Created on : Jun 13, 2023, 1:03:19 PM
    Author     : Train
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>View Ingredient Order</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                background-image: url('Styles/img/alpha.jpg');
                backdrop-filter: blur(3px);
                background-repeat: no-repeat;
                background-size: cover;
                font-family: 'Roboto', sans-serif;

            }

            .table-responsive {
                margin: 0px 0;
                padding-left: 200px;
                height: auto;
            }
            .table-wrapper {
                width: 700px;
                background-color: #f5deb3;
                background-position: center;
                opacity: 0.8;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                height: 605px;
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
                min-width: 100%;
            }
            .table-title h2 {
                margin: 8px 0 0;
                font-size: 22px;
            }

            table.table tr th, table.table tr td {
                border-color: black;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child {
                width: 130px;
                padding-left: 70px;
            }
            table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a.view {
                color: #03A9F4;

            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;

            }
            table.table td i {
                font-size: 19px;
            }
            table.table td:last-child {
                width: 230px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 95%;
                width: 30px;
                height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 30px !important;
                text-align: center;
                padding: 0;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 6px;
                font-size: 95%;
            }

        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Ingredient <b>Details</b></h2></div>
                            <div class="col-sm-4">
                                <!--                        <div class="search-box">
                                                            <i class="material-icons">&#xE8B6;</i>
                                                            <input type="text" class="form-control" placeholder="Search&hellip;">
                                                        </div>-->
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>IngrOrderId</th>
                                <th>Quantity</th>
                                <th>Date</th>
                                <th>Delivered</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>1</td>
                                <td>10</td>
                                <td>date</td>
                                <td>
                                    <span style='font-size:15px'  title="Yes" data-toggle="tooltip">&#128077;</span>
                                    <span style='font-size:15px;' title="Pending" data-toggle="tooltip">&#9203;</span> 
                                    <span style='font-size:15px;' title="No" data-toggle="tooltip">&#128078;</span>

                                </td>

                                <td>
                                    <span style='font-size:15px'  title="Yes" data-toggle="tooltip">&#128077;</span>
                                    <span style='font-size:15px;' title="Pending" data-toggle="tooltip">&#9203;</span> 
                                    <span style='font-size:15px;' title="No" data-toggle="tooltip">&#128078;</span>
                                </td>
                            </tr>

                        </tbody>

                    </table>
                    <!--            <div class="clearfix">
                                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                                    <ul class="pagination">
                                        <li class="page-item disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                                        <li class="page-item"><a href="#" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                                    </ul>
                                </div>-->
                    <form action="service" method="POST">
                        <centre>
                            <input type ="hidden" id = "pro" name="process" value ="ordering-ingredient">
                            <input type ="hidden" id = "pro" name="service"value ="admin">
                        </centre>

                        <input type="submit" value="Order Ingredient"/>
                    </form>
                </div>
            </div>  
        </div>   
    </body>
