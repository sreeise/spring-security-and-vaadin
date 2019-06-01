package dashboard.vaadin.login.security;

import dashboard.vaadin.login.db.UserAccountRepository;
import dashboard.vaadin.login.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPasswordService implements UserDetailsPasswordService {
  private UserAccountRepository accountRepository;

  @Autowired
  public UserPasswordService(UserAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    Optional<UserAccount> userAccount = accountRepository.findByEmail(user.getUsername());
    if (userAccount.isPresent()) {
      UserAccount account = userAccount.get();
      account.setPassword(newPassword);
      return account;
    }

    return null;
  }
}
