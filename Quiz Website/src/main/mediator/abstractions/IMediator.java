package main.mediator.abstractions;

public interface IMediator {
    public <TResult> TResult send(IRequest<TResult> request);
}
