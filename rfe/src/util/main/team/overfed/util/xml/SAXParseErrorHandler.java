package team.overfed.util.xml;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Class extending DefaultHandler to implement an ErrorHandler for SAX parsing.<br>
 * <br>
 * This SAXParseErrorHandler is necessary to manage all Exceptions thrown during a parsing process.
 * The ErrorHandler stores all Exceptions so that the parsing can proceed without being interrupted.<br>
 * After the parsing process all errors can be read from the ErrorHandler to have a detailed list of
 * all Errors recognized.<br>
 * <br>
 * 
 * @author ultimate
 */
public class SAXParseErrorHandler extends DefaultHandler
{
	/**
	 * Enumeration representing the ErrorLevels used in a DefaultHandler.<br>
	 * <br>
	 * 
	 * @author ultimate
	 */
	public static enum ErrorLevel
	{
		ERROR, FATAL_ERROR, WARNING;
	}

	/**
	 * Lists containing the Errors and their levels
	 */
	private ArrayList<SAXParseException> ex;
	private ArrayList<ErrorLevel> levels;

	/**
	 * Construct a new and empty SAXParseErrorHandler
	 */
	public SAXParseErrorHandler()
	{
		super();
		this.ex = new ArrayList<SAXParseException>();
		this.levels = new ArrayList<ErrorLevel>();
	}

	@Override
	public void error(SAXParseException e) throws SAXException
	{
		this.ex.add(e);
		this.levels.add(ErrorLevel.ERROR);
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException
	{
		this.ex.add(e);
		this.levels.add(ErrorLevel.FATAL_ERROR);
		super.fatalError(e);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException
	{
		this.ex.add(e);
		this.levels.add(ErrorLevel.WARNING);
		super.warning(e);
	}

	/**
	 * Evaluate if there have been added Exceptions to this ErrorHandler during the parsing process.<br>
	 * <br>
	 * return - true is there have been Exceptions, false otherwise
	 */
	public boolean hasSAXParseExceptions()
	{
		return (this.ex.size() > 0);
	}

	/**
	 * Get the list of Exceptions stored in this ErrorHandler.<br>
	 * <br>
	 * 
	 * @return - the list of SAXParseExceptions
	 */
	public ArrayList<SAXParseException> getSAXParseExceptions()
	{
		return this.ex;
	}

	/**
	 * Get the list of ErrorLevels stored in this ErrorHandler.<br>
	 * <br>
	 * 
	 * @return - the list of ErrorLevels
	 */
	public ArrayList<ErrorLevel> getSAXParseExceptionsLevels()
	{
		return this.levels;
	}

	/**
	 * Get the Exception stored at the given index if there is one.<br>
	 * <br>
	 * 
	 * @param index -
	 *            the index of the Exception wanted
	 * @return - the Exception at the specified index
	 */
	public SAXParseException getSAXParseException(int index)
	{
		return this.ex.get(index);
	}

	/**
	 * Get the ErrorLevel stored at the given index if there is one.<br>
	 * <br>
	 * 
	 * @param index -
	 *            the index of the ErrorLevel wanted
	 * @return - the ErrorLevel at the specified index
	 */
	public ErrorLevel getSAXParseExceptionLevel(int index)
	{
		return this.levels.get(index);
	}

	/**
	 * Get the indexes at which the given Exception is in the list stored in this ErrorHandler.<br>
	 * <br>
	 * 
	 * @param e -
	 *            the Exception to look for
	 * @return - the indexes matching the given Exception
	 */
	public int[] getIndexForSAXParseException(SAXParseException e)
	{
		ArrayList<Integer> index = new ArrayList<Integer>();
		int i = 0;
		for(SAXParseException ei : ex)
		{
			if(ei.equals(e))
				index.add(i);
			i++;
		}
		Integer[] tmp = index.toArray(new Integer[] {});
		int[] ret = new int[tmp.length];
		for(i = 0; i < tmp.length; i++)
			ret[i] = tmp[i];
		return ret;
	}
}
