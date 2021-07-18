import org.junit.Before;
import org.junit.Test;
import java.awt.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/** Test class for gui view controller. */
public class InteractiveControllerTest {
  class MockModel1 implements IViewModel, ImageModelInterface {
    StringBuffer sb1;

    MockModel1(StringBuffer sb1) {
      this.sb1 = sb1;
    }

    @Override
    public BufferedImage getData() {
      sb1.append("BufferedImageInMockModel1");
      return null;
    }

    @Override
    public void highlightEdgeGrey() {
      sb1.append("highlightEdgeGreyInMockModel1");
    }

    @Override
    public void imageBlur() {
      sb1.append("imageBlurInMockModel1");
    }

    @Override
    public void imageSharpening() {
      sb1.append("imageSharpeningInMockModel1");
    }

    @Override
    public void imageGreyScale() {
      sb1.append("imageGreyScaleInMockModel1");
    }

    @Override
    public void imageSepiaTone() {
      sb1.append("imageSepiaToneInMockModel1");
    }

    @Override
    public void imageDithering() {
      sb1.append("imageDitheringInMockModel1");
    }

    @Override
    public void generateFranceFlag(int height) {
      sb1.append("generateFranceFlagInMockModel1");
    }

    @Override
    public void generateSwitzerlandFlag(int height) {
      sb1.append("generateSwitzerlandFlagInMockModel1");
    }

    @Override
    public void generateNorwayFlag(int height) {
      sb1.append("generateNorwayFlagInMockModel1");
    }

    @Override
    public void generateGreeceFlag(int height) {
      sb1.append("generateGreeceFlagInMockModel1");
    }

    @Override
    public void generateCheckerboard(int nsps, int size) {
      sb1.append("generateCheckerboardInMockModel1");
    }

    @Override
    public void loadImage(String inputFIle) throws IOException {
      sb1.append("loadImageInMockModel1");
    }

    @Override
    public void storeImage(String outputFile) throws IOException {
      sb1.append("storeImageInMockModel1");
    }

    @Override
    public void generateRainbowStrips(int customHeight, int customWidth, boolean horizontal) {
      sb1.append("generateRainbowStripsInMockModel1");
    }

    @Override
    public void imageMosaicing(int seedNumber) {
      sb1.append("imageMosaicingInMockModel1");
    }

    @Override
    public void histogramEqualization() {
      sb1.append("histogramEqualizationInMockModel1");
    }

    @Override
    public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
      sb1.append("removeRedEyeInMockModel1");
    }

    @Override
    public int getWidth() {
      sb1.append("getWidthInMockModel1");
      return 0;
    }

    @Override
    public int getHeight() {
      sb1.append("getHeightInMockModel1");
      return 0;
    }
  }

  class Ex1 extends MockModel1 {

    Ex1(StringBuffer sb1) {
      super(sb1);
    }

    @Override
    public BufferedImage getData() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void highlightEdgeGrey() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageBlur() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageSharpening() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageGreyScale() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageSepiaTone() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageDithering() {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateFranceFlag(int height) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateSwitzerlandFlag(int height) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateNorwayFlag(int height) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateGreeceFlag(int height) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateCheckerboard(int nsps, int size) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void loadImage(String inputFIle) throws IOException {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void storeImage(String outputFile) throws IOException {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void generateRainbowStrips(int customHeight, int customWidth, boolean horizontal) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void imageMosaicing(int seedNumber) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public void histogramEqualization() {
      throw new IllegalArgumentException("123");
    }


    @Override
    public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
      throw new IllegalArgumentException("123");
    }

    @Override
    public int getWidth() {
      sb1.append("getWidthInMockModel1");
      return 0;
    }

    @Override
    public int getHeight() {
      sb1.append("getHeightInMockModel1");
      return 0;
    }
  }

  /**
   * Mock view for testing its controller.
   */
  class MockView implements ViewInterface {
    private StringBuffer sb;

    MockView(StringBuffer sb) {
      this.sb = sb;
    }

    @Override
    public void setFeatures(Features f) throws Exception {
      sb.append("setFeaturesFromView");
      sb.append(f.toString());
    }

    @Override
    public void setImage(IViewModel image) {

      sb.append("setImageFromView");
    }

    @Override
    public void handleError(Exception ex) {
      sb.append("handleErrorFromView");
    }

    @Override
    public void cleanScript() {
      sb.append("cleanInputFromView");
    }

    @Override
    public void showSuccess() {
      sb.append("success");
    }

    @Override
    public String userChosen() {
      sb.append("userChosen");
      return "userChosen";
    }

    @Override
    public String userInput(String question) {
      sb.append("userInput");
      return "1000";
    }

    @Override
    public String userStoreAddress(String question) {
      sb.append("storeAddress");
      return "1000";
    }

    @Override
    public String userInputFromTextArea() {
      sb.append("textArea");
      return "blur";
    }

    @Override
    public Color userColorChoose() {
      sb.append("userColor");
      return null;
    }

    @Override
    public Point userClickPoint() {
      sb.append("userClickPoint");
      return null;
    }
  }

  ImageModelInterface model;
  InteractiveController controller1;
  StringBuffer sb;
  ViewInterface mockView;
  ImageModelInterface model1;
  InteractiveController controller11;
  StringBuffer sb1;
  ViewInterface mockView1;

  @Before
  public void setup() throws Exception {
    sb = new StringBuffer("");
    model = new MockModel1(sb);
    controller1 = new InteractiveController(model);

    mockView = new MockView(sb);
    controller1.setView(mockView);

    sb1 = new StringBuffer("");
    model1 = new Ex1(sb1);
    controller11 = new InteractiveController(model1);

    mockView1 = new MockView(sb1);
    controller11.setView(mockView1);
  }

  @Test
  public void testCheckerboard() throws Exception {
    controller1.generateCheckerboard();
    System.out.println(sb.toString());
    assertTrue(sb.toString().contains("userInput"));
  }

  @Test
  public void testGrayScale() throws Exception {
    controller1.imageGreyScale();
    assertTrue(sb.toString().contains("GreyScaleInMock"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testSetView() throws Exception {

    controller1.setView((ViewInterface) mockView);
    assertTrue(sb.toString().contains("setFeatures"));
  }

  @Test
  public void testHightlightEdge() throws Exception {
    controller1.highlightEdgeGrey();
    assertTrue(sb.toString().contains("highlight"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testHistogram() throws Exception {
    controller1.histogramEqualization();
    assertTrue(sb.toString().contains("histogram"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testBlur() throws Exception {
    controller1.imageBlur();
    assertTrue(sb.toString().contains("imageBlur"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testSharpen() throws Exception {
    controller1.imageSharpening();
    assertTrue(sb.toString().contains("imageSharpen"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testDithering() throws Exception {
    controller1.imageDithering();
    assertTrue(sb.toString().contains("imageDithering"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testMosaicing() throws Exception {
    controller1.imageMosaicing();
    System.out.println(sb.toString());
    assertTrue(sb.toString().contains("Mosaicing"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testSepiaTone() throws Exception {
    controller1.imageSepiaTone();
    assertTrue(sb.toString().contains("SepiaTone"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testRemoveRedEyes() throws Exception {
    controller1.removeRedEye(1, 1, 1, 1);
    assertTrue(sb.toString().contains("removeRedEye"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testRunScript() throws Exception {
    controller1.runScript();
    assertTrue(sb.toString().contains("success"));
    assertTrue(sb.toString().contains("textArea"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertFalse(sb.toString().contains("setImage"));
  }

  @Test
  public void testSelectScript() throws Exception {
    controller1.selectScript();
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertFalse(sb.toString().contains("setImage"));
  }

  @Test
  public void testLoad() throws Exception {
    controller1.loadImage();
    assertTrue(sb.toString().contains("loadImage"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testStore() throws Exception {
    controller1.storeImage();
    assertTrue(sb.toString().contains("storeImage"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testSuccess() throws Exception {
    controller1.storeImage();
    assertTrue(sb.toString().contains("storeImage"));
    assertTrue(sb.toString().contains("setFeaturesFromView"));
    assertTrue(sb.toString().contains("setImage"));
  }

  @Test
  public void testFranceFlag() throws Exception {
    controller1.generateFranceFlag();

    assertTrue(sb.toString().contains("FranceFlag"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testGreeceFlag() throws Exception {
    controller1.generateGreeceFlag();
    assertTrue(sb.toString().contains("GreeceFlag"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testNorwayFlag() throws Exception {
    controller1.generateNorwayFlag();
    assertTrue(sb.toString().contains("NorwayFlag"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testSwitzerland() throws Exception {
    controller1.generateSwitzerlandFlag();
    assertTrue(sb.toString().contains("SwitzerlandFlag"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testRainbowGood() throws Exception {
    controller1.generateRainbowStrips(true);
    System.out.println(sb.toString());
    assertTrue(sb.toString().contains("Rainbow"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testRainbowGoodVertical() throws Exception {
    controller1.generateRainbowStrips(false);
    System.out.println(sb.toString());
    assertTrue(sb.toString().contains("Rainbow"));
    assertTrue(sb.toString().contains("FromView"));
  }

  @Test
  public void testRainbowGood1() throws Exception {
    controller11.generateRainbowStrips(true);
    System.out.println(sb1.toString());
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testRainbowGood2() throws Exception {
    controller11.generateRainbowStrips(false);
    System.out.println(sb1.toString());
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testCheckerboard1() throws Exception {
    controller11.generateCheckerboard();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testGrayScale1() throws Exception {
    controller11.imageGreyScale();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testSetView1() throws Exception {
    controller11.setView((ViewInterface) mockView);
    assertNotNull(controller1);
  }

  @Test
  public void testHightlightEdge1() throws Exception {
    controller11.highlightEdgeGrey();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testHistogram1() throws Exception {
    controller11.histogramEqualization();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testRainbow() throws Exception {
    controller11.generateRainbowStrips(true);
    System.out.println(sb1.toString());
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testBlur1() throws Exception {
    controller11.imageBlur();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testSharpen1() throws Exception {
    controller11.imageSharpening();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testDithering1() throws Exception {
    controller11.imageDithering();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testMosaicing1() throws Exception {
    controller11.imageMosaicing();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testSepiaTone1() throws Exception {
    controller11.imageSepiaTone();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testRemoveRedEyes1() throws Exception {
    controller11.removeRedEye(1, 1, 1, 1);
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testLoad1() throws Exception {
    controller11.loadImage();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testStore1() throws Exception {
    controller11.storeImage();

    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testFranceFlag1() throws Exception {
    controller11.generateFranceFlag();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testGreeceFlag1() throws Exception {
    controller11.generateGreeceFlag();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testNorwayFlag1() throws Exception {
    controller11.generateNorwayFlag();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testSwitzerland1() throws Exception {
    controller11.generateSwitzerlandFlag();
    assertTrue(sb1.toString().contains("handle"));
  }

  @Test
  public void testAddBorder() {
    controller1.addBorder();
    assertTrue(sb.toString().contains("userColor"));
  }

  @Test
  public void testAddMeme() {
    controller1.addMeme();
    assertTrue(sb.toString().contains("userInput"));
  }

  @Test
  public void testAddSticker() {
    controller1.addSticker();
    assertTrue(sb.toString().contains("userColor"));
    assertTrue(sb.toString().contains("ClickPoint"));
    assertTrue(sb.toString().contains("userChosen"));
  }
}
