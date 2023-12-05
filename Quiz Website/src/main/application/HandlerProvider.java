package main.application;


import main.application.announcements.IAnnouncementRepository;
import main.application.commands.admin.AddAnnouncement.AddAnnouncementCommand;
import main.application.commands.admin.AddAnnouncement.AddAnnouncementCommandHandler;

import main.application.commands.quiz.AddQuizAttempt.AddQuizAttemptCommandHandler;
import main.application.commands.quiz.AddQuizAttempt.AddQuizAttemptRequest;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommand;
import main.application.commands.quiz.CreateQuiz.CreateQuizCommandHandler;
import main.application.commands.quiz.DeleteHistory.DeleteHistoryCommand;
import main.application.commands.quiz.DeleteHistory.DeleteHistoryCommandHandler;
import main.application.commands.quiz.DeleteQuiz.DeleteQuizCommand;
import main.application.commands.quiz.DeleteQuiz.DeleteQuizCommandHandler;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandHandler;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandRequest;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandHandler;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandRequest;
import main.application.commands.user.AddMessageCommand.AddMessageCommandHandler;
import main.application.commands.user.AddMessageCommand.AddMessageCommandRequest;
import main.application.commands.user.AddUserComand.AddUserCommandHandler;
import main.application.commands.user.AddUserComand.AddUserCommandRequest;
import main.application.commands.user.DeleteChallengeCommand.DeleteChallengeCommandHandler;
import main.application.commands.user.DeleteChallengeCommand.DeleteChallengeCommandRequest;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandHandler;
import main.application.commands.user.DeleteMessageCommand.DeleteMessageCommandRequest;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandHandler;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandRequest;
import main.application.commands.user.RemoveFriendshipCommand.RemoveFriendshipCommandHandler;
import main.application.commands.user.RemoveFriendshipCommand.RemoveFriendshipCommandRequest;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandHandler;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandRequest;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandHandler;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandRequest;

import main.application.commands.user.SendChallengeCommand.SendChallengeCommandResponse;

import main.application.queries.admin.AllAnnouncements.AllAnnouncementsQuery;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsQueryHandler;
import main.application.queries.admin.Statistics.StatisticsQuery;
import main.application.queries.admin.Statistics.StatisticsQueryHandler;
import main.application.queries.admin.Statistics.StatisticsReponse;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesHandler;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesQuery;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesHandler;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesQuery;
import main.application.queries.quiz.GetFriendsPerformance.FriendsPerformanceQuery;
import main.application.queries.quiz.GetFriendsPerformance.FriendsPerformanceQueryHandler;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQuery;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryHandler;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryResponse;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQuery;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQueryHandler;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQuery;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQueryHandler;
import main.application.queries.quiz.NewQuizes.NewQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesQueryHandler;
import main.application.queries.quiz.PopularQuizes.PopularQuizesHandler;
import main.application.queries.quiz.PopularQuizes.PopularQuizesQuery;
import main.application.queries.quiz.QuizById.QuizByIdQuery;
import main.application.queries.quiz.QuizById.QuizByIdQueryHandler;
import main.application.queries.quiz.QuizesByTitle.QuizesByTitleQuery;
import main.application.queries.quiz.QuizesByTitle.QuizesByTitleQueryHandler;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesHandler;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesHandler;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesQuery;
import main.application.queries.user.ChallengeQuery.ChallengeQueryRequest;
import main.application.queries.user.ChallengeQuery.ChallengeQueryRequestHandler;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryHandler;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryRequest;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryHandler;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryHandler;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryRequest;
import main.application.queries.user.GetSentFriendRequestsQuery.SentFriendRequestsQueryHandler;
import main.application.queries.user.GetSentFriendRequestsQuery.SentFriendRequestsQueryRequest;
import main.application.queries.user.GetUserQuery.UserQueryHandler;
import main.application.queries.user.GetUserQuery.UserQueryRequest;
import main.application.queries.user.MessageQuery.MessageQueryHandler;
import main.application.queries.user.MessageQuery.MessageQueryRequest;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryHandler;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryRequest;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryHandler;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryRequest;
import main.application.queries.user.SearchUsersQuery.SearchUsersQueryHandler;
import main.application.queries.user.SearchUsersQuery.SearchUsersQueryRequest;
import main.application.queries.user.SentMessagesQuery.SentMessagesQueryHandler;
import main.application.queries.user.SentMessagesQuery.SentMessagesQueryRequest;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryHandler;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryRequest;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentHandler;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentRequest;
import main.application.quiz.IQuestionRepository;
import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.*;
import main.infrastructure.*;
import main.mediator.abstractions.IHandlerProvider;
import main.mediator.abstractions.IRequest;
import main.mediator.abstractions.IRequestHandler;
import main.persistence.DbContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HandlerProvider implements IHandlerProvider {

    private Map<Class<? extends IRequest>, Function<Connection, IRequestHandler>> map;
    private DbContext context;

    public HandlerProvider(final DbContext context) {
        map = new HashMap<>();
        this.context = context;
        map.put(NewQuizesQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new NewQuizesQueryHandler(repo);
        });
        map.put(QuizByIdQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new QuizByIdQueryHandler(repo);
        });
        map.put(UserQueryRequest.class, (con) -> {
            IUserRepository repo = new UserRepository(con);
            return new UserQueryHandler(repo);
        });
        map.put(AddUserCommandRequest.class, (con) -> {
            IUserRepository repo = new UserRepository(con);
            return new AddUserCommandHandler(repo);
        });
        map.put(AddAnnouncementCommand.class, (con) -> {
            IAnnouncementRepository repo = new AnnouncementRepository(con);
            return new AddAnnouncementCommandHandler(repo);
        });
        map.put(AllAnnouncementsQuery.class, (con) -> {
            IAnnouncementRepository repo = new AnnouncementRepository(con);
            return new AllAnnouncementsQueryHandler(repo);
        });
        map.put(StatisticsQuery.class, (con) -> {
            IQuizRepository repo1 = new QuizRepository(con);
            IQuizAttemptRepository repo2 = new QuizAttemptRepository(con);
            IUserRepository repo3 = new UserRepository(con);
            return new StatisticsQueryHandler(repo1, repo2, repo3);
        });
        map.put(AddQuizAttemptRequest.class, (con) -> {
            IQuizRepository quizRepository = new QuizRepository(con);
            IUserRepository userRepository = new UserRepository(con);
            IQuizAttemptRepository repo = new QuizAttemptRepository(con);
            return new AddQuizAttemptCommandHandler(quizRepository, userRepository, repo);
        });
        map.put(DeleteHistoryCommand.class, (con) -> {
            IQuizAttemptRepository repo = new QuizAttemptRepository(con);
            return new DeleteHistoryCommandHandler(repo);
        });
        map.put(DeleteQuizCommand.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new DeleteQuizCommandHandler(repo);
        });
        map.put(RemoveUserCommandRequest.class, (con) -> {
            IUserRepository repo = new UserRepository(con);
            return new RemoveUserCommandHandler(repo);
        });
        map.put(SearchUsersQueryRequest.class, (con) -> {
            IUserRepository repo = new UserRepository(con);
            return new SearchUsersQueryHandler(repo);
        });
        map.put(SearchUsersQueryRequest.class, (con) -> {
            IUserRepository repo = new UserRepository(con);
            return new SearchUsersQueryHandler(repo);
        });
        map.put(FriendAttemptedQuizesQuery.class, (con) -> {
            IQuizAttemptRepository repo1 = new QuizAttemptRepository(con);
            IQuizRepository repo2 = new QuizRepository(con);
            IUserRepository repo3 = new UserRepository(con);
            return new FriendAttemptedQuizesHandler(repo1, repo2, repo3);
        });
        map.put(FriendCreatedQuizesQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new FriendCreatedQuizesHandler(repo);
        });
        map.put(PopularQuizesQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new PopularQuizesHandler(repo);
        });
        map.put(QuizesByTitleQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new QuizesByTitleQueryHandler(repo);
        });
        map.put(UserAttemptedQuizesQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new UserAttemptedQuizesHandler(repo);
        });
        map.put(UserCreatedQuizesQuery.class, (con) -> {
            IQuizRepository repo = new QuizRepository(con);
            return new UserCreatedQuizesHandler(repo);
        });
        map.put(AddFriendRequestCommandRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            return new AddFriendRequestCommandHandler(friendRequestRepository,userRepository);
        });
        map.put(AddFriendshipCommandRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            return new AddFriendshipCommandHandler(friendRequestRepository,friendsRepository,userRepository);
        });
        map.put(RemoveFriendRequestCommandRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            return new RemoveFriendRequestCommandHandler(friendRequestRepository, userRepository);
        });
        map.put(RemoveFriendshipCommandRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            return new RemoveFriendshipCommandHandler(friendsRepository,userRepository);
        });
        map.put(GetFriendshipsQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            return new GetFriendshipsQueryHandler(friendsRepository,userRepository);
        });
        map.put(ReceivedFriendRequestsQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            return new ReceivedFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        });
        map.put(SentFriendRequestsQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            return new SentFriendRequestsQueryHandler(friendRequestRepository,userRepository);
        });
        map.put(AddMessageCommandRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            IMessagesRepository messagesRepository = new MessageRepository(con);
            return new AddMessageCommandHandler(friendsRepository,userRepository,messagesRepository);
        });
        map.put(ReceivedMessagesQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IMessagesRepository messagesRepository = new MessageRepository(con);
            return new ReceivedMessagesQueryHandler(messagesRepository, userRepository);
        });
        map.put(SentMessagesQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IMessagesRepository messagesRepository = new MessageRepository(con);
            return new SentMessagesQueryHandler(messagesRepository, userRepository);
        });
        map.put(DeleteMessageCommandRequest.class, (con) -> {
            IMessagesRepository messagesRepository = new MessageRepository(con);
            return new DeleteMessageCommandHandler(messagesRepository);
        });
        map.put(DeleteChallengeCommandRequest.class, (con) -> {
            IChallengeRepository challengeRepository = new ChallengeRepository(con);
            return new DeleteChallengeCommandHandler(challengeRepository);
        });
        map.put(SendChallengeCommandRequest.class, (con) -> {
            IChallengeRepository challengeRepository = new ChallengeRepository(con);
            IUserRepository userRepository = new UserRepository(con);
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            return new SendChallengeCommandHandler(friendsRepository,userRepository,challengeRepository);
        });
        map.put(ReceivedChallengesQueryRequest.class, (con) -> {
            IChallengeRepository challengeRepository = new ChallengeRepository(con);
            IUserRepository userRepository = new UserRepository(con);
            return new ReceivedChallengesQueryHandler(userRepository,challengeRepository);
        });
        map.put(CreateQuizCommand.class, (con) -> {
            IQuizRepository quizRepository = new QuizRepository(con);
            IQuestionRepository questionRepository = new QuestionRepository(con);
            IUserRepository userRepository = new UserRepository(con);
            return new CreateQuizCommandHandler(quizRepository, userRepository, questionRepository);
        });
        map.put(GetUserPastPerformanceQuery.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IQuizRepository quizRepository = new QuizRepository(con);
            IQuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository(con);
            return new GetUserPastPerformanceQueryHandler(userRepository, quizRepository, quizAttemptRepository);
        });
        map.put(TopPerformanceForQuizQuery.class, (con) -> {
            IQuizRepository quizRepository = new QuizRepository(con);
            IQuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository(con);
            return new TopPerformanceForQuizQueryHandler(quizRepository, quizAttemptRepository);
        });
        map.put(FriendsPerformanceQuery.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            IQuizRepository quizRepository = new QuizRepository(con);
            IQuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository(con);
            return new FriendsPerformanceQueryHandler(userRepository, quizRepository, quizAttemptRepository);
        });
        map.put(AddQuizAttemptRequest.class, (con) -> {
            IQuizRepository quizRepository = new QuizRepository(con);
            IUserRepository userRepository = new UserRepository(con);
            IQuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository(con);
            return new AddQuizAttemptCommandHandler(quizRepository, userRepository, quizAttemptRepository);
        });
        map.put(QuizStatisticsQuery.class, (con) -> {
            IQuizRepository quizRepository = new QuizRepository(con);
            IQuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository(con);
            return new QuizStatisticsQueryHandler(quizRepository, quizAttemptRepository);
        });
        map.put(UsernameExistsQueryRequest.class, (con) -> {
            IUserRepository userRepository = new UserRepository(con);
            return new UsernameExistsQueryHandler(userRepository);
        });
        map.put(IsFriendRequestSentRequest.class, (con) -> {
            IFriendRequestRepository friendRequestRepository = new FriendRequestsRepository(con);
            return new IsFriendRequestSentHandler(friendRequestRepository);
        });
        map.put(FriendshipExistsQueryRequest.class, (con) -> {
            IFriendsRepository friendsRepository = new FriendsRepository(con);
            return new FriendshipExistsQueryHandler(friendsRepository);
        });
        map.put(ChallengeQueryRequest.class, (con) -> {
            IChallengeRepository challengeRepository = new ChallengeRepository(con);
            return new ChallengeQueryRequestHandler(challengeRepository);
        });
        map.put(MessageQueryRequest.class, (con) -> {
            IMessagesRepository messagesRepository = new MessageRepository(con);
            return new MessageQueryHandler(messagesRepository);
        });
    }

//    @Override
//    public <TRequest extends IRequest<TResult>, TResult> IRequestHandler<TRequest, TResult> getRequiredHandler(TRequest request) {
//        try {
//            return map.get(request.getClass()).apply(context.getConnection());
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }

    @Override
    public <TRequest extends IRequest<TResult>, TResult> Function<Connection, IRequestHandler> getRequiredHandlerFunction(TRequest request) {
        return map.get(request.getClass());
    }
}
