package co.kr.bongjae.web.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "co.kr.bongjae.db.jpa",
        entityManagerFactoryRef = "entityManagerFactory"
)
@MapperScan(
        basePackages = "co.kr.bongjae.db.mapper",
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class DBConfig {
    /**
     * hikari config
     */
    @Bean(name = "hikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    /**
     * datasource
     */
    @Primary
    @Bean(name = "dataSource")
    public HikariDataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

//    @Bean(name = "jpaDataSource")
//    public HikariDataSource jpaDataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
//        return new HikariDataSource(hikariConfig);
//    }

    /**
     * sessionfactory
     */
    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml")); //mapper path
        sessionFactoryBean.setTypeAliasesPackage("co.kr.bongjae.db.model");
//        sessionFactoryBean.setTypeHandlersPackage("co.kr.bongjae.db.typehandler");
        Objects.requireNonNull(sessionFactoryBean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true); //camelCase
        return sessionFactoryBean.getObject();
    }

    /**
     * sqlsession
     */
    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * jpa entityManagerFactory
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("co.kr.bongjae.db.model");
        factory.setDataSource(dataSource);
        factory.setPersistenceUnitName("MySQL");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factory.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = getStringObjectMap();
        factory.setJpaPropertyMap(properties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);  // sql은 log4j로 출력 org.hibernate.SQL=DEBUG
        properties.put("hibernate.globally_quoted_identifiers", true);  // 예약어 컬럼명 사용 허용
        return properties;
    }

    /**
     * transaction manager
     */
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }

//    @Bean(name = "txManager")
//    public PlatformTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
//        dataSourceTransactionManager.setNestedTransactionAllowed(true); // nested
//
//        return dataSourceTransactionManager;
//    }
}
