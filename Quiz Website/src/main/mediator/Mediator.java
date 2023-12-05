package main.mediator;

import main.mediator.abstractions.IHandlerProvider;
import main.mediator.abstractions.IMediator;
import main.mediator.abstractions.IRequest;
import main.mediator.abstractions.IRequestHandler;
import main.persistence.DbContext;

import java.sql.Connection;

public class Mediator implements IMediator {

    private IHandlerProvider handlerProvider;
    private DbContext dbContext;

    public Mediator(IHandlerProvider handlerProvider) {
        this.handlerProvider = handlerProvider;
        this.dbContext = new DbContext();
    }

    @Override
    public <TResult> TResult send(IRequest<TResult> request) {
        try (Connection connection = dbContext.getConnection()) {
            IRequestHandler<IRequest<TResult>, TResult> handler = handlerProvider.getRequiredHandlerFunction(request).apply(connection);
            return handler.handle(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
