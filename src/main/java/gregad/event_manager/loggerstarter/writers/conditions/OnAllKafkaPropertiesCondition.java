package gregad.event_manager.loggerstarter.writers.conditions;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Greg Adler
 */
public class OnAllKafkaPropertiesCondition extends AllNestedConditions {
    
    public OnAllKafkaPropertiesCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }
   
    @ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
    public static class OnBootstrapServiceCondition{}
    
    @ConditionalOnProperty(name = "spring.kafka.properties.security.protocol")
    public static class OnSecurityProtocolCondition{}
    
    @ConditionalOnProperty(name = "spring.kafka.properties.sasl.mechanism")
    public static class OnSaslMechanismCondition{}
    
    @ConditionalOnProperty(name = "spring.kafka.properties.sasl.jaas.config")
    public static class OnSaslJaasConfigCondition{}
    
    @ConditionalOnProperty(name = "spring.cloud.stream.bindings.exceptionlog.destination")
    public static class OnCloudStreamBindingsExceptionlogDestinationCondition{}
}
