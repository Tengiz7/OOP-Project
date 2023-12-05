package main.mediator.abstractions;

import java.sql.Connection;
import java.util.function.Function;

public interface IHandlerProvider {
    public <TRequest extends IRequest<TResult>, TResult>Function<Connection, IRequestHandler> getRequiredHandlerFunction(TRequest request);
}
