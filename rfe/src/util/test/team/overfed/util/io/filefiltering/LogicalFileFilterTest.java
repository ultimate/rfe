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
import team.overfed.util.io.filefiltering.LogicalFileFilter;
import team.overfed.util.utils.FileUtils;
import team.overfed.util.utils.BooleanUtils.LogicalMode;

/**
 * Class testing AbstractExtendedFileFilter.<br>
 * <br>
 * 
 * @author ultimate
 */
public class LogicalFileFilterTest extends OAELoggerTestCase
{

	@Test
	public void testLogicalFileFilter()
	{
		LogicalFileFilter ff;

		// one file extension - directories true - START
		logger.debug("testing correct initiation (AND constructor arg)");
		ff = new LogicalFileFilter(LogicalMode.AND);

		assertNotNull(ff);
		assertTrue(ff instanceof LogicalFileFilter);
		assertTrue(ff instanceof AbstractExtendedFileFilter);
		assertTrue(ff instanceof javax.swing.filechooser.FileFilter);
		assertTrue(ff instanceof FileFilter);
		assertTrue(ff instanceof FilenameFilter);

		ff.addFileFilter(new FileExtensionFileFilter("java"));
		ff.addFileFilter(new FileExtensionFileFilter("class"));

		assertEquals(true, ff.acceptsDirectories());

		logger.debug("testing on simple file");
		File f1 = new File("resources");
		for(File f : FileUtils.convertDirectoryToFileList(f1))
		{
			boolean b = f.getName().endsWith(((FileExtensionFileFilter) ff.getFileFilters().get(0)).getExtensions().get(0))
					|| f.getName().endsWith(((FileExtensionFileFilter) ff.getFileFilters().get(1)).getExtensions().get(0)) || f.isDirectory();
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

		logger.debug("verifying number of files for OR,'java'");
		ArrayList<File> files = FileUtils.convertDirectoryToFileList(dir, new LogicalFileFilter(LogicalMode.OR, new FileExtensionFileFilter("java")));
		assertNotNull(files);
		assertEquals(131, files.size());

		logger.debug("verifying number of files for OR,'java',false");
		ArrayList<File> filesNoDirs = FileUtils.convertDirectoryToFileList(dir, new LogicalFileFilter(LogicalMode.OR, new FileExtensionFileFilter(
				"java"), false));
		assertNotNull(filesNoDirs);
		assertEquals(50, filesNoDirs.size());

		logger.debug("verifying number of files for OR,'class'");
		ArrayList<File> files2 = FileUtils.convertDirectoryToFileList(dir,
				new LogicalFileFilter(LogicalMode.OR, new FileExtensionFileFilter("class")));
		assertNotNull(files2);
		assertEquals(132, files2.size());

		logger.debug("verifying number of files for OR,'class',false");
		ArrayList<File> files2NoDirs = FileUtils.convertDirectoryToFileList(dir, new LogicalFileFilter(LogicalMode.OR, new FileExtensionFileFilter(
				"class"), false));
		assertNotNull(files2NoDirs);
		assertEquals(51, files2NoDirs.size());

		ArrayList<FileFilter> ffs = new ArrayList<FileFilter>();
		ffs.add(new FileExtensionFileFilter("java"));
		ffs.add(new FileExtensionFileFilter("class"));

		logger.debug("verifying number of files for OR,list,false");
		ArrayList<File> files3 = FileUtils.convertDirectoryToFileList(dir, new LogicalFileFilter(LogicalMode.OR, ffs));
		assertNotNull(files3);
		assertEquals(182, files3.size());

		ArrayList<File> files3NoDirs = FileUtils.convertDirectoryToFileList(dir, new LogicalFileFilter(LogicalMode.OR, ffs, false));
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
		return new JUnit4TestAdapter(LogicalFileFilterTest.class);
	}
}
