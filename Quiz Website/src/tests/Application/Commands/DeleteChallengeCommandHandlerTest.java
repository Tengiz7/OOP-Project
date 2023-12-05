package Application.Commands;

import junit.framework.TestCase;
import main.application.commands.user.DeleteChallengeCommand.DeleteChallengeCommandHandler;
import main.application.commands.user.DeleteChallengeCommand.DeleteChallengeCommandRequest;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandHandler;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandRequest;
import main.application.users.IChallengeRepository;
import main.application.users.IMessagesRepository;
import main.domain.Challenge;
import main.domain.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class DeleteChallengeCommandHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        DeleteChallengeCommandRequest request = new DeleteChallengeCommandRequest();
        request.setId(1);
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
                if(quizID != request.getId()){
                    throw new RuntimeException("Invalid Data");
                }
            }

            @Override
            public boolean challengeExists(int quizID) {
                return true;
            }

            @Override
            public Challenge getChallenge(int challengeId) {
                return null;
            }
        };
        DeleteChallengeCommandHandler handler = new DeleteChallengeCommandHandler(challengeRepository);
        assertNotNull(handler.handle(request));
    }
    public void testHandle_WhenChallengeIDDoesNotExist_ShouldThrowException() {
        DeleteChallengeCommandRequest request = new DeleteChallengeCommandRequest();
        request.setId(1);
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
        DeleteChallengeCommandHandler handler = new DeleteChallengeCommandHandler(challengeRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("challenge doesn't exist", exception.getMessage());
    }

}
