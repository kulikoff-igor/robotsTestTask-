package by.testJazzTeam.robots.controller;

import by.testJazzTeam.robots.tracker.ActivityTracker;
import by.testJazzTeam.robots.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("index/api")
public class RobotRestController {
    @Autowired
    ActivityTracker activityTracker;

    @PostMapping(value = "/addNewTask")
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewTask(@RequestBody Task task) {
        activityTracker.taskDistribution(task);
    }
}
