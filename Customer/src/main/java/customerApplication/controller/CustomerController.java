package customerApplication.controller;

import customerApplication.customerservice.CustomerService;
import customerApplication.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/getbyid/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getById (@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("saveone")
    @ResponseBody
    public ResponseEntity<Integer> saveCustomer (@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.saveCustomer(customer));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
