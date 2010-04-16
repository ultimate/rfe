package team.overfed.oae.resources.transformers;

import team.overfed.oae.resources.ResourceTransformer;
import team.overfed.util.exceptions.NotTransformableException;

/**
 * This ResourceTransformer transforms due to the following rules:<br>
 * <br>
 * String "true" --&gt; Boolean "true"<br>
 * String "false" --&gt; Boolean "false"<br>
 * other String --&gt; NotTransformableException
 * 
 * @author ultimate
 */
public class BooleanResourceTransformer implements ResourceTransformer
{
	/**
	 * Construct a new BooleanResourceTransformer.
	 */
	public BooleanResourceTransformer()
	{
		super();
	}

	@Override
	public Object transform(Object object) throws NotTransformableException
	{
		if(object instanceof String)
		{
			String propS = (String) object;
			if(propS.equalsIgnoreCase("true"))
				return true;
			else if(propS.equalsIgnoreCase("false"))
				return false;
			throw new NotTransformableException(this, object, "String is neither 'true' nor 'false'");
		}
		throw new NotTransformableException(this, object, "Object is not a String");
	}

	@Override
	public Object transform(Object object, Object... args) throws NotTransformableException
	{
		return transform(object);
	}
}
