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
public class UserDataDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String address;
    private String phone;
    private Group group;
}
