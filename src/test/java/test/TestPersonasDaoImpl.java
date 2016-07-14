package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
//import mx.com.gm.jdbc.Persona;
//import mx.com.gm.jdbc.PersonaDao;
import mx.comr.jdbc.IPersonaDao;
import mx.comr.jdbc.Persona;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-test.xml", "classpath:applicationContext.xml" })
public class TestPersonasDaoImpl {
	private static Log logger = LogFactory.getLog("TestPersonasDaoImpl");
	@Autowired
	private IPersonaDao personaDao;

	@Test
	public void deberiaMostrarPersonas() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaMostrarPersonas");
			List<Persona> personas = personaDao.findAllPersonas();
			int contadorPersonas = 0;
			for (Persona persona : personas) {
				logger.info("Persona: " + persona);
				contadorPersonas++;
			}
			// Segun el numero de personas recuperadas, deberia ser el mismo de
			// la tabla
			assertEquals(contadorPersonas, personaDao.contadorPersonas());
			logger.info("Fin del test deberiaMostrarPersonas");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void testContarPersonasPorNombre() {
		try {
			System.out.println();
			logger.info("Inicio del test Contar Personas por nombre");
			String nombre = "Juan";
			Persona personaEjemplo = new Persona();
			personaEjemplo.setNombre(nombre);
			int noPersonasEncontradas = personaDao.contadorPersonasPorNombre(personaEjemplo);
			logger.info("Numero de personas encontradas por nombre '" + nombre + "': " + noPersonasEncontradas);
			assertEquals(2, noPersonasEncontradas);
			logger.info("Fin del test Contar Personas por nombre");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void deberiaEncontrarPersonaPorId() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaEncontrarPersonaPorId");
			int idPersona = 1;
			Persona persona = personaDao.findPersonaById(idPersona);
			// Segun la persona recuperada, deberia ser la misma que el registro
			// 1
			assertEquals("Admin", persona.getNombre());
			// Imprimimos todo el objeto
			logger.info("Persona recuperada (id=" + idPersona + "): " + persona);
			logger.info("Fin del test deberiaEncontrarPersonaPorId");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}
}
