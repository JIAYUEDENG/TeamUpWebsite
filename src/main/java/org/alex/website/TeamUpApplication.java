package org.alex.website;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TeamUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeamUpApplication.class, args);
        log.info("Application started...");
    }
}
