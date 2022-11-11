package Vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<Vendas.domain.entity.ItemPedido,Integer>{

}
