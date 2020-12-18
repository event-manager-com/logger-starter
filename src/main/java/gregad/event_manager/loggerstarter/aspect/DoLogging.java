package gregad.event_manager.loggerstarter.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Greg Adler
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DoLogging {
    String rootPath() default ".";
    int cleanLogDaysAgo() default 30;
    String dateFormat() default "yyyy/MM/dd";
    String timeFormat() default "hh:mm:ss";
}
