package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import fit.iuh.edu.vn.jwtbespring.dto.GroupRoleDTO;
import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.GroupRoleResponse;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupResponsitory;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupRoleResponsitory;
import fit.iuh.edu.vn.jwtbespring.responsitory.RoleResponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupRoleService {
    private final GroupRoleResponsitory roleResponsitory;
    private final GroupResponsitory groupResponsitory;
    private final RoleResponsitory roleResponsitorys;

    public GroupRoleResponse addnewGroupRole(List<GroupRoleDTO> groupRoleDTOS) {
        try {
            List<GroupRole> groupRoles = roleResponsitory.findGroupRoleByGroup_Id(groupRoleDTOS.get(0).getGroupId());
            List<GroupRole> roleListss = roleResponsitory.findAll();
            if (groupRoleDTOS == null || groupRoleDTOS.isEmpty()) {
                for (GroupRole groupRole : groupRoles
                ) {
                    roleResponsitory.delete(groupRole);
                }
                return GroupRoleResponse.builder()
                        .EC(0)
                        .EM("update group role is success")
                        .build();

            } else {
                if (groupRoleDTOS.size() < groupRoles.size()) {

                    List<GroupRole> roleList = groupRoles.stream()
                            .filter(role -> groupRoleDTOS.stream()
                                    .noneMatch(roleDTO -> roleDTO.getGroupId() == role.getGroup().getId()
                                            && roleDTO.getRoleId() == role.getRole().getId()))
                            .collect(Collectors.toList());
                    System.out.println(roleList);
                    for (GroupRole groupRole : roleList
                    ) {
                        roleResponsitory.delete(groupRole);
                    }
                    return GroupRoleResponse.builder()
                            .EC(0)
                            .EM("update group role is success")
                            .build();
                } else {
                    List<GroupRole> roleList = groupRoles.stream()
                            .filter(role -> groupRoleDTOS.stream()
                                    .noneMatch(roleDTO -> roleDTO.getGroupId() == role.getGroup().getId()
                                            && roleDTO.getRoleId() == role.getRole().getId()))
                            .collect(Collectors.toList());
                    for (GroupRole groupRole:roleList
                    ) {
                        roleResponsitory.delete(groupRole);
                    }
                    List<GroupRoleDTO> result = groupRoleDTOS.stream()
                            .filter(roleDTO -> groupRoles.stream()
                                    .noneMatch(role -> role.getGroup().getId() == roleDTO.getGroupId()
                                            && role.getRole().getId() == roleDTO.getRoleId()))
                            .collect(Collectors.toList());
                    List<GroupRole> grouproleadd = convertToGroupRoleList(result);
                    for (GroupRole groupRole:grouproleadd
                    ) {
                        roleResponsitory.save(groupRole);
                    }
                    return GroupRoleResponse.builder()
                            .EC(0)
                            .EM("update group role is success")
                            .build();
                }


            }
        }catch (Exception exception){
            return GroupRoleResponse.builder()
                    .EC(-1)
                    .EM("is not authorization")
                    .DT(null)
                    .build();
        }
    }

    public List<GroupRole> convertToGroupRoleList(List<GroupRoleDTO> groupRoleDTOList) {
        List<GroupRole> groupRoleList = new ArrayList<>();
        for (GroupRoleDTO groupRoleDTO : groupRoleDTOList) {
            GroupRole groupRole = new GroupRole();
            // Set thông tin từ GroupRoleDTO vào GroupRole tương ứng
            groupRole.setGroup(groupResponsitory.findById(groupRoleDTO.getGroupId()).get());
            groupRole.setRole(roleResponsitorys.findById(groupRoleDTO.getRoleId()).get());

            // Thêm GroupRole mới vào danh sách
            groupRoleList.add(groupRole);
        }
        return groupRoleList;
    }

}
