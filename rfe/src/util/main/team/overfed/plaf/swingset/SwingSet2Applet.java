package team.overfed.plaf.swingset;

/*
 * @(#)SwingSet2Applet.java 1.10 05/11/17 Copyright (c) 2006 Sun Microsystems, Inc. All Rights
 * Reserved. Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met: -Redistribution of source code must
 * retain the above copyright notice, this list of conditions and the following disclaimer.
 * -Redistribution in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution. Neither the name of Sun Microsystems, Inc. or the names of contributors may be used
 * to endorse or promote products derived from this software without specific prior written
 * permission. This software is provided "AS IS," without a warranty of any kind. ALL EXPRESS OR
 * IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN
 * MIDROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN NO
 * EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS
 * OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF
 * SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. You acknowledge that this software is
 * not designed, licensed or intended for use in the design, construction, operation or maintenance
 * of any nuclear facility.
 */

/*
 * @(#)SwingSet2Applet.java 1.10 05/11/17
 */

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JApplet;

import team.overfed.oae.logging.OAELogger;

/**
 * @version 1.10 11/17/05
 * @author Jeff Dinkins
 */
@SuppressWarnings( { "static-access", "unused", "unchecked", "serial", "deprecation" })
public class SwingSet2Applet extends JApplet
{
	private OAELogger logger = OAELogger.getOAELogger(getClass());

	public void init()
	{
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new SwingSet2(this), BorderLayout.CENTER);
	}

	public URL getURL(String filename)
	{
		URL codeBase = this.getCodeBase();
		URL url = null;

		try
		{
			url = new URL(codeBase, filename);
			logger.debug(url);
		}
		catch(java.net.MalformedURLException e)
		{
			logger.error("Error: badly specified URL");
			return null;
		}

		return url;
	}

}
