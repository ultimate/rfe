package team.overfed.util.io.filefiltering;

import java.io.File;
import java.util.ArrayList;

import team.overfed.util.utils.FileUtils;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files with a
 * given file name suffix.<br>
 * File name suffix passed to this FileFilter can be anything that is supposed to be the suffix of
 * the names of all accepted files.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NameSuffixFileFilter extends AbstractExtendedFileFilter
{
	private static final String descDir = "Filters Files which filenames do not begin with a suffix set in this NamePrefiFileFilter. Accepts all directories.";
	private static final String descNoDir = "Filters Files which filenames do not begin with a suffix set in this NamePrefiFileFilter. Accepts no directories.";

	private ArrayList<String> suffixes;

	/**
	 * Construct a new empty NameSuffixFileFilter configured with no suffixes.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NameSuffixFileFilter()
	{
		super(true, descDir);
		this.suffixes = new ArrayList<String>();
	}

	/**
	 * Construct a new NameSuffixFileFilter configured with the given suffix.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NameSuffixFileFilter(String suffix)
	{
		super(true, descDir);
		if(suffix == null)
			throw new IllegalArgumentException("The given suffix is null.");
		this.suffixes = new ArrayList<String>();
		this.addSuffix(suffix);
	}

	/**
	 * Construct a new NameSuffixFileFilter configured with the given suffix.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NameSuffixFileFilter(String suffix, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		if(suffix == null)
			throw new IllegalArgumentException("The given suffix is null.");
		this.suffixes = new ArrayList<String>();
		this.addSuffix(suffix);
	}

	/**
	 * Construct a new NameSuffixFileFilter configured with the given suffixes contained in the
	 * list.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NameSuffixFileFilter(ArrayList<String> suffixes)
	{
		super(true, descDir);
		if(suffixes == null)
			throw new IllegalArgumentException("The given ArrayList of suffixes is null.");
		this.suffixes = suffixes;
	}

	/**
	 * Construct a new empty NameSuffixFileFilter configured with no suffixes.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NameSuffixFileFilter(boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		this.suffixes = new ArrayList<String>();
	}

	/**
	 * Construct a new NameSuffixFileFilter configured with the given suffixes contained in the
	 * list.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NameSuffixFileFilter(ArrayList<String> suffixes, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		if(suffixes == null)
			throw new IllegalArgumentException("The given ArrayList of suffixes is null.");
		this.suffixes = suffixes;
	}

	/**
	 * Add a file name suffix to an existing FileFilter.<br>
	 * 
	 * @param suffix -
	 *            the file name suffix to add
	 */
	public void addSuffix(String suffix)
	{
		if(suffix == null)
			throw new IllegalArgumentException("The given suffix is null.");
		if(this.suffixes.contains(suffix))
			throw new IllegalArgumentException("The given suffix is already contained in this FileFilter.");
		if(suffix.equals(""))
			throw new IllegalArgumentException("The given String is not a valid suffix.");
		this.suffixes.add(suffix);
	}

	/**
	 * Remove a file name suffix from an existing FileFilter.<br>
	 * 
	 * @param suffix -
	 *            the file name suffix to remove
	 */
	public void removeSuffix(String suffix)
	{
		if(!this.suffixes.contains(suffix))
			throw new IllegalArgumentException("The given suffix is not contained in this FileFilter.");
		this.suffixes.remove(suffix);
	}

	/**
	 * Get the list of file name suffixes stored in this FileExtensionFileFilter
	 * 
	 * @return
	 */
	public ArrayList<String> getSuffixes()
	{
		return this.suffixes;
	}

	@Override
	public boolean accept(File file)
	{
		if(file.isDirectory())
			return this.acceptDirectories;
		for(String suffix : suffixes)
		{
			if(FileUtils.getFileNameWithoutExtension(file).endsWith(suffix))
				return true;
		}
		return false;
	}
}
