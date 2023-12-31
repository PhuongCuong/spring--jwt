package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import fit.iuh.edu.vn.jwtbespring.dto.RoleDTO;
import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.modules.Role;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.RoleResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.responsitory.RoleResponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleResponsitory roleResponsitory;

    public RoleResponse getAllRole() {
        List<Role> roles = roleResponsitory.findAll();

        if (roles != null && !roles.isEmpty()) {
            List<RoleDTO> roleDTOS =  roles.stream()
                    .map(role -> new RoleDTO(role.getId(),role.getUrl(),
                            role.getMethod(),role.getDescriptions()))
                    .collect(Collectors.toList());
            return RoleResponse.builder()
                    .EC(0)
                    .EM("get data success")
                    .DT(roleDTOS)
                    .build();
        } else {
            return RoleResponse.builder()
                    .EC(1)
                    .EM("no data found or data error")
                    .DT(null)
                    .build();
        }


    }

    public RoleResponse addnewRole(RoleDTO roleDTO) {
        try {
            if(roleDTO.getUrl() == null || roleDTO.getMethod() == null){
                return RoleResponse.builder()
                        .EC(1)
                        .EM("url or method is empty")
                        .DT(null)
                        .build();
            }else{
                Optional<Role> optional = roleResponsitory.findRoleByUrl(roleDTO.getUrl());
                if(optional.isPresent()){
                    return RoleResponse.builder()
                            .EC(1)
                            .EM("url is existed")
                            .DT(null)
                            .build();
                }else{
                    Role role = new Role();
                    role.setUrl(roleDTO.getUrl());
                    role.setMethod(roleDTO.getMethod());
                    role.setDescriptions(roleDTO.getDescriptions());
                    roleResponsitory.save(role);
                    return RoleResponse.builder()
                            .EC(0)
                            .EM("add new role success")
                            .build();
                }
            }
        }catch (Exception exception){
            System.out.println(exception);
            return RoleResponse.builder()
                    .EC(-1)
                    .EM("is not authorization")
                    .DT(null)
                    .build();
        }
    }

    public RoleResponse deleterole(Long id) {
        Optional<Role> optional = roleResponsitory.findById(id);
        if(optional.isPresent()){
            roleResponsitory.delete(optional.get());
            return RoleResponse.builder()
                    .EC(0)
                    .EM("delete role is success")
                    .build();
        }else{
            return RoleResponse.builder()
                    .EC(1)
                    .EM("not found role")
                    .build();
        }
    }
}
