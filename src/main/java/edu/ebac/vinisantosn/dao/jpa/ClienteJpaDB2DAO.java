package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.domain.jpa.ClienteJPA;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDB2DAO;

public class ClienteJpaDB2DAO extends GenericJpaDB2DAO<ClienteJPA, Long> implements IClienteJpaDAO<ClienteJPA> {

    public ClienteJpaDB2DAO() {
        super(ClienteJPA.class);
    }

}
