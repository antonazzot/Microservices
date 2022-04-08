package customerApplication.customerservice;

import customerApplication.customerrepository.CustomerRepository;
import customerApplication.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate <String, Customer> kafkaTemplate;

    public Customer getCustomerById (Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        log.info("Customer info: = {}", customer.getId() +" " + customer.getFirstName() + " " +customer.getLastName());
           return customer;
    }

    public Integer saveCustomer (Customer customer) throws IllegalAccessException {
        log.info("Customer", customer.getId()+" " +customer.getFirstName());
        if (
                Boolean.TRUE.equals(
                        restTemplate.getForObject("http://FRAUD/fraudcheck/byemail/{email}",
                        Boolean.class, customer.getEmail()))

        )
        {kafkaTemplate.send("customerfrauder", customer);
        return customer.getId();}
        else {

            Integer id = customerRepository.save(customer).getId();
            restTemplate.postForEntity("http://NOTIFICATION/notify/hellonotify/{customer}",
            customer, Integer.class, customer);

            return id;
        }
    }
}
