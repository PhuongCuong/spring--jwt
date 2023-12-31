package fit.iuh.edu.vn.jwtbespring.responsitory;

import fit.iuh.edu.vn.jwtbespring.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserResponsitory extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}
