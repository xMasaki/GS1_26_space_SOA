package br.com.fiap.space.application.core.domain.model;

import br.com.fiap.space.application.core.domain.enums.Role;
import br.com.fiap.space.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(DadosCadastroUsuario dados, String senhaCodificada) {
        this.login = dados.login();
        this.senha = senhaCodificada;
        this.role = dados.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_CIENTISTA"),
                    new SimpleGrantedAuthority("ROLE_OPERADOR")
            );
            case CIENTISTA -> List.of(
                    new SimpleGrantedAuthority("ROLE_CIENTISTA"),
                    new SimpleGrantedAuthority("ROLE_OPERADOR")
            );
            case OPERADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_OPERADOR")
            );
        };
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

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
}
