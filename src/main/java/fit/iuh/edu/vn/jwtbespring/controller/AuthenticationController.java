package fit.iuh.edu.vn.jwtbespring.controller;

import fit.iuh.edu.vn.jwtbespring.dto.LoginDTO;
import fit.iuh.edu.vn.jwtbespring.response.AuthenticationResponse;
import fit.iuh.edu.vn.jwtbespring.dto.RegisterDTO;
import fit.iuh.edu.vn.jwtbespring.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDTO request
            ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDTO request,HttpServletResponse response
            ){
            String jwtToken = authenticationService.login(request).getDT();
            Cookie cookie = new Cookie("jwt",jwtToken);
            cookie.setMaxAge(60*60*24);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok(authenticationService.login(request));
    }


}
