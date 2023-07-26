package test;
import jakarta.servlet.http.*;

import java.util.ArrayList;
import java.util.List;

public class MySessionListener implements HttpSessionListener {

    private static List<HttpSession> activeSessions = new ArrayList<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        activeSessions.add(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        activeSessions.remove(session);
    }

    public static List<HttpSession> getActiveSessions() {
        return activeSessions;
    }
}