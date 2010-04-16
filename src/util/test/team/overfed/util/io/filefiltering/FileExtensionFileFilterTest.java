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
import team.overfed.util.io.filefiltering.FileExtensionFileFilter;
import team.overfed.util.utils.FileUtils;

/**
 * Class testing AbstractExtendedFileFilter.<br>
 * <br>
 * 
 * @author ultimate
 */
public class FileExtensionFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testFileExtensionFileFilter()
	{
		FileExtensionFileFilter ff;

		// one file extension - directories true - START
		logger.debug("testing correct initiation (string constructor arg)");
		ff = new FileExtensionFileFilter("java");

		assertNotNull(ff);
		assertTrue(ff instanceof FileExtensionFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().endsWith(ff.getExtensions().get(0)) || f.isDirectory();
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file extension - directories true - END
		// one file extension - directories false - START
		logger.debug("testing correct initiation (string,false constructor arg)");
		ff = new FileExtensionFileFilter("java", false);

		assertNotNull(ff);
		assertTrue(ff instanceof FileExtensionFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(false, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().endsWith(ff.getExtensions().get(0));
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file extension - directories false - END
		// more file extensions - directories true - START
		logger.debug("testing correct initiation (string,true constructor arg)");
		ff = new FileExtensionFileFilter(true);
		ff.addExtension("java");
		ff.addExtension("class");

		assertNotNull(ff);
		assertTrue(ff instanceof FileExtensionFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().endsWith(ff.getExtensions().get(0)) || f.getName().endsWith(ff.getExtensions().get(1)) || f.isDirectory();
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// more file extensions - directories true - END
	}

	@Test
	public void testOnRealDir()
	{
		logger.debug("testing on existing directory");

		File dir = new File("src/old");

		logger.debug("verifying number of files for 'java'");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("java"));
		assertNotNull(files);
		assertEquals(131, files.size());

		logger.debug("verifying number of files for 'java',false");
		ArrayList<File> filesNoDirs = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("java", false));
		assertNotNull(filesNoDirs);
		assertEquals(50, filesNoDirs.size());

		logger.debug("verifying number of files for 'class'");
		ArrayList<File> files2 = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("class"));
		assertNotNull(files2);
		assertEquals(132, files2.size());

		logger.debug("verifying number of files for 'class',false");
		ArrayList<File> files2NoDirs = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("class", false));
		assertNotNull(files2NoDirs);
		assertEquals(51, files2NoDirs.size());

		ArrayList<String> ext = new ArrayList<String>();
		ext.add("java");
		ext.add("class");

		logger.debug("verifying number of files for list");
		ArrayList<File> files3 = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter(ext));
		assertNotNull(files3);
		assertEquals(182, files3.size());

		logger.debug("verifying number of files for list,false");
		ArrayList<File> files3NoDirs = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter(ext, false));
		assertNotNull(files3NoDirs);
		assertEquals(101, files3NoDirs.size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(FileExtensionFileFilterTest.class);
	}
}
