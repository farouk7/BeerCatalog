package io.haufe.beercatalogueservice;


import io.haufe.beercatalogueservice.models.Role;
import io.haufe.beercatalogueservice.models.Users;
import io.haufe.beercatalogueservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeerCatalogueServiceApplication implements CommandLineRunner {
	@Autowired
	private IService<Users> userService;
	@Autowired
	private IService<Role> roleService;
	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogueServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*roleService.saveOrUpdate(new Role(1L, "admin"));
		roleService.saveOrUpdate(new Role(2L, "user"));

		Users user1 = new Users();
		user1.setEmail("farouk@user.com");
		user1.setName("Test User");
		user1.setMobile("9787456545");
		user1.setRole(roleService.findById(2L).get());
		user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
		userService.saveOrUpdate(user1);

		Users user2 = new Users();
		user2.setEmail("admin@user.com");
		user2.setName("Test Admin");
		user2.setMobile("9787456545");
		user2.setRole(roleService.findById(1L).get());
		user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
		userService.saveOrUpdate(user2);*/

	}
}
