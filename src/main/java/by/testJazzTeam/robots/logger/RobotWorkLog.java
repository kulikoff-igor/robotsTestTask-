package by.testJazzTeam.robots.logger;

import org.springframework.stereotype.Component;

@Component
public class RobotWorkLog {

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public RobotWorkLog(String content) {
        this.content = content;
    }

    public RobotWorkLog() {
    }

    public String getContent() {
        return content;
    }

}