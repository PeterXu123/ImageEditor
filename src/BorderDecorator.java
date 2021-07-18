import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * The class represents the viewModel with a border. The border size and color is given by the user.
 */
public class BorderDecorator extends Decorator {
  private final int borderSize;
  private final Color color;

  /**
   * The constructor. It takes in a IViewModel, border size and a color.
   *
   * @param model the IView model
   * @param borderSize the size of the border
   * @param color the color of the border
   */
  public BorderDecorator(IViewModel model, int borderSize, Color color) {
    super(model);
    this.borderSize = borderSize;
    this.color = color;
  }

  @Override
  public BufferedImage getData() {
    BufferedImage image = model.getData();
    int topEnd = borderSize;
    int botStart = image.getHeight() - borderSize - 1;
    int leftEnd = borderSize;
    int rightStart = image.getWidth() - borderSize - 1;
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (row < topEnd || (row >= botStart)) {
          image.setRGB(col, row, color.getRGB());
        }
        if (col < leftEnd || col > rightStart) {
          image.setRGB(col, row, color.getRGB());
        }
      }
    }
    return image;
  }
}
