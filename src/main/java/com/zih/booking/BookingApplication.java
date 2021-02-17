package com.zih.booking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * @author shahy
 */
@EnableTransactionManagement
@MapperScan({"com.zih.booking.dao","com.zih.booking.settlementDao"})
@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
        System.out.println(
                        "==============================\n" +
                        "======启===动===成===功=======\n" +
                        "==============================");
    }

    @Autowired
    DataSource dataSource;

    /**
     * 重写CommandLineRunner接口的run方法，将在程序启动初始化之后运行
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("DATASOURCE = " + dataSource);
    }

}
