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

  @Override
  public void validate() throws BusinessException {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
