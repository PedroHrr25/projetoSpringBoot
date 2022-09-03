package Vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class application {

    @Autowired
    // injetando o valor do bean nesse atributo
    @Qualifier("applicationName")
    public String applicationName;

    @GetMapping("/hello")
    public String helloWorld()
    {
        return applicationName;
    }

    public static void main(String[] args) {

                        SpringApplication.run(application.class,args);
    }
}
