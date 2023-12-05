package main.domain;

import java.time.LocalDateTime;

public class Challenge {
    public int getChallengeID() {
        return challengeID;
    }

    private int challengeID;
    private String senderUsername;
    private String receiverUsername;
    private int quizID;
    private String message;
    private LocalDateTime localDateTime;

    //region getter/setter
    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
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
    public Challenge(String senderUsername, String receiverUsername, int quizID, String message, LocalDateTime localDateTime, int challengeID) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.quizID = quizID;
        this.message = message;
        this.localDateTime = localDateTime;
        this.challengeID = challengeID;
    }
}
