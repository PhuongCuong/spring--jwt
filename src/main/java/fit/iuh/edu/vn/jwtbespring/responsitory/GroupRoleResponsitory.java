package fit.iuh.edu.vn.jwtbespring.responsitory;

import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.pks.GroupRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRoleResponsitory extends JpaRepository<GroupRole, GroupRolePK> {
    List<GroupRole> findGroupRoleByGroup_Id(Long groupId);
}
