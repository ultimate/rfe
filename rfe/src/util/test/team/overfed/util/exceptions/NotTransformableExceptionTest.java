package team.overfed.util.exceptions;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.oae.resources.ResourceTransformer;
import team.overfed.oae.resources.transformers.BooleanResourceTransformer;

/**
 * Test valid and invalid operations on NotTransformableException.
 * 
 * @author ultimate
 */
public class NotTransformableExceptionTest extends OAELoggerTestCase
{

	@Test
	public void testConstructors()
	{
		logger.debug("testing constructors (2 cases)");
		NotTransformableException e;

		ResourceTransformer transformer = new BooleanResourceTransformer();
		String message = "any message";
		Object object = new Object();

		e = new NotTransformableException(transformer, object);
		assertEquals(transformer.getClass().getName() + " could not transform " + object.getClass().getName() + ": " + object.toString(), e
				.getMessage());

		e = new NotTransformableException(transformer, object, message);
		assertEquals(transformer.getClass().getName() + " could not transform " + object.getClass().getName() + ": " + object.toString() + " - "
				+ message, e.getMessage());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NotTransformableExceptionTest.class);
	}
}
