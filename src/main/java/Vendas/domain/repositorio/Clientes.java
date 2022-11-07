package Vendas.domain.repositorio;


import Vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
// só para recapitular as partes de jdbc
    // porem, ira ser iniciado com JPA
//
//   private static String INSERT = "insert into cliente (nome values (?) ";
//    private static String SELECT_ALL = "select * from cliente ";
//    private static String UPDATE = "update cliente set nome = ? where id = ?";
//    private static String DELETE = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        //      jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(id);
    }

    @Transactional
    public List<Cliente> obterTodos() {
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    //opmitizar pesquisa o readonly
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = " select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }


//    private RowMapper<Cliente> obterClienteMapper() {
//        return new RowMapper<Cliente>() {
//            // mapear as colunas para a entidade clientes e retorna "arrumado"
//            @Override
//            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
//                String nome = resultSet.getString("nome");
//                Integer id = resultSet.getInt("id");
//                return new Cliente(id, nome);
//            }
//        };
}

