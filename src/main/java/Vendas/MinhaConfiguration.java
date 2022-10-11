package Vendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
// @Profile("nome do ambiente")
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName()
    {
        return "Sistema de vendas";
    }
    @Bean(name = "outraconfiguration")
    public String outraconfiguration()
    {
        return "Sistema de vendas 2";
    }
}
