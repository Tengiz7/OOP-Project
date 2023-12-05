package main.mediator.abstractions;

public interface IRequestHandler<TRequest extends IRequest<TResult>, TResult> {
    public TResult handle(TRequest request);
}
