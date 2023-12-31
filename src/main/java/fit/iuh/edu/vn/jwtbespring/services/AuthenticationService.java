package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.LoginDTO;
import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.response.AuthenticationResponse;
import fit.iuh.edu.vn.jwtbespring.dto.RegisterDTO;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupResponsitory;
import fit.iuh.edu.vn.jwtbespring.responsitory.UserResponsitory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserResponsitory userResponsitory;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final GroupResponsitory groupResponsitory;
    private final CustomUserDetailService userDetailService;

    public AuthenticationResponse register(RegisterDTO request){
        if(request.getEmail() == null || request.getPassword() == null || request.getPhone() == null){
            return AuthenticationResponse
                    .builder()
                    .EC(1)
                    .EM("email , password or phone is invalid")
                    .build();
        }else {
            Optional<User> optional = userResponsitory.findUserByEmail(request.getEmail());
            if(optional.isPresent()){
                return AuthenticationResponse
                        .builder()
                        .EC(1)
                        .EM("email is existed")
                        .build();
            }else {
                if(request.getEmail().equals("admin@gmail.com")){
                    Optional<Group> groupOptional = groupResponsitory.findById(2L);
                    User user = User.builder()
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .phone(request.getPhone())
                            .email(request.getEmail())
                            .address(request.getAddress())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .group(groupOptional.get())
                            .build();
                    userResponsitory.save(user);
                    return AuthenticationResponse
                            .builder()
                            .EC(0)
                            .EM("register success")
                            .build();
                }
                Optional<Group> groupOptional = groupResponsitory.findById(1L);
                User user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phone(request.getPhone())
                        .email(request.getEmail())
                        .address(request.getAddress())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .group(groupOptional.get())
                        .build();
                userResponsitory.save(user);
                return AuthenticationResponse
                        .builder()
                        .EC(0)
                        .EM("register success")
                        .build();
            }
        }

    }

    public AuthenticationResponse login(LoginDTO request){
        if(request.getEmail() == null || request.getPassword() == null) {
            return AuthenticationResponse
                    .builder()
                    .EC(1)
                    .EM("email or password is invalid")
                    .build();
        }else{
            var user = userResponsitory.findUserByEmail(request.getEmail())
                    .orElseThrow();
            if(user != null){
                if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                    var userDetail = userDetailService.loadUserByUsername(user.getEmail());
                    var jwtToken = jwtService.generateToken(userDetail);
                    return AuthenticationResponse
                            .builder()
                            .EC(0)
                            .EM("login success!")
                            .DT(jwtToken)
                            .build();
                }else{
                    return AuthenticationResponse
                            .builder()
                            .EC(1)
                            .EM("email or password not exactly")
                            .build();
                }

            }
            else{
                return AuthenticationResponse
                        .builder()
                        .EC(1)
                        .EM("not found email")
                        .build();

            }
        }

    }

}
