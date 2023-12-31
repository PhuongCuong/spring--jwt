package fit.iuh.edu.vn.jwtbespring.response;

import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import fit.iuh.edu.vn.jwtbespring.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private int EC;
    private String EM;
    private List<RoleDTO> DT;
}
