package team.overfed.util.exceptions;

import team.overfed.oae.resources.ResourceTransformer;

public class NotTransformableException extends Exception
{
	private static final long serialVersionUID = 1L;

	public NotTransformableException(ResourceTransformer transformer, Object object)
	{
		super(transformer.getClass().getName() + " could not transform " + object.getClass().getName() + ": " + object.toString());
	}

	public NotTransformableException(ResourceTransformer transformer, Object object, String message)
	{
		super(transformer.getClass().getName() + " could not transform " + object.getClass().getName() + ": " + object.toString() + " - " + message);
	}
}
