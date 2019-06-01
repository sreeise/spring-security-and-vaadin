package dashboard.vaadin.login.security;


import dashboard.vaadin.login.entity.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private RequestCache requestCache = new HttpSessionRequestCache();

  /**
   * Modified version of Spring simple aware authentication success handler so that we
   * can store the current user email in an environment variable.
   *
   * @param request
   * @param response
   * @param authentication
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws ServletException, IOException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    Optional<String> optionalAccountString = Optional.ofNullable(authentication.getPrincipal().toString());
    optionalAccountString.ifPresent(System.out::println);
    Optional<UserAccount> user = Optional.ofNullable((UserAccount) authentication.getPrincipal());
    user.ifPresent(value -> System.setProperty("currentUser", value.getUsername()));

    if (savedRequest == null) {
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    String targetUrlParameter = getTargetUrlParameter();
    if (isAlwaysUseDefaultTargetUrl()
          || (targetUrlParameter != null
          && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
      requestCache.removeRequest(request, response);
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    clearAuthenticationAttributes(request);

    // Use the DefaultSavedRequest URL
    String targetUrl = savedRequest.getRedirectUrl();
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
