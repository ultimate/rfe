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
public class NegotiatingFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testNegotiatingFileFilter()
	{
		NegotiatingFileFilter ff;

		// one file extension - directories true - START
		logger.debug("testing correct initiation (FileExtensionFileFilter('java') constructor arg)");
		ff = new NegotiatingFileFilter(new FileExtensionFileFilter("java"));

		logger.debug("testing correct initiation");
		assertNotNull(ff);
		assertTrue(ff instanceof NegotiatingFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources/lang/langfile.dtd");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = !ff.getFileFilter().accept(f);
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file extension - directories true - END
		// one file extension - directories false - START
		logger.debug("testing correct initiation (FileExtensionFileFilter('java'),false constructor arg)");
		ff = new NegotiatingFileFilter(new FileExtensionFileFilter("java"), false);

		assertNotNull(ff);
		assertTrue(ff instanceof NegotiatingFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		assertEquals(false, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = !ff.getFileFilter().accept(f);
			assertEquals(b, ff.accept(f));
			if(f.getParentFile() != null)
				assertEquals(b, ff.accept(f.getParentFile(), f.getName()));
		}
		// one file extension - directories false - END
	}

	@Test
	public void testOnRealDir()
	{
		logger.debug("testing on existing directory");

		File dir = new File("src/old");

		logger.debug("verifying number of files for FileExtensionFileFilter('java')");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new NegotiatingFileFilter(new FileExtensionFileFilter("java")));
		assertNotNull(files);
		assertEquals(357, files.size());

		logger.debug("verifying number of files for FileExtensionFileFilter('java'),false");
		ArrayList<File> filesNoDirs = FileUtils
				.convertDirectoryToFileList(dir, new NegotiatingFileFilter(new FileExtensionFileFilter("java"), false));
		assertNotNull(filesNoDirs);
		assertEquals(276, filesNoDirs.size());
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NegotiatingFileFilterTest.class);
	}
}
