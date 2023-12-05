package Application.Queries;

import MockRepositories.MockUserRepo;
import junit.framework.TestCase;
import main.application.queries.user.SentMessagesQuery.SentMessagesQueryHandler;
import main.application.queries.user.SentMessagesQuery.SentMessagesQueryRequest;
import main.application.queries.user.SentMessagesQuery.SentMessagesQueryResponse;
import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.domain.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class SentMessagesQueryHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        SentMessagesQueryRequest request = new SentMessagesQueryRequest();
        request.setSenderUsername("Tezi");
        IUserRepository userRepository = new MockUserRepo();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Message> messages = Arrays.asList(new Message("Tezi","Xajo","mesiji",localDateTime, 1), new Message("Tezi","Lizi","mesiji2", localDateTime, 2));
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
                return messages;
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

        SentMessagesQueryHandler sentMessagesQueryHandler = new SentMessagesQueryHandler(messagesRepository, userRepository);
        SentMessagesQueryResponse receivedMessagesQueryResponse = sentMessagesQueryHandler.handle(request);
        assertNotNull(receivedMessagesQueryResponse);
        assertTrue(receivedMessagesQueryResponse.getMessages().containsAll(messages));
    }

    public void testHandle_WhenUsernameDoesNotExist_ShouldThrowException() {
        SentMessagesQueryRequest request = new SentMessagesQueryRequest();
        request.setSenderUsername("Tezi");
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

        SentMessagesQueryHandler sentMessagesQueryHandler = new SentMessagesQueryHandler(messagesRepository, userRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sentMessagesQueryHandler.handle(request));
        assertEquals("Sender username doesn't exist", exception.getMessage());
    }
}
