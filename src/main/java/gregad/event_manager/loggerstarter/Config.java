package gregad.event_manager.loggerstarter;

import gregad.event_manager.loggerstarter.aspect.LogModelFactory;
import gregad.event_manager.loggerstarter.cleaner.Cleaner;
import gregad.event_manager.loggerstarter.writers.conditions.ConditionOnAllKafkaProperties;
import gregad.event_manager.loggerstarter.writers.conditions.ConditionOnAnyExcelOrTextOrKafkaProperty;
import gregad.event_manager.loggerstarter.writers.properties.kafka.*;
import gregad.event_manager.loggerstarter.writers.services.ExcelFileLogWriter;
import gregad.event_manager.loggerstarter.writers.services.KafkaLogWriter;
import gregad.event_manager.loggerstarter.writers.services.TextFileLogWriter;
import gregad.event_manager.loggerstarter.writers.properties.LogFileProperties;
import gregad.event_manager.loggerstarter.aspect.LoggingManagerAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Greg Adler
 */
@EnableConfigurationProperties({LogFileProperties.class,
        LogKafkaProperties.class,
        KafkaProperties.class,
        SecurityProperty.class,
        SaslProperty.class,
        JaasProperty.class,
        Output.class})
@Configuration
@EnableScheduling
@ConditionOnAnyExcelOrTextOrKafkaProperty
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
    
    @ConditionalOnProperty("logging.file.format.kafka")
    @Bean
    public KafkaLogWriter kafkaLogWriter(){
        return new KafkaLogWriter();
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
