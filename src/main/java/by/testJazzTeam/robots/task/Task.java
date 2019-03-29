package by.testJazzTeam.robots.task;

public class Task {
    private String typeTask;
    private Integer numberOfStepsInTheJob;
    private Integer timeForOneStep;
    private Integer taskPerformer;

    public Task(String typeTask, Integer numberOfStepsInTheJob, Integer timeForOneStep, Integer taskPerformer) {
        this.typeTask = typeTask;
        this.numberOfStepsInTheJob = numberOfStepsInTheJob;
        this.timeForOneStep = timeForOneStep;
        this.taskPerformer = taskPerformer;
    }

    public Task() {
    }

    public String getTypeTask() {
        return typeTask;
    }

    public Integer getNumberOfStepsInTheJob() {
        return numberOfStepsInTheJob;
    }

    public Integer getTimeForOneStep() {
        return timeForOneStep;
    }

    public Integer getTaskPerformer() {
        return taskPerformer;
    }

}
