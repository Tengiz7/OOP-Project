package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.admin.Statistics.StatisticsQuery;
import main.application.queries.admin.Statistics.StatisticsQueryHandler;
import main.application.queries.admin.Statistics.StatisticsReponse;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import main.domain.User;
import main.domain.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StatisticsQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        IUserRepository userRepository = new IUserRepository() {
            @Override
            public void addUser(User user) {

            }

            @Override
            public User userExists(String username) {
                return null;
            }

            @Override
            public void removeUser(String username) {

            }

            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }

            @Override
            public User getUser(String username, String password) {
                return null;
            }

            @Override
            public void changeUserRole(String username, UserStatus newUserStatus) {

            }

            @Override
            public List<User> search(String username) {
                return null;
            }

            @Override
            public int getUsersNum() {
                return 10;
            }
        };
        IQuizRepository quizRepository = new IQuizRepository() {
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
                return 10;
            }

            @Override
            public int addQuiz(Quiz quiz) {
                return 0;
            }
        };
        IQuizAttemptRepository attemptRepository = new IQuizAttemptRepository() {
            @Override
            public List<QuizAttempt> getQuizzesAttemptedByFriends(String username) {
                return null;
            }

            @Override
            public void deleteHistory(int quizId) {

            }

            @Override
            public int getQuizAttemptsNum() {
                return 10;
            }

            @Override
            public int getUniqueQuizAttemptsNum() {
                return 5;
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
        };
        StatisticsQueryHandler handler = new StatisticsQueryHandler(quizRepository, attemptRepository, userRepository);
        StatisticsReponse response = handler.handle(new StatisticsQuery());
        assertEquals(10, response.getNumberOfUsers());
        assertEquals(10, response.getNumberOfQuizes());
        assertEquals(10, response.getNumberOfQuizesTaken());
        assertEquals(5, response.getNumberOfUniqueQuizesTaken());
    }
}
