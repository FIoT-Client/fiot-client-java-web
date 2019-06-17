package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;

/**
 * Represents a generic interface binding of a Dao.
 *
 * @param <T> the kind of entity being manipulated
 */
public interface DaoI<T extends Validatable> {
  /**
   * Saves an entity into persistence database.
   *
   * @param entity entity to be persisted
   * @return the updated entity from database
   * @throws BusinessException in case a business rule go wrong
   */
  T save(T entity) throws BusinessException;
}
