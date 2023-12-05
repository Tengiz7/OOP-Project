use oop_db;

INSERT INTO users (username, password, userStatus) VALUES ("nika", "34bff7be484da58a7c244a79ef278630f334a732", "NORMAL");

INSERT INTO users (username, password, userStatus) VALUES ("nika2", "34bff7be484da58a7c244a79ef278630f334a732", "NORMAL");

INSERT INTO users (username, password, userStatus) VALUES ("lizi", "34bff7be484da58a7c244a79ef278630f334a732", "ADMIN");

INSERT INTO users (username, password, userStatus) VALUES ("tezi", "34bff7be484da58a7c244a79ef278630f334a732", "NORMAL");

INSERT INTO quizzes (title, creatorId, createdAt, description) VALUES ("title", "nika", CURDATE(), "description");

INSERT INTO questions (quizId, question, answer, imageSource, questionType) VALUES (1, "_t_.", "_t", "", "BLANK");

INSERT INTO quizAttempts (quizId, username, score) VALUES (1, "nika", 1);

INSERT INTO announcements (content, publishedAt) VALUES ("content", CURDATE());

INSERT INTO challenges (senderUsername, receiverUsername, quizId, message, localDateTime) VALUES ("nika", "nika2", 1, "lol", CURDATE());

INSERT INTO messages (senderUsername, receiverUsername, message, localDateTime) VALUES ("nika", "nika2", "lol", CURDATE());

INSERT INTO friendships (username1, username2, date) VALUES ("nika", "nika2", CURDATE());

INSERT INTO friendships (username1, username2, data) VALUES ("nika2", "nika", CURDATE());

INSERT INTO quizzes (title, creatorId, createdAt, description) VALUES ("title", "nika2", CURDATE(), "description");

INSERT INTO questions (quizId, question, answer, imageSource, questionType) VALUES (2, "_t_.", "_t", "", "BLANK");

INSERT INTO quizAttempts (quizId, username, score) VALUES (1, "nika", 1);

INSERT INTO friendRequests (Sender_Username, Receiver_Username, Message) VALUES ("tezi", "nika", "mesiji lol");
