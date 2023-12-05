package main.domain;

import java.time.LocalDateTime;

public class Announcement {
    private int id;
    private String content;

    public String subject;
    private LocalDateTime publishedAt;

    public Announcement(int id, String announcementContent, String subject, LocalDateTime publishDateTime) {
        this.id = id;
        content = announcementContent;
        this.subject = subject;
        publishedAt = publishDateTime;
    }

    public Announcement() {
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
