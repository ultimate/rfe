package team.overfed.oae.resources.transformers;

import team.overfed.oae.resources.ResourceTransformer;
import team.overfed.util.exceptions.NotTransformableException;

/**
 * This ResourceTransformer replaces a specified placeholder in a text with a given argument.<br>
 * The argument is required. The method transform(Object object) will throw an
 * NotTransformableException
 * 
 * @author ultimate
 */
public class PlaceholderResourceTransformer implements ResourceTransformer
{
	private String placeHolder;

	/**
	 * Construct a new PlaceholderResourceTransformer with a given place holder
	 * 
	 * @param placeHolder -
	 *            the place holder
	 */
	public PlaceholderResourceTransformer(String placeHolder)
	{
		super();
		this.placeHolder = placeHolder;
	}

	/**
	 * Get the place holder stored in this instance.
	 * 
	 * @return the place holder
	 */
	public String getPlaceHolder()
	{
		return placeHolder;
	}

	@Override
	public Object transform(Object object) throws NotTransformableException
	{
		throw new NotTransformableException(this, object, "Argument required!");
	}

	@Override
	public Object transform(Object object, Object... args) throws NotTransformableException
	{
		if(object instanceof String)
		{
			String propS = (String) object;
			try
			{
				return propS.replace(this.placeHolder, args[0].toString());
			}
			catch(Exception e)
			{
				throw new NotTransformableException(this, object, e.getMessage());
			}
		}
		throw new NotTransformableException(this, object, "Object is not a String");
	}
}
