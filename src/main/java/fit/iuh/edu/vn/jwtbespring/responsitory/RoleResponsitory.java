package fit.iuh.edu.vn.jwtbespring.responsitory;

import fit.iuh.edu.vn.jwtbespring.modules.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleResponsitory extends JpaRepository<Role,Long> {

    Optional<Role> findRoleByUrl(String url);

}
