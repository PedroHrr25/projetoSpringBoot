package Vendas.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class In18config {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSourge = new ReloadableResourceBundleMessageSource();
        messageSourge.setBasename("classpath:messages");
        messageSourge.setDefaultEncoding("ISO-8859-1");
        messageSourge.setDefaultLocale(Locale.getDefault());
        return messageSourge;
    }


    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }


}
