package Infrastructure;

import junit.framework.TestCase;
import main.application.announcements.IAnnouncementRepository;
import main.domain.Announcement;
import main.infrastructure.AnnouncementRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AnnouncementRepositoryTests extends TestCase {
    private IAnnouncementRepository announcementRepository;
    private BasicDataSource dataSource;

    public AnnouncementRepositoryTests(){
        this.dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oop_db_test");
        dataSource.setUsername("test");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        try {
            this.announcementRepository = new AnnouncementRepository(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() throws SQLException {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("DELETE FROM Announcements");
        statement.execute();
    }

    public void testAddAnnouncement() throws SQLException {
        setUp();
        Announcement announcement = new Announcement();
        announcement.setContent("content");
        announcement.setSubject("subject");
        announcement.setPublishedAt(LocalDateTime.now());
        announcementRepository.addAnnouncement(announcement);
        List<Announcement> announcements = announcementRepository.getAllAnnouncements();
        assertEquals(1, announcements.size());
        assertEquals("content", announcements.get(0).getContent());
        assertEquals("subject", announcements.get(0).getSubject());
    }

    public void testGetAllAnnouncements() throws SQLException {
        setUp();
        Announcement announcement1 = new Announcement();
        announcement1.setContent("content1");
        announcement1.setSubject("subject1");
        announcement1.setPublishedAt(LocalDateTime.now());
        Announcement announcement2 = new Announcement();
        announcement2.setContent("content2");
        announcement2.setSubject("subject2");
        announcement2.setPublishedAt(LocalDateTime.now());
        announcementRepository.addAnnouncement(announcement1);
        announcementRepository.addAnnouncement(announcement2);
        List<Announcement> announcements = announcementRepository.getAllAnnouncements();
        assertEquals(2, announcements.size());
        assertEquals("content1", announcements.get(0).getContent());
        assertEquals("subject1", announcements.get(0).getSubject());
        assertEquals("content2", announcements.get(1).getContent());
        assertEquals("subject2", announcements.get(1).getSubject());
    }
}
