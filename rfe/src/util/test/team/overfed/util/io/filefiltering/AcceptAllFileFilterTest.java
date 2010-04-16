package team.overfed.util.io.filefiltering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.io.filefiltering.AbstractExtendedFileFilter;
import team.overfed.util.io.filefiltering.AcceptAllFileFilter;
import team.overfed.util.utils.FileUtils;

/**
 * Class testing AbstractExtendedFileFilter.<br>
 * <br>
 * 
 * @author ultimate
 */
public class AcceptAllFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testAcceptAllFileFilter()
	{
		AcceptAllFileFilter ff;

		// no constructor arguments - START
		logger.debug("testing correct initiation (no constructor arg)");
		ff = new AcceptAllFileFilter();

		assertNotNull(ff);
		assertTrue(ff instanceof AcceptAllFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			assertEquals(true, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(true, ff.accept(f.getParentFile(), f.getName()));
		}
		// no constructor arguments - END
		// constructor argument - true - START
		logger.debug("testing correct initiation (true constructor arg)");
		ff = new AcceptAllFileFilter(true);

		assertNotNull(ff);
		assertTrue(ff instanceof AcceptAllFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f2 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f2))
		{
			assertEquals(true, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(true, ff.accept(f.getParentFile(), f.getName()));
		}
		// constructor argument - true - END
		// constructor argument - false - START
		logger.debug("testing correct initiation (false constructor arg)");
		ff = new AcceptAllFileFilter(false);

		assertNotNull(ff);
		assertTrue(ff instanceof AcceptAllFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(false, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f3 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f3))
		{
			assertEquals(!f.isDirectory(), ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(!f.isDirectory(), ff.accept(f.getParentFile(), f.getName()));
		}
		// constructor argument - false - END
	}

	@Test
	public void testOnRealDir()
	{
		logger.debug("testing on existing directory");

		File dir = new File("src/old");

		logger.debug("verifying number of files");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new AcceptAllFileFilter());
		assertNotNull(files);
		assertEquals(407, files.size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(AcceptAllFileFilterTest.class);
	}
}
