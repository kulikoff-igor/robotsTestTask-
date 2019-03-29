package by.testJazzTeam.robots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
/*@SpringBootApplication*/
public class RobotsApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(RobotsApplication.class, args);
    }

}
/*
@SpringBootApplication
public class RobotsApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(RobotsApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    // Put your logic here.
    @Override
    public void run(String... args) throws Exception {
        ActivityTracker activityTracker = new ActivityTracker();
        activityTracker.taskDistribution(new Task("build", 20, 30, -1));
        activityTracker.taskDistribution(new Task("build", 30, 700, -1));
        activityTracker.taskDistribution(new Task("build", 40, 500, -1));
        activityTracker.taskDistribution(new Task("build", 20, 5, -1));
        activityTracker.taskDistribution(new Task("dig", 1000, 5, -1));
        activityTracker.taskDistribution(new Task("dig", 50, 300, -1));
        activityTracker.taskDistribution(new Task("dig", 60, 100, -1));
        activityTracker.taskDistribution(new Task("dig", 100, 300, -1));
        activityTracker.taskDistribution(new Task("dig", 20, 300, -1));

    }
}*/
