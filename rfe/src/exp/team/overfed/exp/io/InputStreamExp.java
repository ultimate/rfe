package team.overfed.exp.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import team.overfed.oae.logging.OAELogger;

public class InputStreamExp
{
	private static OAELogger logger = OAELogger.getOAELogger(InputStreamExp.class);

	public static void main(String[] args) throws Exception
	{
		logger.debug("Start");
		File f = new File("test/lang/testgood.xml");
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int curr = bis.read();
		while(curr != -1)
		{
			logger.debug((char) curr);
			curr = bis.read();
		}
		logger.debug("\nEnd");
	}
}
