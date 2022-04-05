package NotificationApp.Controller;

import NotificationApp.NotificationModel.NotificateRequest;
import NotificationApp.NotifyService.NotifyService;
import customerApplication.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notify")
public class Controller {

    private final NotifyService notifyService;

    @PostMapping("/hellonotify/{customer}")
    @ResponseBody
    public Integer welcomenotify (@RequestBody Customer customer) {
        log.info("in this");
        NotificateRequest notificateRequest =NotificateRequest.builder()
                .customerId(customer.getId())
                .customerEmail(customer.getEmail())
                .subject("Hello from notificate service ")
                .message("Dear " + customer.getFirstName() + " " +customer.getLastName()+" , we glad to greet you in our system! Have a nice day. ")
                .build();
        return notifyService.notifyAndSaveRequest(notificateRequest);
    }

}
