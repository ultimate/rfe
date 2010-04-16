package team.overfed.oae.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;

/**
 * Test valid and invalid operations on ResourceHandledObject.
 * 
 * @author ultimate
 */
public class ResourceHandledObjectTest extends OAELoggerTestCase
{

	@Test
	public void testCreateResourceHandledObjectValid()
	{
		logger.debug("testing correct construction including method invocation");

		// initiating
		ResourceHandler rh = new ResourceHandler();

		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));
		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);
		localeBundles.put(new Locale("en"), bundlesEN);

		rh.setLocaleBundles(localeBundles);
		rh.setLocale(new Locale("de"));

		ResourceHandledObject rho;

		String testT = "message";
		String testM = "setText";

		JButton testB = new JButton();
		JLabel testL = new JLabel();
		JTextField testTF = new JTextField();
		// testing valid
		logger.debug("testing with example JButton");
		try
		{
			rho = new ResourceHandledObject(testB, testM, String.class, testT, rh);
			assertEquals("Hallo du!", testB.getText());
			assertEquals(testB, rho.getObject());
			assertEquals(testT, rho.getKeyText());
			assertEquals(testM, rho.getMethodName());
			assertEquals(String.class, rho.getArgumentClass());
		}
		catch(Exception e)
		{
			logger.error(e);
			e.printStackTrace();
			fail("Unexpected Exception occured: " + e.getMessage());
		}
		logger.debug("testing with example JLabel");
		try
		{
			rho = new ResourceHandledObject(testL, testM, String.class, testT, rh);
			assertEquals("Hallo du!", testL.getText());
			assertEquals(testL, rho.getObject());
			assertEquals(testT, rho.getKeyText());
			assertEquals(testM, rho.getMethodName());
			assertEquals(String.class, rho.getArgumentClass());
		}
		catch(Exception e)
		{
			fail("Unexpected Exception occured: " + e.getMessage());
		}
		logger.debug("testing with example JTextField");
		try
		{
			rho = new ResourceHandledObject(testTF, testM, String.class, testT, rh);
			assertEquals("Hallo du!", testTF.getText());
			assertEquals(testTF, rho.getObject());
			assertEquals(testT, rho.getKeyText());
			assertEquals(testM, rho.getMethodName());
			assertEquals(String.class, rho.getArgumentClass());
		}
		catch(Exception e)
		{
			fail("Unexpected Exception occured: " + e.getMessage());
		}
	}

	@Test
	public void testCreateResourceHandledObjectInvalid()
	{
		logger.debug("testing failing construction");

		// initiating
		ResourceHandler rh = new ResourceHandler();

		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));
		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);
		localeBundles.put(new Locale("en"), bundlesEN);

		rh.setLocaleBundles(localeBundles);

		ResourceHandledObject rho = null;

		String testT = "message";
		String testM = "setText";

		JTextField testTF = new JTextField();
		// testing invalid
		logger.debug("testing with null Object");
		try
		{
			rho = new ResourceHandledObject(null, testM, String.class, testT, rh);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
		}
		logger.debug("testing with null Method");
		try
		{
			rho = new ResourceHandledObject(testTF, null, String.class, testT, rh);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
		}
		logger.debug("testing with null class");
		try
		{
			rho = new ResourceHandledObject(testTF, testM, null, testT, rh);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
		}
		logger.debug("testing with null Text");
		try
		{
			rho = new ResourceHandledObject(testTF, testM, String.class, null, rh);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
		}
		logger.debug("testing with illegal method");
		try
		{
			rho = new ResourceHandledObject(testTF, testM + "b", String.class, testT, rh);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
		}
		assertNull(rho);
	}

	@Test
	public void testClone()
	{
		logger.debug("testing cloning");

		ResourceHandledObject rho1, rho2;

		String testT = "message";
		String testM = "setText";

		JButton testB = new JButton();

		rho1 = new ResourceHandledObject(testB, testM, String.class, testT, null);
		rho2 = new ResourceHandledObject(testB, testM, String.class, testT, null);

		assertEquals(rho2, rho1);
		assertEquals(rho2, rho1.clone());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(ResourceHandledObjectTest.class);
	}
}
