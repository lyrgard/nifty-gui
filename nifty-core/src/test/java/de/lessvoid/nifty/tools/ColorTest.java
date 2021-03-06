package de.lessvoid.nifty.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {
  private static final float EPSILON = 0.01f;

  @Test
  public void testMultiply() {
    Color c = new Color(1.0f, 0.5f, 0.6f, 0.8f);
    Color m = new Color("#000f");
    m.multiply(c, 0.5f);
    checkColor(m, 0.5f, 0.25f, 0.3f, 0.4f);
  }

  @Test
  public void testColorShortModeWithoutAlpha() {
    Color c = new Color("#123");
    checkColor(c, 0.066f, 0.133f, 0.2f, 1.0f);
  }

  @Test
  public void testColorLongModeWithoutAlpha() {
    Color c = new Color("#8000ff");
    checkColor(c, 0.5f, 0.0f, 1.0f, 1.0f);
  }

  @Test
  public void testColorInvalid() {
    Color c = new Color("#1");
    checkColor(c, 1.0f, 1.0f, 1.0f, 1.0f);
    c = new Color("1");
    checkColor(c, 1.0f, 1.0f, 1.0f, 1.0f);
  }

  @Test
  public void testCheckColorInvalid() {
    assertFalse(Color.check("#1"));
  }

  @Test
  public void testCheckColorValid() {
    assertTrue(Color.check("#FFF"));
  }

  @Test
  public void testColorValid() {
    Color c = new Color("#1238");
    checkColor(c, 0.066f, 0.133f, 0.2f, 0.533f);

    c = new Color("#10203080");
    checkColor(c, 0.066f, 0.133f, 0.188f, 0.501f);
  }

  @Test
  public void testColorStringFromValues() {
    Color c = new Color(0.0f, 1.0f, 0.5f, 1.0f);
    assertEquals("#0f7f", c.getColorString());
  }

  private void checkColor(Color m, float red, float green, float blue, float alpha) {
    assertEquals(red, m.getRed(), EPSILON);
    assertEquals(green, m.getGreen(), EPSILON);
    assertEquals(blue, m.getBlue(), EPSILON);
    assertEquals(alpha, m.getAlpha(), EPSILON);
  }
}
