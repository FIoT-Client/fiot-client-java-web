package br.ufrn.imd.app.service;

import br.ufrn.imd.app.exception.BusinessException;

public interface ServiceI<T> {

    T save(T entity) throws BusinessException;

}
