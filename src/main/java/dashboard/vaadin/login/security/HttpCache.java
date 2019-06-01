package dashboard.vaadin.login.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 *
 * <p>If the method is considered an internal request from the framework, we skip saving it.
 *
 * @see AuthenticationFilter::isInternalRequest(HttpServletRequest)
 */
public class HttpCache extends HttpSessionRequestCache {
  @Override
  public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
    if (!AuthenticationFilter.isInternalRequest(request)) {
      super.saveRequest(request, response);
    }
  }
}
