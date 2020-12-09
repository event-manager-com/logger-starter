package gregad.event_manager.loggerstarter.writers.properties.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
//@ConfigurationProperties(prefix = "spring.cloud.stream.bindings")
@Getter
@Setter
public class Bindings {
    private Exceptionlog exceptionlog;
}
