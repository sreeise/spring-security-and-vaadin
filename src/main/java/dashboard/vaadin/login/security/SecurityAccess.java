package dashboard.vaadin.login.security;

import dashboard.vaadin.login.entity.UserAccount;
import dashboard.vaadin.login.views.GenericErrorView;
import dashboard.vaadin.login.views.LoginView;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecurityAccess {
  private SecurityAccess() {
  }

  public static Optional<String> getEmail() {
    Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    Optional<UserAccount> user = Optional.ofNullable((UserAccount) userAuthentication.getPrincipal());
    return user.map(userAccount -> Optional.ofNullable(userAccount.getEmail())).orElse(null);
  }

  /**
   * Checks if access is granted for the current user for the given secured view, defined by the
   * view class.
   *
   * @param securedClass View class
   * @return true if access is granted, false otherwise.
   */
  public static boolean isAccessGranted(Class<?> securedClass) {
    final boolean publicView =
          LoginView.class.equals(securedClass) || GenericErrorView.class.equals(securedClass);

    // Always allow access to public views
    if (publicView) {
      return true;
    }
    Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

    // All other views require authentication
    if (!isUserLoggedIn(userAuthentication)) {
      return false;
    }

    // Allow if no roles are required.
    Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
    if (secured == null) {
      return true;
    }

    List<String> allowedRoles = Arrays.asList(secured.value());
    return userAuthentication.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .anyMatch(allowedRoles::contains);
  }

  /**
   * Checks if the user is logged in.
   *
   * @return true if the user is logged in. False otherwise.
   */
  public static boolean isUserLoggedIn() {
    return isUserLoggedIn(SecurityContextHolder.getContext().getAuthentication());
  }

  private static boolean isUserLoggedIn(Authentication authentication) {
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
  }
}
