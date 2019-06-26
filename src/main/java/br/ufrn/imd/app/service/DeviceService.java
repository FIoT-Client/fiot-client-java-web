package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.DeviceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.exception.DaoException;
import br.ufrn.imd.app.model.Device;
import br.ufrn.imd.app.model.Service;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "DeviceService")
public class DeviceService implements DeviceServiceI {

  @PersistenceContext(unitName = "fiot")
  private EntityManager entityManager;

  private DaoI<Device> dao;

  @PostConstruct
  public void init() {
    this.dao = new DeviceDao(entityManager);
  }

  @Override
  public Device save(Device entity) throws BusinessException {
    entity.validate();

    try {
      return dao.save(entity);
    } catch (DaoException e) {
      throw new BusinessException("Connection error, try again in a few minutes.");
    }
  }

  @Override
  public List<Device> findAll() {
    return dao.findAll();
  }

  @Override
  public Device findById(Object id) throws BusinessException {
    return null;
  }

  @Override
  public void delete(Device entity) throws BusinessException {
    Integer id = entity.getId();
    if (id == null || id <= 0) {
      throw new BusinessException("Device: invalid id (" + id + ").");
    }
    dao.delete(entity);
  }

  @Override
  public void validate(Device entity) throws BusinessException {
    entity.validate();
  }

  /**
   * Finds all devices registered with the provided service.
   *
   * @param selectedService the services used to filter the devices
   * @return a list of devices
   * @throws BusinessException if the provided service is not registered
   * @throws NullPointerException if the provided service is null
   */
  public List<Device> findAllByService(Service selectedService) throws BusinessException {
    if (selectedService == null) {
      throw new NullPointerException();
    }
    Integer id = selectedService.getId();
    if (id == null || id <= 0) {
      throw new BusinessException("Service: invalid id. (" + id + ").");
    }

    return ((DeviceDao) dao).findAllByService(selectedService);
  }
}
