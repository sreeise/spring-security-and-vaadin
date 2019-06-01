package dashboard.vaadin.login.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashboard.vaadin.login.security.SecurityAccess;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Route("server-error")
@PageTitle("error")
public class GenericErrorView extends VerticalLayout {
  private Dialog dialog;

  public GenericErrorView() {
    dialog = new Dialog();
    dialog.add(new Label("There was an issue with a request. We apologize for the inconvenience."));

    Button logoutButton =
          new Button(
                "Ok",
                event -> {
                  dialog.close();
                  Optional<UI> optionalUI = getUI();
                  if (!SecurityAccess.isUserLoggedIn()) {
                    System.setProperty("currentUser", "");
                    SecurityContextHolder.clearContext();
                    optionalUI.ifPresent(ui -> ui.navigate("/login"));
                  } else {
                    optionalUI.ifPresent(ui -> ui.navigate(""));
                  }
                });
    dialog.add(logoutButton);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
    add(dialog);
  }
}
