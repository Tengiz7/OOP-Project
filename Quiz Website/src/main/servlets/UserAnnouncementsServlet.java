package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsQuery;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsResponse;
import main.domain.Announcement;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userAnnouncements")
public class UserAnnouncementsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        List<AllAnnouncementsResponse> announcements = mediator.send(new AllAnnouncementsQuery());
        request.setAttribute("announcements", announcements);

        request.getRequestDispatcher("user_announcements.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
