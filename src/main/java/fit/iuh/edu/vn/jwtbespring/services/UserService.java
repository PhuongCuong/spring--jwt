package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.dto.*;
import fit.iuh.edu.vn.jwtbespring.modules.Group;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.response.AuthenticationResponse;
import fit.iuh.edu.vn.jwtbespring.response.UserResponse;
import fit.iuh.edu.vn.jwtbespring.responsitory.GroupResponsitory;
import fit.iuh.edu.vn.jwtbespring.responsitory.UserResponsitory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;

    private final UserResponsitory userResponsitory;
    private final GroupResponsitory groupResponsitory;
    private final PasswordEncoder passwordEncoder;

    public String getcookie(HttpServletRequest req, String nCookie){
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals(nCookie)) {
                  return c.getValue();
                }
            }
        }
        return null;
    }

    public UserResponse getdatauser(HttpServletRequest req, String nCookie) {
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals(nCookie)) {
                    String email = jwtService.extractUsername(c.getValue());
                    System.out.println(email);
                    Optional<User> user = userResponsitory.findUserByEmail(email);
                    if (user.isPresent()) {
                        return UserResponse
                                .builder()
                                .EC(0)
                                .EM("get data success")
                                .DT(
                                        new AccountUserDTO(user.get().getFirstName(),
                                                user.get().getLastName(),
                                                user.get().getAddress(),
                                                user.get().getPhone(),
                                                user.get().getEmail())
                                )
                                .build();
                    } else {
                        return UserResponse
                                .builder()
                                .EC(1)
                                .EM("get data error")
                                .DT(
                                        new AccountUserDTO()
                                )
                                .build();
                    }
                }
            }
        }
        return UserResponse
                .builder()
                .EC(1)
                .EM("get data error")
                .DT(
                        new AccountUserDTO()
                )
                .build();
    }

    public UserResponse getAllUsers() {
        List<User> users = userResponsitory.findAll();

        if (users != null && !users.isEmpty()) {
            List<UserDataDTO> userDataDTOS =  users.stream()
                    .map(user -> new UserDataDTO(user.getId(),user.getLastName(),
                            user.getFirstName(),user.getEmail(),user.getAddress(),user.getPhone(),user.getGroup()))
                    .collect(Collectors.toList());
            return UserResponse.builder()
                    .EC(0)
                    .EM("get data success")
                    .DT("")
                    .data(userDataDTOS)
                    .build();
        } else {
            return UserResponse.builder()
                    .EC(1)
                    .EM("no data found or data error")
                    .DT("")
                    .data(Collections.emptyList())
                    .build();
        }


    }

    public AuthenticationResponse addNewUser(RegisterDTO request){
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
                Optional<Group> groupOptional = groupResponsitory.findById(Long.parseLong(request.getGroup()));
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

    public UserResponse deleteUser(Long id) {
        Optional<User> optionalUser = userResponsitory.findById(id);
        if(optionalUser.isPresent()){
            userResponsitory.delete(optionalUser.get());
            return UserResponse.builder()
                    .EC(0)
                    .EM("delete user is success")
                    .build();
        }else{
            return UserResponse.builder()
                    .EC(1)
                    .EM("not found user")
                    .build();
        }
    }

    public UserResponse updateUsers(UpdateUserDTO userDataDTO) {
        Optional<User> optionalUser = userResponsitory.findById(userDataDTO.getId());
        if(optionalUser.isPresent()){
            Optional<Group> groupOptional = groupResponsitory.findById(Long.parseLong(userDataDTO.getGroup()));

            optionalUser.get().setFirstName(userDataDTO.getFirstName());
            optionalUser.get().setLastName(userDataDTO.getLastName());
            optionalUser.get().setAddress(userDataDTO.getAddress());
            optionalUser.get().setGroup(groupOptional.get());

            userResponsitory.save(optionalUser.get());
            return UserResponse.builder()
                    .EC(0)
                    .EM("update user is success")
                    .build();
        }else{
            return UserResponse.builder()
                    .EC(1)
                    .EM("not found user")
                    .build();
        }
    }



//    public UserResponse getAllUsers() throws JsonProcessingException {
//        List<User> users = userResponsitory.findAll();
//        System.out.println(users);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonData = objectMapper.writeValueAsString(users);
//        return UserResponse
//                .builder()
//                .EC(1)
//                .EM("no data found or data error")
//                .DT("")
//                .data()
//                .build();
//    }

//    public List<User> getAllUsers(){
//        return userResponsitory.findAll();
//    }



}
