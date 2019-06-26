package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.exception.DaoException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.validator.Validatable;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

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
  @Transactional
  T save(T entity) throws BusinessException, DaoException;

  /**
   * Query the database for all entities of that kind.
   *
   * @return a list of entities
   */
  @Transactional
  List<T> findAll();

  /**
   * Searches for a entity by the id
   *
   * @param id the id of the object
   * @return a optional that may contain a entity, if exists.
   */
  Optional<T> findById(Object id) throws BusinessException;

  void delete(T entity) throws BusinessException;
}
