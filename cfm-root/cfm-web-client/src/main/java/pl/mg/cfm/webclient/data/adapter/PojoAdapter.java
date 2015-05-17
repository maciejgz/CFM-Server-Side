package pl.mg.cfm.webclient.data.adapter;

import org.springframework.stereotype.Component;

/**
 * Adapter dla formatowania klas biznesowych POJO do JPA Entity i odwrotnie.
 * Created by m on 2015-05-17.
 */
public interface PojoAdapter<P, E> {

    public P fromEntity(E entity);

    public E toEntity(P pojo);

}
