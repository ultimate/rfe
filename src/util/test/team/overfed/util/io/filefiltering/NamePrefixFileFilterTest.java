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
public class NamePrefixFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testNamePrefixFileFilter()
	{
		NamePrefixFileFilter ff;

		// one file prefix - directories true - START
		logger.debug("testing correct initiation (string constructor arg)");
		ff = new NamePrefixFileFilter("RFE");

		assertNotNull(ff);
		assertTrue(ff instanceof NamePrefixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getPrefixes().get(0)) || f.isDirectory();
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file prefix - directories true - END
		// one file prefix - directories false - START
		logger.debug("testing correct initiation (string,false constructor arg)");
		ff = new NamePrefixFileFilter("RFE", false);

		assertNotNull(ff);
		assertTrue(ff instanceof NamePrefixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(false, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getPrefixes().get(0));
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file prefix - directories false - END
		// more file prefix - directories true - START
		logger.debug("testing correct initiation (list,true constructor arg)");
		ff = new NamePrefixFileFilter(true);
		ff.addPrefix("RFE");
		ff.addPrefix("rfe");

		assertNotNull(ff);
		assertTrue(ff instanceof NamePrefixFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().startsWith(ff.getPrefixes().get(0)) || f.getName().startsWith(ff.getPrefixes().get(1)) || f.isDirectory();
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

		logger.debug("verifying number of files for 'RFE'");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter("RFE"));
		assertNotNull(files);
		assertEquals(101, files.size());

		logger.debug("verifying number of files for 'RFE',false");
		ArrayList<File> filesNoDirs = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter("RFE", false));
		assertNotNull(filesNoDirs);
		assertEquals(20, filesNoDirs.size());

		logger.debug("verifying number of files for 'rfe'");
		ArrayList<File> files2 = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter("rfe"));
		assertNotNull(files2);
		assertEquals(90, files2.size());

		logger.debug("verifying number of files for 'rfe',false");
		ArrayList<File> files2NoDirs = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter("rfe", false));
		assertNotNull(files2NoDirs);
		assertEquals(9, files2NoDirs.size());

		ArrayList<String> ext = new ArrayList<String>();
		ext.add("RFE");
		ext.add("rfe");

		logger.debug("verifying number of files for list");
		ArrayList<File> files3 = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter(ext));
		assertNotNull(files3);
		assertEquals(110, files3.size());

		logger.debug("verifying number of files for list,false");
		ArrayList<File> files3NoDirs = FileUtils.convertDirectoryToFileList(dir, new NamePrefixFileFilter(ext, false));
		assertNotNull(files3NoDirs);
		assertEquals(29, files3NoDirs.size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NamePrefixFileFilterTest.class);
	}
}
