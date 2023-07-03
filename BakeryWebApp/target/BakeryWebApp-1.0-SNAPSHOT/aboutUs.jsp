<%-- 
    Document   : aboutUs
    Created on : Jun 13, 2023, 2:37:01 PM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
         <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
<!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
  <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">
  
  
  <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700|Raleway" rel="stylesheet">
    <link rel="stylesheet" href="Styles/css/bootstrap.min.css">
    <link rel="stylesheet" href="Styles/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="Styles/css/animate.css">
    
    <link rel="stylesheet" href="Styles/css/owl.carousel.min.css">
    <link rel="stylesheet" href="Styles/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="Styles/css/magnific-popup.css">

    <link rel="stylesheet" href="Styles/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="Styles/css/jquery.timepicker.css">

   <link rel="stylesheet" href="Styles/css/icomoon.css">

<style>
       @import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css);

   
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

    
@media screen and (max-width: 768px)
#section-about .img img {
    max-width: 100%;
}

img {
    vertical-align: middle;
    border-style: none;
    width: 100%;
    height: 80%;
    
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
body {
    margin: 0;
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    text-align: left;
    background-color: #fff;
}
</style>


    </head>
    
    <body>
        
        <nav class="navbar" id="navbar">
    <div class="inner-width">
        
      <a href="#" class="logo">ToPieFor</a>
      <div class="navbar-menu">
        <a href="index.jsp">Home</a>
<!--        <a href="login.jsp">Login</a>
        <a href="signup.jsp">SignUp</a>
        <a href="menu.jsp">Menu</a>
        <a href="aboutUs.jsp">About</a>
        <a href="#">Contact</a>-->
        
      </div>
    </div>
  </nav>
        
       </section>
    <section class="ftco-section" id="section-about">
      <div class="container">
        <div class="row">
          <div class="col-md-5 ftco-animate mb-5">
<!--            <h4 class="ftco-sub-title">About Us</h4>-->
            <h2 class="ftco-primary-title display-4">Welcome</h2>
            <p>ToPieFor is a family owned bakery that produces what many say are the best tasting, highest quality cakes, pastries and other baked goods in South Africa</p>

            <p class="mb-4">ToPieFor is the best known bakery in South Africa, and takes pride in providing its customers with elegant Brownies, Cakes, Fresh Breads, Cupcakes, Pies, and a large variety of Cookies and Pastries whose taste cannot be surpassed.</p>
            <p class="mb-4">ToPieFor is dedicated to quality and excellence, and thinks of every customer as an extension of their family. For a special treat or a cake for any occasion, pay us a visit, stay a while, and allow us to share with you the time honored tradition that has made us what we are today</p>
            
            <p><a href="#" class="btn btn-secondary btn-lg">Our Story</a></p>
          </div>
          <div class="col-md-1"></div>
          <div class="col-md-6 ftco-animate img" data-animate-effect="fadeInRight">
            <img src="Styles/img/bake.jpg">
          </div>
        </div>
      </div>
    </section>
       
         <script src="Styles/js/jquery.min.js"></script>
    <script src="Styles/js/popper.min.js"></script>
    <script src="Styles/js/bootstrap.min.js"></script>
    <script src="Styles/js/jquery.easing.1.3.js"></script>
    <script src="Styles/js/jquery.waypoints.min.js"></script>
    <script src="Styles/js/owl.carousel.min.js"></script>
    <script src="Styles/js/jquery.magnific-popup.min.js"></script>

    <script src="Styles/js/bootstrap-datepicker.js"></script>
    <script src="Styles/js/jquery.timepicker.min.js"></script>
    
    <script src="Styles/js/jquery.animateNumber.min.js"></script>
    

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
    <script src="Styles/js/google-map.js"></script>

    <script src="Styles/js/main.js"></script>
       
    </body>
</html>
