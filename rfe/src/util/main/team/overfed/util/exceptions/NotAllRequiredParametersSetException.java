package team.overfed.util.exceptions;

import java.util.ArrayList;

/**
 * Class implementing an Exception intended to be thrown if an operation on a class has been
 * performed although not yet all required parameters have been set by extra set...()-Methods.<br>
 * <br>
 * 
 * @author ultimate
 */
@SuppressWarnings("unchecked")
public class NotAllRequiredParametersSetException extends Exception
{
	private static final long serialVersionUID = 1L;
	private ArrayList<RequiredParameter> missingParameters;
	private Class problemClass;
	private String message;

	private static final String defMessage = "There are not all required parameters set for %C.\nThere are %N parameters missing:\n";

	/**
	 * Construct a new NotAllRequiredParametersSetException with the given class where the problem
	 * occurred and a list containing the RequiredParameters.<br>
	 * <br>
	 * 
	 * @param problemClass -
	 *            the class causing the problem
	 * @param missingParameters -
	 *            the missing RequiredParameter-List
	 */
	public NotAllRequiredParametersSetException(Class problemClass, ArrayList<RequiredParameter> missingParameters)
	{
		if(problemClass == null)
			throw new IllegalArgumentException("The given problemClass is null.");
		if(missingParameters == null)
			throw new IllegalArgumentException("The given ArrayList of missingParameters is null.");
		this.problemClass = problemClass;
		this.missingParameters = missingParameters;
		initMessage();
	}

	/**
	 * Construct a new NotAllRequiredParametersSetException with the given class where the problem
	 * occurred.<br>
	 * The list containing the RequiredParameters will be inited empty.<br>
	 * <br>
	 * 
	 * @param problemClass -
	 *            the class causing the problem
	 */
	public NotAllRequiredParametersSetException(Class problemClass)
	{
		if(problemClass == null)
			throw new IllegalArgumentException("The given problemClass is null.");
		this.problemClass = problemClass;
		this.missingParameters = new ArrayList<RequiredParameter>();
		initMessage();
	}

	/**
	 * Initiate the message of this class with all the RequiredParameters stored in the list.
	 */
	private void initMessage()
	{
		StringBuilder params = new StringBuilder();
		for(RequiredParameter rp : missingParameters)
		{
			params.append(rp.toString() + "\n");
		}
		this.message = defMessage.replace("%C", problemClass.toString()).replace("%N", "" + missingParameters.size()) + params.toString();
	}

	/**
	 * Add an additional missing, but RequiredParameter to the list contained in this instance.<br>
	 * <br>
	 * 
	 * @param missingParameter
	 */
	public void addMissingParameter(RequiredParameter missingParameter)
	{
		if(missingParameter == null)
			throw new IllegalArgumentException("The given missingParameter is null.");
		this.missingParameters.add(missingParameter);
		this.initMessage();
	}

	/**
	 * Get the list contained in this instance.<br>
	 * <br>
	 * 
	 * @return - the list of RequiredParameters
	 */
	public ArrayList<RequiredParameter> getMissingParameters()
	{
		return missingParameters;
	}

	@Override
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * Get the class in which the problem occurred.<br>
	 * <br>
	 * 
	 * @return - the problem class
	 */
	public Class getProblemClass()
	{
		return this.problemClass;
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof NotAllRequiredParametersSetException))
			return false;
		NotAllRequiredParametersSetException e = (NotAllRequiredParametersSetException) o;
		if(!this.missingParameters.equals(e.missingParameters))
			return false;
		if(!this.message.equals(e.message))
			return false;
		if(!this.problemClass.equals(e.problemClass))
			return false;
		if(!this.getMessage().equals(e.getMessage()))
			return false;
		return true;
	}
}
