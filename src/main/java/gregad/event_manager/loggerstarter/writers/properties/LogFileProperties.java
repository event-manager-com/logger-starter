package gregad.event_manager.loggerstarter.writers.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
@ConfigurationProperties(prefix = "logging.file.format")
@Getter
@Setter
public class LogFileProperties {
    private boolean text;
    private boolean excel;
    private boolean kafka;
    private String cronExpresion;
}
