package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String role;
    private String email;
    private String contactNumber;
}
