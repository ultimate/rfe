package team.overfed.util.exceptions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on MultipleException.
 * 
 * @author ultimate
 */
public class MultipleExceptionTest extends OAELoggerTestCase
{

	@Test
	public void testMultipleException()
	{
		MultipleException me = null;
		ArrayList<Exception> ex = new ArrayList<Exception>();

		logger.debug("testing construction and message storing with 0 Exceptions");
		me = new MultipleException();
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(0, me.getNumberOfExceptions());
		assertEquals(null, me.getMessage());
		try
		{
			me.getException(0);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 0 is larger than or equals to the number of Exceptions 0 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(1);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 1 is larger than or equals to the number of Exceptions 0 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.addException(null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Exception is null.", e.getMessage());
		}
		me = null;

		logger.debug("testing construction and message storing with 1 Exception (List)");
		ex.add(new Exception("1"));
		me = new MultipleException(ex);
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(1, me.getNumberOfExceptions());
		assertEquals("1", me.getExceptions().get(0).getMessage());
		assertEquals("1", me.getException(0).getMessage());
		assertEquals(null, me.getMessage());
		try
		{
			me.getException(1);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 1 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(2);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 2 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}
		me = null;

		logger.debug("testing construction and message storing with 1 Exception (Exception)");
		me = new MultipleException(new Exception("2"));
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(1, me.getNumberOfExceptions());
		assertEquals("2", me.getExceptions().get(0).getMessage());
		assertEquals("2", me.getException(0).getMessage());
		assertEquals(null, me.getMessage());
		try
		{
			me.getException(1);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 1 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(2);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 2 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}
		me = null;

		logger.debug("testing construction and message storing with 0 Exceptions and message");
		me = new MultipleException("3");
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(0, me.getNumberOfExceptions());
		assertEquals("3", me.getMessage());
		try
		{
			me.getException(0);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 0 is larger than or equals to the number of Exceptions 0 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(1);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 1 is larger than or equals to the number of Exceptions 0 in this MultipleException.", e.getMessage());
		}
		me = null;

		logger.debug("testing construction and message storing with 2 Exceptions (List)");
		ex.add(new Exception("2"));
		me = new MultipleException("4", ex);
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(2, me.getNumberOfExceptions());
		assertEquals("1", me.getExceptions().get(0).getMessage());
		assertEquals("1", me.getException(0).getMessage());
		assertEquals("2", me.getExceptions().get(1).getMessage());
		assertEquals("2", me.getException(1).getMessage());
		assertEquals("4", me.getMessage());
		try
		{
			me.getException(2);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 2 is larger than or equals to the number of Exceptions 2 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(3);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 3 is larger than or equals to the number of Exceptions 2 in this MultipleException.", e.getMessage());
		}
		me = null;

		logger.debug("testing construction and message storing with 1 Exception and message");
		me = new MultipleException("5", new Exception("6"));
		assertNotNull(me);
		assertNotNull(me.getExceptions());
		assertEquals(1, me.getNumberOfExceptions());
		assertEquals("6", me.getExceptions().get(0).getMessage());
		assertEquals("6", me.getException(0).getMessage());
		assertEquals("5", me.getMessage());
		try
		{
			me.getException(1);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 1 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(2);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 2 is larger than or equals to the number of Exceptions 1 in this MultipleException.", e.getMessage());
		}

		logger.debug("testing adding Exception");
		me.addException(new Exception("added"));
		assertEquals(2, me.getNumberOfExceptions());
		assertEquals("added", me.getExceptions().get(1).getMessage());
		assertEquals("added", me.getException(1).getMessage());
		try
		{
			me.getException(2);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 2 is larger than or equals to the number of Exceptions 2 in this MultipleException.", e.getMessage());
		}
		try
		{
			me.getException(3);
			fail("Expected Exception not occurred.");
		}
		catch(IndexOutOfBoundsException e)
		{
			assertNotNull(e);
			assertEquals("The given index 3 is larger than or equals to the number of Exceptions 2 in this MultipleException.", e.getMessage());
		}
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(MultipleExceptionTest.class);
	}
}
