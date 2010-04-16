package team.overfed.util.io.filefiltering;

import java.io.File;

/**
 * Class extending AbstractExtendedFileFilter to have a file filter that accepts all files but not
 * necessarily all directories.<br>
 * <br>
 * 
 * @author ultimate
 */
public class AcceptAllFileFilter extends AbstractExtendedFileFilter
{
	private static final String descDir = "Accepts all files and all directories.";
	private static final String descNoDir = "Accepts all files but no directories.";

	/**
	 * Construct a new AcceptAllFileFilter that also accepts all directories.<br>
	 * <br>
	 */
	public AcceptAllFileFilter()
	{
		super(true, descDir);
	}

	/**
	 * Construct a new AcceptAllFileFilter that accepts all directories according to the given
	 * boolean acceptDirectories.<br>
	 * <br>
	 * 
	 * @param acceptDirectories -
	 *            whether to accept directories or not
	 */
	public AcceptAllFileFilter(boolean acceptDirectories)
	{
		super(acceptDirectories, (acceptDirectories ? descDir : descNoDir));
	}

	@Override
	public boolean accept(File file)
	{
		if(file == null)
			throw new IllegalArgumentException("The given File is null.");
		if(file.isDirectory())
			return this.acceptDirectories;
		return true;
	}
}
