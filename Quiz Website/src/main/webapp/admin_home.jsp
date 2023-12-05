<%@ page import="main.application.queries.quiz.QuizesResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="main.application.queries.admin.Statistics.StatisticsReponse" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <title>Admin Home Page</title>

  <style>
    @import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600);
    * {
      margin: 0;
      padding: 0;
    }
    body {
      font-family: "Lato";
      font-size: 100%;
      overflow-y: scroll;
      font-family: sans-serif;
      -ms-text-size-adjust: 100%;
      -webkit-text-size-adjust: 100%;
      -ms-text-size-adjust: 100%;
      -webkit-text-size-adjust: 100%;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-rendering: optimizeLegibility;
      background-color: #fefefe;
    }

    a {
      text-decoration: none;
      -webkit-transition: all 0.6s ease;
      -moz-transition: all 0.6s ease;
      transition: all 0.6s ease;
    }
    a:hover {
      -webkit-transition: all 0.6s ease;
      -moz-transition: all 0.6s ease;
      transition: all 0.6s ease;
    }

    .app {
      height: 100vh;
    }

    .sidebar {
      position: fixed;
      width: 17em;
      height: 100vh;
      top: 0;
      overflow: hidden;
      background-color: #19222a;
      -webkit-transform: translateZ(0);
      visibility: visible;
      -webkit-backface-visibility: hidden;
    }
    .sidebar header {
      background-color: #19222a;
      width: 100%;
      display: block;
      padding: 0.75em 1em;
      color: white;
      font-size: 2rem;
    }

    .sidebar-nav {
      position: fixed;
      background-color: #19222a;
      height: 100%;
      font-weight: 400;
      font-size: 1.2em;
      overflow: auto;
      padding-bottom: 6em;
      z-index: 9;
      overflow: hidden;
      -webkit-overflow-scrolling: touch;
    }
    .sidebar-nav ul {
      list-style: none;
      display: block;
      padding: 0;
      margin: 0;
    }
    .sidebar-nav ul li {
      margin-left: 0;
      padding-left: 0;
      display: inline-block;
      width: 100%;
    }
    .sidebar-nav ul li a {
      color: rgba(255, 255, 255, 0.9);
      font-size: 0.75em;
      padding: 1.05em 1em;
      position: relative;
      display: block;
    }
    .sidebar-nav ul li a:hover {
      background-color: rgba(0, 0, 0, 0.9);
      -webkit-transition: all 0.6s ease;
      -moz-transition: all 0.6s ease;
      transition: all 0.6s ease;
    }
    .sidebar-nav ul li i {
      font-size: 1.8em;
      padding-right: 0.5em;
      width: 9em;
      display: inline;
      vertical-align: middle;
    }
    .sidebar-nav > ul > li > a:after {
      font-family: ionicons;
      font-size: 0.5em;
      width: 10px;
      color: #fff;
      position: absolute;
      right: 0.75em;
      top: 45%;
    }
    .sidebar-nav .nav-flyout {
      position: absolute;
      background-color: #080d11;
      z-index: 9;
      left: 2.5em;
      top: 0;
      height: 100vh;
      -webkit-transform: translateX(100%);
      -moz-transform: translateX(100%);
      -ms-transform: translateX(100%);
      -o-transform: translateX(100%);
      transform: translateX(100%);
      -webkit-transition: all 0.5s ease;
      -moz-transition: all 0.5s ease;
      transition: all 0.5s ease;
    }
    .sidebar-nav .nav-flyout a:hover {
      background-color: rgba(255, 255, 255, 0.05);
    }
    .sidebar-nav ul > li:hover .nav-flyout {
      -webkit-transform: translateX(0);
      -moz-transform: translateX(0);
      -ms-transform: translateX(0);
      -o-transform: translateX(0);
      transform: translateX(0);
      -webkit-transition: all 0.5s ease;
      -moz-transition: all 0.5s ease;
      transition: all 0.5s ease;
    }

    .sidebar-nav ul > li > a > i.ion-chevron-right {
      position: absolute;
      right: 1em;
      top: 50%;
      transform: translateY(-50%);
      transition: transform 0.3s ease;
    }

    .sidebar-nav ul > li > a:hover > i.ion-chevron-right {
      transform: translate(5px, -50%);
    }
    .space {
      margin-left: 17em;
    }
    .lorem {
      padding-left: 18em;
      padding-right: 0.5em;
      padding-top: 50px;
    }
    .header {
      position: fixed;
      top: 0;
      left: 16em;
      width: calc(100% - 17em);
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding: 10px;
      background-color: #19222a;
      z-index: 100;
    }

    .profile-button {
      position: relative;
    }

    .profile-toggle-btn {
      border: none;
      background: none;
      cursor: pointer;
    }

    .profile-icon {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }

    .profile-dropdown {
      position: absolute;
      top: 100%;
      right: 0;
      display: none;
      background-color: #19222a;
      border-radius: 5px;
    }

    .profile-dropdown ul {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .profile-dropdown ul li {
      padding: 10px;
    }

    .profile-dropdown ul li a {
      color: white;
      text-decoration: none;
    }

    .profile-button:hover .profile-dropdown {
      display: block;
    }

    @media screen and (max-width: 768px) {
      .header {
        position: static;
        justify-content: flex-start;
        padding: 10px;
      }

      .search-box {
        display: none;
      }

      .profile-button {
        display: none;
      }
    }

    .card-section {
    }
    .card-wrapper {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      padding: 20px;
      background: rgb(247, 247, 247);
      padding-left: 18em;
    }
    .card {
      width: calc(20% - 20px);
      background-color: white;
      border-radius: 35px;
      margin-bottom: 20px;
      padding: 20px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .card h1 {
      font-size: 1.2em;
      color: #000;
      margin-bottom: 10px;
    }

    .card p {
      font-size: 0.8em;
      color: #888;
      margin-bottom: 15px;
    }

    .card img {
      max-width: 100%;
      height: auto;
      border-radius: 25px;
      margin-bottom: 15px;
    }

    .card .btn {
      width: 100%;
      background-color: #ff6600;
      color: white;
      border: none;
      border-radius: 15px;
      padding: 15px 15px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .card .btn:hover {
      background-color: #ffe3d0;
      color: #ff6600;
    }

    @media screen and (max-width: 768px) {
      .card {
        width: calc(50% - 20px);
      }
    }

    @media screen and (max-width: 480px) {
      .card {
        width: calc(100% - 20px);
      }
    }
    .viewAll {
      padding: 7px 10px 7px 18em;
      display: flex;
      flex-direction: row-reverse;
      align-items: center;
    }
    .viewAll a {
      color: black;
    }
    svg {
      margin-left: 5px;
    }
    .title {
      padding-left: 18em;
      margin: 20px auto;
    }
    .title h1 {
      padding-left: 10px;
      font-size: 30px;
    }
    .image-section {
      position: relative;
      width: 100%;
      height: 300px;
      overflow: hidden;
      margin-top: 20px;
    }

    .background-image {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      object-fit: cover;
      z-index: -1;
    }

    .statistics {
      margin-top: 20px;
      padding-left: 18em;
      margin-bottom: 30px;
    }

    .statistics h2 {
      font-size: 24px;
      margin-bottom: 10px;
    }

    .statistics ul {
      list-style: disc;
      margin-left: 20px;
    }

    .statistics li {
      font-size: 18px;
      margin-bottom: 10px;
    }
  </style>

  <script>
    function redirectToQuizPage(quizId) {
      window.location.href = 'adminQuizSummary?quizId=' + quizId;
    }
  </script>
</head>

<body>
<section class="app">
  <aside class="sidebar">
    <header>Quiz Website</header>
    <nav class="sidebar-nav">
      <ul>
        <li>
          <a href=http://localhost:8080/Quiz_Website/adminIndex">
            <i class="ion-ios-home"></i>
            <span>Home</span>
          </a>
        </li>
        <li>
          <a href=http://localhost:8080/Quiz_Website/adminSearchUsers>
            <i class="ion-ios-people"></i>
            <span class="">Users</span>
          </a>
        </li>
        <li>
          <a href=http://localhost:8080/Quiz_Website/adminSearchQuizzes>
            <i class="ion-ios-list"></i>
            <span class="">Quizzes</span>
          </a>
        </li>
        <li>
          <a href=http://localhost:8080/Quiz_Website/writeAnnouncement>
            <i class="ion-ios-megaphone"></i>
            <span class="">Create Announcement</span>
          </a>
        </li>
      </ul>
    </nav>
  </aside>

  <section class="space">s</section>
  <section class="header">
    <div class="profile-button">
      <button class="profile-toggle-btn">
        <img src="https://via.placeholder.com/200x150?text=Profile" alt="Profile" class="profile-icon">
      </button>
      <div class="profile-dropdown">
        <ul>
          <li><a href="#">Logout</a></li>
        </ul>
      </div>
    </div>
  </section>

  <section class="image-section">
    <img src="https://onshorecellars.com/cdn/shop/articles/bordeaux-quiz-918697.jpg?v=1679728300" alt="Image Description" class="background-image">
  </section>

  <% StatisticsReponse statistics = (StatisticsReponse) request.getAttribute("statistics"); %>
  <section class="statistics">
    <h2>Quiz Statistics</h2>
    <ul>
      <li>Number of quizzes: <%= statistics.getNumberOfQuizes() %></li>
      <li>Number of users: <%= statistics.getNumberOfUsers() %></li>
      <li>Number of users who have taken quizzes: <%= statistics.getNumberOfUniqueQuizesTaken() %></li>
      <li>Number of times quizzes have been taken: <%= statistics.getNumberOfQuizesTaken() %></li>
    </ul>
  </section>


  <% List<QuizesResponse> popularQuizzes = (List<QuizesResponse>) request.getAttribute("popularQuizzes"); %>
  <section class="card-section">
    <div class="title">
      <h1>Popular Quizzes</h1>
    </div>
    <div class="card-wrapper">
      <%for (QuizesResponse quiz : popularQuizzes) { %>
      <div class="card">
        <h1><%= quiz.getTitle() %></h1>
        <img src="https://cdn1.edgedatg.com/aws/v2/natgeotv/AmericasNationalParks2022/showimages/c418e802e19225d461cfc05ab831d9b6/2016x807-Q75_c418e802e19225d461cfc05ab831d9b6.jpg" alt="Card Image 1">
        <button class="btn" onclick="redirectToQuizPage('<%= quiz.getId() %>')">Learn More</button>
      </div>
      <% }%>
    </div>
  </section>

</section>