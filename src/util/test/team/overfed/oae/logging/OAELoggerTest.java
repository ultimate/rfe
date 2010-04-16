package team.overfed.oae.logging;

import static org.junit.Assert.*;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

/**
 * Class testing OAELogger.<br>
 * <br>
 * 
 * @author ultimate
 */
public class OAELoggerTest extends OAELoggerTestCase
{
	@Test
	public void testConstructors()
	{
		logger.debug("testing correct initiation");

		String aName = "any possible name";
		OAELogger oaeLogger = new OAELogger(aName);

		assertTrue(oaeLogger instanceof Category);
		assertTrue(oaeLogger instanceof Logger);
		assertTrue(oaeLogger instanceof OAELogger);

		assertEquals(aName, oaeLogger.getName());
	}

	@Test
	public void testGetLogger()
	{
		logger.debug("testing getLogger(String name)");

		Logger logger1 = OAELogger.getLogger("logger1");

		assertTrue(logger1 instanceof Category);
		assertTrue(logger1 instanceof Logger);
		assertTrue(logger1 instanceof OAELogger);
		assertEquals("logger1", logger1.getName());

		Logger logger2 = OAELogger.getLogger("logger2");

		assertTrue(logger2 instanceof Category);
		assertTrue(logger2 instanceof Logger);
		assertTrue(logger2 instanceof OAELogger);
		assertEquals("logger2", logger2.getName());

		Logger logger3 = OAELogger.getLogger("logger1");

		assertTrue(logger3 instanceof Category);
		assertTrue(logger3 instanceof Logger);
		assertTrue(logger3 instanceof OAELogger);
		assertEquals("logger1", logger3.getName());

		assertFalse(logger1.equals(logger2));
		assertTrue(logger1.equals(logger3));

		logger.debug("testing getLogger(Class clazz)");

		Logger logger4 = OAELogger.getLogger(getClass());

		assertTrue(logger4 instanceof Category);
		assertTrue(logger4 instanceof Logger);
		assertTrue(logger4 instanceof OAELogger);
		assertEquals(getClass().getName(), logger4.getName());

		Logger logger5 = OAELogger.getLogger(getClass().getSuperclass());

		assertTrue(logger5 instanceof Category);
		assertTrue(logger5 instanceof Logger);
		assertTrue(logger5 instanceof OAELogger);
		assertEquals(getClass().getSuperclass().getName(), logger5.getName());

		Logger logger6 = OAELogger.getLogger("team.overfed.oae.logging.OAELoggerTest");

		assertTrue(logger6 instanceof Category);
		assertTrue(logger6 instanceof Logger);
		assertTrue(logger6 instanceof OAELogger);
		assertEquals(getClass().getName(), logger6.getName());

		assertFalse(logger4.equals(logger5));
		assertTrue(logger4.equals(logger6));
	}

	@Test
	public void testGetOAELogger()
	{
		// Test with String-Argument
		OAELogger logger1 = OAELogger.getOAELogger("logger1");

		assertTrue(logger1 instanceof Category);
		assertTrue(logger1 instanceof Logger);
		assertTrue(logger1 instanceof OAELogger);
		assertEquals("logger1", logger1.getName());

		OAELogger logger2 = OAELogger.getOAELogger("logger2");

		assertTrue(logger2 instanceof Category);
		assertTrue(logger2 instanceof Logger);
		assertTrue(logger2 instanceof OAELogger);
		assertEquals("logger2", logger2.getName());

		OAELogger logger3 = OAELogger.getOAELogger("logger1");

		assertTrue(logger3 instanceof Category);
		assertTrue(logger3 instanceof Logger);
		assertTrue(logger3 instanceof OAELogger);
		assertEquals("logger1", logger3.getName());

		assertFalse(logger1.equals(logger2));
		assertTrue(logger1.equals(logger3));

		// Test with String-Argument
		OAELogger logger4 = OAELogger.getOAELogger(getClass());

		assertTrue(logger4 instanceof Category);
		assertTrue(logger4 instanceof Logger);
		assertTrue(logger4 instanceof OAELogger);
		assertEquals(getClass().getName(), logger4.getName());

		OAELogger logger5 = OAELogger.getOAELogger(getClass().getSuperclass());

		assertTrue(logger5 instanceof Category);
		assertTrue(logger5 instanceof Logger);
		assertTrue(logger5 instanceof OAELogger);
		assertEquals(getClass().getSuperclass().getName(), logger5.getName());

		OAELogger logger6 = OAELogger.getOAELogger("team.overfed.oae.logging.OAELoggerTest");

		assertTrue(logger6 instanceof Category);
		assertTrue(logger6 instanceof Logger);
		assertTrue(logger6 instanceof OAELogger);
		assertEquals(getClass().getName(), logger6.getName());

		assertFalse(logger4.equals(logger5));
		assertTrue(logger4.equals(logger6));
	}

	@Test
	public void testLogging()
	{
		String newLine = System.getProperty("line.separator");

		String s1 = "1. Line";
		String s2 = "2. Line";
		String s3 = "3. Line";
		String s4 = "4. Line";
		String s5 = "5. Line";
		String s6 = "6. Line";
		String s7 = "7. Line";
		String s8 = "8. Line";

		OAELogger logger = OAELogger.getOAELogger("testLogger");

		TestAppender testAppender = new TestAppender();
		testAppender.setLayout(new PatternLayout("%m%n"));

		logger.addAppender(testAppender);

		logger.info(s1);
		logger.info(s2 + "\n");
		logger.info(s3 + "\n\n");
		logger.info(s4 + "\n" + s5 + "\n" + s6);
		logger.info(s7 + "\n" + s8 + "\n");

		String output = testAppender.getLoggingHistory();

		String expected = s1 + newLine + s2 + newLine + s3 + newLine + s4 + newLine + s5 + newLine + s6 + newLine + s7 + newLine + s8 + newLine;
		assertEquals(expected, output);
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(OAELoggerTest.class);
	}
}
