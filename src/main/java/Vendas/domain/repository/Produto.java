package Vendas.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;


public interface Produto  extends JpaRepository<Vendas.domain.entity.Produto, Integer> {
}
