package team.overfed.util.utils;

import java.util.StringTokenizer;

/**
 * Class implementing useful operations on Strings.<br>
 * <br>
 * Methods offer the opportunity to modify a String so that it is better readable or easier to
 * handle for input and output.<br>
 * Methods are similar to String.trim() but offer more possibilities of variation.<br>
 * <br>
 * For example it is easy to trim and modify a String read from an xml file.<br>
 * Strings inside a tag which are indented can so be modified that the indent will not appear in the
 * application anymore or the opposite way.<br>
 * 
 * @author ultimate
 */
public abstract class StringUtils
{

	/**
	 * Insert the given insertion into the given text.<br>
	 * The insertion will be inserted at the beginning of each line.<br>
	 * 
	 * @param text -
	 *            the text to insert the insertion in as a <code>String</code>
	 * @param insertion -
	 *            the insertion to insert into the text as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String insertStringAtLineStart(String text, String insertion)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(insertion == null)
			throw new IllegalArgumentException("The given insertion is null.");
		if(text.length() == 0)
			return insertion;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(text, "\n", true);
		String tPrev = "\n";
		String tCurr = "";
		while(st.hasMoreTokens())
		{
			tCurr = st.nextToken();
			if(!tCurr.equals("\n") || tPrev.equals("\n"))
			{
				sb.append(insertion);
			}
			sb.append(tCurr);
			tPrev = tCurr;
		}
		return sb.toString();
	}

	/**
	 * Delete the leading white spaces of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteLeadingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		return text.substring(getFirstNonWhiteSpaceCharacterIndex(text), text.length());
	}

	/**
	 * Delete the tailing white spaces of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteTailingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		return text.substring(0, getLastNonWhiteSpaceCharacterIndex(text) + 1);
	}

	/**
	 * Delete the enclosing (leading and tailing) white spaces of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteEnclosingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		return deleteTailingWhiteSpaces(deleteLeadingWhiteSpaces(text));
	}

	/**
	 * Delete the leading white spaces in each line of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteLineLeadingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(text.length() == 0)
			return text;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(text, "\n", true);
		String tCurr = "";
		while(st.hasMoreTokens())
		{
			tCurr = st.nextToken();
			if(!tCurr.equals("\n"))
			{
				tCurr = deleteLeadingWhiteSpaces(tCurr);
			}
			sb.append(tCurr);
		}
		return deleteLeadingWhiteSpaces(sb.toString());
	}

	/**
	 * Delete the tailing white spaces in each line of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteLineTailingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(text.length() == 0)
			return text;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(text, "\n", true);
		String tCurr = "";
		while(st.hasMoreTokens())
		{
			tCurr = st.nextToken();
			if(!tCurr.equals("\n"))
			{
				tCurr = deleteTailingWhiteSpaces(tCurr);
			}
			sb.append(tCurr);
		}
		return deleteTailingWhiteSpaces(sb.toString());
	}

	/**
	 * Delete the enclosing (leading and tailing) white spaces in each line of a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String deleteLineEnclosingWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(text.length() == 0)
			return text;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(text, "\n", true);
		String tCurr = "";
		while(st.hasMoreTokens())
		{
			tCurr = st.nextToken();
			if(!tCurr.equals("\n"))
			{
				tCurr = deleteEnclosingWhiteSpaces(tCurr);
			}
			sb.append(tCurr);
		}
		return deleteEnclosingWhiteSpaces(sb.toString());
	}

	/**
	 * Get the index of the first non white space character in a given text.<br>
	 * Returns the index as specified above.<br>
	 * Returns the length of the text if the text only contains white spaces.<br>
	 * 
	 * @param text -
	 *            the text to search the non white space character in as a <code>String</code>
	 * @return - the first index of a non white space character in the given text as a
	 *         <code>String</code>
	 */
	public static int getFirstNonWhiteSpaceCharacterIndex(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(text.length() == 0)
			return 0;
		StringTokenizer st = new StringTokenizer(text);
		if(st.countTokens() > 0)
			return text.indexOf(st.nextToken());
		else
			return text.length();
	}

	/**
	 * Get the index of the last non white space character in a given text.<br>
	 * Returns the index as specified above.<br>
	 * Returns -1 if the text only contains white spaces.<br>
	 * 
	 * @param text -
	 *            the text to search the non white space character in as a <code>String</code>
	 * @return - the last index of a non white space character in the given text as a
	 *         <code>String</code>
	 */
	public static int getLastNonWhiteSpaceCharacterIndex(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		if(text.length() == 0)
			return -1;
		StringTokenizer st = new StringTokenizer(text);
		if(st.countTokens() > 0)
		{
			String ret = null;
			while(st.hasMoreTokens())
				ret = st.nextToken();
			return text.lastIndexOf(ret) + ret.length() - 1;
		}
		else
			return -1;
	}

	/**
	 * Delete all new line characters ("\n") from a given text.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String ignoreNewLine(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		return text.replace("\n", "");
	}

	/**
	 * Delete all new duplicate white spaces (" ", "\t", "\f") from a given text.<br>
	 * The white spaces are replaced by " ".<br>
	 * After the replacement there will be a maximum of one white space between two none white space
	 * characters.<br>
	 * 
	 * @param text -
	 *            the text to modify as a <code>String</code>
	 * @return - the modified text as a <code>String</code>
	 */
	public static String ignoreDuplicateWhiteSpaces(String text)
	{
		if(text == null)
			throw new IllegalArgumentException("The given text is null.");
		String ret = text;
		String ws = " \t\f"; // \n\r??
		boolean b = true;
		while(b)
		{
			b = false;
			for(int i = 0; i < ws.length(); i++)
			{
				for(int j = 0; j < ws.length(); j++)
				{
					ret = ret.replace(ws.charAt(i) + "" + ws.charAt(j), " ");
				}
			}
			for(int i = 0; i < ws.length(); i++)
			{
				for(int j = 0; j < ws.length(); j++)
				{
					b = b || ret.indexOf(ws.charAt(i) + "" + ws.charAt(j)) != -1;
				}
			}
		}
		return ret;
	}
}
