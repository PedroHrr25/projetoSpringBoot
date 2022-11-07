package Vendas.domain.repositorio;


import Vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {
  List<Cliente> findbyNomeLike(String nome);

  List<Cliente> findByNomeOrId (String nome, Integer id);

  boolean existsBynome(String nome);
}



