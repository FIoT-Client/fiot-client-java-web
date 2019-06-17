package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.ServiceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.validator.Validatable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServiceService implements ServiceI<Service> {

  @PersistenceContext(unitName = "fiot")
  EntityManager entityManager;

  private DaoI<Service> dao;

  @PostConstruct
  public void init() {
    this.dao = new ServiceDao(entityManager);
  }

  @Override
  public Service save(Service entity) throws BusinessException {
    validate(entity);

    return dao.save(entity);
  }

  private void validate(Validatable entity) throws BusinessException {
    entity.validate();
  }
}
