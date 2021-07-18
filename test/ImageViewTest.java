import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.IOException;

import images.ImageUtilities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


/**
 * Test class for view and its functionality.
 */
public class ImageViewTest {
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

  /**
   * Controller mock class for testing view.
   */
  class MockController implements Features {
    StringBuffer sb;

    MockController(StringBuffer sb) {
      this.sb = sb;
    }

    @Override
    public void highlightEdgeGrey() {
      sb.append("From controller");
    }

    @Override
    public void imageBlur() {
      sb.append("From controller");
    }

    @Override
    public void imageSharpening() {
      sb.append("From controller");
    }

    @Override
    public void imageGreyScale() {
      sb.append("From controller");
    }

    @Override
    public void imageSepiaTone() {
      sb.append("From controller");
    }

    @Override
    public void imageDithering() {
      sb.append("From controller");
    }

    @Override
    public void generateCheckerboard() {
      sb.append("From controller");
    }

    @Override
    public void loadImage() {
      sb.append("From controller");
    }

    @Override
    public void storeImage() {
      sb.append("From controller");
    }

    @Override
    public void generateRainbowStrips(boolean horizontal) {
      sb.append("From controller");
    }

    @Override
    public void imageMosaicing() {
      sb.append("From controller");
    }

    @Override
    public void histogramEqualization() {
      sb.append("From controller");
    }

    @Override
    public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
      sb.append("From controller");
    }

    @Override
    public void generateFranceFlag() {
      sb.append("From controller");
    }

    @Override
    public void generateSwitzerlandFlag() {
      sb.append("From controller");
    }

    @Override
    public void generateNorwayFlag() {
      sb.append("From controller");
    }

    @Override
    public void generateGreeceFlag() {
      sb.append("From controller");
    }

    @Override
    public void runScript() {
      sb.append("From controller");
    }

    @Override
    public void selectScript() {
      sb.append("From controller");
    }

    @Override
    public void addBorder() {
      sb.append("add border");
    }

    @Override
    public void addSticker() {
      sb.append("add sticker");
    }

    @Override
    public void addMeme() {
      sb.append("add meme");
    }
  }

  StringBuffer sb1;
  StringBuffer sb2;
  ViewInterface view;
  Features mockController;
  ImageView iv;
  ImageModelInterface mockModel;

  @Before
  public void setup() throws Exception {
    sb1 = new StringBuffer("");
    sb2 = new StringBuffer("");
    view = new ImageView("Testing");
    mockController = new MockController(sb1);
    view.setFeatures(mockController);
    mockModel = new MockModel1(sb2);
    iv = (ImageView) view;
  }

  @Test
  public void testHighlight() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("highlight");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testBlur() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("blur");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testSharpen() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("sharpen");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testSepia() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("sepia");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testEqualization() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("equalization");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testDithering() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("dither");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testRedEyes() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("redEyesRemove");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testMosaics() throws Exception {

    assertTrue(sb1.toString().equals(""));

    iv.clickButton("mosaic");
    System.out.println(sb1.toString());
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testSelectScript() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("selectScript");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testRunScript() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickButton("runScript");
    System.out.println(sb1.toString());
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testLoad() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("load");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testStore() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("store");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testHorizontalRainbow() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("horizontalRainbow");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testVerticalRainbow() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("verticalRainbow");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testCheckerboard() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("checkerboard");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testFrance() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("FranceFlag");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testGreece() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("GreeceFlag");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testNorway() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("NorwayFlag");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test
  public void testSwitzerland() throws Exception {
    assertTrue(sb1.toString().equals(""));
    iv.clickMenuItem("SwitzerlandFlag");
    assertTrue(sb1.toString().contains("From controller"));
  }

  @Test(expected = Exception.class)
  public void testSetImage() {
    iv.setImage((IViewModel) mockModel);
  }

  @Test
  public void testClean() {
    iv.cleanScript();
    assertNotNull(iv);
  }

  @Test
  public void testSetImage1() {
    iv.setImage((IViewModel) new ImageModel());
    assertNotNull(iv);
  }

  @Test
  public void testInputFromTextArea() {
    assertTrue(iv.userInputFromTextArea().equals(""));
    assertNotNull(iv);
  }

  @Test
  public void testAddBorder() {
    iv.clickMenuItem("AddBorder");
    assertTrue(sb1.toString().contains("add border"));
    iv.setImage((IViewModel) new BorderDecorator((IViewModel) new ImageModel(),
            10, Color.blue));
    assertNotNull(iv);
  }

  @Test
  public void testAddSticker() {
    iv.clickMenuItem("AddSticker");
    assertTrue(sb1.toString().contains("add sticker"));
    int[][][] t = new int[3][3][3];
    iv.setImage(
        (IViewModel)
            new StickerDecorator(
                (IViewModel) new ImageModel(), ImageUtilities.toBufferImage(t),
                    Color.green, 0, 0));
    assertNotNull(iv);
  }

  @Test
  public void testAddText() {
    iv.clickMenuItem("AddText");
    assertTrue(sb1.toString().contains("add meme"));
    iv.setImage((IViewModel) new MemeDecorator((IViewModel) new ImageModel(),
            "123", "321"));
    assertNotNull(iv);
  }
}
