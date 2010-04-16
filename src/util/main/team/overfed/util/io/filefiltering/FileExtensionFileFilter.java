package team.overfed.util.io.filefiltering;

import java.io.File;
import java.util.ArrayList;

import team.overfed.util.utils.FileUtils;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files with a
 * given file extension.<br>
 * File extensions passed to this FileFilter must either be an empty String or String not containing
 * any dot.<br>
 * <br>
 * 
 * @author ultimate
 */
public class FileExtensionFileFilter extends AbstractExtendedFileFilter
{
	private static final String descDir = "Filters Files with extensions not set in this FileExtensionFileFilter. Accepts all directories.";
	private static final String descNoDir = "Filters Files with extensions not set in this FileExtensionFileFilter. Accepts no directories.";

	private ArrayList<String> extensions;

	/**
	 * Construct a new empty FileExtensionFileFilter configured with no extensions.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public FileExtensionFileFilter()
	{
		super(true, descDir);
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = true;
	}

	/**
	 * Construct a new FileExtensionFileFilter configured with the given extensions.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public FileExtensionFileFilter(String extension)
	{
		super(true, descDir);
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = true;
		this.addExtension(extension);
	}

	/**
	 * Construct a new FileExtensionFileFilter configured with the given extensions contained in the
	 * list.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public FileExtensionFileFilter(ArrayList<String> extensions)
	{
		super(true, descDir);
		if(extensions == null)
			throw new IllegalArgumentException("The given ArrayList of extensions is null.");
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = true;
		for(String extension : extensions)
			this.addExtension(extension);
	}

	/**
	 * Construct a new empty FileExtensionFileFilter configured with no extensions.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public FileExtensionFileFilter(boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = acceptDirectories;
	}

	/**
	 * Construct a new FileExtensionFileFilter configured with the given extensions.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public FileExtensionFileFilter(String extension, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = acceptDirectories;
		this.addExtension(extension);
	}

	/**
	 * Construct a new FileExtensionFileFilter configured with the given extensions contained in the
	 * list.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public FileExtensionFileFilter(ArrayList<String> extensions, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		if(extensions == null)
			throw new IllegalArgumentException("The given ArrayList of extensions is null.");
		this.extensions = new ArrayList<String>();
		this.acceptDirectories = acceptDirectories;
		for(String extension : extensions)
			this.addExtension(extension);
	}

	/**
	 * Add a file extension to an existing FileFilter.<br>
	 * 
	 * @param extension -
	 *            the file extension to add
	 */
	public void addExtension(String extension)
	{
		if(extension == null)
			throw new IllegalArgumentException("The given extension is null.");
		if(this.extensions.contains(extension))
			throw new IllegalArgumentException("The given extension is already contained in this FileFilter.");
		if(!extension.equals("") && (extension.indexOf(".") != -1))
			throw new IllegalArgumentException("The given String is not a valid extension.");
		this.extensions.add(extension);
	}

	/**
	 * Remove a file extension from an existing FileFilter.<br>
	 * 
	 * @param extension -
	 *            the file extension to remove
	 */
	public void removeExtension(String extension)
	{
		if(!this.extensions.contains(extension))
			throw new IllegalArgumentException("The given extension is not contained in this FileFilter.");
		this.extensions.remove(extension);
	}

	/**
	 * Get the list of file extensions stored in this FileExtensionFileFilter
	 * 
	 * @return
	 */
	public ArrayList<String> getExtensions()
	{
		return this.extensions;
	}

	@Override
	public boolean accept(File file)
	{
		if(file.isDirectory())
			return this.acceptDirectories;
		String extension = FileUtils.getFileExtension(file);
		if(this.extensions.contains(extension))
			return true;
		return false;
	}
}
