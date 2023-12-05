package Application.Commands;

import MockRepositories.MockFriendsRepo;
import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.commands.user.AddMessageCommand.AddMessageCommandHandler;
import main.application.commands.user.AddMessageCommand.AddMessageCommandRequest;
import main.application.users.IFriendsRepository;
import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.domain.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class AddMessageCommandHandlerTest extends TestCase {
    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        AddMessageCommandRequest request = new AddMessageCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        request.setLocalDateTime(LocalDateTime.now());
        IUserRepository userRepository = new MockUserRepo();
        IFriendsRepository friendsRepository = new MockFriendsRepo(){
            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;
            }
        };
        IMessagesRepository messagesRepository = new IMessagesRepository() {
            @Override
            public void sendMessage(String senderName, String receiverName, String message, LocalDateTime localDateTime) {
                if (!(senderName.equals(request.getSenderUsername()) && receiverName.equals(request.getReceiverUsername()) && localDateTime.equals(request.getLocalDateTime()) && message.equals(request.getMessage()))) {
                    throw new RuntimeException("Invalid data");
                }
            }

            @Override
            public List<Message> getReceivedMessages(String receiverName) {
                return null;
            }

            @Override
            public List<Message> getSentMessages(String senderName) {
                return null;
            }

            @Override
            public void deleteMessage(int messageID) {

            }

            @Override
            public boolean messageExists(int messageID) {
                return false;
            }

            @Override
            public Message getMessage(int messageID) {
                return null;
            }
        };

        AddMessageCommandHandler handler = new AddMessageCommandHandler(friendsRepository, userRepository, messagesRepository);
        assertNotNull(handler.handle(request));
    }

    public void testHandle_WhenFriendshipDoesNotExist_ShouldThrowException() {
        AddMessageCommandRequest request = new AddMessageCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        request.setLocalDateTime(LocalDateTime.now());
        IUserRepository userRepository = new MockUserRepo();
        IFriendsRepository friendsRepository = new IFriendsRepository() {
            @Override
            public List<String> getFriendships(String user) {
                return null;
            }

            @Override
            public void addFriend(String user, String futureFriend, LocalDateTime localDateTime) {
            }

            @Override
            public void removeFriendship(String user, String oldFriend) {
            }

            @Override
            public boolean friendshipExists(String user1, String user2) {
                return false;
            }
        };
        IMessagesRepository messagesRepository = new IMessagesRepository() {
            @Override
            public void sendMessage(String senderName, String receiverName, String message, LocalDateTime localDateTime) {

            }

            @Override
            public List<Message> getReceivedMessages(String receiverName) {
                return null;
            }

            @Override
            public List<Message> getSentMessages(String senderName) {
                return null;
            }

            @Override
            public void deleteMessage(int messageID) {

            }

            @Override
            public boolean messageExists(int messageID) {
                return false;
            }

            @Override
            public Message getMessage(int messageID) {
                return null;
            }
        };
        AddMessageCommandHandler handler = new AddMessageCommandHandler(friendsRepository, userRepository, messagesRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Users are not friends", exception.getMessage());
    }

    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        AddMessageCommandRequest request = new AddMessageCommandRequest();
        request.setSenderUsername("tezi");
        request.setReceiverUsername("nika");
        request.setMessage("me is var partyze ro gamicani");
        request.setLocalDateTime(LocalDateTime.now());
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
                return false;
            }
        };
        IFriendsRepository friendsRepository = new IFriendsRepository() {
            @Override
            public List<String> getFriendships(String user) {
                return null;
            }

            @Override
            public void addFriend(String user, String futureFriend, LocalDateTime localDateTime) {
            }

            @Override
            public void removeFriendship(String user, String oldFriend) {
            }

            @Override
            public boolean friendshipExists(String user1, String user2) {
                return true;
            }
        };
        IMessagesRepository messagesRepository = new IMessagesRepository() {
            @Override
            public void sendMessage(String senderName, String receiverName, String message, LocalDateTime localDateTime) {

            }

            @Override
            public List<Message> getReceivedMessages(String receiverName) {
                return null;
            }

            @Override
            public List<Message> getSentMessages(String senderName) {
                return null;
            }

            @Override
            public void deleteMessage(int messageID) {

            }

            @Override
            public boolean messageExists(int messageID) {
                return false;
            }

            @Override
            public Message getMessage(int messageID) {
                return null;
            }
        };
        AddMessageCommandHandler handler = new AddMessageCommandHandler(friendsRepository, userRepository, messagesRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("Username doesn't exist", exception.getMessage());
    }
}
