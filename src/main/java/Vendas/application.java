package Vendas;

import Vendas.domain.entity.Cliente;
import Vendas.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class application {

  /* comando de teste  @Bean
    public CommandLineRunner init (@Autowired Clientes clientes){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Pedro");
            clientes.salvar(cliente);
        };
    }

   */





    public static void main(String[] args) {
              SpringApplication.run(application.class,args);
    }
}
