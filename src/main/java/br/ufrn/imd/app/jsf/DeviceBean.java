package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Device;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.DeviceService;
import br.ufrn.imd.app.service.DeviceServiceI;
import br.ufrn.imd.app.service.ServiceI;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class DeviceBean extends AbstractBean {

  private static final String DEVICE_DASHBOARD_PAGE = "/device/index";
  private static final String DEVICE_FORM_PAGE = "/device/form";

  @EJB(beanName = "DeviceService")
  private DeviceServiceI deviceService;

  @EJB(beanName = "ServiceService")
  private ServiceI<Service> serviceService;

  private String deviceName;
  private String entityName;
  private String endpointAddress;
  private Integer endpointPort;
  private String jsonDescriptor;

  private Service selectedService;

  private Integer serviceId;
  private List<Device> filteredDevices;
  private List<Device> allDevices;

  public static String indexPage() {
    return DEVICE_DASHBOARD_PAGE;
  }

  public static String createPage() {
    return DEVICE_FORM_PAGE;
  }

  public List<Device> getFilteredDevices() {
    return filteredDevices;
  }

  @PostConstruct
  public void init() {
    deviceName = entityName = endpointAddress = jsonDescriptor = null;
    endpointPort = null;
    selectedService = null;

    resetFilter();
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

      return DEVICE_DASHBOARD_PAGE;
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

      showSuccessMessage("Saved with success.\n" + newDevice);
    } catch (BusinessException e) {
      showErrorMessage(e.getMessage());
    }

    filterDevices();
    return DEVICE_DASHBOARD_PAGE;
  }

  /**
   * Deletes a device.
   *
   * @return redirection after operation finishes
   */
  public String delete(Integer deviceId) {
    try {
      deviceService.delete(new Device(deviceId));

      showSuccessMessage("Device deleted successfully.");
    } catch (BusinessException e) {
      showErrorMessage(e.getMessage());
    }

    return DEVICE_DASHBOARD_PAGE + "?faces-redirect=true";
  }

  /**
   * Filter devices by service.
   *
   * @return redirection page
   */
  public String filterDevices() {
    try {
      selectedService = serviceService.findById(serviceId);
      showSuccessMessage("Service selected with success.");

      filteredDevices = deviceService.findAllByService(selectedService);
      allDevices = Collections.emptyList();
    } catch (BusinessException e) {
      showErrorMessage(e.getMessage());
    }
    return DEVICE_DASHBOARD_PAGE;
  }

  /**
   * Clears device filter and returns all devices.
   *
   * @return redirection page
   */
  public String resetFilter() {
    selectedService = null;

    filteredDevices = Collections.emptyList();
    allDevices = deviceService.findAll();

    return DEVICE_DASHBOARD_PAGE;
  }

  /**
   * Gets the datasource for dataTable in the view.
   *
   * <p>It may return the filtered devices or all devices list, if either are empty, returns a empty
   * list.
   *
   * @return a list of devices
   */
  public List<Device> getDevicesDatasource() {
    if (allDevices == null || allDevices.isEmpty()) {
      if (filteredDevices == null || filteredDevices.isEmpty()) {
        return Collections.emptyList();
      } else {
        return filteredDevices;
      }
    } else {
      return allDevices;
    }
  }
}
