package edu.ebac.vinisantosn;

import edu.ebac.vinisantosn.dao.jpa.ClienteJpaDAO;
import edu.ebac.vinisantosn.dao.jpa.ClienteJpaDB2DAO;
import edu.ebac.vinisantosn.dao.jpa.ClienteJpaDB3DAO;
import edu.ebac.vinisantosn.dao.jpa.IClienteJpaDAO;
import edu.ebac.vinisantosn.domain.jpa.ClienteJPA;
import edu.ebac.vinisantosn.domain.jpa.ClienteJpa2;
import edu.ebac.vinisantosn.exceptions.DAOException;
import edu.ebac.vinisantosn.exceptions.MaisDeUmRegistroException;
import edu.ebac.vinisantosn.exceptions.TableException;
import edu.ebac.vinisantosn.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ClienteJpaDao3BancosTest {

    private IClienteJpaDAO<ClienteJPA> clienteDao;

    private IClienteJpaDAO<ClienteJPA> clienteDB2Dao;

    private IClienteJpaDAO<ClienteJpa2> clienteDB3Dao;

    private Random rd;

    public ClienteJpaDao3BancosTest() {
        this.clienteDao = new ClienteJpaDAO();
        this.clienteDB2Dao = new ClienteJpaDB2DAO();
        this.clienteDB3Dao = new ClienteJpaDB3DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJPA> list = clienteDao.buscarTodos();
        excluir(list, clienteDao);

        Collection<ClienteJPA> list2 = clienteDB2Dao.buscarTodos();
        excluir(list2, clienteDB2Dao);

        Collection<ClienteJpa2> list3 = clienteDB3Dao.buscarTodos();
        excluir3(list3);
    }

    private void excluir(Collection<ClienteJPA> list, IClienteJpaDAO<ClienteJPA> clienteDao) {
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluir3(Collection<ClienteJpa2> list) {
        list.forEach(cli -> {
            try {
                clienteDB3Dao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ClienteJPA cliente = criarCliente();
        clienteDao.cadastrar(cliente);

        ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        cliente.setId(null);
        clienteDB2Dao.cadastrar(cliente);

        ClienteJPA clienteConsultado2 = clienteDB2Dao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado2);

        ClienteJpa2 cliente2 = criarCliente2();
        clienteDB3Dao.cadastrar(cliente2);

        ClienteJpa2 clienteConsultado3 = clienteDB3Dao.consultar(cliente2.getId());
        Assert.assertNotNull(clienteConsultado3);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJPA cliente = criarCliente();
        ClienteJPA retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

//        ClienteJPA clienteConsultado = clienteDao.consultar(retorno.getId());
//        Assert.assertNotNull(clienteConsultado);
//
//        clienteDao.excluir(cliente);
//
//        ClienteJPA clienteConsultado1 = clienteDao.consultar(retorno.getId());
//        Assert.assertNull(clienteConsultado1);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJPA cliente = criarCliente();
        ClienteJPA retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJPA cliente = criarCliente();
        ClienteJPA retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Rodrigo Pires");
        clienteDao.alterar(clienteConsultado);

        ClienteJPA clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        ClienteJPA cliente = criarCliente();
        ClienteJPA retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        ClienteJPA cliente1 = criarCliente();
        ClienteJPA retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Collection<ClienteJPA> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<ClienteJPA> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private ClienteJPA criarCliente() {
        ClienteJPA cliente = new ClienteJPA();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }

    private ClienteJpa2 criarCliente2() {
        ClienteJpa2 cliente = new ClienteJpa2();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }

}
