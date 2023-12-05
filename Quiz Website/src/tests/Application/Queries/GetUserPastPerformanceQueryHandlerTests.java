package Application.Queries;

import MockRepositories.MockQuizAttemptRepo;
import MockRepositories.MockQuizRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQuery;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQueryHandler;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceResponse;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetUserPastPerformanceQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        GetUserPastPerformanceQuery query = new GetUserPastPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return username.equals(query.getUsername());
            }
        };

        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId())
                    return new Quiz();
                return null;
            }
        };

        IQuizAttemptRepository quizAttemptRepository = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    QuizAttempt quizAttempt = new QuizAttempt();
                    quizAttempt.setScore(10);
                    QuizAttempt quizAttempt1 = new QuizAttempt();
                    quizAttempt1.setScore(20);
                    return List.of(quizAttempt, quizAttempt1);
                }
                throw new RuntimeException("Invalid data");
            }
        };

        GetUserPastPerformanceQueryHandler handler = new GetUserPastPerformanceQueryHandler(userRepository, quizRepository, quizAttemptRepository);

        List<GetUserPastPerformanceResponse> result = handler.handle(query);

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getScore());
        assertEquals(20, result.get(1).getScore());
    }

    @Test
    public void testWhenUserDoesntExist_ShouldThrowException() {
        GetUserPastPerformanceQuery query = new GetUserPastPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };

        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId())
                    return new Quiz();
                return null;
            }
        };

        IQuizAttemptRepository quizAttemptRepository = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    QuizAttempt quizAttempt = new QuizAttempt();
                    quizAttempt.setScore(10);
                    QuizAttempt quizAttempt1 = new QuizAttempt();
                    quizAttempt1.setScore(20);
                    return List.of(quizAttempt, quizAttempt1);
                }
                throw new RuntimeException("Invalid data");
            }
        };

        GetUserPastPerformanceQueryHandler handler = new GetUserPastPerformanceQueryHandler(userRepository, quizRepository, quizAttemptRepository);

        try {
            handler.handle(query);
            fail("Expected exception");
        } catch (RuntimeException ex) {
            assertEquals("User with username " + query.getUsername() + " does not exist", ex.getMessage());
        }
    }

    @Test
    public void testWhenQuizDoesntExist_ShouldThrowException() {
        GetUserPastPerformanceQuery query = new GetUserPastPerformanceQuery();
        query.setUsername("username");
        query.setQuizId(1);

        IUserRepository userRepository = new MockUserRepo() {
            @Override
            public boolean isUsernameTaken(String username) {
                return true;
            }
        };

        IQuizRepository quizRepository = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                return null;
            }
        };

        IQuizAttemptRepository quizAttemptRepository = new MockQuizAttemptRepo() {
            @Override
            public List<QuizAttempt> getQuizAttemptsByUser(String username, int quizId) {
                if (username.equals(query.getUsername()) && quizId == query.getQuizId()) {
                    QuizAttempt quizAttempt = new QuizAttempt();
                    quizAttempt.setScore(10);
                    QuizAttempt quizAttempt1 = new QuizAttempt();
                    quizAttempt1.setScore(20);
                    return List.of(quizAttempt, quizAttempt1);
                }
                throw new RuntimeException("Invalid data");
            }
        };

        GetUserPastPerformanceQueryHandler handler = new GetUserPastPerformanceQueryHandler(userRepository, quizRepository, quizAttemptRepository);

        try {
            handler.handle(query);
            fail("Expected exception");
        } catch (RuntimeException ex) {
            assertEquals("Quiz with id " + query.getQuizId() + " does not exist", ex.getMessage());
        }
    }
}
