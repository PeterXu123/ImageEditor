import org.junit.Test;

import java.io.IOException;

import images.ImageUtilities;

import static org.junit.Assert.assertNotNull;

/** This class is used to test imageModel class and its methods. */
public class ImageModel2DTest {

  @Test
  public void testGenerateHorizontalRainbowStrips() throws IOException {
    ImageModel m = new ImageModel();
    m.generateRainbowStrips(800, 1024, true);

    m.storeImage("res/Rainbows/" + "horizontalRainbow.png");

    assertNotNull(m);
  }

  @Test
  public void testGenerateVerticalRainbowStrips() throws IOException {
    ImageModel m = new ImageModel();
    m.generateRainbowStrips(800, 1024, false);

    m.storeImage("res/Rainbows/" + "verticalRainbow.png");

    assertNotNull(m);
  }

  @Test
  public void testGenerateCheckerBoard() throws IOException {
    ImageModel m = new ImageModel();
    m.generateCheckerboard(3, 1000);

    m.storeImage("res/Checkerboards/checkerboardsize3.png");

    m.generateCheckerboard(5, 1000);
    m.storeImage("res/Checkerboards/checkerboardsize5.png");

    m.generateCheckerboard(6, 1000);
    m.storeImage("res/Checkerboards/checkerboardsize6.png");

    assertNotNull(m);
  }

  @Test
  public void testBlur() throws IOException {
    int[][][] dataImage = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel m = new ImageModel(dataImage);
    m.imageBlur();
    m.storeImage("res/Manhattan/manhattan-smallblur1.png");

    int[][][] dataImage1 = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m1 = new ImageModel(dataImage1);
    m1.imageBlur();
    m1.storeImage("res/OnePunchImage/hohggkphlvBlur1.jpeg");
    assertNotNull(m);
    assertNotNull(m1);
  }

  @Test
  public void testsharpen() throws IOException {
    int[][][] dataImage = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m = new ImageModel(dataImage);
    m.imageSharpening();
    m.storeImage("res/OnePunchImage/hohggkphlvSharpen1.jpeg");

    int[][][] dataImage1 = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel mm = new ImageModel(dataImage1);
    mm.imageSharpening();
    mm.storeImage("res/Manhattan/manhattan-smallSharpen1.png");
    assertNotNull(m);
    assertNotNull(mm);
  }

  @Test
  public void testGrey() throws IOException {
    int[][][] imageData = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel m = new ImageModel(imageData);

    m.imageGreyScale();
    m.storeImage("res/Manhattan/manhattan-smallGrey.png");

    imageData = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m1 = new ImageModel(imageData);
    m1.imageGreyScale();
    m1.storeImage("res/OnePunchImage/hohggkphlvGrey.jpeg");
    assertNotNull(m);
    assertNotNull(m1);
  }

  @Test
  public void testEdgeDetection() throws IOException {
    int[][][] imageData = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel m = new ImageModel(imageData);

    m.highlightEdgeGrey();
    m.storeImage("res/Manhattan/manhattan-smallEdgeDetection.png");

    imageData = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m1 = new ImageModel(imageData);
    m1.highlightEdgeGrey();
    m1.storeImage("res/OnePunchImage/hohggkphlvEdgeDetection.jpeg");
    assertNotNull(m);
    assertNotNull(m1);
  }

  @Test
  public void testDithering() throws IOException {
    int[][][] imageData = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel m = new ImageModel(imageData);

    m.imageDithering();
    m.storeImage("res/Manhattan/manhattan-smallDithering.png");

    imageData = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m1 = new ImageModel(imageData);
    m1.imageDithering();
    m1.storeImage("res/OnePunchImage/hohggkphlvDithering.jpeg");
    assertNotNull(m);
    assertNotNull(m1);
  }

  @Test
  public void testSepiaTone() throws IOException {
    int[][][] imageData = ImageUtilities.readImage("res/Manhattan/manhattan-small.png");
    ImageModel m = new ImageModel(imageData);
    m.imageSepiaTone();
    m.storeImage("res/Manhattan/manhattan-smallSepiaTone.png");

    imageData = ImageUtilities.readImage("res/OnePunchImage/hohggkphlv.jpeg");
    ImageModel m1 = new ImageModel(imageData);
    m1.imageSepiaTone();
    m1.storeImage("res/OnePunchImage/hohggkphlvSepiaTone.jpeg");
    assertNotNull(m);
    assertNotNull(m1);
  }

  @Test
  public void testGenerateFranceFlag() throws IOException {
    ImageModel m = new ImageModel();
    m.generateFranceFlag(400);
    m.storeImage("res/Flags/FranceFlag.png");
    assertNotNull(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateFranceFlag1() throws IOException {
    ImageModel m = new ImageModel();
    m.generateFranceFlag(1);
    m.storeImage("res/Flags/FranceFlag.png");
    assertNotNull(m);
  }

  @Test
  public void testGenerateGreeceFlag() throws IOException {
    ImageModel m = new ImageModel();
    m.generateGreeceFlag(400);
    //    System.out.println(m.getHeight());
    //    System.out.println(m.getWidth());
    m.storeImage("res/Flags/GreeceFlag.png");
    assertNotNull(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateGreeceFlag1() throws IOException {
    ImageModel m = new ImageModel();
    m.generateGreeceFlag(1);
    //    System.out.println(m.getHeight());
    //    System.out.println(m.getWidth());
    m.storeImage("res/Flags/GreeceFlag.png");
    assertNotNull(m);
  }

  @Test
  public void testGenerateNorwayFlag() throws IOException {
    ImageModel m = new ImageModel();
    m.generateNorwayFlag(512);
    m.storeImage("res/Flags/NorwayFlag.png");
    assertNotNull(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateNorwayFlag1() throws IOException {
    ImageModel m = new ImageModel();
    m.generateNorwayFlag(1);
    m.storeImage("res/Flags/NorwayFlag.png");
    assertNotNull(m);
  }

  @Test
  public void testGenerateSwitzerlandFlag() throws IOException {

    ImageModel m = new ImageModel();
    m.generateSwitzerlandFlag(500);
    m.storeImage("res/Flags/SwitzerlandFlag.png");
    assertNotNull(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateSwitzerlandFlag1() throws IOException {

    ImageModel m = new ImageModel();
    m.generateSwitzerlandFlag(1);
    m.storeImage("res/Flags/SwitzerlandFlag.png");
    assertNotNull(m);
  }

  @Test
  public void testImageMosaicing() throws IOException {
    ImageModel m = new ImageModel();
    m.loadImage("res/Manhattan/manhattan-small.png");
    m.imageMosaicing(500);
    m.storeImage("res/Manhattan/manhattan-small-Mosaicing.png");
    m.loadImage("res/OnePunchImage/hohggkphlv.jpeg");
    m.imageMosaicing(500);
    m.storeImage("res/OnePunchImage/hohggkphlv-Mosaicing.jpeg");
    assertNotNull(m);
  }

  @Test
  public void testHistogramEqualization() throws IOException {
    ImageModel m = new ImageModel();
    m.loadImage("res/HistogramEqualizationImage/ContrastBefore.png");
    m.histogramEqualization();
    m.storeImage("res/HistogramEqualizationImage/ContrastAfter.png");

    m.loadImage("res/HistogramEqualizationImage/ggBefore.png");
    m.histogramEqualization();
    m.storeImage("res/HistogramEqualizationImage/ggAfter.png");
    assertNotNull(m);
  }

  @Test
  public void testRedEyes() throws IOException {
    ImageModel m = new ImageModel();
    m.removeRedEye(
        1 * m.getWidth() / 5, 4 * m.getWidth() / 5,
            2 * m.getHeight() / 5, 2 * m.getHeight() / 3);

    assertNotNull(m);
  }
}
