import java.io.IOException;

/**
 * This class represents the interface of the image model in the MVC pattern. It includes methods
 * that operates on the image data of the model, such as blur and sharpen, and also includes methods
 * that generate new images data like rainbows, flags, and checkerboard. data.
 */
public interface ImageModelInterface {

  /**
   * It replaces the image data with a grayscale image where edges (areas of high contrast) are
   * highlighted from the image data.
   */
  void highlightEdgeGrey();

  /** It replaces the image data with the blur version of the image data in the model. */
  void imageBlur();

  /** It replaces the image data with the sharpening version of the image data in the model. */
  void imageSharpening();

  /**
   * It replaces the image data with the greyscale image data that comes from the image data in the
   * model.
   */
  void imageGreyScale();

  /**
   * It replaces the image data with the SepiaTone image data from the original image data in the
   * model.
   */
  void imageSepiaTone();

  /**
   * It replaces the image data with the dithering image data from the original image data in the
   * model.
   */
  void imageDithering();

  /**
   * It replaces the image data in the model with the France flag image data. It takes in the given
   * height.
   *
   * @param height the height of the flag
   */
  void generateFranceFlag(int height);

  /**
   * It replaces the image data in the model with the Switzerland flag image data. It takes in the
   * given height.
   *
   * @param height the height of the flag
   */
  void generateSwitzerlandFlag(int height);

  /**
   * It replaces the image data in the model with the Norway flag image data. It takes in the given
   * height.
   *
   * @param height the height of the flag
   */
  void generateNorwayFlag(int height);

  /**
   * It replaces the image data in the model with the Greece flag image data. It takes in the given
   * height.
   *
   * @param height the height of the flag
   */
  void generateGreeceFlag(int height);

  /**
   * It generates a checkerboard image with given number of squares per side and the image size, and
   * the new image data will replace the old one.
   *
   * @param nsps the number of squares per side
   * @param size the image size of the checkerboard
   */
  void generateCheckerboard(int nsps, int size);

  /**
   * It loads image and put image data into the model.
   *
   * @param inputFIle the file path of the image
   */
  void loadImage(String inputFIle) throws IOException;

  /**
   * It stores image into the file path.
   *
   * @param outputFile the output file path of the image
   */
  void storeImage(String outputFile) throws IOException;

  /**
   * It replaces the image data with a a rainbow with given height and width. If horizontal, the
   * rainbow is horizontal, else it is vertical.
   *
   * @param customHeight the height of the rainbow
   * @param customWidth the width of the rainbow
   * @param horizontal true if the rainbow is horizontal else false
   */
  void generateRainbowStrips(int customHeight, int customWidth, boolean horizontal);

  /**
   * Generate the image in the model a "stained glass window" and replace the original image data.
   *
   * @param seedNumber the number of seed.
   */
  void imageMosaicing(int seedNumber);

  /**
   * Generate the histogram equalization of the image data in the model and replace previous image
   * data.
   */
  void histogramEqualization();

  //  /**
  //   * This method removes red eyes by taking a template image that is red eye sample. Then,
  //   * It will compare each sub image of the model image with the template image.
  //   Finally, it removes
  //   * the sub image of the model image that has the highest similarity.
  //   * @param tempImage the template image
  //   */
  //  void removeRedEye(int[][][] tempImage);

  /**
   * This method removes red eyes by scanning through the selected area which is given by the
   * parameters of the method. If there is red eye in the selected area, it will be removed.
   *
   * @param startColumn the startColumn of the area or the left bound of the area.
   * @param endColumn the endColumn of the area or the right bound of the area.
   * @param startRow the startRow of the area or the top bound of the area.
   * @param endRow the endRow of the area or the bottom bound of the area.
   */
  void removeRedEye(int startColumn, int endColumn, int startRow, int endRow);

  /**
   * It returns the width of the image in the model.
   *
   * @return the width
   */
  int getWidth();

  /**
   * It returns the width of the image in the model.
   *
   * @return the width
   */
  int getHeight();
}
