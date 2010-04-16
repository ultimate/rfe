package team.overfed.util.io.filefiltering;

import java.io.File;
import java.util.ArrayList;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files with a
 * given file name prefix.<br>
 * File name prefix passed to this FileFilter can be anything that is supposed to be the prefix of
 * the names of all accepted files.<br>
 * <br>
 * 
 * @author ultimate
 */
public class NamePrefixFileFilter extends AbstractExtendedFileFilter
{
	private static final String descDir = "Filters Files which filenames do not begin with a prefix set in this NamePrefiFileFilter. Accepts all directories.";
	private static final String descNoDir = "Filters Files which filenames do not begin with a prefix set in this NamePrefiFileFilter. Accepts no directories.";

	private ArrayList<String> prefixes;

	/**
	 * Construct a new empty NamePrefixFileFilter configured with no prefixes.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NamePrefixFileFilter()
	{
		super(true, descDir);
		this.prefixes = new ArrayList<String>();
	}

	/**
	 * Construct a new NamePrefixFileFilter configured with the given prefix.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NamePrefixFileFilter(String prefix)
	{
		super(true, descDir);
		if(prefix == null)
			throw new IllegalArgumentException("The given prefix is null.");
		this.prefixes = new ArrayList<String>();
		this.addPrefix(prefix);
	}

	/**
	 * Construct a new NamePrefixFileFilter configured with the given prefix.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NamePrefixFileFilter(String prefix, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		if(prefix == null)
			throw new IllegalArgumentException("The given prefix is null.");
		this.prefixes = new ArrayList<String>();
		this.addPrefix(prefix);
	}

	/**
	 * Construct a new NamePrefixFileFilter configured with the given prefixes contained in the
	 * list.<br>
	 * The FileFilter will accept all directories.<br>
	 */
	public NamePrefixFileFilter(ArrayList<String> prefixes)
	{
		super(true, descDir);
		if(prefixes == null)
			throw new IllegalArgumentException("The given ArrayList of prefixes is null.");
		this.prefixes = prefixes;
	}

	/**
	 * Construct a new empty NamePrefixFileFilter configured with no prefixes.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NamePrefixFileFilter(boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		this.prefixes = new ArrayList<String>();
	}

	/**
	 * Construct a new NamePrefixFileFilter configured with the given prefixes contained in the
	 * list.<br>
	 * The FileFilter will accept all directories depending on the given parameter
	 * acceptDirectories.<br>
	 */
	public NamePrefixFileFilter(ArrayList<String> prefixes, boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
		if(prefixes == null)
			throw new IllegalArgumentException("The given ArrayList of prefixes is null.");
		this.prefixes = prefixes;
	}

	/**
	 * Add a file name prefix to an existing FileFilter.<br>
	 * 
	 * @param prefix -
	 *            the file name prefix to add
	 */
	public void addPrefix(String prefix)
	{
		if(prefix == null)
			throw new IllegalArgumentException("The given prefix is null.");
		if(this.prefixes.contains(prefix))
			throw new IllegalArgumentException("The given prefix is already contained in this FileFilter.");
		if(prefix.equals(""))
			throw new IllegalArgumentException("The given String is not a valid prefix.");
		this.prefixes.add(prefix);
	}

	/**
	 * Remove a file name prefix from an existing FileFilter.<br>
	 * 
	 * @param prefix -
	 *            the file name prefix to remove
	 */
	public void removePrefix(String prefix)
	{
		if(!this.prefixes.contains(prefix))
			throw new IllegalArgumentException("The given prefix is not contained in this FileFilter.");
		this.prefixes.remove(prefix);
	}

	/**
	 * Get the list of file name prefixes stored in this FileExtensionFileFilter
	 * 
	 * @return
	 */
	public ArrayList<String> getPrefixes()
	{
		return this.prefixes;
	}

	@Override
	public boolean accept(File file)
	{
		if(file.isDirectory())
			return this.acceptDirectories;
		for(String prefix : prefixes)
		{
			if(file.getName().startsWith(prefix))
				return true;
		}
		return false;
	}
}
