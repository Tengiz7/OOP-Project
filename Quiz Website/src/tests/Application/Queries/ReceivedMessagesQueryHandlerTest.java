package Application.Queries;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryHandler;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryRequest;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryResponse;
import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.domain.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class ReceivedMessagesQueryHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        ReceivedMessagesQueryRequest request = new ReceivedMessagesQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Message> messages = Arrays.asList(new Message("Xajo","Tezi","mesiji",localDateTime, 1), new Message("Lizi","Tezi","mesiji2", localDateTime, 2));
        IMessagesRepository messagesRepository = new IMessagesRepository() {
            @Override
            public void sendMessage(String senderName, String receiverName, String message, LocalDateTime localDateTime) {

            }

            @Override
            public List<Message> getReceivedMessages(String receiverName) {
                return messages;
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

        ReceivedMessagesQueryHandler receivedMessagesQueryHandler = new ReceivedMessagesQueryHandler(messagesRepository, userRepository);
        ReceivedMessagesQueryResponse receivedMessagesQueryResponse = receivedMessagesQueryHandler.handle(request);
        assertNotNull(receivedMessagesQueryResponse);
        assertTrue(receivedMessagesQueryResponse.getMessages().containsAll(messages));
    }

    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        ReceivedMessagesQueryRequest request = new ReceivedMessagesQueryRequest();
        request.setReceiverUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo(){
            @Override
            public boolean isUsernameTaken(String username) {
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

        ReceivedMessagesQueryHandler receivedMessagesQueryHandler = new ReceivedMessagesQueryHandler(messagesRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> receivedMessagesQueryHandler.handle(request));
        assertEquals("Receiver username doesn't exist", exception.getMessage());
    }
}
