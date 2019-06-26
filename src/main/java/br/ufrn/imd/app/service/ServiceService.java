package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.ServiceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.exception.DaoException;
import br.ufrn.imd.app.model.Service;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServiceService implements ServiceI<Service> {

  @PersistenceContext(unitName = "fiot") // , type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  private DaoI<Service> dao;

  @PostConstruct
  public void init() {
    this.dao = new ServiceDao(entityManager);
  }

  @Override
  public Service save(Service entity) throws BusinessException {
    validate(entity);

    try {
      return dao.save(entity);
    } catch (DaoException e) {
      throw new BusinessException("Connection error, try again in a few minutes.");
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
        .orElseThrow(() -> new BusinessException("Can't find Service with id (" + id + ")"));
  }

  @Override
  public void delete(Service entity) throws BusinessException {
    Integer id = entity.getId();
    if (id == null || id == 0) {
      throw new BusinessException("Service: invalid id (" + id + ").");
    }

    dao.delete(entity);
  }

  @Override
  public void validate(Service entity) throws BusinessException {
    entity.validate();
  }
}
