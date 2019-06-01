package dashboard.vaadin.login.security;

import dashboard.vaadin.login.entity.UserAccount;

@FunctionalInterface
public interface CurrentUser {
  UserAccount getAccount();
}
