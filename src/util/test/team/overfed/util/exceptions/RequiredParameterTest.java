package team.overfed.util.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on RequiredParameter.
 * 
 * @author ultimate
 */
public class RequiredParameterTest extends OAELoggerTestCase
{

	@SuppressWarnings("unchecked")
	@Test
	public void testRequiredParameter()
	{
		String name = "name";
		Class parameterClass = Integer.class;

		logger.debug("testing valid constructor args");
		RequiredParameter rp = new RequiredParameter(name, parameterClass);
		assertNotNull(rp);
		assertEquals(parameterClass, rp.getParameterClass());
		assertEquals(name, rp.getName());
		assertEquals("RequiredParameter: \"" + name + "\" - " + parameterClass.toString(), rp.toString());

		logger.debug("testing invalid constructor args (3 cases)");
		rp = null;
		try
		{
			rp = new RequiredParameter(null, parameterClass);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The parameter name is null.", e.getMessage());
			assertNull(rp);
		}
		try
		{
			rp = new RequiredParameter("", parameterClass);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The parameter name is empty.", e.getMessage());
			assertNull(rp);
		}
		try
		{
			rp = new RequiredParameter(name, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The parameter class is null.", e.getMessage());
			assertNull(rp);
		}
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(RequiredParameterTest.class);
	}
}
