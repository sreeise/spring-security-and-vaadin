package dashboard.vaadin.login.security;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import dashboard.vaadin.login.db.UserAccountRepository;
import dashboard.vaadin.login.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.util.Optional;

@EnableWebSecurity
@Configuration
@EnableVaadin
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityControl extends WebSecurityConfigurerAdapter {
  private static final String LOGIN_PROCESSING_URL = "/login";
  private static final String LOGIN_FAILURE_URL = "/login";
  private static final String LOGIN_URL = "/login";
  private static final String LOGOUT_SUCCESS_URL = "/login";

  private final AccountDetails userDetailsService;
  private UserAccountRepository accountRepository;

  @Autowired
  public WebSecurityControl(
        AccountDetails userDetailsService, UserAccountRepository accountRepository) {
    this.userDetailsService = userDetailsService;
    this.accountRepository = accountRepository;
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public CurrentUser currentUser() {
    final Optional<String> optionalEmail = SecurityAccess.getEmail();
    if (optionalEmail.isPresent()) {
      Optional<UserAccount> account = accountRepository.findByEmail(optionalEmail.get());
      if (account.isPresent()) {
        return account::get;
      }
    }
    return null;
  }

  /**
   * Registers our UserDetailsService and the password encoder to be used on login attempts.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    UserPasswordService service = new UserPasswordService(this.accountRepository);
    auth.userDetailsService(userDetailsService)
          .passwordEncoder(BCryptEncoder.encoder())
          .userDetailsPasswordManager(service)
          .and()
          .getOrBuild();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf()
          .disable()
          .requestCache()
          .requestCache(new HttpCache())
          .and()
          .authorizeRequests()
          .requestMatchers(AuthenticationFilter::isInternalRequest)
          .permitAll()
          .anyRequest()
          .hasAnyAuthority(AccountRole.getRoles())
          .and()
          .formLogin()
          .loginPage(LOGIN_URL)
          .permitAll()
          .loginProcessingUrl(LOGIN_PROCESSING_URL)
          .failureUrl(LOGIN_FAILURE_URL)
          .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
          .and()
          .logout()
          .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
          .and()
          .rememberMe()
          .alwaysRemember(true);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
          .antMatchers(
                "/favicon.ico",
                "/VAADIN/**",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",
                "/icons/**",
                "/images/**",
                "/frontend/**",
                "/webjars/**",
                "/h2-console/**",
                "/frontend-es5/**",
                "/frontend-es6/**",
                "/api/**",
                "/error/**");
  }
}
