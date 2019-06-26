package br.ufrn.imd.app.validator;

import br.ufrn.imd.app.exception.BusinessException;
import java.io.Serializable;

/**
 * Interface for persistable entities.
 */
public interface Validatable extends Serializable {

  void validate() throws BusinessException;
}
