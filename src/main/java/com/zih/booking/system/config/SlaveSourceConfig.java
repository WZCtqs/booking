package com.zih.booking.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.zih.booking.settlementDao", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveSourceConfig {

//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return druidProperties.dataSource(dataSource);
    }

//    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:settlementMapper/*.xml"));
        sessionFactoryBean.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                MybatisPlusConfig.paginationInterceptor() //添加分页功能
        });
//        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(false);
//        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }
}

