package dashboard.vaadin.login.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;

@Route("profile")
public class ProfileView extends FlexLayout {
  public ProfileView() {
    AppLayout appLayout = AppMenu.app();
    add(appLayout);
  }
}
