package gregad.event_manager.loggerstarter.writers.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gregad.event_manager.loggerstarter.aspect.DoLogging;
import gregad.event_manager.loggerstarter.aspect.LogModel;
import gregad.event_manager.loggerstarter.writers.interfaces.KafkaProducer;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * @author Greg Adler
 */
public class KafkaLogWriter implements LogWriter {
    
    private ObjectMapper mapper=new ObjectMapper();
    
    @Autowired
    private KafkaProducer producer;
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log, String rootPath, DoLogging annotation) {
        LogDto logDto=new LogDto(log.getDate().format(DateTimeFormatter.ofPattern(annotation.dateFormat())),
                log.getTime().format(DateTimeFormatter.ofPattern(annotation.timeFormat())),
                log.getLogType(),log.getResource(),log.getMessage());
        String jsonLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(logDto);
        producer.exceptionlog().send(MessageBuilder.withPayload(jsonLog).build());
    }
    
    private class LogDto implements Serializable{
        private LogDto(String date, String time, String logType, String resource, String message) {
            this.date = date;
            this.time = time;
            this.logType = logType;
            this.resource = resource;
            this.message = message;
        }

        private static final long serialVersionUID = 1L;

        private String date;
        private String time;
        private String logType;
        private String resource;
        private String message;
    }
}
