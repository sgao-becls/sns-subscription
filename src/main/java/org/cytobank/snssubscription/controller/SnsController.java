/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cytobank.snssubscription.controller;

import lombok.extern.log4j.Log4j2;
import org.cytobank.snssubscription.config.property.SnsProperties;
import org.cytobank.snssubscription.dto.SnsNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sgao
 */
@RestController
@RequestMapping("/sns")
@Log4j2
public class SnsController {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    private final SnsProperties snsProperties;

    private static final String MY_TOPIC = "song-topic";

    @Autowired
    public SnsController(NotificationMessagingTemplate notificationMessagingTemplate, SnsProperties snsProperties) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
        this.snsProperties = snsProperties;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendNotification(@RequestBody SnsNotificationDTO notification) {
        log.debug("Going to send notification {}", notification);
        this.notificationMessagingTemplate.sendNotification(snsProperties.getTopics().get(MY_TOPIC), notification.getMessage(), notification.getSubject());
    }

}
