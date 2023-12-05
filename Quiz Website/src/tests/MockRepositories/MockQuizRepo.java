package MockRepositories;

import main.application.quiz.IQuizRepository;
import main.domain.Quiz;

import java.util.List;

public class MockQuizRepo implements IQuizRepository {
    @Override
    public List<Quiz> getNewQuizes() {
        return null;
    }

    @Override
    public List<Quiz> getPopularQuizes() {
        return null;
    }

    @Override
    public List<Quiz> getQuizesByCreatorId(String username) {
        return null;
    }

    @Override
    public List<Quiz> getQuizesByUserAttempts(String username) {
        return null;
    }

    @Override
    public List<Quiz> getQuizesByFriends(String username) {
        return null;
    }

    @Override
    public List<Quiz> searchQuizes(String title) {
        return null;
    }

    @Override
    public Quiz getQuizById(int id) {
        return null;
    }

    @Override
    public void deleteQuiz(int id) {

    }

    @Override
    public int getQuizesNum() {
        return 0;
    }

    @Override
    public int addQuiz(Quiz quiz) {
        return 0;
    }
}
