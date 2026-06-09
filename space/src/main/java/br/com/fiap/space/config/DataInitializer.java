package br.com.fiap.space.config;

import br.com.fiap.space.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.fiap.space.application.core.domain.enums.Role;
import br.com.fiap.space.application.core.domain.model.Usuario;
import br.com.fiap.space.application.port.out.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedAdmin() {
        return args -> {
            if (usuarioRepository.findByLogin("admin").isEmpty()) {
                DadosCadastroUsuario dados = new DadosCadastroUsuario("admin", "admin123", Role.ADMIN);
                Usuario admin = new Usuario(dados, passwordEncoder.encode("admin123"));
                usuarioRepository.save(admin);
                System.out.println(">>> Usuário admin criado — login: admin | senha: admin123");
            }
        };
    }
}
