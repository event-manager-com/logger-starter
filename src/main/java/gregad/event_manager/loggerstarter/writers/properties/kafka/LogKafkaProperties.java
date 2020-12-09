package gregad.event_manager.loggerstarter.writers.properties.kafka;

import gregad.event_manager.loggerstarter.writers.properties.kafka.KafkaProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
@ConfigurationProperties(prefix = "spring.kafka")
@Getter
@Setter
public class LogKafkaProperties {
    private String bootstrapServers;
    private KafkaProperties properties;
}
