package gregad.event_manager.loggerstarter.writers.conditions;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Greg Adler
 */
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnExcelOrTextOrKafkaPropertyCondition.class)
public @interface ConditionOnAnyExcelOrTextOrKafkaProperty {
}
