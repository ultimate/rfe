package team.overfed.oae.logging;

import static org.junit.Assert.*;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;

/**
 * Class testing OAELoggerFactory.<br>
 * <br>
 * 
 * @author ultimate
 */
public class OAELoggerFactoryTest extends OAELoggerTestCase
{
	@Test
	public void testMakeNewLoggerInstance()
	{
		logger.debug("testing correct initiation");

		String aName = "any possible Logger name";

		LoggerFactory fac = new OAELoggerFactory();

		assertTrue(fac instanceof LoggerFactory);
		assertTrue(fac instanceof OAELoggerFactory);

		logger.debug("testing correct return of makeNewLoggerInstance");

		Logger logger = fac.makeNewLoggerInstance(aName);

		assertTrue(logger instanceof Category);
		assertTrue(logger instanceof Logger);
		assertTrue(logger instanceof OAELogger);

		assertEquals(aName, logger.getName());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(OAELoggerFactoryTest.class);
	}
}
