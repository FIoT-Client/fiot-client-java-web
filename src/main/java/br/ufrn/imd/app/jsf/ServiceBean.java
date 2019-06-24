package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceI;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceBean extends AbstractBean {

  private static final String SERVICE_DASHBOARD_PAGE = "/service/index";
  private static final String SERVICE_FORM_PAGE = "/service/form";

  @EJB private ServiceI<Service> service;

  @Inject private MessageBean message;

  private String serviceName;
  private String servicePath;
  private String serviceApi;

  private Integer serviceId;
  private List<Service> services;

  /** Initialize the session bean. */
  @PostConstruct
  public void init() {}

  public String indexPage() {
    return forward(SERVICE_DASHBOARD_PAGE);
  }

  public String createPage() {
    return forward(SERVICE_FORM_PAGE);
  }

  /**
   * Saves the Service unit into the database.
   *
   * @return a redirection page after operation finishes.
   */
  public String create() {
    try {

      Service newService =
          serviceApi == null || serviceApi.isEmpty()
              ? new Service(serviceName, servicePath)
              : new Service(serviceName, servicePath, serviceApi);

      newService = service.save(newService);

      clearForm();

      message.setSuccess("Salvou com sucesso.\n" + newService);
      return redirect(SERVICE_DASHBOARD_PAGE);
    } catch (BusinessException e) {
      message.setError(e.getMessage());
      return forward(SERVICE_FORM_PAGE);
    }
  }

  private void clearForm() {
    serviceName = servicePath = serviceApi = "";
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServicePath() {
    return servicePath;
  }

  public void setServicePath(String servicePath) {
    this.servicePath = servicePath;
  }

  public String getServiceApi() {
    return serviceApi;
  }

  public void setServiceApi(String serviceApi) {
    this.serviceApi = serviceApi;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public List<Service> getAllServices() {
    if (services == null) {
      services = service.findAll();
    }
    return services;
  }

  /**
   * Selects a service and keeps it in session for device manipulation.
   *
   * @return redirect to devices page
   */
  public String selectService() {
    // TODO save in session
    try {
      Service found = service.findById(serviceId);
      System.err.println("Achou: " + found);
    } catch (BusinessException e) {
      message.setError(e.getMessage());
    }

    return redirect(HomeBean.HOME_PAGE);
  }

  /**
   * Delete a service from database.
   *
   * @param serviceId the id of the service to be deleted
   * @return the redirection page
   */
  public String delete(Integer serviceId) {

    try {
      service.delete(new Service(serviceId));

      message.setSuccess("Service deleted successfully.");
    } catch (BusinessException e) {
      message.setError(e.getMessage());
    }

    return forward(SERVICE_DASHBOARD_PAGE);
  }
}
