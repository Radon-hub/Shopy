package org.radon.shopyvendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ShopyVendorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopyVendorApplication.class, args);
    }

}
