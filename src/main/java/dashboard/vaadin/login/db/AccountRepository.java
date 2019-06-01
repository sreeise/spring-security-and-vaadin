package dashboard.vaadin.login.db;

import dashboard.vaadin.login.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Optional<Account> findByEmail(String email);

  Optional<Account> findByEmailIgnoreCase(String email);
}
