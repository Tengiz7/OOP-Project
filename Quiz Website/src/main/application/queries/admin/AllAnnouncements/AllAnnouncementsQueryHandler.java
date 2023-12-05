package main.application.queries.admin.AllAnnouncements;
import main.application.announcements.IAnnouncementRepository;
import main.domain.Announcement;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class AllAnnouncementsQueryHandler implements IRequestHandler<AllAnnouncementsQuery, List<AllAnnouncementsResponse>> {
    private IAnnouncementRepository repo;
    public AllAnnouncementsQueryHandler(IAnnouncementRepository announcementRepository) {
        repo = announcementRepository;
    }

    @Override
    public List<AllAnnouncementsResponse> handle(AllAnnouncementsQuery request) {
        List<Announcement> announcementList = repo.getAllAnnouncements();
        List<AllAnnouncementsResponse> responses = new ArrayList<>();
        for(Announcement a : announcementList) {
            responses.add(map(a));
        }
        return responses;
    }

    private AllAnnouncementsResponse map(Announcement announcement) {
        AllAnnouncementsResponse result = new AllAnnouncementsResponse();
        result.setId(announcement.getId());
        result.setContent(announcement.getContent());
        result.setSubject(announcement.getSubject());
        result.setPublishedAt(announcement.getPublishedAt());
        return result;
    }
}
