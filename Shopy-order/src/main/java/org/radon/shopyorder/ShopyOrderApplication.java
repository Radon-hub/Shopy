package org.radon.shopyorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopyOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopyOrderApplication.class, args);
    }

}
