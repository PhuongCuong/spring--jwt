package fit.iuh.edu.vn.jwtbespring.dto;

import fit.iuh.edu.vn.jwtbespring.modules.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String group;
}
