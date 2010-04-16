package team.overfed.util.exceptions;

/**
 * Exception to be thrown, if an instance of a class shall be modified although it is not
 * modifiable.<br>
 * <br>
 * For example an instance is created by an empty constructor. After that all fields are set by
 * set...()-Methods. At the end the method setNotModifiable() is called to prevent the instance from
 * being change. If now any set...()-Method is called a NotModifiableException can be thrown to tell
 * that this instance is not allowed to be changed.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NotModifiableException extends Exception
{
	private static final long serialVersionUID = 1L;

	private Object o;

	/**
	 * Construct a new NotModifiableException for a given Object with a specified message<br>
	 * <br>
	 * 
	 * @param o -
	 *            The object that is not modifiable
	 * @param message -
	 *            The message to include into this classes default message
	 */
	public NotModifiableException(Object o, String message)
	{
		super("The given Object is not modifiable:\n" + message + "\n  Object is: " + (o == null ? "null" : o.toString()));
		this.o = o;
	}

	/**
	 * Construct a new NotModifiableException for a given Object <br>
	 * 
	 * @param o -
	 *            The object that is not modifiable
	 */
	public NotModifiableException(Object o)
	{
		super("The given Object is not modifiable." + "\n  Object is: " + (o == null ? "null" : o.toString()));
		this.o = o;
	}

	/**
	 * Get the object stored in this NotModifiableException.
	 * 
	 * @return - the object
	 */
	public Object getObject()
	{
		return this.o;
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof NotModifiableException))
			return false;
		NotModifiableException e = (NotModifiableException) o;
		if(!this.o.equals(e.o))
			return false;
		if(!this.getMessage().equals(e.getMessage()))
			return false;
		return true;
	}
}
