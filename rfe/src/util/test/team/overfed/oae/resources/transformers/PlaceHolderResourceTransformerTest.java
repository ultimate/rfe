package team.overfed.oae.resources.transformers;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.resources.ResourceTransformerTestCase;

public class PlaceHolderResourceTransformerTest extends ResourceTransformerTestCase
{

	@Test
	public void testPlaceHolderResourceTransformer()
	{
		this.rt = new PlaceholderResourceTransformer("%WHAT");

		this.testTransformValid("sage wie bitte", this.rh.getString("option.placeholder"), "wie bitte");
		this.testTransformInvalid("Gruetze");
		this.testTransformInvalid(new Object(), new Object[] {});
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(PlaceHolderResourceTransformerTest.class);
	}
}
