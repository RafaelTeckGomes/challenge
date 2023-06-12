package com.agency.sm360.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class StartHSQLDBManager {
	
	@Autowired
	DataSource dataSource;

	@Bean
	public JdbcTemplate getJdbcTemplate(){
	  return new JdbcTemplate(dataSource);
	}
	
	//@PostConstruct
	/*public void getDbManager(){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:file:db_name1", "--user", "sa", "--password", "sa"});
	}*/

}
