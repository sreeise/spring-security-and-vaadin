package dashboard.vaadin.login.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Dashboard")
public class MainView extends VerticalLayout {
  public MainView() {
    AppLayout appLayout = AppMenu.app();
    add(appLayout);
  }
}
