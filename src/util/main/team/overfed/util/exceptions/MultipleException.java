package team.overfed.util.exceptions;

import java.util.ArrayList;

/**
 * Class implementing an special type of Exception supporting the opportunity to store multiple
 * Exceptions into one single Exception. This Exception saves all added Exceptions and is able to
 * return each one of them for later usage or capturing.<br>
 * <br>
 * Additional to that it is possible to store a message into this Exception containing necessary,
 * additional informations as commonly used in the class Exception.<br>
 * <br>
 * The Exceptions are stored in a <code>java.util.ArrayList&lt;Exception&gt;</code><br>
 * 
 * @author ultimate
 */
public class MultipleException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList containing the stored Exceptions.
	 */
	protected ArrayList<Exception> ex;

	/**
	 * Construct a new MultipleException with no Exceptions and no message. Initiates the ArrayList
	 * with no Elements.
	 */
	public MultipleException()
	{
		super();
		this.ex = new ArrayList<Exception>();
	}

	/**
	 * Construct a new MultipleException with no Exceptions but a message. Initiates the ArrayList
	 * with no Elements.
	 */
	public MultipleException(String message)
	{
		super(message);
		this.ex = new ArrayList<Exception>();
	}

	/**
	 * Construct a new MultipleException with an Exception but no message. Initiates the ArrayList
	 * with this Exception.
	 */
	public MultipleException(Exception e)
	{
		super();
		this.ex = new ArrayList<Exception>();
		this.ex.add(e);
	}

	/**
	 * Construct a new MultipleException with multiple Exceptions but no message. Initiates the
	 * ArrayList with the given ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public MultipleException(ArrayList<Exception> ex)
	{
		super();
		if(ex == null)
			throw new NullPointerException("ArrayList of Exceptions is null.");
		this.ex = (ArrayList<Exception>) ex.clone();
	}

	/**
	 * Construct a new MultipleException with an Exception and a message. Initiates the ArrayList
	 * with this Exception.
	 */
	public MultipleException(String message, Exception e)
	{
		super(message);
		this.ex = new ArrayList<Exception>();
		this.ex.add(e);
	}

	/**
	 * Construct a new MultipleException with multiple Exceptions and a message. Initiates the
	 * ArrayList with the given ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public MultipleException(String message, ArrayList<Exception> ex)
	{
		super(message);
		if(ex == null)
			throw new NullPointerException("ArrayList of Exceptions is null.");
		this.ex = (ArrayList<Exception>) ex.clone();
	}

	/**
	 * Get the ArrayList containing the stored Exceptions.
	 * 
	 * @return the Exceptions as an <code>java.util.ArrayList&lt;Exception&gt;</code>
	 */
	public ArrayList<Exception> getExceptions()
	{
		return this.ex;
	}

	/**
	 * Get the Exception stored in the ArrayList at the given index.
	 * 
	 * @param index -
	 *            the index as an <code>int</code>
	 * @return the Exception for this index
	 */
	public Exception getException(int index)
	{
		if(index >= this.ex.size())
			throw new IndexOutOfBoundsException("The given index " + index + " is larger than or equals to the number of Exceptions "
					+ this.ex.size() + " in this MultipleException.");
		return this.ex.get(index);
	}

	/**
	 * Get the number of Exceptions stored in the ArrayList
	 * 
	 * @return the number of Exception as an <code>int</code>
	 */
	public int getNumberOfExceptions()
	{
		return this.ex.size();
	}

	/**
	 * Store an Exception into the ArrayList contained in this MultipleException.
	 * 
	 * @param e -
	 *            the Exception to store as an <code>Exception</code>
	 */
	public void addException(Exception e)
	{
		if(e == null)
			throw new IllegalArgumentException("The given Exception is null.");
		this.ex.add(e);
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof MultipleException))
			return false;
		MultipleException me = (MultipleException) o;
		if(!this.ex.equals(me.ex))
			return false;
		if(!this.getMessage().equals(me.getMessage()))
			return false;
		return true;
	}
}
