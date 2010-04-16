package team.overfed.oae.resources.transformers;

import team.overfed.oae.resources.ResourceHandler;
import team.overfed.oae.resources.ResourceTransformer;
import team.overfed.util.exceptions.NotTransformableException;

/**
 * This ResourceTransformer replace references to other associated object in a ResourceHandler.<br>
 * <br>
 * E.g. If the ResourceHandler contains to keys a ReferenceResourceTransformer configured with "{"
 * as referenceBegin and "}" as referenceEnding will return for key2 "say yes":<br>
 * key1=yes<br>
 * key2=say {key1}<br>
 * 
 * @author ultimate
 */
public class ReferenceResourceTransformer implements ResourceTransformer
{
	private String referenceBegin;
	private String referenceEnding;
	private ResourceHandler rh;

	/**
	 * Construct a new ReferenceResourceTransformer with a ResourceHandler to lookup the keys and a
	 * referenceBegin and referenceEnding to identify which parts of the String are part of the
	 * reference.
	 * 
	 * @param rh -
	 *            the ResourceHandler to lookup the keys
	 * @param referenceBegin -
	 *            the begin of the reference String
	 * @param referenceEnding -
	 *            the ending of the reference String
	 */
	public ReferenceResourceTransformer(ResourceHandler rh, String referenceBegin, String referenceEnding)
	{
		super();
		if(referenceBegin.equals(referenceEnding))
			throw new IllegalArgumentException("referenceBegin and referenceEnding must not be equal.");
		if(rh == null)
			throw new IllegalArgumentException("The given ResourceHandler is null");
		this.referenceBegin = referenceBegin;
		this.referenceEnding = referenceEnding;
		this.rh = rh;
	}

	/**
	 * Get the begin of the reference String stored in this ReferenceResourceTransformer.
	 * 
	 * @return the begin of the reference String
	 */
	public String getReferenceBegin()
	{
		return this.referenceBegin;
	}

	/**
	 * Get the ending of the reference String stored in this ReferenceResourceTransformer.
	 * 
	 * @return the ending of the reference String
	 */
	public String getReferenceEnding()
	{
		return this.referenceEnding;
	}

	/**
	 * Get the ResourceHandler stored in this ReferenceResourceTransformer
	 * 
	 * @return the ResourceHandler to lookup the keys
	 */
	public ResourceHandler getResourceHandler()
	{
		return rh;
	}

	@Override
	public Object transform(Object object) throws NotTransformableException
	{
		if(object instanceof String)
		{
			String propS = (String) object;
			String newPropS = propS;
			int begin, ending;
			int fromIndex = 0;
			do
			{
				begin = propS.indexOf(this.referenceBegin, fromIndex);
				ending = propS.indexOf(this.referenceEnding, fromIndex);
				if(begin > 0 && ending > 0 && ending > begin)
				{
					begin += this.referenceBegin.length();
					String keyReference = propS.substring(begin, ending);

					newPropS = newPropS.replace(this.referenceBegin + keyReference + this.referenceEnding, this.rh.getObject(keyReference, this)
							.toString());
					fromIndex = ending + referenceEnding.length();
					int oldlength = (this.referenceBegin + keyReference + this.referenceEnding).length();
					int newlength = this.rh.getObject(keyReference, this).toString().length();
					fromIndex = fromIndex - oldlength + newlength;
				}
				else
					break;
			} while(fromIndex < propS.length());
			return newPropS;
		}
		throw new NotTransformableException(this, object, "Object is not a String");
	}

	@Override
	public Object transform(Object object, Object... args) throws NotTransformableException
	{
		return transform(object);
	}

}
