package team.overfed.oae.resources.transformers;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import team.overfed.oae.resources.ResourceTransformerTestCase;

public class BooleanResourceTransformerTest extends ResourceTransformerTestCase
{

	@Test
	public void testBooleanResourceTransformer()
	{
		this.rt = new BooleanResourceTransformer();

		this.testTransformValid(true, this.rh.getString("option.boolean.1"));
		this.testTransformValid(false, this.rh.getString("option.boolean.2"), new Object[] {});
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
		return new JUnit4TestAdapter(BooleanResourceTransformerTest.class);
	}
}
