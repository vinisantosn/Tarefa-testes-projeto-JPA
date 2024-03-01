package edu.ebac.vinisantosn;

import edu.ebac.vinisantosn.dao.jpa.IProdutoJpaDAO;
import edu.ebac.vinisantosn.dao.jpa.ProdutoJpaDAO;
import edu.ebac.vinisantosn.domain.jpa.ProdutoJPA;
import edu.ebac.vinisantosn.exceptions.DAOException;
import edu.ebac.vinisantosn.exceptions.MaisDeUmRegistroException;
import edu.ebac.vinisantosn.exceptions.TableException;
import edu.ebac.vinisantosn.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProdutoJpaDaoTest {

    private IProdutoJpaDAO produtoDao;

    public ProdutoJpaDaoTest() {
        this.produtoDao = new ProdutoJpaDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<ProdutoJPA> list = produtoDao.buscarTodos();
        list.forEach(cli -> {
            try {
                produtoDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
        ProdutoJPA produto = criarProduto("A1");
        assertNotNull(produto);
        ProdutoJPA produtoDB = this.produtoDao.consultar(produto.getId());
        assertNotNull(produtoDB);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJPA produto = criarProduto("A2");
        assertNotNull(produto);
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJPA produto = criarProduto("A3");
        assertNotNull(produto);
        this.produtoDao.excluir(produto);
        ProdutoJPA produtoBD = this.produtoDao.consultar(produto.getId());
        assertNull(produtoBD);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ProdutoJPA produto = criarProduto("A4");
        produto.setNome("Rodrigo Pires");
        produtoDao.alterar(produto);
        ProdutoJPA produtoBD = this.produtoDao.consultar(produto.getId());
        assertNotNull(produtoBD);
        Assert.assertEquals("Rodrigo Pires", produtoBD.getNome());
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        criarProduto("A5");
        criarProduto("A6");
        Collection<ProdutoJPA> list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (ProdutoJPA prod : list) {
            this.produtoDao.excluir(prod);
        }

        list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);

    }

    private ProdutoJPA criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJPA produto = new ProdutoJPA();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produtoDao.cadastrar(produto);
        return produto;
    }
}