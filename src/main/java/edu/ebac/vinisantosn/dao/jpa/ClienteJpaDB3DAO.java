package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.domain.jpa.ClienteJpa2;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDB3DAO;

public class ClienteJpaDB3DAO extends GenericJpaDB3DAO<ClienteJpa2, Long> implements IClienteJpaDAO<ClienteJpa2> {

    public ClienteJpaDB3DAO() {
        super(ClienteJpa2.class);
    }

}
