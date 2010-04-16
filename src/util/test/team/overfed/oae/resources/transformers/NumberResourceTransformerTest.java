package team.overfed.oae.resources.transformers;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.resources.ResourceTransformerTestCase;

public class NumberResourceTransformerTest extends ResourceTransformerTestCase
{

	@Test
	public void testNumberResourceTransformer()
	{
		this.rt = new NumberResourceTransformer(Integer.class);

		this.testTransformValid(123, this.rh.getString("option.number"));
		this.testTransformValid(123, this.rh.getString("option.number"), new Object[] {});
		this.testTransformInvalid("Gruetze");
		this.testTransformInvalid("Gruetze", new Object[] {});
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(NumberResourceTransformerTest.class);
	}
}
