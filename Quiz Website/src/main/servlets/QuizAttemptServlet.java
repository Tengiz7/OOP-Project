package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.quiz.AddQuizAttempt.AddQuizAttemptRequest;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQuery;
import main.application.queries.quiz.QuizById.QuizByIdResponse;
import main.application.quiz.QuestionBase;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quiz/add-attempt")
public class QuizAttemptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        int quizId = Integer.parseInt(req.getParameter("quizId"));
        QuizByIdResponse quiz = mediator.send(new main.application.queries.quiz.QuizById.QuizByIdQuery(quizId));
        int score = 0;
        for (QuestionBase question : quiz.getQuestions()) {
            int id = question.getId();
            String[] answer = req.getParameterValues(id + "");
            score += question.getPoints(List.of(answer));
        }
        AddQuizAttemptRequest request = new AddQuizAttemptRequest();
        request.setQuizId(quiz.getId());
        String username = JWTHelper.getDataFromToken((String) req.getSession().getAttribute("Authorization")).username();
        request.setUsername(username);
        request.setScore(score);
        mediator.send(request);
        GetUserPastPerformanceQuery myPastPerformaceRequest = new GetUserPastPerformanceQuery();
        myPastPerformaceRequest.setUsername(username);
        myPastPerformaceRequest.setQuizId(quiz.getId());
        req.setAttribute("score", score);
        req.setAttribute("myPastPerformances", mediator.send(myPastPerformaceRequest));
        req.setAttribute("quizId", quizId);
        req.getRequestDispatcher("quiz_result_page.jsp").forward(req, resp);
    }
}
