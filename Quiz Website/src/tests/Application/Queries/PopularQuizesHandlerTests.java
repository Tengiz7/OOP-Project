package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.quiz.PopularQuizes.PopularQuizesHandler;
import main.application.queries.quiz.PopularQuizes.PopularQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PopularQuizesHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingRight_ShouldReturnQuizes() {
        IQuizRepository quizRepo = new IQuizRepository() {
            @Override
            public List<Quiz> getNewQuizes() {
                return null;
            }

            @Override
            public List<Quiz> getPopularQuizes() {
                Quiz quiz1 = new Quiz(1, "title1", "description1", LocalDateTime.MIN, "creator1");
                Quiz quiz2 = new Quiz(2, "title2", "description2", LocalDateTime.MIN, "creator2");
                return Arrays.asList(quiz1, quiz2);
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
        };

        PopularQuizesHandler handler = new PopularQuizesHandler(quizRepo);
        List<QuizesResponse> result = handler.handle(new PopularQuizesQuery());
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(0).getCreatedAt());
        assertEquals("creator1", result.get(0).getCreatorId());
        assertEquals(2, result.get(1).getId());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("description2", result.get(1).getDescription());
        assertEquals(LocalDateTime.MIN, result.get(1).getCreatedAt());
        assertEquals("creator2", result.get(1).getCreatorId());
    }
}
