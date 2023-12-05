package main.application.queries.admin.Statistics;

public class StatisticsReponse {
    int numberOfUsers;
    int numberOfQuizes;
    int numberOfQuizesTaken;
    int numberOfUniqueQuizesTaken;

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public int getNumberOfQuizes() {
        return numberOfQuizes;
    }

    public void setNumberOfQuizes(int numberOfQuizes) {
        this.numberOfQuizes = numberOfQuizes;
    }

    public int getNumberOfQuizesTaken() {
        return numberOfQuizesTaken;
    }

    public void setNumberOfQuizesTaken(int numberOfQuizesTaken) {
        this.numberOfQuizesTaken = numberOfQuizesTaken;
    }

    public int getNumberOfUniqueQuizesTaken() {
        return numberOfUniqueQuizesTaken;
    }

    public void setNumberOfUniqueQuizesTaken(int numberOfUniqueQuizesTaken) {
        this.numberOfUniqueQuizesTaken = numberOfUniqueQuizesTaken;
    }
}
