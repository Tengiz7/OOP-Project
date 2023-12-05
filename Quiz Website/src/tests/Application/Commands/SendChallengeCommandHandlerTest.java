package Application.Commands;

import MockRepositories.MockFriendsRepo;
import MockRepositories.MockUserRepo;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandHandler;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandRequest;
import main.application.users.IChallengeRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.domain.Challenge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class SendChallengeCommandHandlerTest {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        SendChallengeCommandRequest request = new SendChallengeCommandRequest();
        request.setSenderName("tezi");
        request.setReceiverName("nika");
        request.setMessage("me is var partyze ro gamicani");
        LocalDateTime localDateTime = LocalDateTime.now();
        request.setLocalDateTime(localDateTime);
        request.setQuizID(1);
        IUserRepository userRepository = new MockUserRepo();
        IFriendsRepository friendsRepository = new MockFriendsRepo(){
            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;
            }
        };
        IChallengeRepository challengeRepository = new IChallengeRepository() {
            @Override
            public void addChallenge(String senderName, String receiverName, int quizID, String message, LocalDateTime localDateTime) {
                if(!(senderName.equals(request.getSenderName()) &&
                        receiverName.equals(request.getReceiverName()) &&
                        (quizID == request.getQuizID()) &&
                        localDateTime.equals(request.getLocalDateTime()) &&
                        message.equals(request.getMessage()))){
                    throw new RuntimeException("Invalid data");
                }
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

        SendChallengeCommandHandler handler = new SendChallengeCommandHandler(friendsRepository, userRepository, challengeRepository);
        assertNotNull(handler.handle(request));
    }

    @Test
    public void testHandle_WhenFriendshipDoesNotExist_ShouldThrowException() {
        SendChallengeCommandRequest request = new SendChallengeCommandRequest();
        request.setSenderName("tezi");
        request.setReceiverName("nika");
        request.setMessage("me is var partyze ro gamicani");
        LocalDateTime localDateTime = LocalDateTime.now();
        request.setLocalDateTime(localDateTime);
        request.setQuizID(1);
        IUserRepository userRepository = new MockUserRepo();
        IFriendsRepository friendsRepository = new MockFriendsRepo();
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

        SendChallengeCommandHandler handler = new SendChallengeCommandHandler(friendsRepository, userRepository, challengeRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        Assertions.assertEquals("Users are not friends", exception.getMessage());
    }

    @Test
    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        SendChallengeCommandRequest request = new SendChallengeCommandRequest();
        request.setSenderName("tezi");
        request.setReceiverName("nika");
        request.setMessage("me is var partyze ro gamicani");
        LocalDateTime localDateTime = LocalDateTime.now();
        request.setLocalDateTime(localDateTime);
        request.setQuizID(1);
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendsRepository friendsRepository = new MockFriendsRepo();
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

        SendChallengeCommandHandler handler = new SendChallengeCommandHandler(friendsRepository, userRepository, challengeRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        Assertions.assertEquals("Username doesn't exist", exception.getMessage());
    }
}
