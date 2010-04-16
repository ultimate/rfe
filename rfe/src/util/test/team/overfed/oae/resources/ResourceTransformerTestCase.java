package team.overfed.oae.resources;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.exceptions.NotTransformableException;

/**
 * Test valid and invalid operations on ResourceTransformer.
 * 
 * @author ultimate
 */
public class ResourceTransformerTestCase extends OAELoggerTestCase
{
	protected ResourceTransformer rt;
	protected ResourceHandler rh;

	public ResourceTransformerTestCase()
	{
		rh = new ResourceHandler();

		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));
		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);
		localeBundles.put(new Locale("en"), bundlesEN);

		rh.setLocaleBundles(localeBundles);
		rh.setLocale(new Locale("de"));
	}

	public void testTransformValid(Object expected, Object argument)
	{
		logger.debug("testing valid transform(Object)");
		try
		{
			assertEquals(expected, rt.transform(argument));
		}
		catch(NotTransformableException e)
		{
			logger.error(e);
			fail("Unexpected Exception occurred");
		}
	}

	public void testTransformValid(Object expected, Object argument, Object... arguments)
	{
		logger.debug("testing valid transform(Object,Object...)");
		try
		{
			assertEquals(expected, rt.transform(argument, arguments));
		}
		catch(NotTransformableException e)
		{
			logger.error(e);
			fail("Unexpected Exception occurred");
		}
	}

	public void testTransformInvalid(Object argument)
	{
		logger.debug("testing invalid transform(Object)");
		try
		{
			rt.transform(argument);
			fail("Expected Exception not occurred.");
		}
		catch(NotTransformableException e)
		{
			assertNotNull(e);
		}
	}

	public void testTransformInvalid(Object argument, Object... arguments)
	{
		logger.debug("testing invalid transform(Object,Object...)");
		try
		{
			rt.transform(argument, arguments);
			fail("Expected Exception not occurred.");
		}
		catch(NotTransformableException e)
		{
			assertNotNull(e);
		}
	}
}
