package main.application.commands.user.DeleteChallengeCommand;

import main.application.users.IChallengeRepository;
import main.infrastructure.ChallengeRepository;
import main.mediator.abstractions.IRequest;
import main.mediator.abstractions.IRequestHandler;

public class DeleteChallengeCommandHandler implements IRequestHandler<DeleteChallengeCommandRequest,DeleteChallengeCommandResponse> {

    private IChallengeRepository challengeRepository;

    public DeleteChallengeCommandHandler(IChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public DeleteChallengeCommandResponse handle(DeleteChallengeCommandRequest request) {
        if(!challengeRepository.challengeExists(request.getId())){
            throw new RuntimeException("challenge doesn't exist");
        }
        challengeRepository.deleteChallenge(request.getId());
        return new DeleteChallengeCommandResponse();
    }
}
