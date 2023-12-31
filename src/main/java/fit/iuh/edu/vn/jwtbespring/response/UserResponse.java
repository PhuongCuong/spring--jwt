package fit.iuh.edu.vn.jwtbespring.response;

import fit.iuh.edu.vn.jwtbespring.dto.UserDataDTO;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int EC;
    private String EM;
    private Object DT;
//    private List<User> data;
    private List<UserDataDTO> data;
}
