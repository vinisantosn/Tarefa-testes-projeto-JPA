package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.generic.jpa.GenericJpaDAO;
import edu.ebac.vinisantosn.domain.jpa.ProdutoJPA;

public class ProdutoJpaDAO extends GenericJpaDAO<ProdutoJPA, Long> implements IProdutoJpaDAO {

    public ProdutoJpaDAO() {
        super(ProdutoJPA.class, "Postgres01");
    }

}
