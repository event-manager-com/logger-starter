package gregad.event_manager.loggerstarter;

import gregad.event_manager.loggerstarter.aspect.LogModelFactory;
import gregad.event_manager.loggerstarter.cleaner.Cleaner;
import gregad.event_manager.loggerstarter.writers.ExcelFileLogWriter;
import gregad.event_manager.loggerstarter.writers.TextFileLogWriter;
import gregad.event_manager.loggerstarter.writers.LogProperties;
import gregad.event_manager.loggerstarter.aspect.LoggingManagerAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Greg Adler
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@EnableScheduling
public class Config {
    
    @ConditionalOnProperty("logging.file.format.text")
    @Bean
    public TextFileLogWriter fileLogWriter(){
        return new TextFileLogWriter();
    }
    
    @ConditionalOnProperty("logging.file.format.excel")
    @Bean
    public ExcelFileLogWriter excelFileLogWriter(){
        return new ExcelFileLogWriter();
    }
    
    @Bean
    public Cleaner cleaner(){
        return new Cleaner();
    }

    @Bean
    public LoggingManagerAspect loggingManagerAspect(){
        return new LoggingManagerAspect();
    }
    
    @Bean
    public LogModelFactory logModelFactory(){
        return new LogModelFactory();
    }
    
}
