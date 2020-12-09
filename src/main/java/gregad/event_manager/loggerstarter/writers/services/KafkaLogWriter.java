package gregad.event_manager.loggerstarter.writers.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gregad.event_manager.loggerstarter.aspect.LogModel;
import gregad.event_manager.loggerstarter.writers.interfaces.KafkaProducer;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author Greg Adler
 */
public class KafkaLogWriter implements LogWriter {
    
    private ObjectMapper mapper=new ObjectMapper();
    
    @Autowired
    private KafkaProducer producer;
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log, String rootPath) {
        String jsonLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(log);
        producer.exceptionlog().send(MessageBuilder.withPayload(jsonLog).build());
    }
}
