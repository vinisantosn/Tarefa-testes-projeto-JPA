package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.exceptions.DAOException;
import edu.ebac.vinisantosn.exceptions.TipoChaveNaoEncontradaException;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDAO;
import edu.ebac.vinisantosn.domain.jpa.VendaJPA;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDB1DAO;

public class VendaExclusaoJpaDAO extends GenericJpaDB1DAO<VendaJPA, Long> implements IVendaJpaDAO {

    public VendaExclusaoJpaDAO() {
        super(VendaJPA.class);
    }

    @Override
    public void finalizarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public void cancelarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public VendaJPA consultarComCollection(Long id) {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

}
