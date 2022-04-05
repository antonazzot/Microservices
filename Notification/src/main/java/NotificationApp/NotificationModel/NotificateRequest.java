package NotificationApp.NotificationModel;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificatereqest")
public class NotificateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "customerid")
    private Integer customerId;
    @Column(name = "customeremail")
    private String customerEmail;
    private String subject;
    private String message;
}
