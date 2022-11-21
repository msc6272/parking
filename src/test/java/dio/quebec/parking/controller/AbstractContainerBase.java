package dio.quebec.parking.controller;

import org.testcontainers.containers.PostgreSQLContainer;
//See https://www.testcontainers.org/features/advanced_options/ for explanation on how I corrected this class
public abstract class AbstractContainerBase {
    static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:10-alpine");
        POSTGRE_SQL_CONTAINER.start();
        //System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.url", "jdbc:tc:postgresql://localhost:5432/parking");
        /* System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
        System.out.println(POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.out.println(POSTGRE_SQL_CONTAINER.getUsername());
        System.out.println(POSTGRE_SQL_CONTAINER.getPassword()); */
        
    }
}
