    package org.example.config;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.EnableWebMvc;
    import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
    import org.thymeleaf.spring5.SpringTemplateEngine;
    import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
    import org.thymeleaf.spring5.view.ThymeleafViewResolver;

    /* нужные зависимости: "Spring Core", "Spring Context", "Spring MVC", "Thymeleaf" */

    /*
    Таким образом, данный код определяет конфигурацию Spring приложения с поддержкой Spring MVC и
    настраивает шаблонизатор Thymeleaf для работы с представлениями веб-страниц.
     */


    @Configuration /*Эта аннотация указывает,
    что класс SpringConfig является конфигурационным классом Spring,
    который будет определять настройки бинов и компонентов. */

    @ComponentScan("org.example") /* Эта аннотация указывает Spring сканировать пакет "org.example" и
    его подпакеты для поиска и создания бинов (компонентов). */

    @EnableWebMvc /*  Эта аннотация активирует конфигурацию Spring MVC, включая обработку HTTP запросов и представлений. */
    public class SpringConfig implements WebMvcConfigurer  {

        private final ApplicationContext applicationContext;

        @Autowired
        public SpringConfig(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        @Bean
        public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setApplicationContext(applicationContext);
            templateResolver.setPrefix("/WEB-INF/views/");
            templateResolver.setSuffix(".html");
            return templateResolver;
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.setEnableSpringELCompiler(true);
            return templateEngine;
        }

        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(templateEngine());
            registry.viewResolver(resolver);
        }
    }