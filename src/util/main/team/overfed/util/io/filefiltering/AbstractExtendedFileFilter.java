package team.overfed.util.io.filefiltering;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Abstract class implementing multiple existing java/sun file filters.<br>
 * <br>
 * This class is the base for individual file filters that offer the opportunity to filter files by
 * personal criteria.<br>
 * <br>
 * This class implements three interfaces to make it possible to use extending file filters for
 * java.swing.* components but also java.io.* components.<br>
 * <br>
 * Extends this class to override accept(File file) and modify it to fit your personal criteria on
 * filtering files.<br>
 * <br>
 * 
 * @author ultimate
 */
public abstract class AbstractExtendedFileFilter extends javax.swing.filechooser.FileFilter implements FileFilter, FilenameFilter
{
	protected boolean acceptDirectories;
	protected String description;

	/**
	 * Construct a new AbstractExtendedFileFilter with two necessary parameters:<br> - Does this
	 * file filter accept directories? The usage of this attribute has to be implemented in the
	 * accept(File file)-Method - A description of how this file filter handles files.
	 * 
	 * @param acceptDirectories -
	 *            a boolean whether this file filter accepts directories
	 * @param description -
	 *            the description
	 */
	public AbstractExtendedFileFilter(boolean acceptDirectories, String description)
	{
		this.acceptDirectories = acceptDirectories;
		this.description = description;
	}

	/**
	 * Does this file filter accept directories?
	 * 
	 * @return - true if yes, false otherwise
	 */
	public final boolean acceptsDirectories()
	{
		return acceptDirectories;
	}

	/**
	 * Set if this file filter accepts directories.
	 * 
	 * @param -
	 *            the parameter as a boolean
	 */
	public final void setAcceptDirectories(boolean acceptDirectories)
	{
		this.acceptDirectories = acceptDirectories;
	}

	@Override
	public final String getDescription()
	{
		return this.description;
	}

	@Override
	public final boolean accept(File dir, String name)
	{
		if(dir == null)
			throw new IllegalArgumentException("The given File dir is null.");
		if(name == null)
			throw new IllegalArgumentException("The given name is null.");
		return accept(new File(dir.getPath() + "/" + name));
	}

	@Override
	public abstract boolean accept(File file);
}
