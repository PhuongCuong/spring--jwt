package fit.iuh.edu.vn.jwtbespring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fit.iuh.edu.vn.jwtbespring.dto.RegisterDTO;
import fit.iuh.edu.vn.jwtbespring.dto.UpdateUserDTO;
import fit.iuh.edu.vn.jwtbespring.dto.UserDataDTO;
import fit.iuh.edu.vn.jwtbespring.dto.UserIDDTO;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.response.AuthenticationResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.services.JwtService;
import fit.iuh.edu.vn.jwtbespring.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/account")
//    @PreAuthorize("@yourPermissionEvaluator.hasPermission(authentication,'/api/user/account')")
    public ResponseEntity<UserResponse> getdataAcount(HttpServletRequest req) {
        return ResponseEntity.ok(userService.getdatauser(req,"jwt"));
    }

    @GetMapping(value = "/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getAllUser() throws JsonProcessingException {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> addnewUser(@RequestBody RegisterDTO registerDTO) throws JsonProcessingException {

        return ResponseEntity.ok(userService.addNewUser(registerDTO));
    }

    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> deletUser(@RequestParam Long id) throws JsonProcessingException {

        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserDTO updateUserDTO) throws JsonProcessingException {

        return ResponseEntity.ok(userService.updateUsers(updateUserDTO));
    }
}
