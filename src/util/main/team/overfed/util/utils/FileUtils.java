package team.overfed.util.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Class implementing useful operations on Files.<br>
 * <br>
 * Methods offer the opportunity to easily list complete directories including sub directories
 * filtered by a file filter or to get information about the file name.<br>
 * 
 * @author ultimate
 */
public abstract class FileUtils
{

	/**
	 * Convert a directory to a list of files.<br>
	 * This method calls itself recursively and adds all files or directories in this directory or
	 * any sub directory to the list of files.<br>
	 * <br>
	 * 
	 * @param directory
	 * @return
	 */
	public static ArrayList<File> convertDirectoryToFileList(File directory)
	{
		if(directory == null)
			throw new IllegalArgumentException("The given directory is null.");
		ArrayList<File> files = new ArrayList<File>();
		files.add(directory);
		if(directory.isDirectory())
		{
			for(File f : directory.listFiles())
			{
				if(f.isFile())
					files.add(f);
				else if(f.isDirectory())
					files.addAll(convertDirectoryToFileList(f));
			}
		}
		return files;
	}

	/**
	 * Convert a directory to a list of files.<br>
	 * This method calls itself recursively and adds all files or directories matching the given
	 * file filter in this directory or any sub directory to the list of files.<br>
	 * <br>
	 * 
	 * @param directory
	 * @return
	 */
	public static ArrayList<File> convertDirectoryToFileList(File directory, FileFilter filter)
	{
		if(directory == null)
			throw new IllegalArgumentException("The given directory is null.");
		if(filter == null)
			throw new IllegalArgumentException("The given FileFilter is null.");
		ArrayList<File> files = new ArrayList<File>();
		if(filter.accept(directory))
			files.add(directory);
		if(directory.isDirectory())
		{
			for(File f : directory.listFiles())
			{
				if(f.isFile())
				{
					if(filter.accept(f))
						files.add(f);
				}
				else if(f.isDirectory())
					files.addAll(convertDirectoryToFileList(f, filter));
			}
		}
		return files;
	}

	/**
	 * Get the extension of the given file.<br>
	 * The method will return the extension if the file has one.<br>
	 * If the file has no extension or is a directory an empty String is returned.<br>
	 * <br>
	 * 
	 * @param file -
	 *            the file to get the extension from
	 * @return - the file extension
	 */
	public static String getFileExtension(File file)
	{
		if(file == null)
			throw new IllegalArgumentException("The given file is null");
		if(file.isDirectory())
			return "";
		else
		{
			if(file.getName().lastIndexOf(".") == -1)
				return "";
			else
				return file.getName().substring(file.getName().lastIndexOf(".") + 1);
		}
	}

	/**
	 * Get the real name of a file.<br>
	 * The method will return the name without the file extension and the last dot.<br>
	 * If the file has no extension or is a directory the complete name will be returned.<br>
	 * <br>
	 * 
	 * @param file -
	 *            the file to get the name from
	 * @return - the file name without extension
	 */
	public static String getFileNameWithoutExtension(File file)
	{
		if(file == null)
			throw new IllegalArgumentException("The given file is null");
		String ext = getFileExtension(file);
		return file.getName().substring(0, file.getName().lastIndexOf(ext) - (ext.equals("") ? 0 : 1));
	}
}
