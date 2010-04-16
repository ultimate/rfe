package team.overfed.oae.resources;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on ResourceSupport.
 * 
 * @author ultimate
 */
public class ResourceSupportTestImplementation extends OAELoggerTestCase implements ResourceSupport
{
	public ResourceHandler getResourceHandler()
	{
		return resourceHandler;
	}
}
