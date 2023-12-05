package main.application.queries.admin.AllAnnouncements;

import java.time.LocalDateTime;

public class AllAnnouncementsResponse {
    private int id;
    private String content;
    private String subject;
    private LocalDateTime publishedAt;

    public void setId(int id) {
        this.id = id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}
