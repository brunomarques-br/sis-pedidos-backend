package br.com.sis.pedidos.backend.security;

import br.com.sis.pedidos.backend.domain.Perfil;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities; //perfis

    public UserSS() {}

    public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // retorna um booleano se o usuário tem o perfil informado no parâmetro enviado
    public boolean hasRole(Perfil perfil){
        //convertendo o perfil para o granted authority do spring security
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }
}
