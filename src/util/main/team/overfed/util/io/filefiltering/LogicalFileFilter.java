package team.overfed.util.io.filefiltering;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import team.overfed.util.utils.BooleanUtils;
import team.overfed.util.utils.BooleanUtils.LogicalMode;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files which are
 * accepted by other FileFilter due to a logical operator .<br>
 * FileFilter will return the result if the file matches one or more FileFilters at the same time.
 * Accepting of directories can be set explicitly.<br>
 * <br>
 * 
 * @author ultimate
 */
public class LogicalFileFilter extends AbstractExtendedFileFilter
{
	private static final String desc = "Accepts files depending on the LogicalMode set in this LogicalFileFilter.";

	private LogicalMode mode;
	private ArrayList<FileFilter> fileFilters;

	/**
	 * Construct a new empty LogicalFileFilter configured with no FileFilters but a necessary
	 * logical operator.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode)
	{
		super(true, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
	}

	/**
	 * Construct a new empty LogicalFileFilter configured with no FileFilters but a necessary
	 * logical operator.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode, boolean acceptDirectories)
	{
		super(acceptDirectories, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
	}

	/**
	 * Construct a new LogicalFileFilter configured with another FileFilter and a necessary logical
	 * operator.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode, FileFilter fileFilter)
	{
		super(true, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		if(fileFilter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
		this.addFileFilter(fileFilter);
	}

	/**
	 * Construct a new LogicalFileFilter configured with another FileFilter and a necessary logical
	 * operator.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode, FileFilter fileFilter, boolean acceptDirectories)
	{
		super(acceptDirectories, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		if(fileFilter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
		this.addFileFilter(fileFilter);
	}

	/**
	 * Construct a new LogicalFileFilter configured with the given FileFilters and a necessary
	 * logical operator.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode, ArrayList<FileFilter> fileFilters)
	{
		super(true, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		if(fileFilters == null)
			throw new IllegalArgumentException("The given ArrayList of FileFilters is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
		for(FileFilter fileFilter : fileFilters)
			this.addFileFilter(fileFilter);
	}

	/**
	 * Construct a new LogicalFileFilter configured with the given FileFilters and a necessary
	 * logical operator.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public LogicalFileFilter(LogicalMode mode, ArrayList<FileFilter> fileFilters, boolean acceptDirectories)
	{
		super(acceptDirectories, desc);
		if(mode == null)
			throw new IllegalArgumentException("The given LogicalMode is null.");
		if(fileFilters == null)
			throw new IllegalArgumentException("The given ArrayList of FileFilters is null.");
		this.mode = mode;
		this.fileFilters = new ArrayList<FileFilter>();
		for(FileFilter fileFilter : fileFilters)
			this.addFileFilter(fileFilter);
	}

	/**
	 * Add a FileFilter to an existing FileFilter.<br>
	 * 
	 * @param fileFilter -
	 *            the FileFilter to add
	 */
	public void addFileFilter(FileFilter fileFilter)
	{
		if(fileFilter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		if(this.fileFilters.contains(fileFilter))
			throw new IllegalArgumentException("The given FileFilter is already contained in this FileFilter.");
		this.fileFilters.add(fileFilter);
	}

	/**
	 * Remove a FileFilter from an existing FileFilter.<br>
	 * 
	 * @param fileFilter -
	 *            the FileFilter to remove
	 */
	public void removeFileFilter(FileFilter fileFilter)
	{
		if(!this.fileFilters.contains(fileFilter))
			throw new IllegalArgumentException("The given FileFilter is not contained in this FileFilter.");
		this.fileFilters.remove(fileFilter);
	}

	/**
	 * Get the list of FileFilters stored in this LogicalFileFilter
	 * 
	 * @return
	 */
	public ArrayList<FileFilter> getFileFilters()
	{
		return this.fileFilters;
	}

	/**
	 * Get the LogicalMode stored in this LogicalFileFilter
	 * 
	 * @return
	 */
	public LogicalMode getMode()
	{
		return mode;
	}

	@Override
	public boolean accept(File file)
	{
		if(file == null)
			throw new IllegalArgumentException("The given File is null.");
		if(file.isDirectory())
			return this.acceptDirectories;
		Boolean[] b = new Boolean[this.fileFilters.size()];
		for(int i = 0; i < b.length; i++)
		{
			b[i] = this.fileFilters.get(i).accept(file);
		}
		return BooleanUtils.logical(mode, b);
	}
}
