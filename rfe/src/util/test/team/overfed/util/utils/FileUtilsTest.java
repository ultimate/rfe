package team.overfed.util.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.io.filefiltering.AcceptAllFileFilter;
import team.overfed.util.io.filefiltering.FileExtensionFileFilter;

/**
 * Class testing FileUtils.<br>
 * <br>
 * 
 * @author ultimate
 */
public class FileUtilsTest extends OAELoggerTestCase
{

	@Test
	public void testConvertDirectoryToFileListNoFileFilter()
	{
		logger.debug("starting test...");

		File dir = new File("src/old");

		logger.debug("checking number of files");
		ArrayList<File> all = FileUtils.convertDirectoryToFileList(dir);
		assertNotNull(all);
		assertEquals(407, all.size());

		logger.debug("successful");
	}

	@Test
	public void testConvertDirectoryToFileListWithFileFilter()
	{
		logger.debug("starting test...");

		File dir = new File("src/old");

		logger.debug("checking number of all files");
		ArrayList<File> all = FileUtils.convertDirectoryToFileList(dir, new AcceptAllFileFilter());
		assertNotNull(all);
		assertEquals(407, all.size());

		logger.debug("checking number of 'java' files");
		ArrayList<File> javaFiles = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("java", false));
		assertNotNull(javaFiles);
		assertEquals(50, javaFiles.size());

		logger.debug("checking number of 'class' files");
		ArrayList<File> classFiles = FileUtils.convertDirectoryToFileList(dir, new FileExtensionFileFilter("class", false));
		assertNotNull(classFiles);
		assertEquals(51, classFiles.size());

		logger.debug("successful");
	}

	@Test
	public void testGetFileExtension()
	{
		logger.debug("starting test...");

		logger.debug("constructing 5 virtual file names");
		String[] names = { "adslhf.", "dskf.sdfa.", "salkf7330.dsfa. da.", "resources" };

		logger.debug("constructing 3 virtual file extensions");
		String[] extensions = { "123", "java", "" };

		File f;
		for(int n = 0; n < names.length; n++)
		{
			for(int e = 0; e < extensions.length; e++)
			{
				f = new File(names[n] + extensions[e]);
				logger.debug("verifying " + f.getName());
				if(!names[n].equals(names[3]))
					assertEquals(extensions[e], FileUtils.getFileExtension(f));
				else
					assertEquals("", FileUtils.getFileExtension(f));
			}
		}
	}

	@Test
	public void testGetFileNameWithoutExtension()
	{
		logger.debug("starting test...");

		logger.debug("constructing 5 virtual file names");
		String[] names = { "adslhf", "dskfsdfa", "salkf7330dsfa da", "resources" };

		logger.debug("constructing 3 virtual file extensions");
		String[] extensions = { ".123", ".java", "" };

		File f;
		for(int n = 0; n < names.length; n++)
		{
			for(int e = 0; e < extensions.length; e++)
			{
				f = new File(names[n] + extensions[e]);
				logger.debug("verifying " + f.getName());
				assertEquals(names[n], FileUtils.getFileNameWithoutExtension(f));
			}
		}
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(FileUtilsTest.class);
	}
}
