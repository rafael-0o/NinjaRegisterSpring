package com.dev.myninjaregister.ninjaregister1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //Anotação que marca a classe como uma fonte de configurações do Spring

public class WebConfig implements WebMvcConfigurer
//Interface do Spring MVC para personalizar a configuração
{
    @Bean/*
    - É um objeto Java reutilizável
    - Gerenciado pelo Spring
    - Segue certas convenções
    @Component, service e repository são um tipo de been
    */
    public HiddenHttpMethodFilter hiddenHttpMethodFilter()
    /*
    - Filtro que permite usar métodos HTTP além de GET e POST em formulários HTML
    - Processa campos ocultos do formulário para determinar o método real
    - Suporta métodos como PUT, DELETE, PATCH, etc.
*/
    {
        return new HiddenHttpMethodFilter();
    }
}