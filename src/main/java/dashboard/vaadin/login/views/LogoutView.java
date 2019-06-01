package dashboard.vaadin.login.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@Tag("sa-logout-view")
@Route(value = LogoutView.ROUTE)
@PageTitle("logout")
public class LogoutView extends FlexLayout {
  static final String ROUTE = "logout";
  private Dialog dialog = new Dialog();

  /**
   * The logout view that confirms with the user that hey want to logout.
   */
  public LogoutView() {
    AppLayout appLayout = AppMenu.app();
    add(appLayout);
    dialog.add(new Label("Are you sure you want to logout?"));
    dialog.add(new Html("<br/>"));

    Button logoutButton =
          new Button(
                "Yes",
                event -> {
                  SecurityContextHolder.clearContext();
                  getUI().get().navigate(LoginView.class);
                });

    Button cancelButton =
          new Button(
                "Cancel",
                event -> {
                  dialog.close();
                  getUI().get().navigate("");
                });

    dialog.add(logoutButton, cancelButton);
    dialog.open();
    add(dialog);
  }
}
