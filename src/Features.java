
/**
 * The interface represents all functionality that the application provides. Each feature is used
 * suitably as a callback by the view, to pass control to its controller.
 */
public interface Features {

  /**
   * It replaces the image view with a highLight edge grey view where edges
   * (areas of high contrast) are highlighted from the image view in GUI view.
   */
  void highlightEdgeGrey();

  /** It replaces the image view with the blur version of the image view in the GUI view. */
  void imageBlur();

  /** It replaces the image view with the sharpening version of the image view in GUI view. */
  void imageSharpening();

  /**
   * It replaces the image view with the greyscale image view that comes from the image view in the
   * Gui view.
   */
  void imageGreyScale();

  /**
   * It replaces the image view with the SepiaTone image view that comes
   * from the original image view.
   */
  void imageSepiaTone();


  /**
   * It replaces the view with the dithering image view from the original image view in the
   * GUI view.
   */
  void imageDithering();


  /**
   * It generates a checkerboard image and replace the old one in the GUI view.
   *
   */
  void generateCheckerboard();

  /**
   * It loads image and put image data into the GUi view.
   *
   */
  void loadImage() ;

  /**
   * It stores image to a file path.
   *

   */
  void storeImage();

  /**
   * It replaces the image in the view with a a rainbow. If horizontal, the
   * rainbow is horizontal, else it is vertical.
   *

   * @param horizontal true if the rainbow is horizontal else false
   */
  void generateRainbowStrips(boolean horizontal);

  /**
   * Generate the image in the view a "stained glass window" and replace the original view.

   */
  void imageMosaicing();


  /**
   * Generate the histogram equalization of the vew in the model and replace previous
   * view.
   */
  void histogramEqualization();


  /**
   * This method removes red eyes by scanning through the selected area which is given by the
   * parameters of the method. If there is red eye in the selected area, it will be removed.
   * @param startColumn the startColumn of the area or the left bound of the area.
   * @param endColumn the endColumn of the area or the right bound of the area.
   * @param startRow the startRow of the area or the top bound of the area.
   * @param endRow the endRow of the area or the bottom bound of the area.
   */
  void removeRedEye(int startColumn, int endColumn, int startRow, int endRow);


  /**
   * It replaces the image in the view with the France flag image.
   *
   */
  void generateFranceFlag();

  /**
   * It replaces the image in the view with the Switzerland flag image.
   *
   */
  void generateSwitzerlandFlag();

  /**
   * It replaces the image in the view with the Norway flag image.
   *
   */
  void generateNorwayFlag();

  /**
   * It replaces the image in the view with the Greece flag image.
   *
   */
  void generateGreeceFlag();


  /**
   * It runs the script in the text area. The result will not be shown in the view.
  
   */
  void runScript();

  /**
   * It selects the script file and runs it. The result will not be shown in the view.

   */
  void selectScript();

  /**
   * It adds a border to the image in the view.
   */
  void addBorder();

  /**
   * It adds a sticker to the image in the view.
   */
  void addSticker();

  /**
   * It adds two texts to the image in the view. One is across the top and the other is across the
   * bottom.
   */
  void addMeme();









}
