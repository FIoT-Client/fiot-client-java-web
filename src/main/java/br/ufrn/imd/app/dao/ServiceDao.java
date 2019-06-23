package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/** Represents the persistence data manipulation for Service entity. */
public class ServiceDao implements DaoI<Service> {

  /** EntityManager for operations. */
  private EntityManager entityManager;

  public ServiceDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional(rollbackOn = {EntityExistsException.class, BusinessException.class})
  @Override
  public Service save(Service entity) throws BusinessException {
    try {
      entityManager.persist(entity);
    } catch (EntityExistsException e) {
      throw new BusinessException("Service: already registered entity for name/path/api.");
    }

    return entity;
  }

  @Override
  public List<Service> findAll() {
    return entityManager.createQuery("SELECT s FROM Service s", Service.class).getResultList();
  }

  @Override
  public Optional<Service> findById(Object id) throws BusinessException {
    if (!(id instanceof Integer)) {
      throw new BusinessException("Service: incompatible id.");
    }

    return Optional.ofNullable(entityManager.find(Service.class, id));
  }
}
