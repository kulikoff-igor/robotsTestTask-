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

