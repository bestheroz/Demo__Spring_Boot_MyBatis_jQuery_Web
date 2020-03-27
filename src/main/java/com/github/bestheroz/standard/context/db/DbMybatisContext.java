package com.github.bestheroz.standard.context.db;

import com.github.bestheroz.standard.override.SqlSessionTemplateOverride;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.github.bestheroz", annotationClass = Mapper.class)
public class DbMybatisContext {
    /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     * <p>
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     */
    @Bean(name = "sqlSession", destroyMethod = "clearCache")
    @Autowired
    @Primary
    public SqlSessionTemplateOverride getMySqlSessionTemplate(final ApplicationContext applicationContext, final HikariDataSource hikariDataSource) throws Exception {
        return new SqlSessionTemplateOverride(this.getSqlSessionFactoryBean(applicationContext, hikariDataSource).getObject());
    }

    @Bean(name = "transactionManager")
    @Primary
    @Autowired
    public PlatformTransactionManager getPlatformTransactionManager(final HikariDataSource hikariDataSource) {
        return new DataSourceTransactionManager(hikariDataSource);
    }

    private SqlSessionFactoryBean getSqlSessionFactoryBean(final ApplicationContext applicationContext, final HikariDataSource hikariDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(hikariDataSource);
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.github.bestheroz");
        sqlSessionFactory.setTypeAliasesSuperType(Serializable.class);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:**/*Mapper.xml"));
        return sqlSessionFactory;
    }

}
