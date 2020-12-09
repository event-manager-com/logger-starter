package gregad.event_manager.loggerstarter.writers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Greg Adler
 */
@ConfigurationProperties(prefix = "logging.file.format")
@Getter
@Setter
public class LogProperties {
    private boolean text;
    private boolean excel;
}
