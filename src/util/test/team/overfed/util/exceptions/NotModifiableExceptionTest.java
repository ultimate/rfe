package team.overfed.util.exceptions;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on NotModifiableException.
 * 
 * @author ultimate
 */
public class NotModifiableExceptionTest extends OAELoggerTestCase
{

	@Test
	public void testConstructors()
	{
		logger.debug("testing constructors (2 cases)");
		NotModifiableException e;
		String message = "any message";

		Object o = "aldsfhlasedf";

		e = new NotModifiableException(o, message);
		assertEquals("The given Object is not modifiable:\n" + message + "\n  Object is: " + o.toString(), e.getMessage());
		assertEquals(o, e.getObject());

		e = new NotModifiableException(o);
		assertEquals("The given Object is not modifiable." + "\n  Object is: " + o.toString(), e.getMessage());
		assertEquals(o, e.getObject());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NotModifiableExceptionTest.class);
	}
}
