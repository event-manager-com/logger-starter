package gregad.event_manager.loggerstarter.writers.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gregad.event_manager.loggerstarter.aspect.DoLogging;
import gregad.event_manager.loggerstarter.aspect.LogModel;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * @author Greg Adler
 */
public class KafkaLogWriter implements LogWriter{
    
    private ObjectMapper mapper=new ObjectMapper();
    @Autowired
    private KafkaTemplate<String,String>template;
    @Value("${cloudkarafka.topic}")
    private String topic;

    
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log, String rootPath, DoLogging annotation) {
        LogDto logDto=new LogDto(log.getDate().format(DateTimeFormatter.ofPattern(annotation.dateFormat())),
                log.getTime().format(DateTimeFormatter.ofPattern(annotation.timeFormat())),
                log.getLogType(),log.getResource(),log.getMessage());
        String jsonLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(logDto);
        template.send(topic,jsonLog);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class LogDto implements Serializable{
        private static final long serialVersionUID = 1L;

        private String date;
        private String time;
        private String logType;
        private String resource;
        private String message;
    }
    
}
