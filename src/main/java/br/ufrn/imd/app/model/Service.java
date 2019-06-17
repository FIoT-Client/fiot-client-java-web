package br.ufrn.imd.app.model;

import br.ufrn.imd.app.exception.BusinessException;
import br.ufrn.imd.app.validator.Validatable;

import java.io.Serializable;

public class Service implements Serializable, Validatable {

    private String name;
    private String path;
    private String API;

    public Service(String name, String path, String API) {

        this.name = name;
        this.path = path;
        this.API = API;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    @Override
    public void validate() throws BusinessException {
        StringBuilder builder = new StringBuilder();
        if (name == null || name.isEmpty()) {
            builder.append("Name: required field.")
                .append(System.lineSeparator());
        }

        if (path == null || path.isEmpty()) {
            builder.append("Path: required field.")
                .append(System.lineSeparator());
        }

        if (API == null || API.isEmpty()) {
            builder.append("API: required field.")
                .append(System.lineSeparator());
        }

        String errors = builder.toString();

        if (!errors.isEmpty()) {
            throw new BusinessException(errors);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Service service = (Service) o;

        if (!name.equals(service.name)) {
            return false;
        }
        if (!path.equals(service.path)) {
            return false;
        }
        return API.equals(service.API);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + API.hashCode();
        return result;
    }
}
