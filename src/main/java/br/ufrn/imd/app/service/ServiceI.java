package br.ufrn.imd.app.service;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;
import java.util.List;
import javax.ejb.Local;

/**
 * Represents a generic interface binding to the service layer.
 *
 * @param <T> the kind of entity being manipulated
 */
@Local
public interface ServiceI<T extends Validatable> {

  /**
   * Creates, or updates, a entity into the database.
   *
   * @param entity the entity to be persisted
   * @return the updated indexed entity
   * @throws BusinessException already existing entity
   */
  T save(T entity) throws BusinessException;

  /**
   * Returns all the entities saved.
   *
   * @return a List of T entities
   */
  List<T> findAll();

  T findById(Object id) throws BusinessException;

  void delete(T entity) throws BusinessException;

  void validate(T entity) throws BusinessException;
}
