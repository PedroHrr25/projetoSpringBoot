package Vendas.domain.repository;

import Vendas.domain.entity.Cliente;
import Vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido,Integer> {


List<Pedido> findByCliente (Cliente clientes);}

//@Query("select p from pedido p left ojin fetch p.itens where p.id = :id")
//Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
//}
