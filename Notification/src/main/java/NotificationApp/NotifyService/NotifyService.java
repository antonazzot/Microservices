package NotificationApp.NotifyService;

import NotificationApp.NotificationModel.NotificateRequest;
import NotificationApp.NotifyRepository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final SenderService senderService;
    private final NotifyRepository notifyRepository;
    @Async
    public Integer notifyAndSaveRequest (NotificateRequest notificateRequest) {
        senderService.sendMail(notificateRequest.getCustomerEmail(), notificateRequest.getSubject(), notificateRequest.getMessage());
       return notifyRepository.save(notificateRequest).getId();
    }

}
