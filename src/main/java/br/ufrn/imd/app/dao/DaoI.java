package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;

public interface DaoI<T extends Validatable> {
    T save(T entity) throws BusinessException;
}
