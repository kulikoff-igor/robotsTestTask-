package by.testJazzTeam.robots.tracker;

import by.testJazzTeam.robots.Robots.RobotBuilder;
import by.testJazzTeam.robots.Robots.RobotDestroyer;
import by.testJazzTeam.robots.Robots.RobotDigger;
import by.testJazzTeam.robots.Robots.RobotInterface;
import by.testJazzTeam.robots.logger.RobotWorkLog;
import by.testJazzTeam.robots.logger.WebSocketEventSender;
import by.testJazzTeam.robots.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ActivityTracker {
    @Autowired
    RobotWorkLog robotWorkLog;
    @Autowired
    private WebSocketEventSender webSocketEventSender;

    private List<RobotInterface> robotList;

    public void taskDistribution(Task task) {
        if (task.getTypeTask().equals("kill")) {
            int idRobot = task.getTaskPerformer();
            for (RobotInterface robot : robotList) {
                if (robot.getIdRobot() == idRobot) {
                    killRobot(robot);
                    robotList.remove(robot);
                    robotWorkLog.setContent("robot killed by id : " + idRobot);
                    webSocketEventSender.sendLogToWebUI(robotWorkLog);

                    break;
                }
            }
        } else {
            if (task.getTaskPerformer() == -1) {
                if (robotList == null || robotList.size() == 0) {
                    robotList = new ArrayList<>();
                    robotList.add(switchRobot(task, getIdRobot()));
                } else {
                    boolean taskTransferredToRobot = false;
                    for (RobotInterface robot : robotList) {
                        if (robot.checkJob(task.getTypeTask()) && robot.numberOfJobsInQueue() < 2) {
                            robot.addTaskToQueue(task);
                            taskTransferredToRobot = true;
                            break;
                        }
                    }
                    if (taskTransferredToRobot == false) {
                        robotList.add(switchRobot(task, getIdRobot()));
                    }
                }
            } else {
                try {
                    for (RobotInterface robot : robotList) {
                        if (robot.getIdRobot() == task.getTaskPerformer()) {
                            robot.addTaskToQueue(task);
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    robotWorkLog.setContent("robot id does not exist : " + task.getTaskPerformer());
                    webSocketEventSender.sendLogToWebUI(robotWorkLog);
                }
            }
        }
    }

    private int getIdRobot() {
        return 0 + (int) (Math.random() * 100);
    }

    private void killRobot(RobotInterface robotInterface) {
        Queue<Task> jobQueue = robotInterface.getJobQueue();
        for (int i = 0; i < jobQueue.size(); i++) {
            try {
                Task taskTemp = jobQueue.poll();
                taskDistribution(taskTemp);
            } catch (NullPointerException e) {
                break;
            }
        }
        robotInterface.killRobot();
    }

    private RobotInterface switchRobot(Task task, Integer idRobot) {
        switch (task.getTypeTask()) {
            case "dig":
                robotWorkLog.setContent("create robot Digger id : " + idRobot);
                webSocketEventSender.sendLogToWebUI(robotWorkLog);
                return new RobotDigger(idRobot, task, webSocketEventSender);
            case "build":
                robotWorkLog.setContent("create robot Builder id : " + idRobot);
                webSocketEventSender.sendLogToWebUI(robotWorkLog);
                return new RobotBuilder(idRobot, task, webSocketEventSender);
            case "destroy":
                robotWorkLog.setContent("create robot Destroyer id : " + idRobot);
                webSocketEventSender.sendLogToWebUI(robotWorkLog);
                return new RobotDestroyer(idRobot, task, webSocketEventSender);
            default:
                return null;
        }
    }
}
