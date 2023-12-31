package fit.iuh.edu.vn.jwtbespring.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fit.iuh.edu.vn.jwtbespring.pks.GroupRolePK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_role")
@IdClass(GroupRolePK.class)
public class GroupRole {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;
    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;

    @Override
    public String toString() {
        return "GroupRole{" +
                "role=" + role +
                '}';
    }
}
