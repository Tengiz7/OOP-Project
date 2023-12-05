<%@ page import="main.domain.Message" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="main.application.queries.user.MessageQuery.MessageQueryResponse" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <title>Message Detail</title>

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

    .search-box {
      margin-right: 10px;
      padding: 5px;
      background-color: #19222a;
      border-radius: 5px;
      display: flex;
      align-items: center;
    }

    .search-box input[type="text"] {
      border: solid rgb(104, 104, 104) 1px;
      border-radius: 5px;
      padding: 8px 15px;
      background-color: #19222a;
      color: white;
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

    .message-container {
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    }
    .message-sender {
      font-weight: bold;
      margin-bottom: 5px;
    }
    .message-content {
      margin-top: 10px;
    }
    .message-detail {
      margin-left: 17em;
      margin-top: 5em;
      padding: 20px;
      background-color: #fefefe;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    }
    .write-message-button-container {
      margin-left: 17em;
      text-align: center;
      margin-top: 20px;
    }

    .write-message-btn {
      background-color: #f39c12;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .write-message-btn:hover {
      background-color: #e67e22;
    }
    .message-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding-top: 10px;
    }

    .message-time {
      color: #777;
    }
  </style>
  <script>
    function redirectToWriteMessage() {
      window.location.href = 'writeMessage';
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
          <a href=http://localhost:8080/Quiz_Website/>
            <i class="ion-ios-home"></i>
            <span>Home</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="ion-ios-people"></i>
            <span class="">Friends</span></a>
          <ul class="nav-flyout">
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=allFriends>My Friends</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/friendsActivity >Friends' Activity</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/searchFriends>Search Friends</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#">
            <i class="ion-ios-list"></i>
            <span class="">Quizzes</span></a>
          <ul class="nav-flyout">
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=allQuizzes>All Quizzes</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=takenByMe>Taken by me</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=takenByFriends>Taken by friends</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=createdByMe>Created by me</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/all?filter=createdByFriends>Created by friends</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#">
            <i class="ion-ios-plus-circle"></i>
            <span class="">Create a Quiz</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="ion-ios-inbox"></i>
            <span class="">Inbox</span></a>
          <ul class="nav-flyout">
            <li>
              <a href=http://localhost:8080/Quiz_Website/messageInbox>Messages</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/writeMessage>Write a Message</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/friendRequests>Friend Requests</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/challengeInbox>Challenges</a>
            </li>
            <li>
              <a href=http://localhost:8080/Quiz_Website/userAnnouncements>Announcements</a>
            </li>
          </ul>
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
          <li><a href="#">Profile</a></li>
          <li><a href="#">Logout</a></li>
        </ul>
      </div>
    </div>
  </section>

  <% MessageQueryResponse message = (MessageQueryResponse) request.getAttribute("message"); %>
  <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);%>
  <div class="message-detail">
    <div class="message-sender"><%= message.getSenderUsername() %></div>
    <div class="message-content">
      <%= message.getMessage() %>
    </div>
    <div class="message-footer">
      <p class="message-time">Sent on <%= message.getLocalDateTime().format(formatter)%>></p>
    </div>
  </div>

  <div class="write-message-button-container">
    <button class="write-message-btn" onclick="redirectToWriteMessage()">Write a Message to <%= message.getSenderUsername() %></button>
  </div>

</section>