package fit.iuh.edu.vn.jwtbespring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fit.iuh.edu.vn.jwtbespring.dto.GroupRoleDTO;
import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.GroupRoleResponse;
import fit.iuh.edu.vn.jwtbespring.services.GroupRoleService;
import fit.iuh.edu.vn.jwtbespring.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-role")
@RequiredArgsConstructor
public class GroupRoleController {

    private final GroupService groupService;
    private final GroupRoleService groupRoleService;

    @GetMapping(value = "/get-by-id",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupRoleResponse> getAllgrouprole(@RequestParam Long groupId) throws JsonProcessingException {

        return ResponseEntity.ok(groupService.getAllGroupRole(groupId));
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupRoleResponse> addNewGroupRole(@RequestBody List<GroupRoleDTO> groupRole){
        return ResponseEntity.ok(groupRoleService.addnewGroupRole(groupRole));
    }
}
