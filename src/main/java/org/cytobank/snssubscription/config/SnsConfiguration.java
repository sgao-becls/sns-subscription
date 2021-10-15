package org.cytobank.snssubscription.config;

import com.amazonaws.services.sns.AmazonSNS;
import org.cytobank.snssubscription.config.property.SnsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SnsProperties.class})
public class SnsConfiguration {

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(@Autowired AmazonSNS amazonSns) {
        return new NotificationMessagingTemplate(amazonSns);
    }

}
