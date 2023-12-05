package main.application.commands.admin.AddAnnouncement;

import main.application.announcements.IAnnouncementRepository;
import main.domain.Announcement;
import main.mediator.abstractions.IRequestHandler;

public class AddAnnouncementCommandHandler implements IRequestHandler<AddAnnouncementCommand,AddAnnouncementResponse> {
    private IAnnouncementRepository repo;
    public AddAnnouncementCommandHandler(IAnnouncementRepository announcementRepository) {
        repo = announcementRepository;
    }
    @Override
    public AddAnnouncementResponse handle(AddAnnouncementCommand request) {
        if(request.getContent().isEmpty()){
            throw new RuntimeException("Content mustn't be empty");
        }
        repo.addAnnouncement(new Announcement(request.getId(), request.getContent(), request.getSubject(), request.getPublishedAt()));
        return new AddAnnouncementResponse();
    }
}
