package uz.isystem.universitysystem._mapper;

import java.util.List;

public interface BaseMapper<E, D>  extends Mapper{

    D toDto(E e);

    List<D> toDto(List<E> e);

}
