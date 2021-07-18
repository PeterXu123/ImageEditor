import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

/**
 * It represents the IView model with two texts. One of the texts is across the top of the IView
 * model and the other is across the bottom of the IView model. It has two texts that are given by
 * the user.
 */
public class MemeDecorator extends Decorator {
  private final String text1;
  private final String text2;

  /**
   * The constructor. It takes in a IViewModel and two texts.
   *
   * @param model the IView model
   * @param text1 one text
   * @param text2 the other text
   */
  MemeDecorator(IViewModel model, String text1, String text2) {
    super(model);
    this.text1 = text1;
    this.text2 = text2;
  }

  @Override
  public BufferedImage getData() {
    BufferedImage image = model.getData();
    Graphics graphics = image.getGraphics();
    graphics.setColor(Color.WHITE);
    double size = image.getHeight() * image.getWidth() / 8000;
    Font font = new Font("Arial", Font.PLAIN, (int) size);
    FontMetrics metrics = graphics.getFontMetrics(font);
    graphics.setFont(font);
    graphics.drawString(text1, (image.getWidth() - metrics.stringWidth(text1)) / 2, (int) size);
    graphics.drawString(
        text2, (image.getWidth() - metrics.stringWidth(text2)) / 2, image.getHeight());
    graphics.dispose();
    return image;
  }
}
