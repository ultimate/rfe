package team.overfed.util.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.xml.SAXParseErrorHandler;
import team.overfed.util.xml.SAXParseErrorHandler.ErrorLevel;

/**
 * Test valid and invalid operations on SAXParseErrorHandler.
 * 
 * @author ultimate
 */
public class SAXParseErrorHandlerTest extends OAELoggerTestCase
{

	@Test
	public void testErrorHandling()
	{
		logger.debug("testing SAXParseErrorHandler");

		SAXParseErrorHandler eh = new SAXParseErrorHandler();

		logger.debug("constructing 3 virtual Exceptions");
		SAXParseException e1 = new SAXParseException("message 1", new LocatorImpl());
		SAXParseException e2 = new SAXParseException("message 2", new LocatorImpl());
		SAXParseException e3 = new SAXParseException("message 3", new LocatorImpl());

		logger.debug("simulating error handling");
		try
		{
			eh.warning(e1);
			eh.error(e2);
			eh.error(e1);
			eh.fatalError(e3);
			fail("Expected Exception not occurred!");
		}
		catch(SAXException e)
		{
			assertNotNull(e);
			assertEquals(e3, e);
			logger.debug("expected exception occurred!");
		}

		ArrayList<SAXParseException> ex = eh.getSAXParseExceptions();
		ArrayList<ErrorLevel> is = eh.getSAXParseExceptionsLevels();

		logger.debug("verifying number of Exceptions");
		assertEquals(4, ex.size());
		assertEquals(4, is.size());

		int[] expI, realI;

		logger.debug("verifying Exception #1");
		assertEquals(e1, ex.get(0));
		assertEquals(SAXParseErrorHandler.ErrorLevel.WARNING, is.get(0));
		expI = new int[] { 0, 2 };
		realI = eh.getIndexForSAXParseException(e1);
		assertEquals(expI.length, realI.length);
		for(int i = 0; i < expI.length; i++)
			assertEquals(expI[i], realI[i]);

		logger.debug("verifying Exception #2");
		assertEquals(e2, ex.get(1));
		assertEquals(SAXParseErrorHandler.ErrorLevel.ERROR, is.get(1));
		expI = new int[] { 1 };
		realI = eh.getIndexForSAXParseException(e2);
		assertEquals(expI.length, realI.length);
		for(int i = 0; i < expI.length; i++)
			assertEquals(expI[i], realI[i]);

		logger.debug("verifying Exception #3");
		assertEquals(e1, ex.get(2));
		assertEquals(SAXParseErrorHandler.ErrorLevel.ERROR, is.get(2));
		expI = new int[] { 0, 2 };
		realI = eh.getIndexForSAXParseException(e1);
		assertEquals(expI.length, realI.length);
		for(int i = 0; i < expI.length; i++)
			assertEquals(expI[i], realI[i]);

		logger.debug("verifying Exception #4");
		assertEquals(e3, ex.get(3));
		assertEquals(SAXParseErrorHandler.ErrorLevel.FATAL_ERROR, is.get(3));
		expI = new int[] { 3 };
		realI = eh.getIndexForSAXParseException(e3);
		assertEquals(expI.length, realI.length);
		for(int i = 0; i < expI.length; i++)
			assertEquals(expI[i], realI[i]);

		logger.debug("testing SAXParseErrorHandler successfully completed");
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(SAXParseErrorHandlerTest.class);
	}
}
