package co.edu.unisimon.integration.config;



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
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "co.edu.unisimon.integration.repository.aula.posgrado",
    entityManagerFactoryRef = "posgradoEntityManager",
    transactionManagerRef = "posgradoTransactionManager"
)
public class PosgradoAulaConfig {

    @Bean
    @ConfigurationProperties(prefix = "posgradoaula.datasource")
    public DataSource posgradoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean posgradoEntityManager(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(posgradoDataSource())
                .packages("co.edu.unisimon.integration.model.aula.posgrado")
                .persistenceUnit("posgradoPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager posgradoTransactionManager(
            @Qualifier("posgradoEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}

