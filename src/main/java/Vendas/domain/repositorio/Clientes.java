package Vendas.domain.repositorio;


import Vendas.domain.entity.Cliente;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    //utilizando HQL
    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> findByNomeLike(@Param("nome") String nome);

    @Query ("delete from Cliente c where c.nome = :nome")
    @Modifying
    void deletebyNome (@Param("nome") String nome);

}



