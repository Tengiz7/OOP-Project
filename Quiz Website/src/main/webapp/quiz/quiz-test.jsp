<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.application.queries.quiz.QuizById.QuizByIdResponse" %>
<%@ page import="main.application.quiz.QuestionBase" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Test</title>
</head>
<body>

<%
    if (request.getSession().getAttribute("Authorization") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    QuizByIdResponse quiz = (QuizByIdResponse) request.getAttribute("currentQuiz");
%>

<h2>Quiz Test</h2>
<form action="add-attempt" method="post">
    <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
    <%
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
    %>
    <div>
            <%= quiz.getQuestions().get(i).render() %>
    </div>
    <%
        }
    %>
    <button type="submit">Submit</button>
</form>

</body>
</html>
