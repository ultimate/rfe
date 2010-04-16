package team.overfed.oae.resources.transformers;

import team.overfed.oae.resources.ResourceTransformer;
import team.overfed.util.exceptions.NotTransformableException;
import team.overfed.util.utils.NumberUtils;

/**
 * This ResourceTransformer transforms Strings to number if possible due to the constructors of each
 * Subclass of java.lang.Number.<br>
 * <br>
 * If not possible --&gt; NotTransformableException
 * 
 * @author ultimate
 */
public class NumberResourceTransformer implements ResourceTransformer
{
	private Class<? extends Number> numberClass;

	/**
	 * Construct a new NumberResourceTransformer with a given Subclass of java.lang.Number.
	 * 
	 * @param numberClass -
	 *            the class to transform to
	 */
	public NumberResourceTransformer(Class<? extends Number> numberClass)
	{
		super();
		this.numberClass = numberClass;
	}

	/**
	 * Get the class to transform to stored in this instance.
	 * 
	 * @return the class to transform to
	 */
	public Class<? extends Number> getNumberClass()
	{
		return numberClass;
	}

	@Override
	public Object transform(Object object) throws NotTransformableException
	{
		if(object instanceof String)
		{
			String propS = (String) object;
			try
			{
				return NumberUtils.parseStringProperty(propS, this.numberClass);
			}
			catch(NumberFormatException e)
			{
				throw new NotTransformableException(this, object, e.getMessage());
			}
		}
		throw new NotTransformableException(this, object, "Object is not a String");
	}

	@Override
	public Object transform(Object object, Object... args) throws NotTransformableException
	{
		return transform(object);
	}
}
