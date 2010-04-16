package team.overfed.exp.resources;

import java.util.Locale;
import java.util.ResourceBundle;

import team.overfed.oae.logging.OAELogger;

public class ResourceExp
{
	private static OAELogger logger = OAELogger.getOAELogger(ResourceExp.class);

	public static void main(String[] args)
	{
		ResourceBundle rsbun = ResourceBundle.getBundle("properties.reload");
		logger.debug(rsbun);
		logger.debug(rsbun.getClass().getName());

		ResourceBundle rsbun2 = ResourceBundle.getBundle("properties.reload", Locale.getDefault(), ResourceExp.class.getClassLoader());
		logger.debug(rsbun2);
		logger.debug(rsbun2.getClass().getName());
	}
}
