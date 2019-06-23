package br.ufrn.imd.app.model;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the username in session.
 *
 * <p>Also used for modeling the username entity to the persistence.
 */
@Entity
@Table(name = "users")
public class User implements Validatable {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "salt")
  private String salt;

  public User(String username, String password) {
    this(username);
    this.password = password;
  }

  public User(String username) {
    this.username = username;
  }

  /** Default constructor for new User. Generates a a salt to encrypt password on save. */
  public User() {
    this.salt = UUID.randomUUID().toString();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public void validate() throws BusinessException {
    StringBuilder builder = new StringBuilder();
    if (username == null || username.isEmpty()) {
      builder.append("Username: required field.").append(System.lineSeparator());
    }

    // TODO Remove this when username persistence occurs
    //    if (password == null || password.isEmpty()) {
    //      builder.append("Password: required field.").append(System.lineSeparator());
    //    }

    String errors = builder.toString();

    if (!errors.isEmpty()) {
      throw new BusinessException(errors);
    }
  }
}
