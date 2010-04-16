package team.overfed.util.io.filefiltering;

import static org.junit.Assert.*;

import java.io.FileFilter;
import java.io.FilenameFilter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.io.filefiltering.AbstractExtendedFileFilter;
import team.overfed.util.io.filefiltering.AcceptAllFileFilter;

import junit.framework.JUnit4TestAdapter;

/**
 * Class testing AbstractExtendedFileFilter.<br>
 * <br>
 * 
 * @author ultimate
 */
public class AbstractExtendedFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testAbstractExtendedFileFilter()
	{
		logger.debug("testing correct initiation");

		AbstractExtendedFileFilter aeff = new AcceptAllFileFilter();

		assertNotNull(aeff);
		assertTrue(aeff instanceof AbstractExtendedFileFilter);
		assertTrue(aeff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(aeff instanceof FileFilter);
		assertTrue(aeff instanceof FilenameFilter);
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(AbstractExtendedFileFilterTest.class);
	}
}
