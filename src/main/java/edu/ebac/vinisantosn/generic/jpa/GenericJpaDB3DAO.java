package edu.ebac.vinisantosn.generic.jpa;

import edu.ebac.vinisantosn.dao.jpa.Persistente;

import java.io.Serializable;

public abstract class GenericJpaDB3DAO <T extends Persistente, E extends Serializable>
        extends GenericJpaDAO<T,E> {

    public GenericJpaDB3DAO(Class<T> persistenteClass) {
        super(persistenteClass, "MySQL01");
    }

}
