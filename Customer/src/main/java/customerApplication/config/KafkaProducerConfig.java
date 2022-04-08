package customerApplication.config;

import com.fasterxml.jackson.databind.JsonSerializable;
import customerApplication.model.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String botstrapService;

    public Map<String, Object> produserConfig () {
        Map <String, Object> prop = new HashMap<>();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, botstrapService);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return prop;
    }
    @Bean
    public ProducerFactory <String, Customer> producerFactory () {
        return new DefaultKafkaProducerFactory<>(produserConfig());
    }

    @Bean
    public KafkaTemplate <String, Customer> kafkaTemplate (@Autowired ProducerFactory<String, Customer> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
