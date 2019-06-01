package dashboard.vaadin.login.security;

public enum AccountRole {
  ADMIN("ADMIN"),
  EMPLOYEE("EMPLOYEE");

  private String accountRole;

  AccountRole(String accountRole) {
    this.accountRole = accountRole;
  }

  public static String[] getRoles() {
    int rolesLength = AccountRole.values().length;
    String[] roles = new String[rolesLength];
    for (int i = 0; i < rolesLength; i++) {
      roles[i] = AccountRole.values()[i].getAccountRole();
    }
    return roles;
  }

  public String getAccountRole() {
    return this.accountRole;
  }
}
