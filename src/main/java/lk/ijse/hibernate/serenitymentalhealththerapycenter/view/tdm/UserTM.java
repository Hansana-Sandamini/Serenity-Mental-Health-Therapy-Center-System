package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserTM {
    private String username;
    private String password;
    private String name;
    private String role;
    private String email;
    private String contactNumber;
}
