package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.annotation.DeviceQualifier;
import br.ufrn.imd.app.annotation.ServiceQualifier;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Device;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceI;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class DeviceBean extends AbstractBean {

  private static final String DEVICE_DASHBOARD_PAGE = "/device/index";
  private static final String DEVICE_FORM_PAGE = "/device/form";

  @EJB(beanName = "DeviceService")
  private ServiceI<Device> deviceService;

  @EJB(beanName = "ServiceService")
  private ServiceI<Service> serviceService;

  private String deviceName;
  private String entityName;
  private String endpointAddress;
  private Integer endpointPort;
  private String jsonDescriptor;

  @Inject private MessageBean message;

  private Service selectedService;

  private Integer serviceId;

  public static String createPage() {
    return redirect(DEVICE_FORM_PAGE);
  }

  @PostConstruct
  public void init() {
    deviceName = entityName = endpointAddress = jsonDescriptor = null;
    endpointPort = null;
  }

  public Service getSelectedService() {
    return selectedService;
  }

  public void setSelectedService(Service selectedService) {
    this.selectedService = selectedService;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
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

  /**
   * Selects a service and keeps it in session for device manipulation.
   *
   * @return redirect to devices page
   */
  public String selectService() {
    try {
      init();
      selectedService = serviceService.findById(serviceId);
      showSuccessMessage("Service selected with success.");

      return DEVICE_FORM_PAGE;
    } catch (BusinessException e) {
      showErrorMessage(e.getMessage());
      return HomeBean.HOME_PAGE;
    }
  }

  /**
   * Saves the Device unit into the database.
   *
   * @return a redirection page after operation finishes
   */
  public String create() {
    Service service = new Service(serviceId);

    Device newDevice =
        new Device(deviceName, entityName, endpointAddress, endpointPort, jsonDescriptor);
    newDevice.setService(service);

    try {
      newDevice = deviceService.save(newDevice);

      showSuccessMessage("Saved with succeess.\n" + newDevice);
    } catch (BusinessException e) {
      showErrorMessage(e.getMessage());
    }

    return DEVICE_DASHBOARD_PAGE;
  }
}
