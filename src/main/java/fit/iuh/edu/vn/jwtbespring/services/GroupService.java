package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import fit.iuh.edu.vn.jwtbespring.dto.GroupRoleDTO;
import fit.iuh.edu.vn.jwtbespring.dto.RoleDTO;
import fit.iuh.edu.vn.jwtbespring.dto.UserDataDTO;
import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.modules.Role;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.GroupRoleResponse;
import fit.iuh.edu.vn.jwtbespring.response.RoleResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupResponsitory;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupRoleResponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupResponsitory groupResponsitory;
    private final GroupRoleResponsitory roleResponsitory;
    public GroupResponse getAllGroup() {
        List<Group> groups = groupResponsitory.findAll();

        if (groups != null && !groups.isEmpty()) {
            List<GroupDTO> groupDTOS =  groups.stream()
                    .map(group -> new GroupDTO(group.getId(),group.getDescriptions(),
                            group.getName()))
                    .collect(Collectors.toList());
            return GroupResponse.builder()
                    .EC(0)
                    .EM("get data success")
                    .DT(groupDTOS)
                    .build();
        } else {
            return GroupResponse.builder()
                    .EC(1)
                    .EM("no data found or data error")
                    .DT(null)
                    .build();
        }


    }

    public GroupRoleResponse getAllGroupRole(Long groupId) {
        List<GroupRole> groupRoles = roleResponsitory.findGroupRoleByGroup_Id(groupId);

        if (groupRoles != null && !groupRoles.isEmpty()) {
            List<GroupRoleDTO> groupDTOS =  groupRoles.stream()
                    .map(group -> new GroupRoleDTO(group.getGroup().getId(), group.getRole().getId()
                            ))
                    .collect(Collectors.toList());
            return GroupRoleResponse.builder()
                    .EC(0)
                    .EM("get data success")
                    .DT(groupDTOS)
                    .build();
        } else {
            return GroupRoleResponse.builder()
                    .EC(1)
                    .EM("no data found or data error")
                    .DT(null)
                    .build();
        }


    }

    public GroupResponse addnewGroup(GroupDTO groupDTO) {
        try {
            if(groupDTO.getName() == null){
                return GroupResponse.builder()
                        .EC(1)
                        .EM("name is empty")
                        .DT(null)
                        .build();
            }else{
                Optional<Group> optional = groupResponsitory.findGroupByName(groupDTO.getName());
                if(optional.isPresent()){
                    return GroupResponse.builder()
                            .EC(1)
                            .EM("role is existed")
                            .DT(null)
                            .build();
                }else{
                    Group group = new Group();
                    group.setName(groupDTO.getName());
                    group.setDescriptions(groupDTO.getDescription());
                    groupResponsitory.save(group);
                    return GroupResponse.builder()
                            .EC(0)
                            .EM("add new group success")
                            .build();
                }
            }
        }catch (Exception exception){
            System.out.println(exception);
            return GroupResponse.builder()
                    .EC(-1)
                    .EM("is not authorization")
                    .DT(null)
                    .build();
        }
    }

    public GroupResponse deleteGroup(Long id) {
        Optional<Group> optional = groupResponsitory.findById(id);
        if(optional.isPresent()){
            groupResponsitory.delete(optional.get());
            return GroupResponse.builder()
                    .EC(0)
                    .EM("delete group is success")
                    .build();
        }else{
            return GroupResponse.builder()
                    .EC(1)
                    .EM("not found group")
                    .build();
        }
    }
}
