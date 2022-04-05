package NotificationApp.NotifyRepository;

import NotificationApp.NotificationModel.NotificateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NotifyRepository extends JpaRepository <NotificateRequest, Integer> {
}
