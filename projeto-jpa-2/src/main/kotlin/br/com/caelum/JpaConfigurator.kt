package br.com.caelum

import java.util.Properties

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class JpaConfigurator {

    val dataSource: DataSource
        @Bean
        get() {
            val dataSource = DriverManagerDataSource()

            dataSource.setDriverClassName("com.mysql.jdbc.Driver")
            dataSource.url = "jdbc:mysql://localhost/projeto_jpa"
            dataSource.username = "root"
            dataSource.password = ""

            return dataSource
        }

    @Bean
    fun getEntityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactory = LocalContainerEntityManagerFactoryBean()

        entityManagerFactory.setPackagesToScan("br.com.caelum")
        entityManagerFactory.dataSource = dataSource

        entityManagerFactory.jpaVendorAdapter = HibernateJpaVendorAdapter()

        val props = Properties()

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect")
        props.setProperty("hibernate.show_sql", "true")
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop")

        entityManagerFactory.setJpaProperties(props)
        return entityManagerFactory
    }

    @Bean
    fun getTransactionManager(emf: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = emf

        return transactionManager
    }

}
