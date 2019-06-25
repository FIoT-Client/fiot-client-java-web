package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.ServiceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.validator.Validatable;
import br.ufrn.imd.fiotclient.iot.FiwareIotClient;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServiceService implements ServiceI<Service> {

  @PersistenceContext(unitName = "fiot") // , type = PersistenceContextType.EXTENDED)
  EntityManager entityManager;

  private FiwareIotClient fiotIot;
  private DaoI<Service> dao;

  @PostConstruct
  public void init() {
    this.dao = new ServiceDao(entityManager);
    try {
      this.fiotIot = new FiwareIotClient(getConfigLocation());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getConfigLocation() {
    String configFile =
        this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

    configFile += '/' + "config.ini";
    return configFile;
  }

  @Override
  public Service save(Service entity) throws BusinessException {
    validate(entity);

    try {
      return dao.save(entity);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @Override
  public List<Service> findAll() {
    return dao.findAll();
  }

  @Override
  public Service findById(Object id) throws BusinessException {
    if (id != null && !(id instanceof Integer)) {
      throw new BusinessException("Id: invalid field.");
    }

    return dao.findById(id)
        .orElseThrow(
            () -> new BusinessException("Can't find Service with id (" + id + ")"));
  }

  @Override
  public void delete(Service entity) throws BusinessException {

    dao.delete(entity);
  }

  private void validate(Validatable entity) throws BusinessException {
    entity.validate();
  }
}
