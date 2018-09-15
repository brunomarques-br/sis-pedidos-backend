package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static UserSS authenticated(){
        try {
            //função do Spring Security que retorna o usuário logado.
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null; //caso o método seja chamado sem nenhum usuário autenticado no sistema.
        }
    }
}
