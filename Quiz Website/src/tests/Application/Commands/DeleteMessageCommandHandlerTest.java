package Application.Commands;

import junit.framework.TestCase;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandHandler;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandRequest;
import main.application.users.IMessagesRepository;
import main.domain.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class DeleteMessageCommandHandlerTest extends TestCase {

    @Test
    public void testWhenEverythingIsFine_ShouldReturnResult() {
        DeleteMessageCommandRequest request = new DeleteMessageCommandRequest();
        request.setId(1);
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
                if(messageID != request.getId()){
                    throw new RuntimeException("Invalid data");
                }
            }

            @Override
            public boolean messageExists(int messageID) {
                return true;
            }

            @Override
            public Message getMessage(int messageID) {
                return null;
            }
        };
        DeleteMessageCommandHandler handler = new DeleteMessageCommandHandler(messagesRepository);
        assertNotNull(handler.handle(request));
    }

    public void testHandle_WhenMessageIDDoesNotExist_ShouldThrowException() {
        DeleteMessageCommandRequest request = new DeleteMessageCommandRequest();
        request.setId(1);
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
        DeleteMessageCommandHandler handler = new DeleteMessageCommandHandler(messagesRepository);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(request));
        assertEquals("message doesn't exist", exception.getMessage());
    }
}
