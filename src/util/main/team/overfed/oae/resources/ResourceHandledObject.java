package team.overfed.oae.resources;

import java.lang.reflect.Method;

import team.overfed.oae.logging.OAELogger;

/**
 * This class offers the possibility of instant Locale switching.<br>
 * <br>
 * Supervised by a ResourceHandler an instance of this class is always updated, whenever the Locale
 * in the ResourceHandler is changed. This makes it possible to update the object stored in this
 * instance by calling a specified method.<br>
 * <br>
 * This class works with Reflections and invokes the specified method with the given String always
 * when updateString() is called.<br>
 * 
 * @author ultimate
 */
public class ResourceHandledObject implements Cloneable
{
	private OAELogger logger = OAELogger.getOAELogger(getClass());

	private Object object;
	private String methodName;
	private String keyText;
	private ResourceHandler rh;
	private Method method;
	private Class<?> argumentClass;
	private ResourceTransformer transformer;

	/**
	 * Construct a new ResourceHandledObject with all necessary parameters.<br>
	 * The constructed instance will not be changeable after being constructed. The includes the
	 * Object, the method and the keyText.<br>
	 * The ResourceHandler can be changed anyway when ever it is needed.<br>
	 * If the ResourceHandler is null the keyText itself will be set as the argument.<br>
	 * 
	 * @param o -
	 *            the Object to invoke the method at
	 * @param method -
	 *            the name of the method to invoke
	 * @param keyText -
	 *            the keyText of the String to add as an argument to the method
	 * @param rh -
	 *            the ResourceHandler to get the message from
	 * @throws IllegalArgumentException
	 *             if one parameter (except the ResourceHandler) is null
	 */
	public ResourceHandledObject(Object object, String methodName, Class<?> argumentClass, String keyText, ResourceHandler rh)
	{
		this.init(object, methodName, argumentClass, keyText, rh, null);
	}

	/**
	 * Construct a new ResourceHandledObject with all necessary parameters.<br>
	 * The constructed instance will not be changeable after being constructed. The includes the
	 * Object, the method and the keyText.<br>
	 * The ResourceHandler can be changed anyway when ever it is needed.<br>
	 * This constructor takes an additional ResourceTransformer to transform Resources if wanted.<br>
	 * If the ResourceHandler is null the keyText itself will be set as the argument.<br>
	 * 
	 * @param o -
	 *            the Object to invoke the method at
	 * @param method -
	 *            the name of the method to invoke
	 * @param keyText -
	 *            the keyText of the String to add as an argument to the method
	 * @param rh -
	 *            the ResourceHandler to get the message from
	 * @param transformer -
	 *            the ResourceTransformer to transform the Resource with
	 * @throws IllegalArgumentException
	 *             if one parameter (except the ResourceHandler) is null
	 */
	public ResourceHandledObject(Object object, String methodName, Class<?> argumentClass, String keyText, ResourceHandler rh,
			ResourceTransformer transformer)
	{
		this.init(object, methodName, argumentClass, keyText, rh, transformer);
	}

	/**
	 * Initialize an instance of this class with all necessary parameters.<br>
	 * This method also tests the invocation of the given method to grant that the usage will be
	 * possible when needed.<br>
	 * If the ResourceHandler is null the keyText itself will be set as the argument.<br>
	 * 
	 * @param o -
	 *            the Object to invoke the method at
	 * @param method -
	 *            the name of the method to invoke
	 * @param argumentClass -
	 *            the required class of the argument used with the method
	 * @param keyText -
	 *            the keyText of the String to add as an argument to the method
	 * @param rh -
	 *            the ResourceHandler to get the message from
	 * @param transformer -
	 *            the ResourceTransformer to transform the Resource with
	 * @throws IllegalArgumentException
	 *             if one parameter (except the ResourceHandler) is null
	 */
	private void init(Object object, String methodName, Class<?> argumentClass, String keyText, ResourceHandler rh, ResourceTransformer transformer)
	{
		if(object == null)
			throw new IllegalArgumentException("The given object is null");
		if(methodName == null || methodName.equals(""))
			throw new IllegalArgumentException("The given method name is null or empty.");
		if(argumentClass == null)
			throw new IllegalArgumentException("The given argument class is null.");
		if(keyText == null)
			throw new IllegalArgumentException("The given key text is null");
		this.object = object;
		this.methodName = methodName;
		this.argumentClass = argumentClass;
		this.keyText = keyText;
		this.rh = rh;
		this.transformer = transformer;

		Class<?> clazz = object.getClass();
		try
		{
			this.method = clazz.getMethod(this.methodName, argumentClass);
			if(this.rh == null)
				this.method.invoke(this.object, keyText);
			else if(this.transformer == null)
				this.method.invoke(this.object, this.rh.getObject(keyText));
			else
				this.method.invoke(this.object, this.rh.getObject(keyText, transformer));
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("The given method is invalid for the Object o.", e);
		}
	}

	/**
	 * Set the ResourceHandler used in this instance to get the message from.<br>
	 * If the ResourceHandler is null the keyText itself will be set as the argument.
	 * 
	 * @param rh -
	 *            the ResourceHandler to set
	 */
	public void setResourceHandler(ResourceHandler rh)
	{
		this.rh = rh;
		this.updateString();
	}

	/**
	 * Notify this Object to invoke the stored method to update the String mapped by the stored
	 * keyText.<br>
	 */
	public void updateString()
	{
		try
		{
			if(this.rh == null)
				this.method.invoke(this.object, keyText);
			else if(this.transformer == null)
				this.method.invoke(this.object, this.rh.getObject(keyText));
			else
				this.method.invoke(this.object, this.rh.getObject(keyText, transformer));
		}
		catch(Exception e)
		{
			logger.error("Should never occur...");
			logger.error(e);
		}
	}

	/**
	 * Get the Object stored in this ResourceHandledObject.
	 * 
	 * @return the Object
	 */
	public Object getObject()
	{
		return this.object;
	}

	/**
	 * Get the Method stored in this ResourceHandledObject.
	 * 
	 * @return the Method
	 */
	public Method getMethod()
	{
		return this.method;
	}

	/**
	 * Get the name of the Method stored in this ResourceHandledObject.
	 * 
	 * @return the name of the Method
	 */
	public String getMethodName()
	{
		return this.methodName;
	}

	/**
	 * Get the class of the argument that will be passed to the invoked method.
	 * 
	 * @return the class of the argument used with the method
	 */
	public Class<?> getArgumentClass()
	{
		return argumentClass;
	}

	/**
	 * Get the key text stored in this ResourceHandledObject.
	 * 
	 * @return the Resourceuage key
	 */
	public String getKeyText()
	{
		return this.keyText;
	}

	/**
	 * Get the ResourceHandler stored in this ResourceHandledObject.
	 * 
	 * @return the ResourceHandler
	 */
	public ResourceHandler getResourceHandler()
	{
		return this.rh;
	}

	@Override
	public String toString()
	{
		return "ResourceHandledObjekt: " + object.getClass().toString() + "." + this.methodName + "()" + " - keyText: " + this.keyText;
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof ResourceHandledObject))
			return false;
		ResourceHandledObject rho = (ResourceHandledObject) o;
		return this.object.equals(rho.object) && this.methodName.equals(rho.methodName) && this.keyText.equals(rho.keyText)
				&& (this.rh == null ? rho.rh == null : this.rh.equals(rho.rh)) && this.method.equals(rho.method);
	}

	@Override
	public Object clone()
	{
		return new ResourceHandledObject(this.object, this.methodName, this.argumentClass, this.keyText, this.rh);
	}
}
