package ua.goit.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String currentTimeHtml = "<h1>Current Time is %s</h1>";
        String timeZoneParameter = req.getParameter("timezone");
        ZoneOffset offset = ZoneOffset.UTC;
        String chosenTimeZone;
        if (timeZoneParameter != null) {
            int offsetHours = Integer.parseInt(timeZoneParameter.substring(3).trim());
            offset = ZoneOffset.ofHours(offsetHours);
            chosenTimeZone = timeZoneParameter.replace(" ","+");
        } else {
            chosenTimeZone = "UTC";
        }
        ZonedDateTime utcTime = ZonedDateTime.now(offset);
        String formattedDateTime = utcTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ")) + chosenTimeZone;
        resp.getWriter().write(currentTimeHtml.formatted(formattedDateTime));
        resp.getWriter().close();
    }
}
