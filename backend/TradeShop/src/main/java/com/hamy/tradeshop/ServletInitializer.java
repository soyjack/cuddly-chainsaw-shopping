package com.hamy.tradeshop;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The ServletInitializer class configures the application when it is deployed to a servlet container.
 * It extends SpringBootServletInitializer to provide additional configuration when deploying the application as a WAR file.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configures the application by specifying the sources for the Spring application context.
     * 
     * @param application the SpringApplicationBuilder instance.
     * @return the configured SpringApplicationBuilder instance.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TradeShopApplication.class);
    }
}
