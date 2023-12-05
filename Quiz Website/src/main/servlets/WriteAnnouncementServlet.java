package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.admin.AddAnnouncement.AddAnnouncementCommand;
import main.application.commands.admin.AddAnnouncement.AddAnnouncementResponse;
import main.application.commands.user.AddMessageCommand.AddMessageCommandResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/writeAnnouncement")
public class WriteAnnouncementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin_add_announcement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        String subject = request.getParameter("subject");
        String announcement = request.getParameter("announcement");

        AddAnnouncementCommand addAnnouncementCommand = new AddAnnouncementCommand(announcement, subject, LocalDateTime.now());
        AddAnnouncementResponse addAnnouncement = mediator.send(addAnnouncementCommand);

        request.getRequestDispatcher("adminIndex").forward(request, response);
    }
}
