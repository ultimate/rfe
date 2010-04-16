package team.overfed.util.exceptions;

/**
 * Class implementing a required parameter.<br>
 * Instances of this class have to be passed to a NotAllRequiredParametersSetException to obtain the
 * information about which parameters have not been set.<br>
 * <br>
 * 
 * @author ultimate
 */
@SuppressWarnings("unchecked")
public class RequiredParameter
{
	private String name;
	private Class parameterClass;

	/**
	 * Construct a new RequiredParemeter with a given parameter name and the class of which the
	 * parameter is an instance.<br>
	 * <br>
	 * 
	 * @param name
	 * @param parameterClass
	 */
	public RequiredParameter(String name, Class parameterClass)
	{
		super();
		if(name == null)
			throw new IllegalArgumentException("The parameter name is null.");
		if(name.equals(""))
			throw new IllegalArgumentException("The parameter name is empty.");
		if(parameterClass == null)
			throw new IllegalArgumentException("The parameter class is null.");
		this.name = name;
		this.parameterClass = parameterClass;
	}

	/**
	 * Get the name of this RequiredParemeter
	 * 
	 * @return - the name as a <code>String</code>
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get the class of which this RequiredParemeter is an instance.<br>
	 * 
	 * @return - the class
	 */
	public Class getParameterClass()
	{
		return parameterClass;
	}

	@Override
	public String toString()
	{
		return "RequiredParameter: \"" + name + "\" - " + parameterClass.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof RequiredParameter))
			return false;
		RequiredParameter rp = (RequiredParameter) o;
		if(!this.parameterClass.equals(rp.parameterClass))
			return false;
		if(!this.name.equals(rp.name))
			return false;
		return true;
	}
}
