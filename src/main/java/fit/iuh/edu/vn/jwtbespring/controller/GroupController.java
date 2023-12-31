package fit.iuh.edu.vn.jwtbespring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fit.iuh.edu.vn.jwtbespring.dto.GroupDTO;
import fit.iuh.edu.vn.jwtbespring.dto.RoleDTO;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.RoleResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping(value = "/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupResponse> getAllgroup() throws JsonProcessingException {

        return ResponseEntity.ok(groupService.getAllGroup());
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupResponse> createNewGroup(@RequestBody GroupDTO groupDTO){
        return ResponseEntity.ok(groupService.addnewGroup(groupDTO));
    }

    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupResponse> deleteGroup(@RequestParam Long id) throws JsonProcessingException {

        return ResponseEntity.ok(groupService.deleteGroup(id));
    }

}
