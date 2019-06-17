package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.util.JPAUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/** Represents the persistence data manipulation for Service entity. */
@Named
@ApplicationScoped
public class ServiceDAO implements DaoI<Service> {

  /** EntityManager for operations. */
  private EntityManager entityManager = JPAUtils.getEntityManager();

  @Override
  public Service save(Service entity) throws BusinessException {
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(entity);
      transaction.commit();
    } catch (EntityExistsException e) {
      throw new BusinessException(e.getMessage());
    }

    return entity;
  }
}
