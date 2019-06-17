package br.ufrn.imd.app.service;

import br.ufrn.imd.app.dao.DaoI;
import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Service;
import br.ufrn.imd.app.validator.Validatable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceService implements ServiceI<Service> {

    @Inject
    private DaoI<Service> dao;

    @Override
    public Service save(Service entity) throws BusinessException {
        validate(entity);

        return dao.save(entity);
    }

    private void validate(Validatable entity) throws BusinessException {
        entity.validate();
    }
}
