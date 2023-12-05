package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesHandler;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserAttemptedQuizesHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingOk_ShouldReturnResult() {
        UserAttemptedQuizesQuery query = new UserAttemptedQuizesQuery("username");
        IQuizRepository repo = new IQuizRepository() {
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
                if (username.equals(query.getUsername())) {
                    return List.of(new Quiz(1, "title1", "description1", LocalDateTime.MIN, "user1")
                            , new Quiz(2, "title2", "description2", LocalDateTime.MIN, "user2"));
                }
                throw new RuntimeException("Invalid username");
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
        };

        UserAttemptedQuizesHandler handler = new UserAttemptedQuizesHandler(repo);

        List<QuizesResponse> result = handler.handle(query);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(0).getCreatedAt());
        assertEquals("user1", result.get(0).getCreatorId());
        assertEquals(2, result.get(1).getId());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("description2", result.get(1).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(1).getCreatedAt());
        assertEquals("user2", result.get(1).getCreatorId());
    }
}
