package dashboard.vaadin.login.entity;

import dashboard.vaadin.login.security.AccountRole;
import dashboard.vaadin.login.security.CurrentUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAccount extends Account implements UserDetails, CurrentUser {
  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = true)
  private String address;

  @Column(nullable = true)
  private String city;

  @Column(nullable = true)
  private String state;

  @Column(nullable = true)
  private String zipCode;

  @Column(name = "apiKey")
  private String apiKey;

  public UserAccount(String email, String password, AccountRole role, AccountStatus status) {
    super(email, password, role, status);
  }

  public String getAccountRole() {
    return super.getRole();
  }

  public void setAccountRole(String accountRole) {
    super.setRole(AccountRole.valueOf(accountRole));
  }

  @Override
  public UserAccount clone() throws CloneNotSupportedException {
    return (UserAccount) super.clone();
  }

  @Override
  public String toString() {
    return String.format(
          "UserAccount[firstName=%s, lastName='%s', address='%s', state=%s]",
          firstName, lastName, address, state);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.getRole()));
    return authorities;
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return AccountStatus.valueOf(this.getAccountStatus()) == AccountStatus.ACTIVE;
  }

  @Override
  public boolean isAccountNonLocked() {
    return AccountStatus.valueOf(this.getAccountStatus()) == AccountStatus.ACTIVE;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return AccountStatus.valueOf(this.getAccountStatus()) == AccountStatus.ACTIVE;
  }

  @Override
  public boolean isEnabled() {
    return AccountStatus.valueOf(this.getAccountStatus()) == AccountStatus.ACTIVE;
  }

  @Override
  public UserAccount getAccount() {
    return this;
  }
}
