package br.com.sis.pedidos.backend.resources;

import br.com.sis.pedidos.backend.dto.EmailDTO;
import br.com.sis.pedidos.backend.security.JWTUtil;
import br.com.sis.pedidos.backend.security.UserSS;
import br.com.sis.pedidos.backend.services.AuthService;
import br.com.sis.pedidos.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthService authService;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDTO objDTO) {
        authService.sendNewPassword(objDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

}
