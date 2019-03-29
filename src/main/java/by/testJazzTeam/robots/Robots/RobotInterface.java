package by.testJazzTeam.robots.Robots;

import by.testJazzTeam.robots.task.Task;

import java.util.Queue;

public interface RobotInterface {
    void job();

    void killRobot();

    boolean checkJob(String typeTask);

    void addTaskToQueue(Task task);

    Integer numberOfJobsInQueue();

    Queue<Task> getJobQueue();

    Integer getIdRobot();
}
