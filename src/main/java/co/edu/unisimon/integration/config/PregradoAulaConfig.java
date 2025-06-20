package co.edu.unisimon.integration.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "co.edu.unisimon.integration.repository.aula.pregrado",
    entityManagerFactoryRef = "pregradoEntityManager",
    transactionManagerRef = "pregradoTransactionManager"
)
public class PregradoAulaConfig {

    @Bean
    @ConfigurationProperties(prefix = "pregradoaula.datasource")
    public DataSource pregradoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean pregradoEntityManager(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pregradoDataSource())
                .packages("co.edu.unisimon.integration.model.aula.pregrado")
                .persistenceUnit("pregradoPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager pregradoTransactionManager(
            @Qualifier("pregradoEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}

