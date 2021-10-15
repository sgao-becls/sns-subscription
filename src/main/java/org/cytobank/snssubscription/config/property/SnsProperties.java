package org.cytobank.snssubscription.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sgao
 */
@Data
@ConfigurationProperties(prefix = SnsProperties.PREFIX)
public class SnsProperties {

    public static final String PREFIX = "cytobank.aws.sns";

    /**
     * Topics
     */
    private Map<String, String> topics = new HashMap<>(5);

}
