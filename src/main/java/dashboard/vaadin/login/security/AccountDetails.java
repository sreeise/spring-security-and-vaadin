package dashboard.vaadin.login.security;

import dashboard.vaadin.login.db.UserAccountRepository;
import dashboard.vaadin.login.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class AccountDetails implements UserDetailsService {
  private final UserAccountRepository repository;

  @Autowired
  public AccountDetails(UserAccountRepository repository) {
    this.repository = repository;
  }

  /**
   * Recovers the {@link UserAccount} from the database using the e-mail address supplied in the
   * login screen. If the user is found, returns a {@link
   * org.springframework.security.core.userdetails.User}.
   *
   * @param email User's e-mail address
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<UserAccount> accountOptional = repository.findByEmailIgnoreCase(email);
    if (!accountOptional.isPresent()) {
      throw new UsernameNotFoundException("No user present with username: " + email);
    } else {
      return accountOptional.get();
    }
  }
}
