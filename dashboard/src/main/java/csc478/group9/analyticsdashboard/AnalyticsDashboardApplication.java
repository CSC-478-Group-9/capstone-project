package csc478.group9.analyticsdashboard;

import org.apache.ibatis.session.SqlSessionFactory;


import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@MapperScan("csc478.group9.analyticsdashboard")

@SpringBootApplication
public class AnalyticsDashboardApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnalyticsDashboardApplication.class, args);
	}
}
