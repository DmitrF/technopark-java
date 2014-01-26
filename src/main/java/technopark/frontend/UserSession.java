package technopark.frontend;

import java.util.concurrent.atomic.AtomicInteger;

public class UserSession {
    private static final AtomicInteger currentSessionId = new AtomicInteger();

    private int sessionId;
    private String username = "";
    private int userId;

    public UserSession() {
        sessionId = currentSessionId.incrementAndGet();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getSessionId() {
        return sessionId;
    }
}
