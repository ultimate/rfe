package team.overfed.oae.logging;

import java.util.StringTokenizer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Class extending Logger to better readable multiline logging opportunities. This logger will split
 * every message into lines and will log each line separately.
 * 
 * @author ultimate
 */
public class OAELogger extends Logger
{
	private static String separator = "\n\r" + System.getProperty("line.separator");

	/**
	 * Taken from Log4j examples
	 */
	private static OAELoggerFactory oaeLoggerFactory = new OAELoggerFactory();
	private static final String FQCN = OAELogger.class.getName() + ".";

	/**
	 * Taken from Logger:<br>
	 * <br>
	 * Shorthand for <code>getLogger(clazz.getName())</code>.
	 * 
	 * @param clazz
	 *            The name of <code>clazz</code> will be used as the name of the logger to
	 *            retrieve. See {@link #getLogger(String)} for more detailed information.
	 */
	@SuppressWarnings("unchecked")
	public static Logger getLogger(Class clazz)
	{
		return Logger.getLogger(clazz.getName(), oaeLoggerFactory);
	}

	/**
	 * Taken from Logger:<br>
	 * <br>
	 * Retrieve a logger named according to the value of the <code>name</code> parameter. If the
	 * named logger already exists, then the existing instance will be returned. Otherwise, a new
	 * instance is created.<br>
	 * <br>
	 * By default, loggers do not have a set level but inherit it from their nearest ancestor with a
	 * set level. This is one of the central features of log4j.
	 * 
	 * @param name
	 *            The name of the logger to retrieve.
	 */
	public static Logger getLogger(String name)
	{
		return Logger.getLogger(name, oaeLoggerFactory);
	}

	/**
	 * Shorthand for <code>(OAELogger) getLogger(clazz.getName())</code>.<br>
	 * <br>
	 * Attention:<br>
	 * This variant of getLogger(...) may cause a ClassCastException if a logger with the same name
	 * but a different class has been initiated before.
	 * 
	 * @param clazz
	 *            The name of <code>clazz</code> will be used as the name of the logger to
	 *            retrieve. See {@link #getLogger(String)} for more detailed information.
	 */
	@SuppressWarnings("unchecked")
	public static OAELogger getOAELogger(Class clazz)
	{
		return (OAELogger) getLogger(clazz);
	}

	/**
	 * Shorthand for <code>(OAELogger) getLogger(name)</code>.<br>
	 * <br>
	 * Attention:<br>
	 * This variant of getLogger(...) may cause a ClassCastException if a logger with the same name
	 * but a different class has been initiated before.
	 * 
	 * @param name
	 *            The name of the logger to retrieve.
	 */
	public static OAELogger getOAELogger(String name)
	{
		return (OAELogger) getLogger(name);
	}

	/**
	 * Just calls the parent constructor.
	 */
	public OAELogger(String name)
	{
		super(name);
	}

	@Override
	public void debug(Object message)
	{
		logMultilineMessage(Level.DEBUG, message, null);
	}

	@Override
	public void debug(Object message, Throwable t)
	{
		logMultilineMessage(Level.DEBUG, message, t);
	}

	@Override
	public void info(Object message)
	{
		logMultilineMessage(Level.INFO, message, null);
	}

	@Override
	public void info(Object message, Throwable t)
	{
		logMultilineMessage(Level.INFO, message, t);
	}

	@Override
	public void warn(Object message)
	{
		logMultilineMessage(Level.WARN, message, null);
	}

	@Override
	public void warn(Object message, Throwable t)
	{
		logMultilineMessage(Level.WARN, message, t);
	}

	@Override
	public void error(Object message)
	{
		logMultilineMessage(Level.ERROR, message, null);
	}

	@Override
	public void error(Object message, Throwable t)
	{
		logMultilineMessage(Level.ERROR, message, t);
	}

	@Override
	public void fatal(Object message)
	{
		logMultilineMessage(Level.FATAL, message, null);
	}

	@Override
	public void fatal(Object message, Throwable t)
	{
		logMultilineMessage(Level.FATAL, message, t);
	}

	@Override
	public void trace(Object message)
	{
		logMultilineMessage(Level.TRACE, message, null);
	}

	@Override
	public void trace(Object message, Throwable t)
	{
		logMultilineMessage(Level.TRACE, message, t);
	}

	/**
	 * Split a message into lines and log them separately.
	 * 
	 * @param level -
	 *            the level to log the message with
	 * @param message -
	 *            the message to log
	 * @param t -
	 *            an optional Throwable
	 */
	private void logMultilineMessage(Level level, Object message, Throwable t)
	{
		StringTokenizer st = new StringTokenizer(message.toString(), separator);
		while(st.hasMoreTokens())
			super.log(FQCN, level, st.nextToken(), t);
	}
}
