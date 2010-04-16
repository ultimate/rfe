package team.overfed.oae.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Own LoggerFactory to match standard procedures while creating loggers.<br>
 * Use OAELogger.getOAELogger(...) to create a new Logger with this OAELoggerFactory.
 * 
 * @author ultimate
 */
public class OAELoggerFactory implements LoggerFactory
{
	/**
	 * Just calls the parent constructor.
	 */
	public OAELoggerFactory()
	{
		super();
	}

	@Override
	public Logger makeNewLoggerInstance(String name)
	{
		return new OAELogger(name);
	}
}
