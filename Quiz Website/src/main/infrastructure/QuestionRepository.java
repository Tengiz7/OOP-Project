package main.infrastructure;

import main.application.quiz.IQuestionRepository;
import main.domain.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionRepository implements IQuestionRepository {

    private Connection connection;

    public QuestionRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addQuestion(Question question) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO questions (quizId, imageSource, question, answer, questionType) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, question.getQuizId() + "");
            statement.setString(2, question.getImageSource());
            statement.setString(3, question.getQuestion());
            statement.setString(4, question.getAnswer());
            statement.setString(5, question.getType().toString());
            return statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }
}
