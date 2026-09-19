package todo.Security.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionServiceImpl {

    /*
     * username -> active JTI
     */
    private final Map<String, String> activeSessions = new ConcurrentHashMap<>();


    public void createSession(String username, String jti) {

        /*
         * If the user already has a session,
         * it will be replaced.
         */
        activeSessions.put(username, jti);
    }


    public boolean isSessionActive(String username, String jti) {

        String activeJti = activeSessions.get(username);

        return activeJti != null &&
                activeJti.equals(jti);
    }


    public void logout(String username, String jti) {

        activeSessions.remove(username, jti);
    }
}
