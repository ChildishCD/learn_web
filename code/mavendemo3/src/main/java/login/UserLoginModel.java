package login;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserLoginModel {
     private Integer uid;
     private String username;
     private String password;
     private Long roleId;
     private LocalDateTime regTime;
     private LocalDateTime loginTime;
     private Integer isValid;
}
