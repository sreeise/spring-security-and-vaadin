package dashboard.vaadin.login;

import dashboard.vaadin.login.db.UserAccountRepository;
import dashboard.vaadin.login.entity.AccountStatus;
import dashboard.vaadin.login.entity.UserAccount;
import dashboard.vaadin.login.security.AccountRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	// Creates a default administrator if no previous users have been created.
	// This should only be used in development.
	@Bean
	public CommandLineRunner demo (UserAccountRepository repository) {
		return (args) -> {
			List<UserAccount> list = repository.findAll();
			if (list.size() < 1) {
				createAdmin(repository);
			} else {
				Optional<UserAccount> accountOptional = repository.findByEmail("admin@dashboard.com");
				if (!accountOptional.isPresent()) {
					createAdmin(repository);
				} else {
					UserAccount account = accountOptional.get();
					if (!account.getEmail().equals("admin@dashboard.com")) {
						account.setEmail("admin@dashboard.com");
						account.setPassword("admin");
					}
				}
			}
		};
	}

	private void createAdmin(UserAccountRepository repository) {
		UserAccount userAccount = new UserAccount();
		userAccount.setAccountStatus(AccountStatus.ACTIVE);
		userAccount.setPassword("admin");
		userAccount.setEmail("admin@dashboard.com");
		userAccount.setZipCode("232093");
		userAccount.setState("CA");
		userAccount.setAddress("15 Amphitheatre Parkway, Mountain View");
		userAccount.setAccountRole(AccountRole.ADMIN.name());
		userAccount.setFirstName("Admin");
		userAccount.setLastName("Admin");
		userAccount.setApiKey("API-KEY-ADMIN");
		repository.save(userAccount);
	}
}
