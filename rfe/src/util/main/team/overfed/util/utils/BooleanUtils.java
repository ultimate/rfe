package team.overfed.util.utils;

/**
 * Class implementing useful operations on Booleans.<br>
 * <br>
 * 
 * @author ultimate
 */
public abstract class BooleanUtils
{

	/**
	 * An enumeration representing the supported logical operations on boolean in this class.<br>
	 * Operations are typical logical concatenations:<br>
	 * AND - both must agree<br>
	 * OR - at least one must agree<br>
	 * XOR - exactly one must agree<br>
	 * NOR - nothing must agree<br>
	 * <br>
	 * 
	 * @author ultimate
	 */
	public static enum LogicalMode
	{
		AND, OR, XOR, NOR
	}

	/**
	 * Concatenate two booleans with the operation "AND"<br>
	 * <br>
	 * 
	 * @param b1 -
	 *            the first boolean
	 * @param b2 -
	 *            the second boolean
	 * @return - the result (b1 && b2)
	 */
	public static Boolean and(Boolean b1, Boolean b2)
	{
		if(b1 == null)
			throw new IllegalArgumentException("The given Boolean b1 is null.");
		if(b2 == null)
			throw new IllegalArgumentException("The given Boolean b2 is null.");
		return b1 && b2;
	}

	/**
	 * Concatenate more than two booleans with the operation "AND"<br>
	 * <br>
	 * 
	 * @param b -
	 *            the array of booleans
	 * @return - the result (b1 && b2 && b3 && ... && bn)
	 */
	public static Boolean and(Boolean[] b)
	{
		if(b == null)
			throw new IllegalArgumentException("The given Array of Boolean b is null.");
		if(b.length == 0)
			throw new IllegalArgumentException("The given Array of Boolean contains no elements.");
		for(int i = 0; i < b.length; i++)
		{
			if(b[i] == null)
				throw new IllegalArgumentException("The given Array of Boolean contains a null element: index " + i + ".");
		}
		Boolean ret = true;
		for(Boolean bi : b)
		{
			ret = ret && bi;
			if(!ret)
				return false;
		}
		return ret;
	}

	/**
	 * Concatenate two booleans with the operation "OR"<br>
	 * <br>
	 * 
	 * @param b1 -
	 *            the first boolean
	 * @param b2 -
	 *            the second boolean
	 * @return - the result (b1 || b2)
	 */
	public static Boolean or(Boolean b1, Boolean b2)
	{
		if(b1 == null)
			throw new IllegalArgumentException("The given Boolean b1 is null.");
		if(b2 == null)
			throw new IllegalArgumentException("The given Boolean b2 is null.");
		return b1 || b2;
	}

	/**
	 * Concatenate more than two booleans with the operation "OR"<br>
	 * <br>
	 * 
	 * @param b -
	 *            the array of booleans
	 * @return - the result (b1 || b2 || b3 || ... || bn)
	 */
	public static Boolean or(Boolean[] b)
	{
		if(b == null)
			throw new IllegalArgumentException("The given Array of Boolean b is null.");
		if(b.length == 0)
			throw new IllegalArgumentException("The given Array of Boolean contains no elements.");
		for(int i = 0; i < b.length; i++)
		{
			if(b[i] == null)
				throw new IllegalArgumentException("The given Array of Boolean contains a null element: index " + i + ".");
		}
		for(Boolean bi : b)
		{
			if(bi)
				return true;
		}
		return false;
	}

	/**
	 * Concatenate two booleans with the operation "XOR"<br>
	 * <br>
	 * 
	 * @param b1 -
	 *            the first boolean
	 * @param b2 -
	 *            the second boolean
	 * @return - the result (b1 != b2)
	 */
	public static Boolean xor(Boolean b1, Boolean b2)
	{
		if(b1 == null)
			throw new IllegalArgumentException("The given Boolean b1 is null.");
		if(b2 == null)
			throw new IllegalArgumentException("The given Boolean b2 is null.");
		return (b1 != b2);
	}

	/**
	 * Concatenate more than two booleans with the operation "XOR"<br>
	 * <br>
	 * 
	 * @param b -
	 *            the array of booleans
	 * @return - the result (exactly one boolean is true)
	 */
	public static Boolean xor(Boolean[] b)
	{
		if(b == null)
			throw new IllegalArgumentException("The given Array of Boolean b is null.");
		if(b.length == 0)
			throw new IllegalArgumentException("The given Array of Boolean contains no elements.");
		for(int i = 0; i < b.length; i++)
		{
			if(b[i] == null)
				throw new IllegalArgumentException("The given Array of Boolean contains a null element: index " + i + ".");
		}
		int numberOfTrues = 0;
		for(Boolean bi : b)
		{
			if(bi)
				numberOfTrues++;
		}
		return (numberOfTrues == 1);
	}

	/**
	 * Concatenate two booleans with the operation "NOR"<br>
	 * <br>
	 * 
	 * @param b1 -
	 *            the first boolean
	 * @param b2 -
	 *            the second boolean
	 * @return - the result (!b1 && !b2)
	 */
	public static Boolean nor(Boolean b1, Boolean b2)
	{
		if(b1 == null)
			throw new IllegalArgumentException("The given Boolean b1 is null.");
		if(b2 == null)
			throw new IllegalArgumentException("The given Boolean b2 is null.");
		return (!b1 && !b2);
	}

	/**
	 * Concatenate more than two booleans with the operation "NOR"<br>
	 * <br>
	 * 
	 * @param b -
	 *            the array of booleans
	 * @return - the result (no boolean is true)
	 */
	public static Boolean nor(Boolean[] b)
	{
		if(b == null)
			throw new IllegalArgumentException("The given Array of Boolean b is null.");
		if(b.length == 0)
			throw new IllegalArgumentException("The given Array of Boolean contains no elements.");
		for(int i = 0; i < b.length; i++)
		{
			if(b[i] == null)
				throw new IllegalArgumentException("The given Array of Boolean contains a null element: index " + i + ".");
		}
		int numberOfTrues = 0;
		for(Boolean bi : b)
		{
			if(bi)
				numberOfTrues++;
		}
		return (numberOfTrues == 0);
	}

	/**
	 * Concatenate two booleans with the given operation.<br>
	 * <br>
	 * 
	 * @param b1 -
	 *            the first boolean
	 * @param b2 -
	 *            the second boolean
	 * @return - the result (b1 o b2)
	 */
	public static Boolean logical(LogicalMode logic, Boolean b1, Boolean b2)
	{
		if(b1 == null)
			throw new IllegalArgumentException("The given Boolean b1 is null.");
		if(b2 == null)
			throw new IllegalArgumentException("The given Boolean b2 is null.");

		if(logic == LogicalMode.AND)
			return and(b1, b2);
		else if(logic == LogicalMode.OR)
			return or(b1, b2);
		else if(logic == LogicalMode.XOR)
			return xor(b1, b2);
		else if(logic == LogicalMode.NOR)
			return nor(b1, b2);
		else
			throw new IllegalArgumentException("The given LogicalMode is not valid.");
	}

	/**
	 * Concatenate more than two booleans with the given operation.<br>
	 * <br>
	 * 
	 * @param b -
	 *            the array of booleans
	 * @return - the result
	 */
	public static Boolean logical(LogicalMode logic, Boolean[] b)
	{
		if(b == null)
			throw new IllegalArgumentException("The given Array of Boolean b is null.");
		if(b.length == 0)
			throw new IllegalArgumentException("The given Array of Boolean contains no elements.");
		for(int i = 0; i < b.length; i++)
		{
			if(b[i] == null)
				throw new IllegalArgumentException("The given Array of Boolean contains a null element: index " + i + ".");
		}
		if(logic == LogicalMode.AND)
			return and(b);
		else if(logic == LogicalMode.OR)
			return or(b);
		else if(logic == LogicalMode.XOR)
			return xor(b);
		else if(logic == LogicalMode.NOR)
			return nor(b);
		else
			throw new IllegalArgumentException("The given LogicalMode is not valid.");
	}
}
