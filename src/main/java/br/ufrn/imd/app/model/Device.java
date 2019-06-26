package br.ufrn.imd.app.model;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Class that representes a IoT device (thing) */
@Entity
@Table(name = "devices")
public class Device implements Validatable {

  @Id @GeneratedValue private Integer id;

  @Column(name = "device_name")
  private String deviceName;

  @Column(name = "entity_name")
  private String entityName;

  @Column(name = "endpoint_address")
  private String endpointAddress;

  @Column(name = "endpoint_port")
  private Integer endpointPort;

  @Column(name = "json_descriptor")
  private String jsonDescriptor;

  @JoinColumn(name = "id_service")
  @ManyToOne(cascade = CascadeType.ALL)
  private Service service;

  /** @deprecated */
  @Deprecated
  public Device() {}

  public Device(Integer id) {
    this.id = id;
  }

  public Device(
      String deviceName,
      String entityName,
      String endpointAddress,
      Integer endpointPort,
      String jsonDescriptor) {
    this.deviceName = deviceName;
    this.entityName = entityName;
    this.endpointAddress = endpointAddress;
    this.endpointPort = endpointPort;
    this.jsonDescriptor = jsonDescriptor;
  }

  @Override
  public void validate() throws BusinessException {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public String getEndpointAddress() {
    return endpointAddress;
  }

  public void setEndpointAddress(String endpointAddress) {
    this.endpointAddress = endpointAddress;
  }

  public Integer getEndpointPort() {
    return endpointPort;
  }

  public void setEndpointPort(Integer endpointPort) {
    this.endpointPort = endpointPort;
  }

  public String getJsonDescriptor() {
    return jsonDescriptor;
  }

  public void setJsonDescriptor(String jsonDescriptor) {
    this.jsonDescriptor = jsonDescriptor;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }
}
