package dashboard.vaadin.login.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import dashboard.vaadin.login.security.SecurityAccess;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Route("profile")
public class ProfileView extends FlexLayout {
  public ProfileView() {
    AppLayout appLayout = AppMenu.app();
    Optional<String> email = SecurityAccess.getEmail();
    if (!email.isPresent()) {
      SecurityContextHolder.clearContext();
      getUI().get().navigate(LoginView.class);
    }

    add(appLayout);
  }
}
