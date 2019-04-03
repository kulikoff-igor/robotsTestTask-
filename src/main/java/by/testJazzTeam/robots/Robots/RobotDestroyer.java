package by.testJazzTeam.robots.Robots;

import by.testJazzTeam.robots.logger.RobotWorkLog;
import by.testJazzTeam.robots.logger.WebSocketEventSender;
import by.testJazzTeam.robots.task.Task;

import java.util.LinkedList;
import java.util.Queue;

public class RobotDestroyer implements RobotInterface {
    private WebSocketEventSender webSocketEventSender;
    private Integer idRobot;
    private final String typeJob = "destroy";
    private Thread thread;

    private Queue<Task> jobQueue = new LinkedList<>();

    public Integer getIdRobot() {
        return idRobot;
    }

    public String getTypeJob() {
        return typeJob;
    }

    public Queue<Task> getJobQueue() {
        return jobQueue;
    }

    public RobotDestroyer(Integer idRobot, Task jobQueue, WebSocketEventSender webSocketEventSender) {
        this.idRobot = idRobot;
        this.jobQueue.add(jobQueue);
        this.webSocketEventSender = webSocketEventSender;
        Runnable task = () -> job();
        thread = new Thread(task);
        thread.start();
    }

    @Override
    public void job() {
        RobotWorkLog robotWorkLog = new RobotWorkLog();
        if (jobQueue.size() == 0) {
            robotWorkLog.setContent("idRobot Destroyer : " + idRobot + " status is free ");
            webSocketEventSender.sendLogToWebUI(robotWorkLog);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i <= jobQueue.size(); i++) {
                Task taskTemp = jobQueue.poll();
                for (int j = 0; j <= taskTemp.getNumberOfStepsInTheJob(); j++) {
                    robotWorkLog.setContent("idRobot Destroyer : " + idRobot + " Task : " + taskTemp.getTypeTask() + " № Task " + j);
                    webSocketEventSender.sendLogToWebUI(robotWorkLog);
                    try {
                        Thread.sleep(taskTemp.getTimeForOneStep() * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        job();
    }

    @Override
    public void killRobot() {
        thread.stop();
    }

    @Override
    public boolean checkJob(String typeTask) {
        if (typeJob.equals(typeTask)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addTaskToQueue(Task task) {
        jobQueue.add(task);
    }

    @Override
    public Integer numberOfJobsInQueue() {
        return jobQueue.size();
    }
}