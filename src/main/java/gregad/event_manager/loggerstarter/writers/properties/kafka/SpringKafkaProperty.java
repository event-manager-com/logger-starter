package gregad.event_manager.loggerstarter.writers.properties.kafka;

import gregad.event_manager.loggerstarter.writers.properties.kafka.LogKafkaProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
//@ConfigurationProperties(prefix = "spring")
@Getter
@Setter
public class SpringKafkaProperty {
    private LogKafkaProperties kafka;
    private Cloud cloud;
}
