package fit.iuh.edu.vn.jwtbespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUserDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
