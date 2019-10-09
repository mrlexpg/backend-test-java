package br.com.fcamara.asnpark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.fcamara.asnpark.domain.EstadoUsuario;
import br.com.fcamara.asnpark.domain.Role;
import br.com.fcamara.asnpark.domain.User;
import br.com.fcamara.asnpark.repository.UserRepository;

@SpringBootApplication
public class AsnParkApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AsnParkApplication.class, args);
	}
		
	// init bean into h2 database.
	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {  			
        	repository.save(new User()
			        			.comLogin("fcadm")
			        			.ePassword("$2a$04$m1iVE4CPJ8yG05ojcKVGOOj13YSYXiyLG0yqSjejx433E01jVA94q")
			        			.comPerfil(new Role().nome("ADMIN"))
			        			.comStatus(EstadoUsuario.Ativo));
        	
        	repository.save(new User()
        			.comLogin("fcuser")
        			.ePassword("$2a$04$m1iVE4CPJ8yG05ojcKVGOOj13YSYXiyLG0yqSjejx433E01jVA94q")
        			.comPerfil(new Role().nome("USER"))
        			.comStatus(EstadoUsuario.Ativo));
        };
	}
}
