package team.overfed.util.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class implementing useful operations on Numbers.<br>
 * <br>
 * 
 * @author ultimate
 */
public abstract class NumberUtils
{

	/**
	 * Parse a given String to an Object extending Number.<br>
	 * This makes it easy to genericly parse Strings to Numbers if needed.<br>
	 * The given Class must extend java.lang.Number.<br>
	 * An instance of the given class will be returned.<br>
	 * <br>
	 * e.g.: parseStringProperty("4", Integer.class) will return in Integer with the value 4<br>
	 * <br>
	 * 
	 * @param propS -
	 *            the number to parse as a <code>String</code>
	 * @param numberClass -
	 *            the class to parse the number to. must extend <code>Number</code>
	 * @return - an instance of the class given representing the given <code>String</code> as a
	 *         <code>Number</code>
	 * @throws NumberFormatException -
	 *             if the NumberFormat is invalid
	 */
	@SuppressWarnings("unchecked")
	public static Number parseStringProperty(String propS, Class numberClass) throws NumberFormatException
	{
		if(numberClass.equals(Byte.class))
		{
			Byte b = Byte.parseByte(propS);
			return b;
		}
		else if(numberClass.equals(Short.class))
		{
			Short s = Short.parseShort(propS);
			return s;
		}
		else if(numberClass.equals(Integer.class))
		{
			Integer i = Integer.parseInt(propS);
			return i;
		}
		else if(numberClass.equals(Long.class))
		{
			Long l = Long.parseLong(propS);
			return l;
		}
		else if(numberClass.equals(Float.class))
		{
			Float f = Float.parseFloat(propS);
			return f;
		}
		else if(numberClass.equals(Double.class))
		{
			Double d = Double.parseDouble(propS);
			return d;
		}
		else if(numberClass.equals(AtomicInteger.class))
		{
			AtomicInteger ai = new AtomicInteger(Integer.parseInt(propS));
			return ai;
		}
		else if(numberClass.equals(AtomicLong.class))
		{
			AtomicLong al = new AtomicLong(Long.parseLong(propS));
			return al;
		}
		else if(numberClass.equals(BigInteger.class))
		{
			BigInteger bi = new BigInteger(propS);
			return bi;
		}
		else if(numberClass.equals(BigDecimal.class))
		{
			BigDecimal bd = new BigDecimal(propS);
			return bd;
		}
		else
			return null;
	}
}
