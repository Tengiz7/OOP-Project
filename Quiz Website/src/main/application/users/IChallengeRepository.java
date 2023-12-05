package main.application.users;

import main.domain.Challenge;
import main.domain.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface IChallengeRepository {
    void addChallenge (String senderName, String receiverName,int quizID, String message, LocalDateTime localDateTime);
    List<Challenge> getReceivedChallenges (String receiverName);
    void deleteChallenge (int quizID);
    boolean challengeExists (int quizID);
    public Challenge getChallenge (int challengeId);
}
