package Vendas.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;


public interface Produtos extends JpaRepository<Vendas.domain.entity.Produto, Integer> {
}
