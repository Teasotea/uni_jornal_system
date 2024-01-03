package md.sotea.journal_as_a_table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import md.sotea.journal_as_a_table.repositories.UserRepository;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class JournalTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalTableApplication.class, args);

		try {
			ProcessBuilder pb = new ProcessBuilder("xdg-open", "http://localhost:8080");
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}