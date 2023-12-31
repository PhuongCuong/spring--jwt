package fit.iuh.edu.vn.jwtbespring.response;

import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private int EC;
    private String EM;
    private List<GroupDTO> DT;
}
