package Application.Queries;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryHandler;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryRequest;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryResponse;
import main.application.users.IChallengeRepository;
import main.application.users.IUserRepository;
import main.domain.Challenge;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class ReceivedChallengesQueryHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        ReceivedChallengesQueryRequest request = new ReceivedChallengesQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Challenge> requests = Arrays.asList(new Challenge("Xajo","Tezi",1, "mesiji",localDateTime, 1), new Challenge("Lizi","Tezi",2, "mesiji2",localDateTime, 2));
        IChallengeRepository challengeRepository = new IChallengeRepository() {
            @Override
            public void addChallenge(String senderName, String receiverName, int quizID, String message, LocalDateTime localDateTime) {

            }

            @Override
            public List<Challenge> getReceivedChallenges(String receiverName) {
                return requests;
            }

            @Override
            public void deleteChallenge(int quizID) {

            }

            @Override
            public boolean challengeExists(int quizID) {
                return false;
            }

            @Override
            public Challenge getChallenge(int challengeId) {
                return null;
            }
        };

        ReceivedChallengesQueryHandler receivedChallengesQueryHandler = new ReceivedChallengesQueryHandler(userRepository, challengeRepository);
        ReceivedChallengesQueryResponse receivedChallengesQueryResponse = receivedChallengesQueryHandler.handle(request);
        assertNotNull(receivedChallengesQueryResponse);
        assertTrue(receivedChallengesQueryResponse.getRequests().containsAll(requests));
    }

    @Test
    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        ReceivedChallengesQueryRequest request = new ReceivedChallengesQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        LocalDateTime localDateTime = LocalDateTime.now();

        IChallengeRepository challengeRepository = new IChallengeRepository() {
            @Override
            public void addChallenge(String senderName, String receiverName, int quizID, String message, LocalDateTime localDateTime) {

            }

            @Override
            public List<Challenge> getReceivedChallenges(String receiverName) {
                return null;
            }

            @Override
            public void deleteChallenge(int quizID) {

            }

            @Override
            public boolean challengeExists(int quizID) {
                return false;
            }

            @Override
            public Challenge getChallenge(int challengeId) {
                return null;
            }
        };

        ReceivedChallengesQueryHandler receivedChallengesQueryHandler = new ReceivedChallengesQueryHandler(userRepository, challengeRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> receivedChallengesQueryHandler.handle(request));
        assertEquals("Receiver username doesn't exist", exception.getMessage());
    }
}
