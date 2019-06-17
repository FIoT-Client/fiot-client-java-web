package br.ufrn.imd.app.validator;

import br.ufrn.imd.app.exception.BusinessException;

public interface Validatable {

    void validate() throws BusinessException;
}
