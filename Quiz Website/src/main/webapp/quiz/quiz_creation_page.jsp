<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Creation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #ffffff;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        select,
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: border-color 0.3s ease;
        }

        select:hover,
        input[type="text"]:hover,
        textarea:hover {
            border-color: #999;
        }

        input[type="submit"] {
            background-color: #ff6600;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #ff8800;
            color: #fff;
        }

        .question-box {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.1);
        }

        .remove-question {
            background-color: #ff4444;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }

        .remove-question:hover {
            background-color: #ff3333;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            let questionCount = 0;

            $("#addQuestionButton").click(function() {
                questionCount++;
                let questionType = $("#questionType").val();

                let questionBox = $("<div>", { class: "question-box" });
                let questionTypeInput = $("<input>", { type: "hidden", name: "questionTypes", value: questionType });
                let removeButton = $("<button>", { class: "remove-question", text: "Remove", type: "button" });

                questionBox.append(questionTypeInput);

                if (questionType === "BLANK") {
                    questionBox.append("Delimiter: <input type=\"text\" name=\"delimiters\"><br>");
                    questionBox.append("Question: <input type=\"text\" name=\"questions\"><br>");
                    questionBox.append("Answers (comma-separated for synonyms, delimiter for different blanks): <input type=\"text\" name=\"answers\"><br>");
                    questionBox.append("Image: <input type=\"url\" name=\"images\"><br>");
                } else if (questionType === "MULTIPLE_CHOICE") {
                    questionBox.append("Question: <input type=\"text\" name=\"questions\"><br>");
                    questionBox.append("<button type=\"button\" class=\"add-answer-button\">Add Answer</button><br>");
                    questionBox.append("Image: <input type=\"url\" name=\"images\"><br>");
                    questionBox.append("<input type=\"hidden\" name=\"answerCount\" value=\"0\">");
                } else if (questionType === "QUESTION_RESPONSE") {
                    questionBox.append("Question: <input type=\"text\" name=\"questions\"><br>");
                    questionBox.append("Correct Answers (comma-separated): <input type=\"text\" name=\"answers\"><br>");
                    questionBox.append("Image: <input type=\"url\" name=\"images\"><br>");
                }
                questionBox.append(removeButton);

                $("#questionsContainer").append(questionBox);
            });

            $(document).on("click", ".remove-question", function() {
                $(this).closest(".question-box").remove();
                questionCount--;
            });

            $(document).on("click", ".add-answer-button", function() {
                let answerCountInput = $(this).siblings("input[name='answerCount']");
                let answerCount = parseInt(answerCountInput.val()) + 1;
                answerCountInput.val(answerCount);

                let answerInput = $("<input>", { type: "text", name: "answers", placeholder: "Answer text" });
                let correctCheckbox = $("<input>", { type: "checkbox", name: "correctAnswers", value: "true" });
                let hiddenCheckbox = $("<input>", { type: "hidden", name: "isCorrectAnswer", value: "false" });
                let answerBox = $("<div>", { class: "answer-box" });

                correctCheckbox.on("click", function() {
                    hiddenCheckbox.val(correctCheckbox.prop("checked") ? "true" : "false");
                });

                answerBox.append(answerInput);
                answerBox.append(correctCheckbox);
                answerBox.append(hiddenCheckbox);
                $(this).before(answerBox);
            });
        });
    </script>
</head>

<body>
<h1>Create Quiz</h1>
<% if(request.getSession().getAttribute("Authorization") == null) {
  request.getRequestDispatcher("../user/sign_in.jsp").forward(request, response);
}%>

<form action="../quiz" method="post">
    Title: <input type="text" name="title" required><br>
    Description: <textarea name="description" required></textarea><br>
    Question Type:
    <select id="questionType">
        <option value="BLANK">Blank</option>
        <option value="MULTIPLE_CHOICE">Multiple Choice</option>
        <option value="QUESTION_RESPONSE">Question-Response</option>
    </select>
    <button type="button" id="addQuestionButton">Add Question</button>
    <div id="questionsContainer"></div>
    <button type="submit" >Create Quiz</button>
</form>

</body>
</html>
