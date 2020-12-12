package gregad.event_manager.loggerstarter.writers.conditions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;

/**
 * @author Greg Adler
 */
public class OnExcelOrTextOrKafkaPropertyCondition extends AnyNestedCondition {

    public OnExcelOrTextOrKafkaPropertyCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }
    @ConditionalOnProperty(name = "logging.file.format.kafka")
    public static class OnAllKafkaPropertyCondition {}

    @ConditionalOnProperty(name = "logging.file.format.excel")
    public static class OnExcelCondition{}

    @ConditionalOnProperty(name = "logging.file.format.text")
    public static class OnTextCondition{}
    
}
