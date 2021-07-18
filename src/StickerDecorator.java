import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This class represents the IView model with a sticker image. It has the coordinate x and y of the
 * top left point of the sticker. Also, It included the sticker BufferedImage, a color that is the
 * background color of the sticker, and the IView model.
 */
public class StickerDecorator extends Decorator {
  private final int x;
  private final int y;
  private final BufferedImage image;
  private final Color color;

  /**
   * The constructor. It takes in a IView model, sticker's BufferedImage, a background color of the
   * sticker and the sticker's top left position.
   *
   * @param model the IViewModel
   * @param image the sticker's BufferedImage
   * @param color the color
   * @param x the x coordinate of the top left point of the sticker.
   * @param y the y coordinate of the top left point of the sticker.
   */
  public StickerDecorator(IViewModel model, BufferedImage image, Color color, int x, int y) {
    super(model);
    this.image = image;
    this.x = x;
    this.y = y;
    this.color = color;
  }

  @Override
  public BufferedImage getData() {
    BufferedImage originalImage = model.getData();
    System.out.println("x " + x);
    System.out.println("y " + y);
    System.out.println(originalImage.getWidth());
    System.out.println(originalImage.getHeight());
    System.out.println(image.getWidth());
    System.out.println(image.getHeight());

    if (x + image.getWidth() > originalImage.getWidth()
        || y + image.getHeight() > originalImage.getHeight()
        || x < 0
        || y < 0) {
      throw new IllegalArgumentException("sticker is outside the range of original image");
    }
    for (int row = 0; row < originalImage.getWidth(); row++) {
      for (int col = 0; col < originalImage.getHeight(); col++) {
        if (row >= y && row < y + image.getHeight() && col >= x && col < x + image.getWidth()) {
          if (image.getRGB(col - x, row - y) != color.getRGB()) {
            originalImage.setRGB(col, row, image.getRGB(col - x, row - y));
          }
        }
      }
    }
    //    Graphics graphics = originalImage.getGraphics();
    //    graphics.drawImage(image, x, y, null, null);

    return originalImage;
  }
}
