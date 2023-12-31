package fit.iuh.edu.vn.jwtbespring.setupfilter;

import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.modules.User;
import fit.iuh.edu.vn.jwtbespring.responsitory.UserResponsitory;
import fit.iuh.edu.vn.jwtbespring.services.JwtService;
import fit.iuh.edu.vn.jwtbespring.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LogFilter implements Filter {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserResponsitory userResponsitory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Lấy URI từ request
        String requestURI = httpRequest.getRequestURI();

        // Lấy context path từ request
        String contextPath = httpRequest.getContextPath();

        // Lấy path tương đối (loại bỏ context path)
        String relativePath = requestURI.substring(contextPath.length());

        // Sử dụng path tương đối ở đây

        if (relativePath.equals("/login") || relativePath.equals("/register")
                || relativePath.equals("/api/user/account")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = userService.getcookie(httpRequest, "jwt");
            String email = jwtService.extractUsername(token);
//            if(email.equals("admin@gmail.com")){
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
            Optional<User> user = userResponsitory.findUserByEmail(email);
            if (user.isPresent()) {
                List<String> url = new ArrayList<>();
                for (GroupRole role : user.get().getGroup().getRoles()
                ) {
                    url.add(role.getRole().getUrl());
                }
                boolean isauthorization = url.contains(relativePath);
                if (isauthorization == true) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
