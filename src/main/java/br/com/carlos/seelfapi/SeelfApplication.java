package br.com.carlos.seelfapi;

import br.com.carlos.seelfapi.model.Usuario;
import br.com.carlos.seelfapi.model.enums.PerfilUsuario;
import br.com.carlos.seelfapi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeelfApplication.class, args);
	}

	// --- INJEÇÃO AUTOMÁTICA DE USUÁRIO ---
	@Bean
	public CommandLineRunner demo(UsuarioRepository usuarioRepository) {
		return (args) -> {
			// Verifica se já existe alguém, se não, cria o Admin
			if (usuarioRepository.count() == 0) {
				Usuario admin = new Usuario();
				admin.setNomeCompleto("Carlos Daniel");
				admin.setEmail("admin@seelf.com");
				admin.setFuncao(PerfilUsuario.ADMIN);
				usuarioRepository.save(admin);
				System.out.println("✅ USUÁRIO ADMIN CRIADO COM SUCESSO (ID: " + admin.getId() + ")");
			}
		};
	}
}