package team.overfed.util.utils;

import static org.junit.Assert.*;
import org.junit.Test;

import team.overfed.oae.logging.OAELoggerTestCase;
import team.overfed.util.utils.StringUtils;
import junit.framework.JUnit4TestAdapter;

/**
 * Class testing StringUtils.<br>
 * 
 * @author ultimate
 */
public class StringUtilsTest extends OAELoggerTestCase
{

	@Test
	public void testInsertStringAtLineStartValid()
	{
		logger.debug("testing legal arguments (3 cases)");

		String res;
		String[] pre = new String[4];

		logger.debug("testing with 4 insertions");
		pre[0] = "  ";
		pre[1] = "Ajaserlkjdhgf";
		pre[2] = "\t";
		pre[3] = "\n";

		logger.debug("testing with different Strings to insert into");
		String t1 = "abc";
		String t2 = "def";
		String t3 = "ghi";

		// test working
		for(int i = 0; i < pre.length; i++)
		{
			logger.debug("testing insertion with insertion: " + pre[i]);

			res = StringUtils.insertStringAtLineStart(t1, pre[i]);
			assertEquals(pre[i] + t1, res);

			res = StringUtils.insertStringAtLineStart(t1 + "\n" + t2, pre[i]);
			assertEquals(pre[i] + t1 + "\n" + pre[i] + t2, res);

			res = StringUtils.insertStringAtLineStart(t1 + "\n" + t2 + "\n", pre[i]);
			assertEquals(pre[i] + t1 + "\n" + pre[i] + t2 + "\n", res);

			res = StringUtils.insertStringAtLineStart(t1 + "\n" + t2 + "\t  " + t3 + "\n", pre[i]);
			assertEquals(pre[i] + t1 + "\n" + pre[i] + t2 + "\t  " + t3 + "\n", res);

			res = StringUtils.insertStringAtLineStart("", pre[i]);
			assertEquals(pre[i], res);

			res = StringUtils.insertStringAtLineStart("\n", pre[i]);
			assertEquals(pre[i] + "\n", res);

			res = StringUtils.insertStringAtLineStart(t1 + "\n" + t2 + "\n\n" + t3 + "\n", pre[i]);
			assertEquals(pre[i] + t1 + "\n" + pre[i] + t2 + "\n" + pre[i] + "\n" + pre[i] + t3 + "\n", res);

			logger.debug("7 example Strings verified");
		}
	}

	@Test
	public void testInsertStringAtLineStartInvalid()
	{
		logger.debug("testing null arguments (3 cases)");

		String res = null;
		// test not working
		try
		{
			res = StringUtils.insertStringAtLineStart(null, "");
			fail("Expected Error not occured!");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		try
		{
			res = StringUtils.insertStringAtLineStart("", null);
			fail("Expected Error not occured!");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given insertion is null.", e.getMessage());
		}
		try
		{
			res = StringUtils.insertStringAtLineStart(null, null);
			fail("Expected Error not occured!");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(res);
	}

	@Test
	public void testDeleteLeadingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (6 example cases)");

		String ret = null;

		ret = StringUtils.deleteLeadingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteLeadingWhiteSpaces("     ");
		assertEquals("", ret);

		ret = StringUtils.deleteLeadingWhiteSpaces("   \t\n  \n  \t");
		assertEquals("", ret);

		ret = StringUtils.deleteLeadingWhiteSpaces("  \t \n \t aseldkjf");
		assertEquals("aseldkjf", ret);

		ret = StringUtils.deleteLeadingWhiteSpaces("asdlkja \n sadf\t alsd");
		assertEquals("asdlkja \n sadf\t alsd", ret);

		ret = StringUtils.deleteLeadingWhiteSpaces(" \t \n  asdlkja \n ");
		assertEquals("asdlkja \n ", ret);
	}

	@Test
	public void testDeleteLeadingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteLeadingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testDeleteTailingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (6 example cases)");

		String ret = null;

		ret = StringUtils.deleteTailingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteTailingWhiteSpaces("     ");
		assertEquals("", ret);

		ret = StringUtils.deleteTailingWhiteSpaces("   \t\n  \n  \t");
		assertEquals("", ret);

		ret = StringUtils.deleteTailingWhiteSpaces("aseldkjf  \t \n \t ");
		assertEquals("aseldkjf", ret);

		ret = StringUtils.deleteTailingWhiteSpaces("asdlkja \n sadf\t alsd");
		assertEquals("asdlkja \n sadf\t alsd", ret);

		ret = StringUtils.deleteTailingWhiteSpaces(" \t \n  asdlkja \n ");
		assertEquals(" \t \n  asdlkja", ret);
	}

	@Test
	public void testDeleteTailingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteTailingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testDeleteEnclosingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (6 example cases)");

		String ret = null;

		ret = StringUtils.deleteEnclosingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteEnclosingWhiteSpaces("     ");
		assertEquals("", ret);

		ret = StringUtils.deleteEnclosingWhiteSpaces("   \t\n  \n  \t");
		assertEquals("", ret);

		ret = StringUtils.deleteEnclosingWhiteSpaces("  \t \n \t aseldkjf");
		assertEquals("aseldkjf", ret);

		ret = StringUtils.deleteEnclosingWhiteSpaces("asdlkja \n sadf\t alsd");
		assertEquals("asdlkja \n sadf\t alsd", ret);

		ret = StringUtils.deleteEnclosingWhiteSpaces(" \t \n  asdlkja \n ");
		assertEquals("asdlkja", ret);
	}

	@Test
	public void testDeleteEnclosingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteEnclosingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testDeleteLineLeadingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (5 example cases)");

		String ret = null;

		ret = StringUtils.deleteLineLeadingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteLineLeadingWhiteSpaces("      ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineLeadingWhiteSpaces("  \t\n\n \t    ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineLeadingWhiteSpaces("  \t\noalskdjf\n \t    ");
		assertEquals("oalskdjf\n", ret);

		ret = StringUtils.deleteLineLeadingWhiteSpaces("  \t\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas    \n asdf \t    ");
		assertEquals("adlsdf\nslksdf\nalsdf\n\nssdfas    \nasdf \t    ", ret);
	}

	@Test
	public void testDeleteLineLeadingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteLineLeadingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testDeleteLineTailingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (5 example cases)");

		String ret = null;

		ret = StringUtils.deleteLineTailingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteLineTailingWhiteSpaces("      ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineTailingWhiteSpaces("  \t\n\n \t    ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineTailingWhiteSpaces("  \t\noalskdjf\n \t    ");
		assertEquals("\noalskdjf", ret);

		ret = StringUtils.deleteLineTailingWhiteSpaces("  \t\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas    \n asdf \t    ");
		assertEquals("\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas\n asdf", ret);
	}

	@Test
	public void testDeleteLineTailingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteLineTailingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testDeleteLineEnclosingWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (5 example cases)");

		String ret = null;

		ret = StringUtils.deleteLineEnclosingWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.deleteLineEnclosingWhiteSpaces("      ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineEnclosingWhiteSpaces("  \t\n\n \t    ");
		assertEquals("", ret);

		ret = StringUtils.deleteLineEnclosingWhiteSpaces("  \t\noalskdjf\n \t    ");
		assertEquals("oalskdjf", ret);

		ret = StringUtils.deleteLineEnclosingWhiteSpaces("  \t\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas    \n asdf \t    ");
		assertEquals("adlsdf\nslksdf\nalsdf\n\nssdfas\nasdf", ret);
	}

	@Test
	public void testDeleteLineEnclosingWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.deleteLineEnclosingWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testGetFirstNonWhiteSpaceCharacterPositionValid()
	{
		logger.debug("testing legal arguments (6 example cases)");

		int ret = -1;

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex("");
		assertEquals(0, ret);

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex("      ");
		assertEquals("      ".length(), ret);

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex("  \t\n\n \t    ");
		assertEquals("  \t\n\n \t    ".length(), ret);

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex("    a");
		assertEquals(4, ret);

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex(" \t  \n aads öfg had. ,s \n");
		assertEquals(6, ret);

		ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex("als kdfö fghad \t");
		assertEquals(0, ret);
	}

	@Test
	public void testGetFirstNonWhiteSpaceCharacterPositionInvalid()
	{
		logger.debug("testing null argument");

		int ret = -2;
		try
		{
			ret = StringUtils.getFirstNonWhiteSpaceCharacterIndex(null);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertEquals(-2, ret);
	}

	@Test
	public void testGetLastNonWhiteSpaceCharacterPositionValid()
	{
		logger.debug("testing legal arguments (6 example cases)");

		int ret = -2;

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("");
		assertEquals(-1, ret);

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("aölsd  \t\naskldf");
		assertEquals("aölsd  \t\naskldf".length() - 1, ret);

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("     ");
		assertEquals(-1, ret);

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("asdf     ");
		assertEquals(3, ret);

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("  \t\n\n \t    ");
		assertEquals(-1, ret);

		ret = StringUtils.getLastNonWhiteSpaceCharacterIndex("aölsd  \t\n\n \t    ");
		assertEquals(4, ret);

	}

	@Test
	public void testGetLastNonWhiteSpaceCharacterPositionInvalid()
	{
		logger.debug("testing null argument");

		int ret = -2;
		try
		{
			ret = StringUtils.getLastNonWhiteSpaceCharacterIndex(null);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertEquals(-2, ret);
	}

	@Test
	public void testIgnoreNewLineValid()
	{
		logger.debug("testing legal arguments (5 example cases)");

		String ret = null;

		ret = StringUtils.ignoreNewLine("");
		assertEquals("", ret);

		ret = StringUtils.ignoreNewLine("      ");
		assertEquals("      ", ret);

		ret = StringUtils.ignoreNewLine("  \t\n\n \t    ");
		assertEquals("  \t \t    ", ret);

		ret = StringUtils.ignoreNewLine("  \t\noalskdjf\n \t    ");
		assertEquals("  \toalskdjf \t    ", ret);

		ret = StringUtils.ignoreNewLine("  \t\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas    \n asdf \t    ");
		assertEquals("  \tadlsdf    slksdfalsdf \tssdfas     asdf \t    ", ret);
	}

	@Test
	public void testIgnoreNewLineInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.ignoreNewLine(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	@Test
	public void testIgnoreDuplicateWhiteSpacesValid()
	{
		logger.debug("testing legal arguments (5 example cases)");

		String ret = null;

		ret = StringUtils.ignoreDuplicateWhiteSpaces("");
		assertEquals("", ret);

		ret = StringUtils.ignoreDuplicateWhiteSpaces("      ");
		assertEquals(" ", ret);

		ret = StringUtils.ignoreDuplicateWhiteSpaces("  \t\n\n \t    ");
		assertEquals(" \n\n ", ret);

		ret = StringUtils.ignoreDuplicateWhiteSpaces("  \t\noalskdjf\n \t    ");
		assertEquals(" \noalskdjf\n ", ret);

		ret = StringUtils.ignoreDuplicateWhiteSpaces("  \t\nadlsdf\n    slksdf\nalsdf\n\n \tssdfas    \n asdf \t    ");
		assertEquals(" \nadlsdf\n slksdf\nalsdf\n\n ssdfas \n asdf ", ret);
	}

	@Test
	public void testIgnoreDuplicateWhiteSpacesInvalid()
	{
		logger.debug("testing null argument");

		String ret = null;
		try
		{
			ret = StringUtils.ignoreDuplicateWhiteSpaces(ret);
			fail("Expected exception did not occur.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given text is null.", e.getMessage());
		}
		assertNull(ret);
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(StringUtilsTest.class);
	}

}
