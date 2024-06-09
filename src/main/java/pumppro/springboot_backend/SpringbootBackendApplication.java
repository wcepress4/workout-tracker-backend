package pumppro.springboot_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.model.User;

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
//
//		User user1 = new User();
//		user1.setUsername("joeschmo");
//		user1.setEmail("joeschmo@gmail.com");
//		user1.setFirstName("Joe");
//		user1.setLastName("Schmo");
//		user1.setPassword("monkey");
//		user1.setVerified(true);
//		userRepository.save(user1);
	} //run
}
