package fit.iuh.edu.vn.jwtbespring.pks;

import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.modules.Role;

import java.io.Serializable;

public class GroupRolePK implements Serializable {
    private Role role;
    private Group group;
}
