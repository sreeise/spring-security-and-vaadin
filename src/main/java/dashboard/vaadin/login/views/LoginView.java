package dashboard.vaadin.login.views;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import dashboard.vaadin.login.security.SecurityAccess;

@Route("login")
public class LoginView extends VerticalLayout
      implements AfterNavigationObserver, BeforeEnterObserver {
  private LoginOverlay login = new LoginOverlay();

  public LoginView() {
    LoginI18n i18n = LoginI18n.createDefault();
    i18n.setHeader(new LoginI18n.Header());
    i18n.getHeader().setTitle("Dashboard");
    i18n.getHeader().setDescription("Login");
    i18n.setAdditionalInformation(null);
    i18n.setForm(new LoginI18n.Form());
    i18n.getForm().setSubmit("Sign in");
    i18n.getForm().setTitle("Sign in");
    i18n.getForm().setUsername("Email");
    i18n.getForm().setPassword("Password");
    i18n.getForm().setForgotPassword("Forgot Password");
    login.setI18n(i18n);
    login.setAction("login");
    login.setOpened(true);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    if (SecurityAccess.isUserLoggedIn()) {
      event.rerouteTo(MainView.class);
    }
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    login.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
  }
}
