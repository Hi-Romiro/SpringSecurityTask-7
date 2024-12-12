package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
        Object userPrincipal = auth.getPrincipal();
        Set<String> authorities = AuthorityUtils.authorityListToSet(auth.getAuthorities());
        Integer id = null;

        if (userPrincipal instanceof User) {
            id = ((User) userPrincipal).getId();
        }

        if (authorities.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else {
            if (authorities.contains("ROLE_USER")) {
                if (id != null) {
                    response.sendRedirect("/user/" + id);
                } else {
                    System.err.println("Error: Missing user ID");
                    response.sendRedirect("/login?error=missing_user_id");
                }
            } else {
                System.err.println("Error: No matching role found for user");
                response.sendRedirect("/login");
            }
        }
    }
}