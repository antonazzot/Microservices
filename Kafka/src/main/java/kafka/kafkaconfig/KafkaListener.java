package kafka.kafkaconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import customerApplication.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListener {
    @Autowired
    private final RestTemplate restTemplate;

    @SneakyThrows
    @org.springframework.kafka.annotation.KafkaListener
            (groupId = "frauderGroup",
            topics = "customerfrauder")
    public void kafkaListener (String message)  {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(message, Customer.class);
        log.info("From listener ={}", message);
        restTemplate.postForEntity("http://NOTIFICATION/notify/hellonotify/{customer}",
                customer, Integer.class, customer);
    }
}
