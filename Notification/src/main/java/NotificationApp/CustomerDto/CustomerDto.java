package NotificationApp.CustomerDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

}
