package test;

import static org.junit.Assert.assertEquals;import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-test.xml" })
public class TestJDBC{
	private static Log logger = LogFactory.getLog("TestJdbc");
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testJdbc() {
		
		logger.info("Inicio del test Jdbc");
		int noPersonas = jdbcTemplate.queryForObject("select count(*) from persona", Integer.class);
		logger.info("Numero de personas:" + noPersonas);
		assertEquals(3, noPersonas);
		logger.info("Fin del test Jdbc");
	}
}