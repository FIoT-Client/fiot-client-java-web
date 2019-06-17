package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/** Represents the persistence data manipulation for Service entity. */
public class ServiceDao implements DaoI<Service> {

  /** EntityManager for operations. */
  private EntityManager entityManager;

  public ServiceDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Service save(Service entity) throws BusinessException {
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(entity);
      transaction.commit();
    } catch (EntityExistsException e) {
      throw new BusinessException("Service: already registered entity for name/path/api.");
    }

    return entity;
  }
}
