package br.ufrn.imd.app.model;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity that representes a Service, a project unit that contains several devices and belongs to a
 * User.
 */
@Entity
@Table(name = "services")
public class Service implements Serializable, Validatable {

  @GeneratedValue
  @Id
  @Column(name = "id_service")
  private Integer id;

  /** Name of the Service. */
  @Column(name = "name", unique = true)
  private String name;

  /** Path of the service. */
  @Column(name = "path", unique = true)
  private String path;

  /** API for authentication. */
  @Column(name = "api")
  private String api;

  /** Owner of the service */
  @Column(name = "owner")
  private String owner = "admin";

  @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
  private List<Device> devices;

  /**
   * Constructor for new Services, missing id.
   *
   * @param name the name of the service
   * @param path the path of the service
   * @param api the api of service
   */
  public Service(String name, String path, String api) {
    this.name = name;
    this.path = path;
    this.api = api;
  }

  /**
   * Constructor for new Services with generated API, missing id.
   *
   * @param name the name of the service
   * @param path the path of the service
   */
  public Service(String name, String path) {
    this.name = name;
    this.path = path;
    this.api = UUID.randomUUID().toString();
  }

  /** @deprecated */
  @Deprecated
  public Service() {}

  /**
   * Constructor for setting id for retrieving or exclusion.
   *
   * @param serviceId the id of the service
   * @throws NullPointerException if the serviceId is null
   */
  public Service(Integer serviceId) {
    if (serviceId == null) {
      throw new NullPointerException("Service: id can't be set to null.");
    } else if (serviceId == 0) {
      throw new IllegalArgumentException("Service: id can't be set to zero.");
    }
    this.id = serviceId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getApi() {
    return api;
  }

  public void setApi(String theApi) {
    this.api = theApi;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Service{"
        + "name='"
        + name
        + '\''
        + ", path='"
        + path
        + '\''
        + ", api='"
        + api
        + ", owner='"
        + owner
        + '\''
        + '}';
  }

  @Override
  public void validate() throws BusinessException {
    StringBuilder builder = new StringBuilder();
    if (name == null || name.isEmpty()) {
      builder.append("Name: required field.").append(System.lineSeparator());
    }

    if (path == null || path.isEmpty()) {
      builder.append("Path: required field.").append(System.lineSeparator());
    }

    String errors = builder.toString();

    if (!errors.isEmpty()) {
      throw new BusinessException(errors);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Service service = (Service) o;

    if (!name.equals(service.name)) {
      return false;
    }
    if (!path.equals(service.path)) {
      return false;
    }
    return api.equals(service.api);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + path.hashCode();
    result = 31 * result + api.hashCode();
    return result;
  }

  public List<Device> getDevices() {
    return devices;
  }

  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }
}
