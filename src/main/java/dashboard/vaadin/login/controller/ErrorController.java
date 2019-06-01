package dashboard.vaadin.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
  private static final String ERROR_PATH = "/error";
  private static final String ERROR_REDIRECT = "/server-error";

  @RequestMapping(ERROR_PATH)
  public void error(HttpServletResponse response) throws IOException {
    response.sendRedirect(ERROR_REDIRECT);
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}
