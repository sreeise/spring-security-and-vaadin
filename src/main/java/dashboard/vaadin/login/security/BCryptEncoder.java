package dashboard.vaadin.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoder {
  public static String encode(String value) {
    return encoder().encode(value);
  }

  /**
   * The password encoder to use when encrypting passwords.
   */
  @Bean
  public static PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(16);
  }
}
