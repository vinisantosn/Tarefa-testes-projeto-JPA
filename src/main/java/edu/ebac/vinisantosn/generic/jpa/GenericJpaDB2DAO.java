package edu.ebac.vinisantosn.generic.jpa;

import edu.ebac.vinisantosn.dao.jpa.Persistente;

import java.io.Serializable;

public abstract class GenericJpaDB2DAO <T extends Persistente, E extends Serializable>
        extends GenericJpaDAO<T,E> {

    public GenericJpaDB2DAO(Class<T> persistenteClass) {
        super(persistenteClass, "Postgres02");
    }

}
