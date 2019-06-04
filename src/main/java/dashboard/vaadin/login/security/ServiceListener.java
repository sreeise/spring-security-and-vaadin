package dashboard.vaadin.login.security;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import dashboard.vaadin.login.views.LoginView;
import dashboard.vaadin.login.views.MainView;

@SpringComponent
public class ServiceListener implements VaadinServiceInitListener {
  @Override
  public void serviceInit(ServiceInitEvent event) {
    event
          .getSource()
          .addUIInitListener(
                uiEvent -> {
                  final UI ui = uiEvent.getUI();
                  ui.addBeforeEnterListener(this::beforeEnter);
                });
  }

  /**
   * Reroutes the user if they are not authorized to access the view.
   *
   * @param event before navigation event with event details
   */
  private void beforeEnter(BeforeEnterEvent event) {
    final boolean accessGranted = SecurityAccess.isAccessGranted(event.getNavigationTarget());
    if (!accessGranted) {
      if (SecurityAccess.isUserLoggedIn()) {
        event.rerouteTo(MainView.class);
      } else {
        event.rerouteTo(LoginView.class);
      }
    }
  }
}
