package main.application.commands.user.SendChallengeCommand;

import main.mediator.abstractions.IRequest;

import java.time.LocalDateTime;

public class SendChallengeCommandRequest implements IRequest<SendChallengeCommandResponse> {
    private String senderName;
    private String receiverName;
    private int quizID;
    private String message;
    private LocalDateTime localDateTime;

    //region getter/setter
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    //endregion
}
