package team.overfed.util.io.filefiltering;

import java.io.File;
import java.io.FileFilter;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files which are
 * not accepted by another FileFilter and that not accepts files accepted by the other FileFilter.<br>
 * FileFilter will completely return the opposite result which would be returned by the other
 * FileFilter except of the result returned for directories. Accepting of directories can be set
 * explicitly.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NegotiatingFileFilter extends AbstractExtendedFileFilter
{
	private static final String desc = "Accepts files not excepted by the FileFilter set in this NegotiatingFileFilter.";

	private FileFilter fileFilter;

	/**
	 * Construct a new NegotiatingFileFilter configured with another FileFilter.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NegotiatingFileFilter(FileFilter fileFilter)
	{
		super(true, desc);
		if(fileFilter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		this.fileFilter = fileFilter;
	}

	/**
	 * Construct a new NegotiatingFileFilter configured with another FileFilter.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NegotiatingFileFilter(FileFilter fileFilter, boolean acceptDirectories)
	{
		super(true, desc);
		if(fileFilter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		this.fileFilter = fileFilter;
		this.acceptDirectories = acceptDirectories;
	}

	/**
	 * Get the FileFilter stored in this NegotiatingFileFilter
	 * 
	 * @return
	 */
	public FileFilter getFileFilter()
	{
		return this.fileFilter;
	}

	@Override
	public boolean accept(File file)
	{
		if(file == null)
			throw new IllegalArgumentException("The given File is null.");
		if(file.isDirectory())
			return this.acceptDirectories;
		return !this.fileFilter.accept(file);
	}
}
