package team.overfed.oae.resources;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.oae.resources.transformers.BooleanResourceTransformer;

/**
 * Test valid and invalid operations on ResourceHandler.
 * 
 * @author ultimate
 */
public class ResourceHandlerTest extends OAELoggerTestCase
{

	@Test
	public void testLanguageSwitching()
	{
		logger.debug("testing a valid construction and usage of a ResourceHandler");

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

		logger.debug("adding LangHandledObjects");
		JButton jb1 = new JButton("anytext");
		JButton jb2 = new JButton("anytext");
		JButton jb3 = new JButton("anytext");

		rh.addObject(jb1, "setText", String.class, "message");
		rh.addObject(jb2, "setText", String.class, "option.yes");
		rh.addObject(jb3, "setText", String.class, "option.no");

		logger.debug("testing LangHandledObject texts by default");
		assertEquals("Hallo du!", jb1.getText());
		assertEquals("ja", jb2.getText());
		assertEquals("nein", jb3.getText());

		logger.debug("testing LangHandledObject texts EN");
		rh.setLocale(new Locale("en"));
		assertEquals(new Locale("en"), rh.getLocale());
		assertEquals("Hello You!", jb1.getText());
		assertEquals("yes", jb2.getText());
		assertEquals("no", jb3.getText());
	}

	@Test
	public void testInvalidConstructorArgs()
	{
		logger.debug("testing illegal constructor arguments");

		ResourceHandler rh = null;

		logger.debug("testing null Map");
		try
		{
			rh = new ResourceHandler(null);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The Map of ResourceBundles is null.", e.getMessage());
		}
		logger.debug("testing empty Map");
		try
		{
			rh = new ResourceHandler(new HashMap<Locale, ArrayList<ResourceBundle>>());
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The Map of ResourceBundles contains no Locale.", e.getMessage());
		}

		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));
		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);
		localeBundles.put(new Locale("en"), bundlesEN);

		logger.debug("testing Map without Systems default Locale");
		Locale old = Locale.getDefault();
		Locale.setDefault(new Locale("TMP"));
		try
		{
			rh = new ResourceHandler(localeBundles, null);
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("This ResourceHandler does not contain the given Locale: null", e.getMessage());
		}
		finally
		{
			Locale.setDefault(old);
		}
		assertNull(rh);
	}

	@Test
	public void testInvalidOperations()
	{
		logger.debug("testing invalid (not allowed) operations");

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

		try
		{
			rh.setLocale(new Locale("xyz"));
			fail("Expected Exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Locale \"xyz\" is not available in this ResourceHandler", e.getMessage());
		}
	}

	@Test
	public void testGetters()
	{
		logger.debug("testing all the getters");

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

		logger.debug("testing getObject(String)");
		assertEquals("ja", rh.getObject("option.yes"));
		assertEquals("nein", rh.getObject("option.no"));

		logger.debug("testing getObject(String,Locale)");
		assertEquals("ja", rh.getObject("option.yes", new Locale("de")));
		assertEquals("nein", rh.getObject("option.no", new Locale("de")));
		assertEquals("yes", rh.getObject("option.yes", new Locale("en")));
		assertEquals("no", rh.getObject("option.no", new Locale("en")));

		logger.debug("testing getObject(String,ResourceTransformer)");
		assertEquals(true, rh.getObject("option.boolean.1", new BooleanResourceTransformer()));
		assertEquals(false, rh.getObject("option.boolean.2", new BooleanResourceTransformer()));

		logger.debug("testing getObject(String,Locale,ResourceTransformer)");
		assertEquals(true, rh.getObject("option.boolean.1", new Locale("de"), new BooleanResourceTransformer()));
		assertEquals(false, rh.getObject("option.boolean.2", new Locale("de"), new BooleanResourceTransformer()));
		assertEquals(false, rh.getObject("option.boolean.1", new Locale("en"), new BooleanResourceTransformer()));
		assertEquals(true, rh.getObject("option.boolean.2", new Locale("en"), new BooleanResourceTransformer()));

		logger.debug("testing shortcut getters...");

		assertEquals(new AtomicInteger(123), rh.getAtomicInteger("option.number"));
		assertEquals(new AtomicInteger(123), rh.getAtomicInteger("option.number", new Locale("de")));
		assertEquals(new AtomicInteger(122), rh.getAtomicInteger("option.number", new Locale("en")));

		assertEquals(new AtomicLong(123), rh.getAtomicLong("option.number"));
		assertEquals(new AtomicLong(123), rh.getAtomicLong("option.number", new Locale("de")));
		assertEquals(new AtomicLong(122), rh.getAtomicLong("option.number", new Locale("en")));

		assertEquals(new BigDecimal("123"), rh.getBigDecimal("option.number"));
		assertEquals(new BigDecimal("123"), rh.getBigDecimal("option.number", new Locale("de")));
		assertEquals(new BigDecimal("122"), rh.getBigDecimal("option.number", new Locale("en")));

		assertEquals(new BigInteger("123"), rh.getBigInteger("option.number"));
		assertEquals(new BigInteger("123"), rh.getBigInteger("option.number", new Locale("de")));
		assertEquals(new BigInteger("122"), rh.getBigInteger("option.number", new Locale("en")));

		assertEquals(true, rh.getBoolean("option.boolean.1"));
		assertEquals(false, rh.getBoolean("option.boolean.2"));
		assertEquals(true, rh.getBoolean("option.boolean.1", new Locale("de")));
		assertEquals(false, rh.getBoolean("option.boolean.2", new Locale("de")));
		assertEquals(false, rh.getBoolean("option.boolean.1", new Locale("en")));
		assertEquals(true, rh.getBoolean("option.boolean.2", new Locale("en")));

		assertEquals(new Byte("123"), rh.getByte("option.number"));
		assertEquals(new Byte("123"), rh.getByte("option.number", new Locale("de")));
		assertEquals(new Byte("122"), rh.getByte("option.number", new Locale("en")));

		assertEquals(new Double("123"), rh.getDouble("option.number"));
		assertEquals(new Double("123"), rh.getDouble("option.number", new Locale("de")));
		assertEquals(new Double("122"), rh.getDouble("option.number", new Locale("en")));

		assertEquals(new Float("123"), rh.getFloat("option.number"));
		assertEquals(new Float("123"), rh.getFloat("option.number", new Locale("de")));
		assertEquals(new Float("122"), rh.getFloat("option.number", new Locale("en")));

		assertEquals(new Integer("123"), rh.getInteger("option.number"));
		assertEquals(new Integer("123"), rh.getInteger("option.number", new Locale("de")));
		assertEquals(new Integer("122"), rh.getInteger("option.number", new Locale("en")));

		assertEquals(new Long("123"), rh.getLong("option.number"));
		assertEquals(new Long("123"), rh.getLong("option.number", new Locale("de")));
		assertEquals(new Long("122"), rh.getLong("option.number", new Locale("en")));

		assertEquals(new Short("123"), rh.getShort("option.number"));
		assertEquals(new Short("123"), rh.getShort("option.number", new Locale("de")));
		assertEquals(new Short("122"), rh.getShort("option.number", new Locale("en")));
	}

	@Test
	public void testInformationGetters()
	{
		logger.debug("testing getters for locale information");

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

		logger.debug("testing getLocaleName()");
		assertEquals("Deutsch", rh.getLocaleName());
		assertEquals("Deutsch", rh.getLocaleName(new Locale("de")));
		assertEquals("English", rh.getLocaleName(new Locale("en")));

		logger.debug("testing getLocaleDescription()");
		assertEquals("Hochdeutsch", rh.getLocaleDescription());
		assertEquals("Hochdeutsch", rh.getLocaleDescription(new Locale("de")));
		assertEquals("English as spoken in Great Britain", rh.getLocaleDescription(new Locale("en")));

		logger.debug("testing getLocaleImagePath()");
		assertEquals("data/nations/Germany.gif", rh.getLocaleImagePath());
		assertEquals("data/nations/Germany.gif", rh.getLocaleImagePath(new Locale("de")));
		assertEquals("data/nations/GreatBritain.gif", rh.getLocaleImagePath(new Locale("en")));

		logger.debug("testing getLocaleImageIcon()");
		assertEquals(new ImageIcon("data/nations/Germany.gif").getImage(), rh.getLocaleImageIcon().getImage());
		assertEquals(new ImageIcon("data/nations/Germany.gif").getImage(), rh.getLocaleImageIcon(new Locale("de")).getImage());
		assertEquals(new ImageIcon("data/nations/GreatBritain.gif").getImage(), rh.getLocaleImageIcon(new Locale("en")).getImage());
	}

	@Test
	public void testReplace()
	{
		logger.debug("testing replacing");

		logger.debug("contructing ResourceHandler 1...");
		ResourceHandler rh = new ResourceHandler();

		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);

		rh.setLocaleBundles(localeBundles);
		rh.setLocale(new Locale("de"));

		logger.debug("contructing ResourceHandler 2...");
		ResourceHandler rh2 = new ResourceHandler();

		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles2 = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles2.put(new Locale("en"), bundlesEN);

		rh2.setLocaleBundles(localeBundles2);
		rh2.setLocale(new Locale("en"));

		logger.debug("expecting not equals...");
		assertNotSame(rh2, rh);

		logger.debug("replacing...");
		rh.replace(rh2);

		logger.debug("expecting equals...");
		assertEquals(rh2, rh);
	}

	@Test
	public void testClone()
	{
		logger.debug("testing cloning");

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

		ResourceHandler rh2 = (ResourceHandler) rh.clone();

		assertEquals(rh, rh2);
		assertEquals(rh.getAvailableLocales(), rh2.getAvailableLocales());
		assertEquals(rh.getLocale(), rh2.getLocale());
	}

	@Test
	public void testConstruct()
	{
		logger.debug("testing constructResourceHandler(String configurationBaseName)");
		ResourceSupportTestImplementation rsti = new ResourceSupportTestImplementation();

		logger.debug("Costructing ResourceHandler with interface replacing...");
		ResourceHandler rh1 = ResourceHandler.construct("test_resources");
		assertNotNull(rh1);

		logger.debug("Costructing ResourceHandler without interface replacing...");
		ResourceHandler rh2 = ResourceHandler.construct("test2_resources");
		assertNotNull(rh2);

		logger.debug("Comparing ResourceHandlers with each other");
		assertEquals(rh1, rsti.getResourceHandler());
		assertNotSame(rh2, rh1);
		assertNotSame(rh2, rsti.getResourceHandler());

		logger.debug("Checking locales of ResourceHandler 1");
		assertEquals(2, rh1.getAvailableLocales().size());
		assertTrue(rh1.getAvailableLocales().contains(new Locale("de")));
		assertTrue(rh1.getAvailableLocales().contains(new Locale("en")));

		logger.debug("Checking locales of ResourceHandler 2");
		assertEquals(2, rh2.getAvailableLocales().size());
		assertTrue(rh2.getAvailableLocales().contains(new Locale("de")));
		assertTrue(rh2.getAvailableLocales().contains(new Locale("en")));

		logger.debug("Checking content of ResourceHandler 1");
		assertEquals("Hallo du!", rh1.getString("message", new Locale("de")));
		assertEquals("Hello You!", rh1.getString("message", new Locale("en")));
		assertEquals("onlyone", rh1.getString("onlyone", new Locale("de")));
		assertEquals("onlyone", rh1.getString("onlyone", new Locale("en")));

		logger.debug("Checking content of ResourceHandler 2");
		assertEquals("message", rh2.getString("message", new Locale("de")));
		assertEquals("message", rh2.getString("message", new Locale("en")));
		assertEquals("deutsch", rh2.getString("onlyone", new Locale("de")));
		assertEquals("english", rh2.getString("onlyone", new Locale("en")));
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(ResourceHandlerTest.class);
	}
}
