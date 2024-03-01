package edu.ebac.vinisantosn.generic.jpa;

import edu.ebac.vinisantosn.dao.jpa.Persistente;

import java.io.Serializable;

public abstract class GenericJpaDB1DAO <T extends Persistente, E extends Serializable>
        extends GenericJpaDAO<T,E> {

    public GenericJpaDB1DAO(Class<T> persistenteClass) {
        super(persistenteClass, "Postgres01");
    }

}
