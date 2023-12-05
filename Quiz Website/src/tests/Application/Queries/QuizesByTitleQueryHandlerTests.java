package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.quiz.QuizesByTitle.QuizesByTitleQuery;
import main.application.queries.quiz.QuizesByTitle.QuizesByTitleQueryHandler;
import main.application.queries.quiz.QuizesResponse;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class QuizesByTitleQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingOk_ShouldReturnResult() {
        QuizesByTitleQuery query = new QuizesByTitleQuery();
        query.setTitle("title");

        IQuizRepository quizRepo = new IQuizRepository() {
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
                if (title.equals(query.getTitle())) {
                    return List.of(new Quiz(1, "title1", "desc1", LocalDateTime.MIN, "creator1")
                                , new Quiz(2, "title2", "desc2", LocalDateTime.MIN, "creator2"));
                }
                throw new RuntimeException("Invalid title");
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

        QuizesByTitleQueryHandler handler = new QuizesByTitleQueryHandler(quizRepo);

        List<QuizesResponse> result = handler.handle(query);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("desc1", result.get(0).getDescription());
        assertEquals("creator1", result.get(0).getCreatorId());
        assertEquals(2, result.get(1).getId());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("desc2", result.get(1).getDescription());
        assertEquals("creator2", result.get(1).getCreatorId());
        assertEquals(LocalDateTime.MIN, result.get(0).getCreatedAt());
        assertEquals(LocalDateTime.MIN, result.get(1).getCreatedAt());
    }
}
