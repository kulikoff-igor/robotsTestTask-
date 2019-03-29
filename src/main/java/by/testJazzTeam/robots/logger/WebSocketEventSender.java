package by.testJazzTeam.robots.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class WebSocketEventSender {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventSender.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public void sendLogToWebUI(RobotWorkLog log) {
        messagingTemplate.convertAndSend("/topic/robotWorkLog", log);
    }
}