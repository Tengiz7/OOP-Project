package Application.Queries;

import junit.framework.TestCase;
import main.application.announcements.IAnnouncementRepository;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsQuery;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsQueryHandler;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsResponse;
import main.domain.Announcement;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AllAnnouncementsQueryHandlerTests extends TestCase {

    @Test
    public void testWhenEverythingFine_ShouldReturnCorrectResult() {
        IAnnouncementRepository repo = new IAnnouncementRepository() {
            @Override
            public void addAnnouncement(Announcement announcement) {

            }

            @Override
            public List<Announcement> getAllAnnouncements() {
                return Arrays.asList(
                        new Announcement(1, "content1", "subject1", LocalDateTime.MIN),
                        new Announcement(2, "content2", "subject2", LocalDateTime.MIN));
            }
        };
        AllAnnouncementsQueryHandler handler = new AllAnnouncementsQueryHandler(repo);
        List<AllAnnouncementsResponse> result = handler.handle(new AllAnnouncementsQuery());
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("content1", result.get(0).getContent());
        assertEquals("subject1", result.get(0).getSubject());
        assertEquals(LocalDateTime.MIN, result.get(0).getPublishedAt());
        assertEquals(2, result.get(1).getId());
        assertEquals("content2", result.get(1).getContent());
        assertEquals("subject2", result.get(1).getSubject());
        assertEquals(LocalDateTime.MIN, result.get(1).getPublishedAt());
    }
}
