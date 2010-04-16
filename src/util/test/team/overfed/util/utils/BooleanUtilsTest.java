package team.overfed.util.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Class testing BooleanUtils.<br>
 * <br>
 * 
 * @author ultimate
 */
public class BooleanUtilsTest extends OAELoggerTestCase
{

	@Test
	public void testAnd2()
	{
		logger.debug("testing AND with 2 args");

		logger.debug("testing AND with 2 args (valid)");
		assertEquals(true, BooleanUtils.and(true, true));
		assertEquals(false, BooleanUtils.and(true, false));
		assertEquals(false, BooleanUtils.and(false, true));
		assertEquals(false, BooleanUtils.and(false, false));

		logger.debug("testing AND with 2 args (invalid)");
		try
		{
			BooleanUtils.and(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.and(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	@Test
	public void testAndArray()
	{
		logger.debug("testing AND with 3 args");

		logger.debug("testing AND with 3 args (valid)");
		assertEquals(true, BooleanUtils.and(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { true, false, true }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { false, true, true }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { false, true, false }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtils.and(new Boolean[] { false, false, false }));

		logger.debug("testing AND with 3 args (invalid)");
		try
		{
			BooleanUtils.and(null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.and(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtils.and(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	@Test
	public void testOr2()
	{
		logger.debug("testing OR with 2 args");

		logger.debug("testing OR with 2 args (valid)");
		assertEquals(true, BooleanUtils.or(true, true));
		assertEquals(true, BooleanUtils.or(true, false));
		assertEquals(true, BooleanUtils.or(false, true));
		assertEquals(false, BooleanUtils.or(false, false));

		logger.debug("testing OR with 2 args (invalid)");
		try
		{
			BooleanUtils.or(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.or(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	@Test
	public void testOrArray()
	{
		logger.debug("testing OR with 3 args");

		logger.debug("testing OR with 3 args (valid)");
		assertEquals(true, BooleanUtils.or(new Boolean[] { true, true, true }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { true, true, false }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { true, false, true }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { true, false, false }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { false, true, true }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { false, true, false }));
		assertEquals(true, BooleanUtils.or(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtils.or(new Boolean[] { false, false, false }));

		logger.debug("testing OR with 3 args (invalid)");
		try
		{
			BooleanUtils.or(null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.or(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtils.or(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	@Test
	public void testXor2()
	{
		logger.debug("testing XOR with 2 args");

		logger.debug("testing XOR with 2 args (valid)");
		assertEquals(false, BooleanUtils.xor(true, true));
		assertEquals(true, BooleanUtils.xor(true, false));
		assertEquals(true, BooleanUtils.xor(false, true));
		assertEquals(false, BooleanUtils.xor(false, false));

		logger.debug("testing XOR with 2 args (invalid)");
		try
		{
			BooleanUtils.xor(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.xor(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	@Test
	public void testXorArray()
	{
		logger.debug("testing XOR with 3 args");

		logger.debug("testing XOR with 3 args (valid)");
		assertEquals(false, BooleanUtils.xor(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtils.xor(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtils.xor(new Boolean[] { true, false, true }));
		assertEquals(true, BooleanUtils.xor(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtils.xor(new Boolean[] { false, true, true }));
		assertEquals(true, BooleanUtils.xor(new Boolean[] { false, true, false }));
		assertEquals(true, BooleanUtils.xor(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtils.xor(new Boolean[] { false, false, false }));

		logger.debug("testing XOR with 3 args (invalid)");
		try
		{
			BooleanUtils.xor(null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.xor(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtils.xor(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	@Test
	public void testNor2()
	{
		logger.debug("testing OR with 2 args");

		logger.debug("testing NOR with 2 args (valid)");
		assertEquals(false, BooleanUtils.nor(true, true));
		assertEquals(false, BooleanUtils.nor(true, false));
		assertEquals(false, BooleanUtils.nor(false, true));
		assertEquals(true, BooleanUtils.nor(false, false));

		logger.debug("testing NOR with 2 args (invalid)");
		try
		{
			BooleanUtils.nor(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.nor(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	@Test
	public void testNorArray()
	{
		logger.debug("testing NOR with 3 args");

		logger.debug("testing NOR with 3 args (valid)");
		assertEquals(false, BooleanUtils.nor(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { true, false, true }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { false, true, true }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { false, true, false }));
		assertEquals(false, BooleanUtils.nor(new Boolean[] { false, false, true }));
		assertEquals(true, BooleanUtils.nor(new Boolean[] { false, false, false }));

		logger.debug("testing NOR with 3 args (invalid)");
		try
		{
			BooleanUtils.nor(null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtils.nor(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtils.nor(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	@Test
	public void testLogical2()
	{
		logger.debug("testing LOGICAL with 2 args");

		logger.debug("testing LOGICAL with 2 args (valid only)");
		Boolean[][] b = new Boolean[4][2];
		b[0] = new Boolean[] { true, true };
		b[1] = new Boolean[] { true, false };
		b[2] = new Boolean[] { false, true };
		b[3] = new Boolean[] { false, false };

		for(int i = 0; i < b.length; i++)
		{
			assertEquals(BooleanUtils.and(b[i][0], b[i][1]), BooleanUtils.logical(BooleanUtils.LogicalMode.AND, b[i][0], b[i][1]));
			assertEquals(BooleanUtils.or(b[i][0], b[i][1]), BooleanUtils.logical(BooleanUtils.LogicalMode.OR, b[i][0], b[i][1]));
			assertEquals(BooleanUtils.xor(b[i][0], b[i][1]), BooleanUtils.logical(BooleanUtils.LogicalMode.XOR, b[i][0], b[i][1]));
			assertEquals(BooleanUtils.nor(b[i][0], b[i][1]), BooleanUtils.logical(BooleanUtils.LogicalMode.NOR, b[i][0], b[i][1]));
		}
	}

	@Test
	public void testLogicalArray()
	{
		logger.debug("testing LOGICAL with 3 args");

		logger.debug("testing LOGICAL with 3 args (valid only)");
		Boolean[][] b = new Boolean[8][];
		b[0] = new Boolean[] { true, true, true };
		b[1] = new Boolean[] { true, true, false };
		b[2] = new Boolean[] { true, false, true };
		b[3] = new Boolean[] { true, false, false };
		b[4] = new Boolean[] { false, true, true };
		b[5] = new Boolean[] { false, true, false };
		b[6] = new Boolean[] { false, false, true };
		b[7] = new Boolean[] { false, false, false };

		for(int i = 0; i < b.length; i++)
		{
			assertEquals(BooleanUtils.and(b[i]), BooleanUtils.logical(BooleanUtils.LogicalMode.AND, b[i]));
			assertEquals(BooleanUtils.or(b[i]), BooleanUtils.logical(BooleanUtils.LogicalMode.OR, b[i]));
			assertEquals(BooleanUtils.xor(b[i]), BooleanUtils.logical(BooleanUtils.LogicalMode.XOR, b[i]));
			assertEquals(BooleanUtils.nor(b[i]), BooleanUtils.logical(BooleanUtils.LogicalMode.NOR, b[i]));
		}
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(BooleanUtilsTest.class);
	}
}
