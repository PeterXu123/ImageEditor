import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import java.io.Reader;
import java.io.StringReader;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to test ImageController. It will create mock model that helps to test the
 * controller.
 */
public class ImageControllerInterfaceTest {
  /**
   * This is a mock model. It has a StringBuffer. Whenever we call a method in the class, it will
   * append corresponding text to the StringBuffer.
   */
  class MockModel implements ImageModelInterface {
    private final StringBuffer sb;

    /**
     * It constructs the mock model with a given StringBuffer.
     *
     * @param st the given StringBuffer
     */
    MockModel(StringBuffer st) {
      this.sb = st;
    }

    @Override
    public void highlightEdgeGrey() {
      sb.append("highlightEdgeGrey \n");
    }

    @Override
    public void imageBlur() {
      sb.append("imageBlur \n");
    }

    @Override
    public void imageSharpening() {
      sb.append("imageSharpening \n");
    }

    @Override
    public void imageGreyScale() {
      sb.append("imageGreyScale \n");
    }

    @Override
    public void imageSepiaTone() {
      sb.append("imageSepiaTone \n");
    }

    @Override
    public void imageDithering() {
      sb.append("imageDithering \n");
    }

    @Override
    public void generateFranceFlag(int height) {

      sb.append("France " + height + "\n");
    }

    @Override
    public void generateSwitzerlandFlag(int height) {
      sb.append("Switzerland " + height + "\n");
    }

    @Override
    public void generateNorwayFlag(int height) {
      sb.append("Norway " + height + "\n");
    }

    @Override
    public void generateGreeceFlag(int height) {
      sb.append("Greece " + height + "\n");
    }

    @Override
    public void generateCheckerboard(int nsps, int size) {
      sb.append("checkerboard " + nsps + " " + size + "\n");
    }

    @Override
    public void loadImage(String inputFIle) throws IOException {
      //      sb.delete(0, sb.length());
      sb.append(inputFIle + "\n");
    }

    @Override
    public void storeImage(String outputFile) throws IOException {
      sb.append("store");

    }

    @Override
    public void generateRainbowStrips(int customHeight, int customWidth, boolean horizontal) {
      sb.append("rainbow " + customHeight + " " + customWidth + " " + horizontal + " \n");
    }

    @Override
    public void imageMosaicing(int seedNumber) {
      sb.append("mosaics");

    }

    @Override
    public void histogramEqualization() {
      sb.append("contrast");

    }



    @Override
    public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
      return;
    }

    @Override
    public int getWidth() {
      return 0;
    }

    @Override
    public int getHeight() {
      return 0;
    }



  }


  ImageControllerInterface controller;
  StringBuffer sb;

  @Before
  public void setup() throws Exception {
    Reader in =
        new StringReader(
            "load test1.txt\n"
                + "dither\nblur\nsharpen\nsepia\ngreyscale\nhighlightEdge\n"
                + "checkerboard 4 100\n"
                + "rainbow vertical 100 100\nrainbow horizontal 100 100\n"
                + "load test1.txt\nflag France 100\nflag Norway 100\n"
                + "flag Switzerland 200\nflag "
                + "Greece 250\nmosaics 5\ncontrastEnhancement\nsave test1output.txt");
    controller = new ImageController(in);
    sb = new StringBuffer("");
    controller.getControl(new MockModel(sb));
  }

  @Test
  public void testController() throws IOException {

    String ss = sb.toString();
    assertTrue(ss.contains("test1.txt"));
    assertTrue(ss.contains("store"));
    assertTrue(ss.contains("highlightEdgeGrey"));
    assertTrue(ss.contains("imageBlur"));
    assertTrue(ss.contains("imageSharpening"));
    assertTrue(ss.contains("imageGreyScale"));
    assertTrue(ss.contains("imageSepiaTone"));
    assertTrue(ss.contains("imageDithering"));
    assertTrue(ss.contains("France"));
    assertTrue(ss.contains("Switzerland"));
    assertTrue(ss.contains("Norway "));
    assertTrue(ss.contains("Greece"));
    assertTrue(ss.contains("checkerboard"));
    assertTrue(ss.contains("true"));
    assertTrue(ss.contains("false"));
    assertTrue(ss.contains("mosaics"));
    assertTrue(ss.contains("contrast"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongInputArgument() throws Exception {
    Reader in = new StringReader("load test1.txt 1 2 3 4\n");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongFlagInput() throws Exception {
    Reader in = new StringReader("load test1.txt\nflag America 100");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongFlagInput1() throws Exception {
    Reader in = new StringReader("load test1.txt\nflag Greece a");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongRainbowInput() throws Exception {
    Reader in = new StringReader("load test1.txt\nrainbow vertical a b");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongRainbowInput1() throws Exception {
    Reader in = new StringReader("load test1.txt\nrainbow vertical1 100 100");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongLoad() throws Exception {
    Reader in = new StringReader("load1 test1.txt\nrainbow vertical 100 100");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongFilter() throws Exception {
    Reader in = new StringReader("filter");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongInputTypeRainbow() throws Exception {
    Reader in = new StringReader("rainbow vertical a 100");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongInputTypeCheckerboard() throws Exception {
    Reader in = new StringReader("checkerboard 3 a");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWrongInputTypeFlag() throws Exception {
    Reader in = new StringReader("flag1 China 100");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
  }

  @Test
  public void testControllerWithEmptyInput() throws Exception {
    Reader in = new StringReader("");
    controller = new ImageController(in);
    controller.getControl(new MockModel(new StringBuffer("")));
    assertNotNull(controller);
  }
}
