package br.ufrn.imd.app.service;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.model.Device;
import br.ufrn.imd.app.model.Service;
import java.util.List;

/** Device Service Interface for specific services for devices. */
public interface DeviceServiceI extends ServiceI<Device> {
  List<Device> findAllByService(Service selectedService) throws BusinessException;
}
