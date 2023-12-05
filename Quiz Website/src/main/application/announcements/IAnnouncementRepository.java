package main.application.announcements;
import main.domain.Announcement;
import java.util.List;

public interface IAnnouncementRepository {
    public void addAnnouncement(Announcement announcement);
    public List<Announcement> getAllAnnouncements();
}
