package fit.iuh.edu.vn.jwtbespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String description;
    private String name;
}
