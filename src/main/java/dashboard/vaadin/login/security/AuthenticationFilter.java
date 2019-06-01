package dashboard.vaadin.login.security;

import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public final class AuthenticationFilter {

  private AuthenticationFilter() {
  }

  static boolean isInternalRequest(HttpServletRequest request) {
    final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
    return parameterValue != null
          && Stream.of(ServletHelper.RequestType.values())
          .anyMatch(r -> r.getIdentifier().equals(parameterValue));
  }
}
