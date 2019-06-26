package br.ufrn.imd.app.model;

public class ContextConfiguration {

  private Integer id;

  private String orionHost;
  private Integer orionPort;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOrionHost() {
    return this.orionHost;
  }

  public void setOrionHost(String orionHost) {
    this.orionHost = orionHost;
  }

  public Integer getOrionPort() {
    return orionPort;
  }

  public void setOrionPort(Integer port) {
    this.orionPort = port;
  }

  String getOrionFullAddress() {
    return String.format("%s:%s", orionHost, orionPort);
  }
}
