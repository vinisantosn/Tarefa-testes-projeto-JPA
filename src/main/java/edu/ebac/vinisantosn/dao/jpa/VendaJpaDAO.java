package edu.ebac.vinisantosn.dao.jpa;

import edu.ebac.vinisantosn.exceptions.DAOException;
import edu.ebac.vinisantosn.exceptions.TipoChaveNaoEncontradaException;
import edu.ebac.vinisantosn.domain.jpa.ClienteJPA;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDAO;
import edu.ebac.vinisantosn.domain.jpa.ProdutoJPA;
import edu.ebac.vinisantosn.domain.jpa.VendaJPA;
import edu.ebac.vinisantosn.generic.jpa.GenericJpaDB1DAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



public class VendaJpaDAO extends GenericJpaDB1DAO<VendaJPA, Long> implements IVendaJpaDAO {

    public VendaJpaDAO() {
        super(VendaJPA.class);
    }

    @Override
    public void finalizarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }

    @Override
    public void cancelarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
        super.alterar(venda);
    }

    @Override
    public void excluir(VendaJPA entity) throws DAOException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public VendaJPA cadastrar(VendaJPA entity) throws TipoChaveNaoEncontradaException, DAOException {
        try {
            openConnection();
            entity.getProdutos().forEach(prod -> {
                ProdutoJPA prodJPA = entityManager.merge(prod.getProduto());
                prod.setProduto(prodJPA);
            });
            ClienteJPA cliente = entityManager.merge(entity.getCliente());
            entity.setCliente(cliente);
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            closeConnection();
            return entity;
        } catch (Exception e) {
            throw new DAOException("ERRO SALVANDO VENDA ", e);
        }

    }

    @Override
    public VendaJPA consultarComCollection(Long id) {
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendaJPA> query = builder.createQuery(VendaJPA.class);
        Root<VendaJPA> root = query.from(VendaJPA.class);
        root.fetch("cliente");
        root.fetch("produtos");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<VendaJPA> tpQuery =
                entityManager.createQuery(query);
        VendaJPA venda = tpQuery.getSingleResult();
        closeConnection();
        return venda;
    }



}
