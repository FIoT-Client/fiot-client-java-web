package br.ufrn.imd.app.dao;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ServiceDAO implements DaoI<Service> {

    private List<Service> dataSource = new ArrayList<>();

    @Override
    public synchronized Service save(Service entity) throws BusinessException {
        if (dataSource.contains(entity)) {
            throw new BusinessException("Service: already registered for name, path or api.");
        }
        dataSource.add(entity);

        return entity;
    }
}
