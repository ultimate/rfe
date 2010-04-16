package team.overfed.exp.log;

import org.apache.log4j.Logger;

import team.overfed.oae.logging.OAELogger;

public class LogExp
{

	public static void main(String[] args)
	{
		Logger oaeLogger = OAELogger.getLogger("team.overfed...oaelogger");

		oaeLogger.debug("oaeLogger");
		oaeLogger.debug("1\n2\n3\n");
		oaeLogger.debug(oaeLogger.getClass());
		oaeLogger.debug(oaeLogger.getName());
		oaeLogger.warn("<ydksjh", new Exception("lalala"));
	}
}
