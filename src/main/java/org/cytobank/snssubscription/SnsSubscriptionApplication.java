package org.cytobank.snssubscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSns;

@SpringBootApplication
@EnableSns
public class SnsSubscriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnsSubscriptionApplication.class, args);
    }

}
