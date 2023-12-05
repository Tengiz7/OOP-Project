package main.infrastructure;

import main.application.announcements.IAnnouncementRepository;
import main.domain.Announcement;
import main.domain.ObjectMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepository implements IAnnouncementRepository {
    private Connection connection;

    public AnnouncementRepository (Connection connection){
        this.connection = connection;
    }

    public void addAnnouncement(Announcement announcement){
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Announcements (content, subject, publishedAt) values (?, ?, ?);")){
            statement.setString(1, announcement.getContent());
            statement.setString(2, announcement.getSubject());
            statement.setTimestamp(3, Timestamp.valueOf(announcement.getPublishedAt()));
            statement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public List<Announcement> getAllAnnouncements(){
        List<Announcement> announcements = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM announcements ORDER BY publishedAt DESC")){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                String subject = resultSet.getString("subject");
                LocalDateTime publishedAt = resultSet.getTimestamp("publishedAt").toLocalDateTime();
                Announcement announcement = new Announcement(id, content, subject, publishedAt);
                announcements.add(announcement);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return announcements;
    }
}
