package team.overfed.oae.resources;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.ImageIcon;

import team.overfed.oae.logging.OAELogger;
import team.overfed.oae.resources.transformers.BooleanResourceTransformer;
import team.overfed.oae.resources.transformers.NumberResourceTransformer;
import team.overfed.util.exceptions.NotTransformableException;

/**
 * This is an own implementation that extends to possible usages of the java ResourceBundle.<br>
 * This implementation offers additional features and opportunities for handling messages or other
 * Strings in different languages.<br>
 * <br>
 * This class offers the opportunity to manage and handle multiple instances of ResourceBundles.
 * This offers the opportunity to split configuration in *.property files to multipe files to
 * garantee a structered design.<br>
 * In addition to that the ResourceHandler manages the updating of language specific labeled
 * objects. This means ResourceHandledObjects can be added to this ResourceHandler which are
 * configured with a key and a method to call. If the language is change the ResourceHandler will
 * call the specified method for each ResourceHandledObject to update the label texts.<br>
 * e.g. If you have a button the method will be "setText" and the key might be "button.text"<br>
 * <br>
 * To construct a new ResourceHandler with the method construct(String configurationBaseName) it is
 * necessary to have a configuration file in *.properties format. This file may contain the
 * following configuration keys:<br>
 * locale.i - where i represents a continuos counting index like in a list, all specified locales
 * will be loaded<br>
 * basename.i - where i represents a continuos index counting index like in a list, all specified
 * bundles will be loaded<br>
 * conf.interface.replace - wether to replace the ResourceSupport-interfaces ResourceHandler or not<br>
 * 
 * @author ultimate
 */
public class ResourceHandler implements Cloneable
{
	private static OAELogger logger = OAELogger.getOAELogger(ResourceHandler.class);

	private Map<Locale, ArrayList<ResourceBundle>> localeBundles;
	private ArrayList<ResourceHandledObject> handledObjects;
	private Locale currentLocale;

	/**
	 * Construct a new ResourceHandler and configure it depending on the settings made it the
	 * ResourceBundles stored at the given configurationBaseName.
	 * 
	 * @param configurationBaseName -
	 *            the base name for the configuration stored in a *.properties file
	 * @return the constructed ResourceHandler
	 */
	public static ResourceHandler construct(String configurationBaseName)
	{
		logger.info("Construction new ResourceHandler");
		ResourceHandler rh = new ResourceHandler();
		boolean replaceInterface = true;

		logger.info("Loading Resource configuration");
		ResourceBundle configuration = ResourceBundle.getBundle(configurationBaseName);

		try
		{
			replaceInterface = (Boolean) new BooleanResourceTransformer().transform(configuration.getString("conf.interface.replace"));
		}
		catch(NotTransformableException e)
		{
		}

		logger.info("Loading Locales");
		ArrayList<Locale> locales = new ArrayList<Locale>();
		for(int i = 0; true; i++)
		{
			if(!configuration.containsKey("locale." + i))
				break;
			locales.add(new Locale(configuration.getString("locale." + i)));
		}
		logger.info(locales.size() + " Locales found");

		logger.info("Loading basenames");
		ArrayList<String> baseNames = new ArrayList<String>();
		for(int i = 0; true; i++)
		{
			if(!configuration.containsKey("basename." + i))
				break;
			baseNames.add(configuration.getString("basename." + i));
		}
		logger.info(locales.size() + " basenames found");

		logger.info("Loading ResourceBundles for Locales...");
		HashMap<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		ArrayList<ResourceBundle> tmpList;
		for(Locale locale : locales)
		{
			logger.info("Loading ResourceBundle for Locale: " + locale);
			tmpList = new ArrayList<ResourceBundle>();

			for(String baseName : baseNames)
				tmpList.add(ResourceBundle.getBundle(baseName, locale));

			localeBundles.put(locale, tmpList);
		}
		logger.info("ResourceBundles loaded");

		rh.setLocaleBundles(localeBundles);
		rh.setLocale(locales.get(0));

		if(replaceInterface)
		{
			logger.info("Replacing of interface is enabled.");
			ResourceSupport.resourceHandler.replace(rh);
		}
		else
			logger.info("Replacing of interface is disnabled.");
		return rh;
	}

	/**
	 * This method is for internal use only. In constructs a new empty ResourceHandler which has to
	 * be configured after being constructed.
	 */
	protected ResourceHandler()
	{
		this.localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		this.handledObjects = new ArrayList<ResourceHandledObject>();
		currentLocale = Locale.getDefault();
	}

	/**
	 * Construct a new ResourceHandler with the given Map of bundles for the supported Locales.
	 * 
	 * @param localeBundles -
	 *            the Map of bundles for Locale
	 */
	public ResourceHandler(Map<Locale, ArrayList<ResourceBundle>> localeBundles)
	{
		if(localeBundles == null)
			throw new IllegalArgumentException("The Map of ResourceBundles is null.");
		if(localeBundles.size() == 0)
			throw new IllegalArgumentException("The Map of ResourceBundles contains no Locale.");
		this.localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>(localeBundles);
		this.handledObjects = new ArrayList<ResourceHandledObject>();
		try
		{
			this.setLocale(Locale.getDefault());
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("This ResourceHandler does not contain the Systems default Locale.");
		}
	}

	/**
	 * Construct a new ResourceHandler with the given Map of bundles for the supported Locales which
	 * is set to the given Locale.
	 * 
	 * @param localeBundles -
	 *            the Map of bundles for Locale
	 * @param locale -
	 *            the Locale to set
	 */
	public ResourceHandler(Map<Locale, ArrayList<ResourceBundle>> localeBundles, Locale locale)
	{
		if(localeBundles == null)
			throw new IllegalArgumentException("The Map of ResourceBundles is null.");
		if(localeBundles.size() == 0)
			throw new IllegalArgumentException("The Map of ResourceBundles contains no Locale.");
		this.localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>(localeBundles);
		this.handledObjects = new ArrayList<ResourceHandledObject>();
		try
		{
			this.setLocale(locale);
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("This ResourceHandler does not contain the given Locale: " + locale);
		}
	}

	/**
	 * Set the Map of bundles for the supported Locales
	 * 
	 * @param localeBundles -
	 *            the Map of bundles for Locale
	 */
	protected void setLocaleBundles(Map<Locale, ArrayList<ResourceBundle>> localeBundles)
	{
		this.localeBundles = localeBundles;
	}

	/**
	 * Add a ResourceHandledObject to this ResourceHandler.
	 * 
	 * @param rho -
	 *            the ResourceHandledObject to add
	 * @throws IllegalArgumentException
	 *             if the ResourceHandledObject is already contained in this ResourceHandler
	 */
	public ResourceHandledObject addObject(ResourceHandledObject rho)
	{
		if(this.handledObjects.contains(rho))
			throw new IllegalArgumentException("ResourceHandler already contains the given ResourceHandledObject.");
		rho.setResourceHandler(this);
		this.handledObjects.add(rho);
		rho.updateString();
		return rho;
	}

	/**
	 * Add a ResourceHandledObject to this ResourceHandler without constructing it before. This
	 * method calls the ResourceHandledObject constructor and then adds the new Object to this
	 * ResourceHandler.
	 * 
	 * @param o -
	 *            the Object to invoke the method at
	 * @param method -
	 *            the name of the method to invoke
	 * @param argumentClass -
	 *            the required class of the argument used with the method
	 * @param keyText -
	 *            the keyText of the String to add as an argument to the method
	 * @throws IllegalArgumentException
	 *             if one parameter is null or the ResourceHandler does not contain the given key or
	 *             if the ResourceHandledObject is already contained in this ResourceHandler
	 */
	public ResourceHandledObject addObject(Object o, String method, Class<?> argumentClass, String keyText)
	{
		ResourceHandledObject rho = new ResourceHandledObject(o, method, argumentClass, keyText, this);
		if(this.handledObjects.contains(rho))
			throw new IllegalArgumentException("ResourceHandler already contains the given ResourceHandledObject.");
		this.handledObjects.add(rho);
		rho.updateString();
		return rho;
	}

	/**
	 * Add a ResourceHandledObject to this ResourceHandler without constructing it before. This
	 * method calls the ResourceHandledObject constructor and then adds the new Object to this
	 * ResourceHandler.
	 * 
	 * @param o -
	 *            the Object to invoke the method at
	 * @param method -
	 *            the name of the method to invoke
	 * @param argumentClass -
	 *            the required class of the argument used with the method
	 * @param keyText -
	 *            the keyText of the String to add as an argument to the method
	 * @param transformer -
	 *            the ResourceTransformer to transform the Resource with
	 * @throws IllegalArgumentException
	 *             if one parameter is null or the ResourceHandler does not contain the given key or
	 *             if the ResourceHandledObject is already contained in this ResourceHandler
	 */
	public ResourceHandledObject addObject(Object o, String method, Class<?> argumentClass, String keyText, ResourceTransformer transformer)
	{
		ResourceHandledObject rho = new ResourceHandledObject(o, method, argumentClass, keyText, this, transformer);
		if(this.handledObjects.contains(rho))
			throw new IllegalArgumentException("ResourceHandler already contains the given ResourceHandledObject.");
		this.handledObjects.add(rho);
		rho.updateString();
		return rho;
	}

	/**
	 * Remove a ResourceHandledObject from this ResourceHandler if it is contained in this
	 * ResourceHandler.
	 * 
	 * @param rho -
	 *            the ResourceHandledObject to reomve
	 * @throws IllegalArgumentException
	 *             if the ResourceHandledObject is not contained in this ResourceHandler
	 */
	public void removeObject(ResourceHandledObject rho)
	{
		if(!this.handledObjects.contains(rho))
			throw new IllegalArgumentException("ResourceHandler does not contain the given ResourceHandledObject.");
		this.handledObjects.remove(rho);
	}

	/**
	 * Remove all ResourceHandledObjects from this ResourceHandler.
	 */
	public void clearObjects()
	{
		this.handledObjects = new ArrayList<ResourceHandledObject>();
	}

	/**
	 * Set the Locale used in this ResourceHandler.
	 * 
	 * @param newLocale -
	 *            the Locale to change to
	 */
	public void setLocale(Locale newLocale)
	{
		if(!this.localeBundles.containsKey(newLocale))
			throw new IllegalArgumentException("The given Locale \"" + newLocale + "\" is not available in this ResourceHandler");
		logger.info("Switching Locale: " + currentLocale + " --> " + newLocale);
		this.currentLocale = newLocale;
		this.updateObjects();
	}

	/**
	 * Get the current Locale used in this ResourceHandler.
	 * 
	 * @return the current Locale
	 */
	public Locale getLocale()
	{
		return this.currentLocale;
	}

	/**
	 * Get the spoken name of the current Locale.
	 * 
	 * @return the locale name
	 */
	public String getLocaleName()
	{
		return getLocaleName(this.currentLocale);
	}

	/**
	 * Get the spoken name of the given Locale.
	 * 
	 * @return the locale name
	 */
	public String getLocaleName(Locale locale)
	{
		return getString("locale.name", locale);
	}

	/**
	 * Get the description of the current Locale.
	 * 
	 * @return the locale description
	 */
	public String getLocaleDescription()
	{
		return getLocaleDescription(this.currentLocale);
	}

	/**
	 * Get the description of the given Locale.
	 * 
	 * @return the locale description
	 */
	public String getLocaleDescription(Locale locale)
	{
		return getString("locale.description", locale);
	}

	/**
	 * Get the image path of the current Locale.
	 * 
	 * @return the locale image path
	 */
	public String getLocaleImagePath()
	{
		return getLocaleImagePath(this.currentLocale);
	}

	/**
	 * Get the image path of the given Locale.
	 * 
	 * @return the locale image path
	 */
	public String getLocaleImagePath(Locale locale)
	{
		return getString("locale.image", locale);
	}

	/**
	 * Get the image of the current Locale.
	 * 
	 * @return the locale image as an ImageIcon
	 */
	public ImageIcon getLocaleImageIcon()
	{
		return getLocaleImageIcon(this.currentLocale);
	}

	/**
	 * Get the image of the given Locale.
	 * 
	 * @return the locale image as an ImageIcon
	 */
	public ImageIcon getLocaleImageIcon(Locale locale)
	{
		return new ImageIcon(getString("locale.image", locale));
	}

	/**
	 * Get all available Locales in this ResourceHandler.
	 * 
	 * @return the Set of available Locales
	 */
	public Set<Locale> getAvailableLocales()
	{
		return this.localeBundles.keySet();
	}

	/**
	 * Get the String associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.
	 * 
	 * @param key -
	 *            the key for the String
	 * @return the associated String
	 */
	public String getString(String key)
	{
		return getString(key, this.currentLocale);
	}

	/**
	 * Get the String associated with the given key in this ResourceHandler in the given Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.
	 * 
	 * @param key -
	 *            the key for the String
	 * @param locale -
	 *            the Locale to get the String in
	 * @return the associated String
	 */
	public String getString(String key, Locale locale)
	{
		if(key == null)
			throw new IllegalArgumentException("The given key is null.");
		if(locale == null)
			throw new IllegalArgumentException("The given Locale is null.");
		if(!this.localeBundles.containsKey(locale))
			throw new IllegalArgumentException("The given Locale is not available in this ResourceHandler");
		for(ResourceBundle rsBun : this.localeBundles.get(locale))
		{
			if(rsBun.containsKey(key))
				return rsBun.getString(key);
		}
		return key;
	}

	/**
	 * Get the String associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the ResourceHandler will try to replace the placeholders {i} with the
	 * given optional argument in the Object-Array.
	 * 
	 * @param key -
	 *            the key for the String
	 * @param args -
	 *            the optional inserted arguments
	 * @return the associated String
	 */
	public String getString(String key, Object... args)
	{
		return getString(key, this.currentLocale, args);
	}

	/**
	 * Get the String associated with the given key in this ResourceHandler in the given Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the ResourceHandler will try to replace the placeholders {i} with the
	 * given optional argument in the Object-Array.
	 * 
	 * @param key -
	 *            the key for the String
	 * @param locale -
	 *            the Locale to get the String in
	 * @param args -
	 *            the optional inserted arguments
	 * @return the associated String
	 */
	public String getString(String key, Locale locale, Object... args)
	{
		if(key == null)
			throw new IllegalArgumentException("The given key is null.");
		if(locale == null)
			throw new IllegalArgumentException("The given Locale is null.");
		if(!this.localeBundles.containsKey(locale))
			throw new IllegalArgumentException("The given Locale is not available in this ResourceHandler");
		if(args == null)
			throw new IllegalArgumentException("The argument Object is null.");
		String ret;
		int count = 0;
		for(ResourceBundle rsBun : this.localeBundles.get(locale))
		{
			if(rsBun.containsKey(key))
			{
				ret = rsBun.getString(key);
				for(Object o : args)
				{
					ret = ret.replace("{" + (count++) + "}", o.toString());
				}
				return ret;
			}
		}
		return key;
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @return the associated Object
	 */
	public Object getObject(String key)
	{
		return getObject(key, this.currentLocale);
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the given Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the associated Object
	 */
	public Object getObject(String key, Locale locale)
	{
		if(key == null)
			throw new IllegalArgumentException("The given key is null.");
		if(locale == null)
			throw new IllegalArgumentException("The given Locale is null.");
		if(!this.localeBundles.containsKey(locale))
			throw new IllegalArgumentException("The given Locale is not available in this ResourceHandler");
		for(ResourceBundle rsBun : this.localeBundles.get(locale))
		{
			if(rsBun.containsKey(key))
				return rsBun.getObject(key);
		}
		return key;
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the given ResourceTransformer will transform the Object as specified in
	 * the ResourceTransformer. For example a ResourceTransformer may try to cast a String into a
	 * number. So that usage will be easier after that. If the transformation is not possible the
	 * untransformed Object itself will be returned.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @return the transformed associated Object
	 */
	public Object getObject(String key, ResourceTransformer transformer)
	{
		return getObject(key, this.currentLocale, transformer);
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the given Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the given ResourceTransformer will transform the Object as specified in
	 * the ResourceTransformer. For example a ResourceTransformer may try to cast a String into a
	 * number. So that usage will be easier after that. If the transformation is not possible the
	 * untransformed Object itself will be returned.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the transformed associated Object
	 */
	public Object getObject(String key, Locale locale, ResourceTransformer transformer)
	{
		if(key == null)
			throw new IllegalArgumentException("The given key is null.");
		if(transformer == null)
			throw new IllegalArgumentException("The given ResourceTransformer is null.");
		if(locale == null)
			throw new IllegalArgumentException("The given Locale is null.");
		if(!this.localeBundles.containsKey(locale))
			throw new IllegalArgumentException("The given Locale is not available in this ResourceHandler");
		for(ResourceBundle rsBun : this.localeBundles.get(locale))
		{
			if(rsBun.containsKey(key))
			{
				try
				{
					return transformer.transform(rsBun.getObject(key));
				}
				catch(NotTransformableException e)
				{
					return rsBun.getObject(key);
				}
			}
		}
		return key;
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the given ResourceTransformer will transform the Object as specified in
	 * the ResourceTransformer. For example a ResourceTransformer may try to cast a String into a
	 * number. So that usage will be easier after that. If the transformation is not possible the
	 * untransformed Object itself will be returned.<br>
	 * In addition to that the ResourceHandler will try to replace the placeholders {i} with the
	 * given optional argument in the Object-Array.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @param args -
	 *            the optional inserted arguments
	 * @return the transformed associated Object
	 */
	public Object getObject(String key, ResourceTransformer transformer, Object... args)
	{
		return getObject(key, this.currentLocale, transformer, args);
	}

	/**
	 * Get the Object associated with the given key in this ResourceHandler in the current Locale.<br>
	 * The ResourceHandler will lookup the List of ResourceBundles and return the first matching
	 * result.<br>
	 * In addition to that the given ResourceTransformer will transform the Object as specified in
	 * the ResourceTransformer. For example a ResourceTransformer may try to cast a String into a
	 * number. So that usage will be easier after that. If the transformation is not possible the
	 * untransformed Object itself will be returned.<br>
	 * In addition to that the ResourceHandler will try to replace the placeholders {i} with the
	 * given optional argument in the Object-Array.
	 * 
	 * @param key -
	 *            the key for the Object
	 * @param locale -
	 *            the Locale to get the Object in
	 * @param args -
	 *            the optional inserted arguments
	 * @return the transformed associated Object
	 */
	public Object getObject(String key, Locale locale, ResourceTransformer transformer, Object... args)
	{
		if(key == null)
			throw new IllegalArgumentException("The given key is null.");
		if(transformer == null)
			throw new IllegalArgumentException("The given ResourceTransformer is null.");
		if(locale == null)
			throw new IllegalArgumentException("The given Locale is null.");
		if(!this.localeBundles.containsKey(locale))
			throw new IllegalArgumentException("The given Locale is not available in this ResourceHandler");
		if(args == null)
			throw new IllegalArgumentException("The argument Object is null.");
		for(ResourceBundle rsBun : this.localeBundles.get(Locale.getDefault()))
		{
			if(rsBun.containsKey(key))
			{
				try
				{
					return transformer.transform(rsBun.getObject(key), args);
				}
				catch(NotTransformableException e)
				{
					return rsBun.getObject(key);
				}
			}
		}
		return key;
	}

	/**
	 * Get the resource associated with the given key as a Boolean if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Boolean
	 */
	public Boolean getBoolean(String key)
	{
		return getBoolean(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Boolean if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Boolean
	 */
	public Boolean getBoolean(String key, Locale locale)
	{
		return (Boolean) getObject(key, locale, new BooleanResourceTransformer());
	}

	/**
	 * Get the resource associated with the given key as a Number if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param numberClass -
	 *            the class to which the resource should be transformed
	 * @return the resource as a Integer
	 */
	public Number getNumber(String key, Class<? extends Number> numberClass)
	{
		return getNumber(key, this.currentLocale, numberClass);
	}

	/**
	 * Get the resource associated with the given key as a Number if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @param numberClass -
	 *            the class to which the resource should be transformed
	 * @return the resource as a Integer
	 */
	public Number getNumber(String key, Locale locale, Class<? extends Number> numberClass)
	{
		return (Number) getObject(key, locale, new NumberResourceTransformer(numberClass));
	}

	/**
	 * Get the resource associated with the given key as a Byte if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Byte
	 */
	public Byte getByte(String key)
	{
		return getByte(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Byte if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Byte
	 */
	public Byte getByte(String key, Locale locale)
	{
		return (Byte) getObject(key, locale, new NumberResourceTransformer(Byte.class));
	}

	/**
	 * Get the resource associated with the given key as a Short if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Short
	 */
	public Short getShort(String key)
	{
		return getShort(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Short if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Short
	 */
	public Short getShort(String key, Locale locale)
	{
		return (Short) getObject(key, locale, new NumberResourceTransformer(Short.class));
	}

	/**
	 * Get the resource associated with the given key as a Integer if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Integer
	 */
	public Integer getInteger(String key)
	{
		return getInteger(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Integer if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Integer
	 */
	public Integer getInteger(String key, Locale locale)
	{
		return (Integer) getObject(key, locale, new NumberResourceTransformer(Integer.class));
	}

	/**
	 * Get the resource associated with the given key as a Long if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Long
	 */
	public Long getLong(String key)
	{
		return getLong(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Long if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Long
	 */
	public Long getLong(String key, Locale locale)
	{
		return (Long) getObject(key, locale, new NumberResourceTransformer(Long.class));
	}

	/**
	 * Get the resource associated with the given key as a Float if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Float
	 */
	public Float getFloat(String key)
	{
		return getFloat(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Float if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Float
	 */
	public Float getFloat(String key, Locale locale)
	{
		return (Float) getObject(key, locale, new NumberResourceTransformer(Float.class));
	}

	/**
	 * Get the resource associated with the given key as a Double if possible in the current Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a Double
	 */
	public Double getDouble(String key)
	{
		return getDouble(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a Double if possible in the given Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a Double
	 */
	public Double getDouble(String key, Locale locale)
	{
		return (Double) getObject(key, locale, new NumberResourceTransformer(Double.class));
	}

	/**
	 * Get the resource associated with the given key as a AtomicInteger if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a AtomicInteger
	 */
	public AtomicInteger getAtomicInteger(String key)
	{
		return getAtomicInteger(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a AtomicInteger if possible in the given
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a AtomicInteger
	 */
	public AtomicInteger getAtomicInteger(String key, Locale locale)
	{
		return (AtomicInteger) getObject(key, locale, new NumberResourceTransformer(AtomicInteger.class));
	}

	/**
	 * Get the resource associated with the given key as a AtomicLong if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a AtomicLong
	 */
	public AtomicLong getAtomicLong(String key)
	{
		return getAtomicLong(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a AtomicLong if possible in the given
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a AtomicLong
	 */
	public AtomicLong getAtomicLong(String key, Locale locale)
	{
		return (AtomicLong) getObject(key, locale, new NumberResourceTransformer(AtomicLong.class));
	}

	/**
	 * Get the resource associated with the given key as a BigInteger if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a BigInteger
	 */
	public BigInteger getBigInteger(String key)
	{
		return getBigInteger(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a BigInteger if possible in the given
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a BigInteger
	 */
	public BigInteger getBigInteger(String key, Locale locale)
	{
		return (BigInteger) getObject(key, locale, new NumberResourceTransformer(BigInteger.class));
	}

	/**
	 * Get the resource associated with the given key as a BigDecimal if possible in the current
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @return the resource as a BigDecimal
	 */
	public BigDecimal getBigDecimal(String key)
	{
		return getBigDecimal(key, this.currentLocale);
	}

	/**
	 * Get the resource associated with the given key as a BigDecimal if possible in the given
	 * Locale.
	 * 
	 * @param key -
	 *            the resource key
	 * @param locale -
	 *            the Locale to get the Object in
	 * @return the resource as a BigDecimal
	 */
	public BigDecimal getBigDecimal(String key, Locale locale)
	{
		return (BigDecimal) getObject(key, locale, new NumberResourceTransformer(BigDecimal.class));
	}

	/**
	 * Update all LangHandledObjects contained in this LangHandler.<br>
	 * This method will be invoked if the language is changed.
	 */
	public void updateObjects()
	{
		for(ResourceHandledObject rho : this.handledObjects)
			rho.updateString();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Locale l : this.localeBundles.keySet())
		{
			sb.append(" \"" + l.getDisplayName() + "\"");
		}
		return "ResourceHandler: containing Locales:" + sb.toString() + "\n" + "  currently set to " + this.currentLocale + "\n" + "  handling "
				+ this.handledObjects.size() + " ResourceHandledObjects";
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof ResourceHandler))
			return false;
		ResourceHandler rh = (ResourceHandler) o;
		return this.localeBundles.equals(rh.localeBundles) && this.handledObjects.equals(rh.handledObjects)
				&& this.currentLocale.equals(rh.currentLocale);
	}

	/**
	 * Replace this ResourceHandler with another ResourceHandler.<br>
	 * This method is very useful if an instance of this class may be final. In this case normal
	 * replacement is not possible and this method is the perfect alternative.<br>
	 * To use the ResourceHandler in the interface ResourceSupport the usage of this method is
	 * necessary.
	 * 
	 * @param rh -
	 *            the ResourceHandler that will replace the current instance
	 */
	public void replace(ResourceHandler rh)
	{
		if(rh == null)
			throw new IllegalArgumentException("The ResourceHandler is null.");
		this.localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>(rh.localeBundles);
		this.handledObjects = new ArrayList<ResourceHandledObject>(rh.handledObjects);
		this.currentLocale = (Locale) rh.currentLocale.clone();
	}

	@Override
	public Object clone()
	{
		ResourceHandler rh = new ResourceHandler();
		rh.localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>(this.localeBundles);
		rh.handledObjects = new ArrayList<ResourceHandledObject>(this.handledObjects);
		rh.currentLocale = (Locale) this.currentLocale.clone();
		return rh;
	}
}
