package dashboard.vaadin.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
      classes = LoginApplication.class,
      webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class TestStartup {
  @Test
  public void test_first_run() {
    // Fails if the Spring application does not start and run correctly.
    // Uses h2 database specified in test.properties to prevent
    // database connection failure.
  }
}
