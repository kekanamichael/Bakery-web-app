

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>User Profile</title>
        <style>
            /* CSS styling for the user profile */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f1f1f1;
            }

            h1 {
                color: #333;
            }

            .profile {
                display: flex;
                align-items: center;
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            .profile-image {
                margin-right: 20px;
            }

            .profile-image img {
                width: 150px;
                height: 150px;
                border-radius: 50%;
            }

            .profile-details {
                flex-grow: 1;
            }

            .profile-details h2 {
                margin-top: 0;
                margin-bottom: 10px;
            }

            .profile-details p {
                margin-top: 0;
                color: #777;
            }

            .update-button {
                background-color: #de6900;
                border: none;
                color: #fff;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            /* CSS styling for the popup form */
            .popup {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .popup-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                width: 300px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                color: #333;
            }

            .form-group input[type="text"] {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            .form-group input[type="submit"] {
                background-color: #de6900;
                border: none;
                color: #fff;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;

            }

            .form-group input[type="submit"]:hover {
                background-color: #45a049;
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
    </head>
    <body>
        <div class="profile">
            <div class="profile-image">
                <img src="assets/pic.png" alt="Profile Image">
            </div>
            <div class="profile-details">
                <h2>Michael Kekana</h2>
                <p>Email: kekanamichael1999@gmail.com</p>
                <p>Location: 158 Johnson St, Sunnyside USA</p>
                <p>0716455987</p>
                <button class="update-button" onclick="openPopup()">Update Profile</button>
            </div>
        </div>

        <!-- Popup form for updating the profile -->
        <div id="popup" class="popup">
            <div class="popup-content">
                <span class="close" onclick="closePopup()">&times;</span>
                <h2>Update Profile</h2>
                <form>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" placeholder="Enter your name">
                    </div>
                    <div class="form-group">
                        <label for="name">Surname:</label>
                        <input type="text" id="name" name="name" placeholder="Enter your surname">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" placeholder="Enter your email">
                    </div>

                    <div class="form-group">
                        <label for="occupation">Contact number:</label>
                        <input type="text" id="occupation" name="occupation" placeholder="Enter your contacts">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Save">
                    </div>
                </form>
            </div>
        </div>

        <script>
            // JavaScript functions for opening and closing the popup
            function openPopup() {
                document.getElementById("popup").style.display = "block";
            }

            function closePopup() {
                document.getElementById("popup").style.display = "none";
            }
        </script>
    </body>
</html>

