package dashboard.vaadin.login.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@Data
@MappedSuperclass
public abstract class AbstractEntity {
  @Id
  @GeneratedValue
  private Long id;

  @Version
  private int version;
}
