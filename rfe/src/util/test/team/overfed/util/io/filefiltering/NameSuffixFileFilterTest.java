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
import team.overfed.util.utils.FileUtils;

/**
 * Class testing AbstractExtendedFileFilter.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NameSuffixFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testNameSuffixFileFilter()
	{
		NameSuffixFileFilter ff;

		// one file prefix - directories true - START
		logger.debug("testing correct initiation (string constructor arg)");
		ff = new NameSuffixFileFilter("Listener");

		assertNotNull(ff);
		assertTrue(ff instanceof NameSuffixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getSuffixes().get(0)) || f.isDirectory();
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file prefix - directories true - END
		// one file prefix - directories false - START
		logger.debug("testing correct initiation (string,false constructor arg)");
		ff = new NameSuffixFileFilter("Panel", false);

		assertNotNull(ff);
		assertTrue(ff instanceof NameSuffixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(false, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getSuffixes().get(0));
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file prefix - directories false - END
		// more file prefix - directories true - START
		logger.debug("testing correct initiation (true constructor arg)");
		ff = new NameSuffixFileFilter(true);
		ff.addSuffix("Listener");
		ff.addSuffix("Panel");

		assertNotNull(ff);
		assertTrue(ff instanceof NameSuffixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getSuffixes().get(0)) || f.getName().startsWith(ff.getSuffixes().get(1)) || f.isDirectory();
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// more file prefix - directories true - END
	}

	@Test
	public void testOnRealDir()
	{
		logger.debug("testing on existing directory");

		File dir = new File("src/old");

		logger.debug("verifying number of files for 'Listener'");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter("Listener"));
		assertNotNull(files);
		assertEquals(113, files.size());

		logger.debug("verifying number of files for 'Listener',false");
		ArrayList<File> filesNoDirs = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter("Listener", false));
		assertNotNull(filesNoDirs);
		assertEquals(32, filesNoDirs.size());

		logger.debug("verifying number of files for 'Panel'");
		ArrayList<File> files2 = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter("Panel"));
		assertNotNull(files2);
		assertEquals(97, files2.size());

		logger.debug("verifying number of files for 'Panel',false");
		ArrayList<File> files2NoDirs = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter("Panel", false));
		assertNotNull(files2NoDirs);
		assertEquals(16, files2NoDirs.size());

		ArrayList<String> ext = new ArrayList<String>();
		ext.add("Listener");
		ext.add("Panel");

		logger.debug("verifying number of files for list");
		ArrayList<File> files3 = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter(ext));
		assertNotNull(files3);
		assertEquals(129, files3.size());

		logger.debug("verifying number of files for list,false");
		ArrayList<File> files3NoDirs = FileUtils.convertDirectoryToFileList(dir, new NameSuffixFileFilter(ext, false));
		assertNotNull(files3NoDirs);
		assertEquals(48, files3NoDirs.size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NameSuffixFileFilterTest.class);
	}
}
