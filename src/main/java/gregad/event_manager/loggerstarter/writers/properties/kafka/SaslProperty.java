package gregad.event_manager.loggerstarter.writers.properties.kafka;

import gregad.event_manager.loggerstarter.writers.properties.kafka.JaasProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
@ConfigurationProperties(prefix = "spring.kafka.properties.sasl")
@Getter
@Setter
public class SaslProperty {
    private String mechanism;
    private JaasProperty jaas;
}
