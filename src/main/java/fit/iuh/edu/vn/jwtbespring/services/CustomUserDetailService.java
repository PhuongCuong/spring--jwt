package fit.iuh.edu.vn.jwtbespring.services;

import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserResponsitory userResponsitory;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userResponsitory.findUserByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("not found user"));
        return user;
    }
}
