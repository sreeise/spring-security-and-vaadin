package dashboard.vaadin.login.db;


import dashboard.vaadin.login.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
  List<UserAccount> findByLastNameStartsWithIgnoreCase(String lastName);

  Optional<UserAccount> findByEmail(String email);

  Optional<UserAccount> findByEmailIgnoreCase(String email);
}
