package team.overfed.oae.resources.transformers;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import team.overfed.oae.resources.ResourceTransformerTestCase;

public class ReferenceResourceTransformerTest extends ResourceTransformerTestCase
{

	@Test
	public void testReferenceResourceTransformer()
	{
		this.rt = new ReferenceResourceTransformer(this.rh, "{", "}");

		this.testTransformValid("sage ja", this.rh.getString("option.reference"));
		this.testTransformValid("sage ja", this.rh.getString("option.reference"), new Object[] {});
		this.testTransformValid("er sagte: \"sage ja\"", this.rh.getString("option.reference2"));
		this.testTransformValid("er sagte: \"sage ja\"", this.rh.getString("option.reference2"), new Object[] {});
		this.testTransformInvalid(new Object());
		this.testTransformInvalid(new Object(), new Object[] {});
	}

	/**
	 * Create a TestSuite from this class
	 * 
	 * @return The JUnit4TestAdapter for this class
	 */
	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(ReferenceResourceTransformerTest.class);
	}
}
