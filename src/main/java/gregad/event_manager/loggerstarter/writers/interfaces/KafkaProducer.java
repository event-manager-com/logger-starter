package gregad.event_manager.loggerstarter.writers.interfaces;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaProducer {
    String EXCEPTION_LOG="exceptionlog";
    @Output(EXCEPTION_LOG)
    MessageChannel exceptionlog();
    
}
