package main.application.commands.admin.AddAnnouncement;
import main.mediator.abstractions.IRequest;

import java.time.LocalDateTime;

public class AddAnnouncementCommand implements IRequest<AddAnnouncementResponse> {
    private int id;
    private String content;
    private String subject;
    private LocalDateTime publishedAt;

    public AddAnnouncementCommand(String content, String subject, LocalDateTime publishedAt) {
        this.content = content;
        this.subject = subject;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
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

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
