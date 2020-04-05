
package manami.color.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import manami.color.utils.ColorIndex;

public class ColorTest {

	// [value=#D58D35, r=213, g=141, b=53]
	private ColorModel color;

	@Before
	public void setUp() {
		color = new ColorModel(213, 141, 53);
	}

	@After
	public void tearDown() {
		color = null;
	}

	// -----------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------

	// -------------------------------------
	// Constructor Color(r, g, b)
	@Test
	public void test_constructor_RGB() {
		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#d58d35", color.getHexValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_red_value_smaller_than_0_Exception() {
		color = new ColorModel(-3, 141, 53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_red_value_bigger_than_255_Exception() {
		color = new ColorModel(264, 141, 53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_green_value_smaller_than_0_Exception() {
		color = new ColorModel(213, -31, 53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_green_value_bigger_than_255_Exception() {
		color = new ColorModel(213, 421, 53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_blue_value_smaller_than_0_Exception() {
		color = new ColorModel(213, 141, -132);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_RGB_with_blue_value_bigger_than_255_Exception() {
		color = new ColorModel(213, 141, 533);
	}

	// -------------------------------------
	// Constructor Color(hexValue)
	@Test
	public void test_constructor_HexValue() {
		color = new ColorModel("#D58D35");
		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#D58D35", color.getHexValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_HexValue_with_null_value_Exception() {
		color = new ColorModel(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_HexValue_with_wrong_value_1_Exception() {
		color = new ColorModel("A26F76");
	}

	

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_HexValue_with_wrong_value_3_Exception() {
		color = new ColorModel("#G26F76");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_HexValue_with_wrong_value_4_Exception() {
		color = new ColorModel("#A26F7");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_constructor_HexValue_with_wrong_value_5_Exception() {
		color = new ColorModel("#A26F71E");
	}
	
	// -----------------------------------------------------------------------------
	// Override methods
	// -----------------------------------------------------------------------------
	@Test
	public void testToString_not_null() {
		assertNotNull(color.toString());
	}
	
	@Test
	public void testToString() {
		assertEquals("toString() est incorrect", "[value=#d58d35, r=213, g=141, b=53]", color.toString());
	}

	// -----------------------------------------------------------------------------
	// Getters
	@Test
	public void testGetRed() {
		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#d58d35", color.getHexValue());	
	}

	@Test
	public void testGetGreen() {
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));

		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#d58d35", color.getHexValue());
	}

	@Test
	public void testGetBlue() {
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));

		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getHexValue() est incorrect", "#d58d35", color.getHexValue());
	}

	@Test
	public void testGetHexValue() {
		assertEquals("getHexValue() est incorrect", "#d58d35", color.getHexValue());

		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
	}

	// -----------------------------------------------------------------------------
	// Setters
	// -----------------------------------------------------------------------------

	// -------------------------------------
	// RED
	@Test
	public void testSetRed() {
		color.setColor(25, ColorIndex.RED);
		assertEquals("getRed() est incorrect", 25, color.getColor(ColorIndex.RED));
		
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#198d35", color.getHexValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRed_with_value_bigger_than_255_Exception() {
		color.setColor(425, ColorIndex.RED);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRed_with_value_smaller_than_0_Exception() {
		color.setColor(-12, ColorIndex.RED);
	}

	// -------------------------------------
	// GREEN
	@Test
	public void testSetGreen() {
		color.setColor(215, ColorIndex.GREEN);
		assertEquals("getGreen() est incorrect", 215, color.getColor(ColorIndex.GREEN));
		
		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getBlue() est incorrect", 53, color.getColor(ColorIndex.BLUE));
		assertEquals("getHexValue() est incorrect", "#d5d735", color.getHexValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGreen_with_value_bigger_than_255_Exception() {
		color.setColor(615, ColorIndex.GREEN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetGreen_with_value_smaller_than_0_Exception() {
		color.setColor(-132, ColorIndex.GREEN);
	}

	// -------------------------------------
	// BLUE
	@Test
	public void testSetBlue() {
		color.setColor(134, ColorIndex.BLUE);
		assertEquals("getBlue() est incorrect", 134, color.getColor(ColorIndex.BLUE));
		
		assertEquals("getRed() est incorrect", 213, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 141, color.getColor(ColorIndex.GREEN));
		assertEquals("getHexValue() est incorrect", "#d58d86", color.getHexValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetBlue_with_value_bigger_than_255_Exception() {
		color.setColor(731, ColorIndex.BLUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetBlue_with_value_smaller_than_0_Exception() {
		color.setColor(-1, ColorIndex.BLUE);
	}

	// -------------------------------------
	// HEX VALUE
	@Test
	public void testSetHexValue() {
		color.setHexValue("#A26F76");
		assertEquals("getHexValue() est incorrect", "#A26F76", color.getHexValue());
		
		assertEquals("getRed() est incorrect", 162, color.getColor(ColorIndex.RED));
		assertEquals("getGreen() est incorrect", 111, color.getColor(ColorIndex.GREEN));
		assertEquals("getBlue() est incorrect", 118, color.getColor(ColorIndex.BLUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHexValue_with_null_value_Exception() {
		color.setHexValue(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHexValue_with_wrong_value_1_Exception() {
		color.setHexValue("A26F76");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetHexValue_with_wrong_value_3_Exception() {
		color.setHexValue("#G26F76");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHexValue_with_wrong_value_4_Exception() {
		color.setHexValue("#A26F7");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHexValue_with_wrong_value_5_Exception() {
		color.setHexValue("#A26F71E");
	}
}
