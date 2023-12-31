package fit.iuh.edu.vn.jwtbespring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fit.iuh.edu.vn.jwtbespring.dto.RoleDTO;
import fit.iuh.edu.vn.jwtbespring.response.GroupResponse;
import fit.iuh.edu.vn.jwtbespring.response.RoleResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.services.GroupService;
import fit.iuh.edu.vn.jwtbespring.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> getAllrole() throws JsonProcessingException {

        return ResponseEntity.ok(roleService.getAllRole());
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> createNewRole(@RequestBody RoleDTO roleDTO){
        return ResponseEntity.ok(roleService.addnewRole(roleDTO));
    }

    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> deleteRole(@RequestParam Long id) throws JsonProcessingException {

        return ResponseEntity.ok(roleService.deleterole(id));
    }
}
