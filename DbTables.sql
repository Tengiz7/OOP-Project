USE oop_db;

DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS quizAttempts;
DROP TABLE IF EXISTS announcements;
DROP TABLE IF EXISTS challenges;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS friendRequests;
DROP TABLE IF EXISTS friendships;

CREATE TABLE users (
	username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    userStatus VARCHAR(50) NOT NULL
);

CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    creatorId VARCHAR(255),
    createdAt DATETIME,
    description TEXT,
    FOREIGN KEY (creatorId) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE questions(
	id INT AUTO_INCREMENT PRIMARY KEY,
    quizId INT,
    question VARCHAR(255),
    answer VARCHAR(255),
    imageSource VARCHAR(255),
    questionType VARCHAR(255),
    FOREIGN KEY (quizId) REFERENCES quizzes(id) ON DELETE CASCADE
);

CREATE TABLE quizAttempts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quizId INT,
    username VARCHAR(255),
    score INT,
    FOREIGN KEY (quizId) REFERENCES quizzes(id) ON DELETE CASCADE,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE announcements (
	id INT PRIMARY KEY AUTO_INCREMENT,
    subject VARCHAR(255),
    content VARCHAR(255),
    publishedAt DATETIME
);

CREATE TABLE challenges (
	id INT PRIMARY KEY AUTO_INCREMENT,
    senderUsername VARCHAR(255),
    receiverUsername VARCHAR(255),
    quizId INT,
    message VARCHAR(255),
    localDateTime DATETIME,
    FOREIGN KEY (senderUsername) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (receiverUsername) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (quizId) REFERENCES quizzes(id) ON DELETE CASCADE
);

CREATE TABLE messages (
	id INT PRIMARY KEY AUTO_INCREMENT,
    senderUsername VARCHAR(255),
    receiverUsername VARCHAR(255),
    message VARCHAR(255),
    localDateTime DATETIME,
    FOREIGN KEY (senderUsername) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (receiverUsername) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE friendRequests (
	id INT PRIMARY KEY AUTO_INCREMENT,
    Sender_Username VARCHAR(255),
    Receiver_Username VARCHAR(255),
    Message VARCHAR(255),
    FOREIGN KEY (Sender_Username) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (Receiver_Username) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE friendships (
	id INT PRIMARY KEY AUTO_INCREMENT,
    username1 VARCHAR(255),
    username2 VARCHAR(255),
    date DATETIME,
    FOREIGN KEY (username1) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (username2) REFERENCES users(username) ON DELETE CASCADE
);