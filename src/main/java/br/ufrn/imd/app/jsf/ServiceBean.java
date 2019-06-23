package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceI;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceBean {

  private static final String SERVICE_FORM_PAGE = "service/form";

  @EJB private ServiceI<Service> service;

  @Inject private MessageBean message;

  private String serviceName;
  private String servicePath;
  private String serviceApi;

  private String serviceId;

  public String createPage() {
    return SERVICE_FORM_PAGE;
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

      message.setSuccess("Salvou com sucesso.\n" + newService);
      return "/home";
    } catch (BusinessException e) {
      message.setError(e.getMessage());
      return SERVICE_FORM_PAGE;
    }
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

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public List<Service> getAllServices() {
    return service.findAll();
  }

  /**
   * Selects a service and keeps it in session for device manipulation.
   *
   * @return redirect to devices page
   */
  public String selectService() {
    // TODO find Service by id and save in session
  }

    // TODO redirect to device page

    return HomeBean.HOME_PAGE;
  }

  /**
   * Forwards to another page
   *
   * @param uri the uri from resource
   */
  void forward(String uri) {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(uri);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Redirects to another page or resource
   *
   * @param uri the uri from the page or resource
   */
  void redirect(String uri) {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(uri);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
