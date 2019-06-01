package dashboard.vaadin.login.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import dashboard.vaadin.login.security.SecurityAccess;

import java.util.Optional;

@SpringComponent
@UIScope
public class AppMenu {
  public static AppLayout app() {
    AppLayout appLayout = new AppLayout();
    AppLayoutMenu menu = appLayout.createMenu();

    menu.addMenuItems(
          new AppLayoutMenuItem(VaadinIcon.HOME.create(), "Dashboard", ""),
          new AppLayoutMenuItem(VaadinIcon.USER.create(), "Profile", "profile"),
          new AppLayoutMenuItem(VaadinIcon.CHEVRON_RIGHT.create(), "Logout", "logout"));

    Optional<String> userEmail = SecurityAccess.getEmailFromProperty();
    userEmail.ifPresent(s -> menu.addMenuItem(new Label(s)));

    return appLayout;
  }
}
