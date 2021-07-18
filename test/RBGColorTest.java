import org.junit.Before;
import org.junit.Test;


import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class is used to test RGBColor and its methods.
 */
public class RBGColorTest {
  RBGColor color;

  @Before
  public void setup() {
    color = new RBGColor(255, 254, 0);
  }

  @Test
  public void getChannelOne() {
    assertEquals(255, color.getChannelOne());
  }

  @Test
  public void getChannelTwo() {
    assertEquals(254, color.getChannelTwo());
  }

  @Test
  public void getChannelThree() {
    assertEquals(0, color.getChannelThree());
  }

  @Test
  public void rainbowMap() {
    assertEquals(RBGColor.rainbowMap().size(), 7);
  }

  @Test
  public void testHashCode() {
    assertNotEquals(RBGColor.rainbowMap().get(1).hashCode(),
            RBGColor.rainbowMap().get(2).hashCode());
    assertEquals(RBGColor.rainbowMap().get(1).hashCode(), RBGColor.rainbowMap().get(1).hashCode());
  }

  @Test
  public void testEquals() {
    RBGColor color1 = new RBGColor(0, 0, 0);
    assertEquals(false, color1.equals(color));
    assertEquals(true, color1.equals(new RBGColor(0,
            0, 0)));
    System.out.println("heii".split(" ")[0]);
    System.out.println(Integer.MIN_VALUE);
    TreeMap rm = new TreeMap();
    rm.put(1,2);
    rm.put(0,1);
    System.out.println(rm.keySet());
  }

}
