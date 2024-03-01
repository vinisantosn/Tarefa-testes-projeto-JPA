package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.domain.jpa.ClienteJPA;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDAO;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDB1DAO;

public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJPA, Long> implements IClienteJpaDAO<ClienteJPA> {

    public ClienteJpaDAO() {
        super(ClienteJPA.class);
    }

}
