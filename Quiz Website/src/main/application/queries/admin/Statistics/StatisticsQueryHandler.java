package main.application.queries.admin.Statistics;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class StatisticsQueryHandler implements IRequestHandler<StatisticsQuery, StatisticsReponse> {
    private IQuizRepository quizRepo;
    private IQuizAttemptRepository quizAttemptRepo;
    private IUserRepository userRepo;

    public StatisticsQueryHandler(IQuizRepository quizRepository, IQuizAttemptRepository quizAttemptRepository, IUserRepository userRepository) {
        quizRepo = quizRepository;
        quizAttemptRepo = quizAttemptRepository;
        userRepo = userRepository;
    }

    @Override
    public StatisticsReponse handle(StatisticsQuery request) {
        StatisticsReponse stats = new StatisticsReponse();
        stats.setNumberOfUsers(userRepo.getUsersNum());
        stats.setNumberOfQuizes(quizRepo.getQuizesNum());
        stats.setNumberOfQuizesTaken(quizAttemptRepo.getQuizAttemptsNum());
        stats.setNumberOfUniqueQuizesTaken(quizAttemptRepo.getUniqueQuizAttemptsNum());
        return stats;
    }
}
