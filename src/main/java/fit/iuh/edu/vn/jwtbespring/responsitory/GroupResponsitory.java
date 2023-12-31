package fit.iuh.edu.vn.jwtbespring.responsitory;

import fit.iuh.edu.vn.jwtbespring.modules.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupResponsitory extends JpaRepository<Group,Long> {
    Optional<Group> findGroupByName(String name);
}
