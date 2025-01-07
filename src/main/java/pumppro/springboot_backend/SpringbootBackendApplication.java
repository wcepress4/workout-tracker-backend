package pumppro.springboot_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pumppro.springboot_backend.repository.UserRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) { SpringApplication.run(SpringbootBackendApplication.class, args); } //main

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("wcepress");
//		user.setEmail("wcepress@gmail.com");
//		user.setFirstName("Will");
//		user.setLastName("Cepress");
//		user.setPassword("pineapple");
//		user.setVerified(false);
//		userRepository.save(user);
	} //run
}
