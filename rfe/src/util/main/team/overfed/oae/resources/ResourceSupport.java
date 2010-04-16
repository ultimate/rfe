package team.overfed.oae.resources;

/**
 * This interface provides easy usage of a ResourceHandler.<br>
 * When constructing a ResourceHandler with ResourceUtil it is possible to enable or disable
 * interface support. If it is enabled the utility will replace this ResourceHandler for easy
 * project wide usage. To use the constructed ResourceHandler only implementing this interface will
 * be necessary.<br>
 * 
 * @author ultimate
 */
public interface ResourceSupport
{
	/**
	 * This is the ResourceHandler offered by this interface.<br>
	 * Because this is an interface the ResourceHandler will be final. To use a custom
	 * ResourceHandler it is necessary to use the replace()-method offered by the ResourceHandler.<br>
	 */
	public static final ResourceHandler resourceHandler = new ResourceHandler();
}