package Application.Queries;

import junit.framework.TestCase;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesHandler;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FriendCreatedQuizesHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        FriendCreatedQuizesQuery query = new FriendCreatedQuizesQuery("username");

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
                return null;
            }

            @Override
            public List<Quiz> getQuizesByFriends(String username) {
                if (username.equals(query.getUsername())) {
                    Quiz q1 = new Quiz();
                    q1.setId(1);
                    q1.setTitle("title1");
                    q1.setDescription("description1");
                    Quiz q2 = new Quiz();
                    q2.setId(2);
                    q2.setTitle("title2");
                    q2.setDescription("description2");
                    return List.of(q1, q2);
                }
                throw new RuntimeException("Wrong username");
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

        FriendCreatedQuizesHandler handler = new FriendCreatedQuizesHandler(repo);

        List<QuizesResponse> result = handler.handle(query);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals(2, result.get(1).getId());
        assertEquals("title2", result.get(1).getTitle());
        assertEquals("description2", result.get(1).getDescription());
    }
}
