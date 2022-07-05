package uz.isystem.universitysystem._service;

import uz.isystem.universitysystem._mapper.Mapper;

public abstract class AbstractService<M extends Mapper> {
    protected M mapper;

    protected AbstractService(M mapper) {
        this.mapper = mapper;
    }
}
