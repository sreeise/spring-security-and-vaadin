package dashboard.vaadin.login.entity;

import dashboard.vaadin.login.security.AccountRole;
import dashboard.vaadin.login.security.BCryptEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Account extends AbstractEntity {
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private String accountStatus;

  public Account() {}

  public Account(String email, String password, AccountRole role, AccountStatus status) {
    super();
    this.setEmail(email);
    this.setRole(role);
    this.setPassword(password);
    this.setAccountStatus(status);
  }

  public String getAccountStatus() {
    return this.accountStatus;
  }

  public void setAccountStatus(AccountStatus status) {
    this.accountStatus = status.name();
  }

  public void setAccountStatusValueOf(String status) {
    this.accountStatus = AccountStatus.valueOf(status).name();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    StringBuilder builder = new StringBuilder();
    builder.append("{bcrypt}").append(BCryptEncoder.encode(password));
    this.password = builder.toString();
  }

  public String getRole() {
    return role;
  }

  public void setRole(AccountRole role) {
    this.role = role.name();
  }

  @Override
  public Account clone() throws CloneNotSupportedException {
    return (Account) super.clone();
  }

  @Override
  public String toString() {
    return String.format(
          "Account[role=%s, email='%s', account_status='%s']",
          this.getRole(), this.getEmail(), this.getAccountStatus());
  }
}
