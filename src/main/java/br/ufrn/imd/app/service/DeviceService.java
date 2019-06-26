package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.DeviceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.exception.DaoException;
import br.ufrn.imd.app.model.Device;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeviceService implements ServiceI<Device> {

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
    return null;
  }

  @Override
  public Device findById(Object id) throws BusinessException {
    return null;
  }

  @Override
  public void delete(Device entity) throws BusinessException {}

  @Override
  public void validate(Device entity) throws BusinessException {
    entity.validate();
  }
}
