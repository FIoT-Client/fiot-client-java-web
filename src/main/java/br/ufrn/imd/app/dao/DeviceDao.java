package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Device;
import br.ufrn.imd.app.model.Service;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class DeviceDao implements DaoI<Device> {

  private EntityManager entityManager;

  public DeviceDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Device save(Device entity) throws BusinessException {
    Service service = entityManager.find(Service.class, entity.getService().getId());

    entity.setService(service);

    Integer id = entity.getId();
    if (id != null && id > 0) {
      entityManager.merge(entity);
    } else {
      entityManager.persist(entity);
    }

    return entity;
  }

  @Override
  public List<Service> findAll() {
    return null;
  }

  @Override
  public Optional<Device> findById(Object id) throws BusinessException {
    return Optional.empty();
  }

  @Override
  public void delete(Device entity) throws BusinessException {}
}