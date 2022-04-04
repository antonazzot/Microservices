package customerApplication.customerservice;

import customerApplication.customerrepository.CustomerRepository;
import customerApplication.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public Customer getCustomerById (Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        log.info("Customer info: = {}", customer.getId() +" " + customer.getFirstName() + " " +customer.getLastName());
           return customer;
    }

    public Integer saveCustomer (Customer customer) throws IllegalAccessException {
        if (
                Boolean.TRUE.equals(
                        restTemplate.getForObject("http://localhost:8081/fraudcheck/byemail/{email}",
                        Boolean.class, customer.getEmail()))
        )
            throw new IllegalAccessException("Frauder detected");
        else
        return customerRepository.save(customer).getId();
    }
}
