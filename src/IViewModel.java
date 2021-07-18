import java.awt.image.BufferedImage;

/**
 * It represents the ViewModel that only has one method. The method returns the copied data from
 * the model.
 */
public interface IViewModel {
  /**
   * It returns the copied data from the model.
   * @return BufferedImage
   */
  BufferedImage getData();
}
