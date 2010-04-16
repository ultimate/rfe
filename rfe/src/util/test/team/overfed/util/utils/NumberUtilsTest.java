package team.overfed.util.utils;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Class testing NumberUtils.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NumberUtilsTest extends OAELoggerTestCase
{

	@Test
	public void testParseStringProperty()
	{
		logger.debug("testing parsing of Strings");

		logger.debug("testing with result type Byte");
		assertTrue(NumberUtils.parseStringProperty("4", Byte.class) instanceof Byte);
		assertEquals((Byte) Byte.parseByte("4"), (Byte) NumberUtils.parseStringProperty("4", Byte.class));

		logger.debug("testing with result type Short");
		assertTrue(NumberUtils.parseStringProperty("4", Short.class) instanceof Short);
		assertEquals((Short) Short.parseShort("4"), (Short) NumberUtils.parseStringProperty("4", Short.class));

		logger.debug("testing with result type Integer");
		assertTrue(NumberUtils.parseStringProperty("4", Integer.class) instanceof Integer);
		assertEquals((Integer) Integer.parseInt("4"), (Integer) NumberUtils.parseStringProperty("4", Integer.class));

		logger.debug("testing with result type Long");
		assertTrue(NumberUtils.parseStringProperty("4", Long.class) instanceof Long);
		assertEquals((Long) Long.parseLong("4"), (Long) NumberUtils.parseStringProperty("4", Long.class));

		logger.debug("testing with result type Float");
		assertTrue(NumberUtils.parseStringProperty("4.4", Float.class) instanceof Float);
		assertEquals((Float) Float.parseFloat("4.4"), (Float) NumberUtils.parseStringProperty("4.4", Float.class));

		logger.debug("testing with result type Double");
		assertTrue(NumberUtils.parseStringProperty("4.4", Double.class) instanceof Double);
		assertEquals((Double) Double.parseDouble("4.4"), (Double) NumberUtils.parseStringProperty("4.4", Double.class));

		logger.debug("testing with result type AtomicInteger");
		assertTrue(NumberUtils.parseStringProperty("4", AtomicInteger.class) instanceof AtomicInteger);
		assertEquals(new AtomicInteger(Integer.parseInt("4")), (AtomicInteger) NumberUtils.parseStringProperty("4", AtomicInteger.class));

		logger.debug("testing with result type AtomicLong");
		assertTrue(NumberUtils.parseStringProperty("4", AtomicLong.class) instanceof AtomicLong);
		assertEquals(new AtomicLong(Long.parseLong("4")), (AtomicLong) NumberUtils.parseStringProperty("4", AtomicLong.class));

		logger.debug("testing with result type BigInteger");
		assertTrue(NumberUtils.parseStringProperty("4", BigInteger.class) instanceof BigInteger);
		assertEquals(new BigInteger("4"), (BigInteger) NumberUtils.parseStringProperty("4", BigInteger.class));

		logger.debug("testing with result type BigDecimal");
		assertTrue(NumberUtils.parseStringProperty("4.4", BigDecimal.class) instanceof BigDecimal);
		assertEquals(new BigDecimal("4.4"), (BigDecimal) NumberUtils.parseStringProperty("4.4", BigDecimal.class));
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NumberUtilsTest.class);
	}
}
