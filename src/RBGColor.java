import java.util.HashMap;
import java.util.Objects;

/** It represents a rgb color. */
public class RBGColor {
  /** It represents the first channel of a pixel of color image. */
  private final int channelOne;
  /** It represents the second channel of a pixel of color image. */
  private final int channelTwo;
  /** It represents the third channel of a pixel of color image. */
  private final int channelThree;

  /**
   * It constructs a RGBColor with given three integers that represents the three channel value.
   *
   * @param channelOne the first channel of a pixel of color image
   * @param channelTwo the second channel of a pixel of color image
   * @param channelThree the third channel of a pixel of color image
   */
  RBGColor(int channelOne, int channelTwo, int channelThree) {
    this.channelOne = channelOne;
    this.channelTwo = channelTwo;
    this.channelThree = channelThree;
  }

  /**
   * It returns the value of channelOne.
   *
   * @return an integer
   */
  public int getChannelOne() {
    return channelOne;
  }

  /**
   * It returns the value of channelTwo.
   *
   * @return an integer
   */
  public int getChannelTwo() {
    return channelTwo;
  }

  /**
   * It returns the value of the channelThree.
   *
   * @return an integer
   */
  public int getChannelThree() {
    return channelThree;
  }

  /**
   * It returns a Hashmap that includes seven colors. The key is a number from 0 to 6. The value is
   * a RBGColor.
   *
   * @return a hashmap
   */
  public static HashMap<Integer, RBGColor> rainbowMap() {
    HashMap<Integer, RBGColor> map1 = new HashMap<>();
    map1.put(0, new RBGColor(255, 0, 0));
    map1.put(1, new RBGColor(148, 0, 211));
    map1.put(2, new RBGColor(75, 0, 130));
    map1.put(3, new RBGColor(0, 0, 255));
    map1.put(4, new RBGColor(255, 255, 0));
    map1.put(5, new RBGColor(255, 127, 0));
    map1.put(6, new RBGColor(0, 255, 0));
    return map1;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getChannelOne(), getChannelTwo(), getChannelTwo());
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof RBGColor)) {
      return false;
    }
    RBGColor rc = (RBGColor) o;

    return rc.getChannelOne() == this.getChannelOne()
        && rc.getChannelTwo() == this.getChannelTwo()
        && rc.getChannelThree() == this.getChannelThree();
  }
}
