package by.testJazzTeam.robots.Robots;

import by.testJazzTeam.robots.logger.RobotWorkLog;
import by.testJazzTeam.robots.task.Task;
import by.testJazzTeam.robots.logger.WebSocketEventSender;

import java.util.LinkedList;
import java.util.Queue;

public class RobotDigger implements RobotInterface {

    private WebSocketEventSender webSocketEventSender;
    private Integer idRobot;
    private final String typeJob = "dig";
    private Queue<Task> jobQueue = new LinkedList<>();
    private Thread thread;


    public RobotDigger(Integer idRobot, Task jobQueue ,  WebSocketEventSender webSocketEventSender) {
        this.idRobot = idRobot;
        this.jobQueue.add(jobQueue);
        this.webSocketEventSender= webSocketEventSender;
        Runnable task = () -> job();
        thread = new Thread(task);
        thread.start();

    }

    public Integer getIdRobot() {
        return idRobot;
    }

    public String getTypeJob() {
        return typeJob;
    }
    @Override
    public Queue<Task> getJobQueue() {
        return jobQueue;
    }

    @Override
    public void job() {
        RobotWorkLog robotWorkLog=new RobotWorkLog();
        if (jobQueue.size() == 0) {
            robotWorkLog.setContent("idRobot Digger : " + idRobot + " status is free  ");
            webSocketEventSender.sendLogToWebUI(robotWorkLog);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i <= jobQueue.size(); i++) {
                Task taskTemp = jobQueue.poll();
                try {
                    for (int j = 0; j <= taskTemp.getNumberOfStepsInTheJob(); j++) {
                        robotWorkLog.setContent("idRobot Digger : " + idRobot + " Task : " + taskTemp.getTypeTask() + " № Task " + j);
                        webSocketEventSender.sendLogToWebUI(robotWorkLog);
                        try {
                            Thread.sleep(taskTemp.getTimeForOneStep() * 100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {

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
