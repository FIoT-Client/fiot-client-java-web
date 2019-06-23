package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.dao.ServiceDao;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.validator.Validatable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServiceService implements ServiceI<Service> {

  @PersistenceContext(unitName = "fiot") // , type = PersistenceContextType.EXTENDED)
  EntityManager entityManager;

  private DaoI<Service> dao;

  @PostConstruct
  public void init() {
    this.dao = new ServiceDao(entityManager);
  }

  @Override
  public Service save(Service entity) throws BusinessException {
    validate(entity);

    try {
      return dao.save(entity);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @Override
  public List<Service> findAll() {
    return dao.findAll();
  }

  private void validate(Validatable entity) throws BusinessException {
    entity.validate();
  }
}
