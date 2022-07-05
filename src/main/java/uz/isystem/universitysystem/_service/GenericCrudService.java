package uz.isystem.universitysystem._service;

import java.io.Serializable;
import java.util.List;

/*
* @param <E> -> Entity
* @param <D> -> Dto
* @param <K> -> Class that defines the primary key for your pojo class
*
* */

public interface GenericCrudService<E, D, K extends Serializable > {

    D getById(K id);

    void create(D dto);

    void update(D dto, K id);

    void delete(K id);

    List<D> getAll();
}
