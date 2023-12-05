<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <title>Sign Up</title>

    <style>
        body {
            font-family: "Lato", sans-serif;
            background-color: #19222a;
        }

        .signin-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .signin-form {
            width: 300px;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 5px;
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .signin-form h2 {
            text-align: center;
        }

        .signin-form input[type="text"],
        .signin-form input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .signin-form button {
            width: 100%;
            padding: 10px;
            background-color: #ff6600;
            color: white;
            border: none;
            border-radius: 15px;
            padding: 15px 15px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .signin-form button:hover {
            background-color: #ffe3d0;
            color: #ff6600;
        }
    </style>

</head>
<body>
<div class="signin-container">
    <div class="signin-form">
        <h2>Sign Up</h2>
        <form action="../user/sign-up" method="post">
            <input type="text" id="username" name="username" placeholder="Username" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <button type="submit">Sign Up</button>
        </form>
    </div>
</div>
</body>
</html>
