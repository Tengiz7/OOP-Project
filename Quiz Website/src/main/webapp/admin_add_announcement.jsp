<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <title>Admin Create Announcement</title>

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

    .announcement-form {
      margin: 20px auto;
      max-width: 800px;
      padding: 20px;
      background: white;
      border-radius: 15px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      font-size: 18px;
      margin-bottom: 10px;
    }

    .form-group input,
    .form-group textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .submit-btn {
      background-color: #ff6600;
      color: white;
      border: none;
      border-radius: 5px;
      padding: 10px 20px;
      cursor: pointer;
      font-size: 18px;
      transition: background-color 0.3s ease;
    }

    .submit-btn:hover {
      background-color: #ffe3d0;
      color: #ff6600;
    }

  </style>
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

  <section class="lorem">
    <div class="announcement-form">
      <form action="writeAnnouncement" method="post">
        <div class="form-group">
          <label for="subject">Announcement Subject</label>
          <input type="text" id="subject" name="subject" placeholder="Enter subject" required>
        </div>
        <div class="form-group">
          <label for="announcement">Announcement</label>
          <textarea id="announcement" name="announcement" rows="4" placeholder="Enter announcement" required></textarea>
        </div>
        <button type="submit" class="submit-btn">Submit Announcement</button>
      </form>
    </div>
  </section>


</section>