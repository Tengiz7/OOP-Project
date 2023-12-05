<%@ page import="main.application.queries.quiz.NewQuizes.NewQuizesResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="main.application.queries.quiz.QuizesResponse" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz App</title>
    <style>
        body {
            background-color: #eff0f2;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            color: white;
        }

        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #335384;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar li {
            padding: 8px 12px;
            text-align: center;
        }

        .sidebar li a {
            text-decoration: none;
            color: white;
            display: block;
            transition: 0.3s;
        }

        .sidebar li a:hover {
            background-color: #1a5276;
        }

        .content {
            margin-left: 250px;
            padding: 15px;
            color: #335384;
        }

        .top-right {
            position: absolute;
            top: 20px;
            right: 20px;
            display: flex;
            align-items: center;
        }

        .search-bar {
            padding: 5px;
            border: 2px solid #335384;
            border-radius: 5px;
            color: #335384;
        }

        .icon {
            color: #335384;
            font-size: 20px;
            margin-left: 15px;
        }

        .wide-picture {
            background-image: url('https://onshorecellars.com/cdn/shop/articles/bordeaux-quiz-918697.jpg?v=1679728300'); /* Placeholder image */
            background-size: cover;
            background-position: center;
            height: 300px;
            position: relative;
            margin-top: 20px;
        }

        .popular-quizzes {
            margin-top: 40px;
        }

        .quizzes-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .quiz {
            width: calc(25% - 20px);
            text-align: center;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .quiz img {
            width: 100%;
            height: auto;
            max-width: 200px;
        }

        .quiz h3 {
            margin-top: 10px;
        }

        .quiz button {
            margin-top: 10px;
            background-color: #335384;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .quiz button:hover {
            background-color: #1a5276;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>

<div class="sidebar">
    <ul>
        <li><a href="#"><i class="fas fa-home"></i> Home</a></li>
        <li><a href="#"><i class="fas fa-users"></i> Friends</a></li>
        <li><a href="#"><i class="fas fa-question-circle"></i> Quizzes</a></li>
        <li><a href="#"><i class="fas fa-plus"></i> Create a Quiz</a></li>
        <li><a href="#"><i class="fas fa-bullhorn"></i> Announcements</a></li>
    </ul>
</div>
<div class="content">
    <div class="top-right">
        <input type="text" class="search-bar" placeholder="Search friends">
        <i class="fas fa-bell icon"></i>
        <i class="fas fa-user-circle icon"></i>
    </div>

    <h1>Welcome to the Quiz App!</h1>
    <div class="wide-picture"></div>

    <% List<QuizesResponse> popularQuizzes = (List<QuizesResponse>) request.getAttribute("popularQuizzes"); %>
    <div class="popular-quizzes">
    <h2>Popular Quizzes</h2>
        <div class="quizzes-container">
        <%for (QuizesResponse quiz : popularQuizzes) { %>
            <div class="quiz">
            <img src="https://via.placeholder.com/200x150?text=Quiz+1">
            <h3><%= quiz.getTitle() %></h3>
            <button>Start</button>
        </div>
        <% }%>
        </div>
    </div>
</div>
</body>
</html>
