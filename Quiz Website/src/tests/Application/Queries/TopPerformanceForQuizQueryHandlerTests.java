package Application.Queries;

import MockRepositories.MockQuizAttemptRepo;
import MockRepositories.MockQuizRepo;
import junit.framework.TestCase;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQuery;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQueryHandler;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQueryResponse;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import main.domain.QuizAttempt;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TopPerformanceForQuizQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        TopPerformanceForQuizQuery query = new TopPerformanceForQuizQuery(1);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                if (id == query.getQuizId()) {
                    return new Quiz();
                }
                throw new RuntimeException("Invalid id");
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public java.util.List<main.domain.QuizAttempt> getTopQuizAttemptsByQuiz(int quizId) {
                if (quizId == query.getQuizId()) {
                    java.util.List<main.domain.QuizAttempt> result = new java.util.ArrayList<>();
                    QuizAttempt attempt = new QuizAttempt("username1", 1, 1);
                    QuizAttempt attempt1 = new QuizAttempt("username2", 1, 1);
                    result.add(attempt);
                    result.add(attempt1);
                    return result;
                }
                throw new RuntimeException("Invalid id");
            }
        };

        TopPerformanceForQuizQueryHandler handler = new TopPerformanceForQuizQueryHandler(quizRepo, quizAttemptRepo);
        List<TopPerformanceForQuizQueryResponse> result = handler.handle(query);

        assertEquals(2, result.size());
        assertEquals("username1", result.get(0).getUsername());
        assertEquals("username2", result.get(1).getUsername());
        assertEquals(1, result.get(0).getScore());
        assertEquals(1, result.get(1).getScore());
    }

    @Test
    public void testWhenQuizDoesntExist_ShouldThrowException() {
        TopPerformanceForQuizQuery query = new TopPerformanceForQuizQuery(1);

        IQuizRepository quizRepo = new MockQuizRepo() {
            @Override
            public Quiz getQuizById(int id) {
                return null;
            }
        };

        IQuizAttemptRepository quizAttemptRepo = new MockQuizAttemptRepo() {
            @Override
            public java.util.List<main.domain.QuizAttempt> getTopQuizAttemptsByQuiz(int quizId) {
                throw new RuntimeException("Invalid id");
            }
        };

        TopPerformanceForQuizQueryHandler handler = new TopPerformanceForQuizQueryHandler(quizRepo, quizAttemptRepo);
        try {
            handler.handle(query);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Quiz does not exist", e.getMessage());
        }
    }
}
