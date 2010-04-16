package team.overfed.util.exceptions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on NotAllRequiredParametersSetException.
 * 
 * @author ultimate
 */
public class NotAllRequiredParametersSetExceptionTest extends OAELoggerTestCase
{

	@SuppressWarnings("unchecked")
	@Test
	public void testNotAllRequiredParametersSetException()
	{
		int limit = 10;
		Class problemClass = Integer.class;
		StringBuilder expectedMessage = new StringBuilder(
				"There are not all required parameters set for class java.lang.Integer.\nThere are %I parameters missing:\n");
		ArrayList<RequiredParameter> rps = new ArrayList<RequiredParameter>();

		NotAllRequiredParametersSetException rpe;

		logger.debug("testing constructor no RequiredParameter");
		rpe = new NotAllRequiredParametersSetException(problemClass);
		assertNotNull(rpe);
		assertEquals(expectedMessage.toString().replace("%I", "0"), rpe.getMessage());
		assertNotNull(rpe.getMissingParameters());
		assertEquals(0, rpe.getMissingParameters().size());

		String name;
		Class parameterClass = Double.class;
		RequiredParameter rp;
		for(int i = 0; i < limit; i++)
		{
			logger.debug("testing adding of RequiredParameter");
			name = "param " + (i + 1);
			rp = new RequiredParameter(name, parameterClass);
			rpe.addMissingParameter(rp);
			rps.add(rp);
			assertEquals(i + 1, rpe.getMissingParameters().size());
			assertEquals(rp, rpe.getMissingParameters().get(i));
			expectedMessage.append(rp.toString() + "\n");
			assertEquals(expectedMessage.toString().replace("%I", "" + (i + 1)), rpe.getMessage());
		}

		logger.debug("testing constructor RequiredParameter list");
		rpe = new NotAllRequiredParametersSetException(problemClass, rps);
		assertNotNull(rpe);
		assertEquals(expectedMessage.toString().replace("%I", "" + limit), rpe.getMessage());
		assertNotNull(rpe.getMissingParameters());
		assertEquals(limit, rpe.getMissingParameters().size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NotAllRequiredParametersSetExceptionTest.class);
	}
}
