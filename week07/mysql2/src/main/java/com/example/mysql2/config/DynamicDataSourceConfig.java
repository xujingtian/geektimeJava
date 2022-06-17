package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import com.example.db.DynamicDataSource;
import com.example.properties.DynamicDataSourceProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {"com.example.dao"},
        sqlSessionFactoryRef = "SqlSessionFactory")
public class DynamicDataSourceConfig {
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(DynamicDataSourceProperties properties) {
        Map targetDataSource = new HashMap(8);
        properties.datasources.forEach((k, v) -> {
            targetDataSource.put(k, v.initializeDataSourceBuilder().build());
        });
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(targetDataSource.get(properties.defaultdatasource));
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*/*.xml"));
        return bean.getObject();
    }
}
