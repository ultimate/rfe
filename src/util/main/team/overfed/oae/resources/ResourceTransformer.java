package team.overfed.oae.resources;

import team.overfed.util.exceptions.NotTransformableException;

/**
 * This interface provides the necessary method to transform resources offered by a ResourceHandler.<br>
 * With using this interface easy transforming or casting of Objects is possible by passing an
 * instance of an implementing class to the getObject-Method of ResourceHandler.<br>
 * <br>
 * E.g. There are several possibilities for the following line:<br>
 * 1. <code>Integer.parseInt(resourceHandler.getString("key"))</code><br>
 * 2. <code>resourceHandler.getObject("key", new NumberResourceTransformer(Integer.class))</code><br>
 * 3. <code>resourceHandler.getInteger("key")<br>
 * Explanation:<br>
 * 1. This is the classical way.<br>
 * 2. This might not be shorter in this example but can be much shorter for more complex examples<br>
 * 3. This is in fact a shortcut implemented by ResourceHandler which exactly calls 2.<br>
 * 
 * @author ultimate
 */
public interface ResourceTransformer
{
	/**
	 * Simply transform an Object into another Object with NO additional arguments.
	 * 
	 * @param object -
	 *            the Object to transform
	 * @return the transformed Object
	 * @throws NotTransformableException
	 *             if the Object does not match the format the ResourceHandler need to transform the
	 *             Object
	 */
	public abstract Object transform(Object object) throws NotTransformableException;

	/**
	 * Simply transform an Object into another Object with additional arguments.
	 * 
	 * @param object -
	 *            the Object to transform
	 * @param args -
	 *            addtional optional arguments for the transformation
	 * @return the transformed Object
	 * @throws NotTransformableException
	 *             if the Object does not match the format the ResourceHandler need to transform the
	 *             Object
	 */
	public abstract Object transform(Object object, Object... args) throws NotTransformableException;
}
