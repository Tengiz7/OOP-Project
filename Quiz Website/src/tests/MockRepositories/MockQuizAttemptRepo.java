package MockRepositories;

import main.application.quiz.IQuizAttemptRepository;
import main.domain.QuizAttempt;

import java.util.List;

public class MockQuizAttemptRepo implements IQuizAttemptRepository {
    @Override
    public List<QuizAttempt> getQuizzesAttemptedByFriends(String username) {
        return null;
    }

    @Override
    public void deleteHistory(int quizId) {

    }

    @Override
    public int getQuizAttemptsNum() {
        return 0;
    }

    @Override
    public int getUniqueQuizAttemptsNum() {
        return 0;
    }

    @Override
    public void addQuizAttempt(QuizAttempt quizAttempt) {

    }

    @Override
    public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
        return null;
    }

    @Override
    public List<QuizAttempt> getTopQuizAttemptsByQuiz(int quizId) {
        return null;
    }

    @Override
    public int getNumberOfTimesTaken(int quizId) {
        return 0;
    }

    @Override
    public int getNumberOfUniqueTimesTaken(int quizId) {
        return 0;
    }

    @Override
    public int getMaxScore(int quizId) {
        return 0;
    }

    @Override
    public double getAverageScore(int quizId) {
        return 0;
    }

    @Override
    public List<QuizAttempt> getFriendsQuizAttempts(String username, int quizId) {
        return null;
    }
}
