package user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    private Integer id;
    private String name;
    private String password;
}
