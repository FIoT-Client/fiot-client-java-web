package br.ufrn.imd.app.jsf;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.service.ServiceI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceBean {

    private static final String SERVICE_FORM_PAGE = "service/form";

    @Inject
    private ServiceI<Service> sService;

    @Inject
    private MessageBean message;

    private String serviceName;
    private String servicePath;
    private String serviceAPI;

    private String exemplo = "0";


    public String createPage() {
        return SERVICE_FORM_PAGE;
    }

    public String create() {
        try {
            Service service = new Service(serviceName, servicePath, serviceAPI);
            sService.save(service);
        } catch (BusinessException e) {
            message.setError(e.getMessage());
            return SERVICE_FORM_PAGE;
        }

        //TODO: change for Service listing
        return "home";
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

    public String getServiceAPI() {
        return serviceAPI;
    }

    public void setServiceAPI(String serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }
}
