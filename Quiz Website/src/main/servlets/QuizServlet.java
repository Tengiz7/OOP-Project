package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.quiz.CreateQuiz.CreateQuestionCommand;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommand;
import main.application.queries.quiz.QuizById.QuizByIdQuery;
import main.application.queries.quiz.QuizById.QuizByIdResponse;
import main.domain.enums.QuestionType;
import main.mediator.Mediator;
import main.mediator.abstractions.IMediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
            QuizByIdResponse response = mediator.send(new QuizByIdQuery(id));
            req.getRequestDispatcher("quiz_summary_page.jsp").forward(req, resp);
        } catch (NumberFormatException exception) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String username = JWTHelper.getDataFromToken((String) req.getSession().getAttribute("Authorization")).username();

        CreateQuizCommand command = new CreateQuizCommand();
        List<CreateQuestionCommand> questionCommands = new ArrayList<>();
        command.setTitle(title);
        command.setDescription(description);
        command.setCreatedAt(LocalDateTime.now());
        command.setCreator(username);

        Iterator<String> questions = Arrays.stream(req.getParameterValues("questions")).iterator();
        Iterator<String> questionTypes = Arrays.stream(req.getParameterValues("questionTypes")).iterator();
        Iterator<String> answers = null;
        if (req.getParameterValues("answers") != null) {
            answers = Arrays.stream(req.getParameterValues("answers")).iterator();
        }
        Iterator<String> correctAnswers = null;
        if (req.getParameterValues("isCorrectAnswer") != null) {
            correctAnswers = Arrays.stream(req.getParameterValues("isCorrectAnswer")).iterator();
        }
        Iterator<String> delimIterator = null;
        if (req.getParameterValues("delimiters") != null) {
            delimIterator = Arrays.stream(req.getParameterValues("delimiters")).iterator();
        }
        Iterator<String> images = null;
        if (req.getParameterValues("images") != null) {
            images = Arrays.stream(req.getParameterValues("images")).iterator();
        }
        Iterator<String> answerCounts = null;
        if (req.getParameterValues("answerCount") != null) {
            answerCounts = Arrays.stream(req.getParameterValues("answerCount")).iterator();
        }

        while(questions.hasNext()) {
            CreateQuestionCommand questionCommand = new CreateQuestionCommand();
            QuestionType type = QuestionType.valueOf(questionTypes.next());
            switch (type) {
                case QUESTION_RESPONSE -> {
                    questionCommand.setQuestion(questions.next());
                    questionCommand.setAnswer(answers.next());
                    questionCommand.setImgSource(images.next());
                    questionCommand.setType(type);
                }
                case BLANK -> {
                    String question = questions.next();
                    String delim = delimIterator.next();
                    questionCommand.setQuestion(delim + question +
                            (question.charAt(question.length() - 1) == delim.charAt(0) ? "." : ""));
                    questionCommand.setType(type);
                    questionCommand.setAnswer(delim + answers.next());
                    questionCommand.setImgSource(images.next());
                }
                case MULTIPLE_CHOICE -> {
                    int answerCount = Integer.parseInt(answerCounts.next());
                    questionCommand.setQuestion(questions.next());
                    questionCommand.setType(type);
                    questionCommand.setImgSource(images.next());
                    StringBuilder answer = new StringBuilder();
                    answer.append('|');
                    for(int i = 0; i < answerCount; i++) {
                        answer.append((correctAnswers.next().equals("true") ? "+" : "-") + answers.next());
                        answer.append('|');
                    }
                    answer.deleteCharAt(answer.length() - 1);
                    questionCommand.setAnswer(answer.toString());
                }
            }
            questionCommands.add(questionCommand);
        }

        command.setQuestions(questionCommands);
        IMediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        mediator.send(command);
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
