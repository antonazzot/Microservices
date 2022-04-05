package fraudApplication.controller;

import customerApplication.model.Customer;
import fraudApplication.service.FraudEmailService;
import fraudApplication.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fraudcheck")
public class FraudController {

    private final FraudService fraudService;
    private final FraudEmailService fraudEmailService;


    @GetMapping("/byid/{customerId}")
    @ResponseBody
    public boolean isCustomerFraud (@PathVariable Integer customerId) {
        return fraudService.isCustomerFraud(customerId);
    }

    @GetMapping("/byidandemail/{customerId}/{email}")
    @ResponseBody
    public boolean fullCheck (@PathVariable Integer customerId, @PathVariable String email) {
        return fraudService.isCustomerFraud(customerId) && fraudEmailService.isFraudEmail(email);
    }

    @GetMapping("/byemail/{email}")
    @ResponseBody
    public boolean frauderByEmail (@PathVariable String email) {
        return  fraudEmailService.isFraudEmail(email);
    }

    @PostMapping("/addtoblacklist/{customer}")
    @ResponseBody
    public ResponseEntity<?> addToBlackList (@RequestBody Customer customer) {
    f
    }
}
