import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import images.ImageUtilities;

/**
 * It represents the abstract class of image model that implements the ImageModelInterface. It has
 * all image generation function including checkerboard, flags, and rainbow imageh generations.
 */
public class ImageModel implements ImageModelInterface, IViewModel {
  private int[][][] rgb;

  //  /** the stack that is used to store previous image data. */
  //  private final Stack<int[][][]> undoStack;
  //  /** the stack that is used to store current image before calling undo method. */
  //  private final Stack<int[][][]> redoStack;

  /**
   * It constructs image model with given image file path.
   *
   * @param filepath file path of the image
   * @throws IOException exception throw during read image
   */
  public ImageModel(String filepath) throws IOException {
    loadImage(filepath);
  }

  /**
   * It constructs AbstractImageGeneration class with given rgb. It just assigns the given rgb (a
   * three dimensional array) to the rgb of the model
   *
   * @param rgb a three dimensional array
   */
  public ImageModel(int[][][] rgb) {
    //    this.undoStack = new Stack<>();
    //    this.redoStack = new Stack<>();
    this.rgb = rgb;
    //    store();
  }

  /** This is the default constructor. It just assign default value to rgb of the model. */
  public ImageModel() {
    //    this.undoStack = new Stack<>();
    //    this.redoStack = new Stack<>();
    this.rgb = new int[500][500][3];
    //    store();
  }

  @Override
  public void generateRainbowStrips(int customHeight, int customWidth, boolean horizontal) {
    if (horizontal) {
      checkRainbowStrips(customHeight);
    } else {
      checkRainbowStrips(customWidth);
    }
    int[][][] answer = new int[customHeight][customWidth][3];
    List colorNumber = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
    Collections.shuffle(colorNumber);
    HashMap<Integer, RBGColor> map1 = RBGColor.rainbowMap();
    int numRowForColor = (horizontal) ? customHeight / 7 : customWidth / 7;
    int needToAddLast = (horizontal) ? customHeight % 7 : customWidth % 7;
    int rowNumber = 0;
    int rth = 0;
    int rowl = -1;
    while (rth < 7) {
      if (needToAddLast > 0) {
        rowl = numRowForColor + 1;
        needToAddLast -= 1;
      } else {
        rowl = numRowForColor;
      }
      int ll = rowNumber + rowl;

      while (rowNumber < ll) {
        if (horizontal) {
          for (int col = 0; col < customWidth; col++) {
            RBGColor rc = map1.get(colorNumber.get(rth));
            answer[rowNumber][col][0] = rc.getChannelOne();
            answer[rowNumber][col][1] = rc.getChannelTwo();
            answer[rowNumber][col][2] = rc.getChannelThree();
          }
        } else {
          for (int row = 0; row < customHeight; row++) {
            RBGColor rc = map1.get(colorNumber.get(rth));
            answer[row][rowNumber][0] = rc.getChannelOne();
            answer[row][rowNumber][1] = rc.getChannelTwo();
            answer[row][rowNumber][2] = rc.getChannelThree();
          }
        }
        rowNumber += 1;
      }
      rth += 1;
    }

    //    store();

    this.rgb = answer;

    //    return answer;
  }

  private double distanceTwoPoints(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }

  private Map<Point, List<Point>> calculateAverage(Map<Point, List<Point>> map1) {
    Map<Point, List<Point>> output = new HashMap<>();
    for (Point p : map1.keySet()) {
      int cx = map1.get(p).stream().mapToInt(a -> a.x).sum() / map1.get(p).size();
      int cy = map1.get(p).stream().mapToInt(a -> a.y).sum() / map1.get(p).size();
      output.put(new Point(cx, cy), map1.get(p));
    }
    return output;
  }

  @Override
  public void imageMosaicing(int seedNumber) {
    List<Point> randomPoints =
        ImageOperation2D.randomNumberPixel(seedNumber, getHeight(), getWidth());
    //    System.out.println(randomPoints.size());
    Map<Point, List<Point>> clusterCenters = new HashMap<>();
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        double smallestDistance = Integer.MAX_VALUE;
        Point center = null;
        Point current = new Point(i, j);
        for (Point p : randomPoints) {
          if (distanceTwoPoints(p, current) < smallestDistance) {
            center = p;
            smallestDistance = distanceTwoPoints(p, current);
          }
        }
        //        System.out.println(center);
        if (!clusterCenters.containsKey(center)) {
          clusterCenters.put(center, new ArrayList<>(Collections.singletonList(current)));
        } else {
          List<Point> temp = clusterCenters.get(center);
          temp.add(current);
          clusterCenters.put(center, temp);
        }
      }
    }
    //    System.out.println(clusterCenters.keySet());
    Map<Point, List<Point>> averavePointandclusterPoints = calculateAverage(clusterCenters);
//    System.out.println(averavePointandclusterPoints);
    for (Point p : averavePointandclusterPoints.keySet()) {
      for (Point p1 : averavePointandclusterPoints.get(p)) {
        for (int channel = 0; channel < 3; channel++) {
          rgb[p1.y][p1.x][channel] = rgb[p.y][p.x][channel];
        }
      }
    }
  }

  @Override
  public void histogramEqualization() {

    for (int channel = 0; channel < 3; channel++) {
      int[][] data = new int[getHeight()][getWidth()];
      for (int j = 0; j < getHeight(); j++) {
        for (int i = 0; i < getWidth(); i++) {
          data[j][i] = rgb[j][i][channel];
        }
      }

      TreeMap<Integer, Integer> ht = ImageOperation2D.histogramTable(data, 256);
      TreeMap<Integer, Integer> cdft = ImageOperation2D.cdf(ht);
      TreeMap<Integer, Integer> htid =
          ImageOperation2D.idealizedHistogram(getHeight() * getWidth(), 256);
      TreeMap<Integer, Integer> cdfid = ImageOperation2D.cdf(htid);

      TreeMap<Integer, Integer> equalizedVt = ImageOperation2D.mapTo(cdft, cdfid);
      for (int j = 0; j < getHeight(); j++) {
        for (int i = 0; i < getWidth(); i++) {
          for (Integer ii : equalizedVt.keySet()) {
            if (rgb[j][i][channel] == ii) {
              rgb[j][i][channel] = equalizedVt.get(ii);
              break;
            }
          }
        }
      }
    }
  }

  //  private void removeRedEyeHelper(int[][][] tempImage) {
  //    TreeMap<Double, Point> map1 = new TreeMap<>();
  //    for (int height = 0; height < rgb.length; height++) {
  //      for (int width = 0; width < rgb[0].length; width++) {
  //        int[][][] subImageData = new int[tempImage.length][tempImage[0].length][3];
  //        if (height + tempImage.length <= rgb.length
  //                && width + tempImage[0].length <= rgb[0].length) {
  //          for (int i = 0; i < tempImage.length; i++) {
  //            for (int j = 0; j < tempImage[0].length; j++) {
  //              for (int channel = 0; channel < 3; channel++) {
  //                subImageData[i][j][channel] = rgb[height + i][width + j][channel];
  //              }
  //            }
  //          }
  //          map1.put(ImageOperation2D.correlation(subImageData, tempImage),
  //          new Point(width, height));
  //        }
  //      }
  //    }
  //    //    for (int time = 0; time < 1; time++) {
  //    System.out.println(map1.keySet().size());
  //    Point a = map1.lastEntry().getValue();
  //    System.out.println(a);
  //    for (int height = a.y; height < a.y + tempImage.length; height++) {
  //      for (int width = a.x; width < a.x + tempImage[0].length; width++) {
  //        if (rgb[height][width][0] > rgb[height][width][1] + rgb[height][width][2]) {
  //          rgb[height][width][0] = (rgb[height][width][1] + rgb[height][width][2]) / 2;
  //        }
  //      }
  //    }
  //  }
  //  @Override
  //  public void removeRedEye(int[][][] tempImage) {
  //    removeRedEyeHelper(tempImage);
  //    removeRedEyeHelper(tempImage);
  //
  //    //      map1.remove(map1.lastKey());
  //    //    }
  //  }

  @Override
  public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
    if (startColumn < 0 && endColumn >= getWidth() && startRow < 0 && endRow >= getHeight()) {
      throw new IllegalArgumentException("selected area exceeds the whole image");
    }
    if (startColumn > endColumn || startRow > endRow) {
      throw new IllegalArgumentException("selected area is not valid");
    }

    for (int row = startRow; row < endRow; row++) {
      for (int col = startColumn; col < endColumn; col++) {
        if (rgb[row][col][0] > rgb[row][col][1] + rgb[row][col][2]) {
          rgb[row][col][0] = (rgb[row][col][1] + rgb[row][col][2]) / 2;
        }
      }
    }
  }

  /**
   * It checked if heightOrWidth < 7. If so, it throws exception.
   *
   * @param heightOrWidth an integer
   */
  private void checkRainbowStrips(int heightOrWidth) {
    if (heightOrWidth < 7) {
      throw new IllegalArgumentException("rainbow has 7 colors at least.");
    }
  }

  /**
   * It is helper function for edgeDetection.
   *
   * @param imageData three dimensional array
   * @return a three dimensional array
   */
  private int[][][] edgeDetectionHelper(int[][][] imageData) {
    double[][] kx = {{1.0, 0.0, -1.0}, {2.0, 0.0, -2.0}, {1.0, 0.0, -1.0}};
    double[][] ky = {{1.0, 2.0, 1.0}, {0.0, 0.0, 0.0}, {-1.0, -2.0, -1.0}};
    int[][][] gx = ImageOperation2D.generalFilterAlgorithm(imageData, kx);
    int[][][] gy = ImageOperation2D.generalFilterAlgorithm(imageData, ky);
    int[][][] output1 = new int[imageData.length][imageData[0].length][3];
    for (int i = 0; i < imageData.length; i++) {
      for (int j = 0; j < imageData[0].length; j++) {
        for (int c = 0; c < 3; c++) {
          output1[i][j][c] = (int) Math.sqrt(Math.pow(gx[i][j][c], 2) + Math.pow(gy[i][j][c], 2));
        }
      }
    }
    //    checkoutModify(output1);
    //    int[][][] output = ImageOperation2D.generalFilterAlgorithmVersion2(imageData, kx, ky);
    return output1;
  }

  /**
   * It returns an array that stores maximum values for each channel of the given image data.
   *
   * @param imageData a three dimensional array
   * @return an array
   */
  private int[] maxValue(int[][][] imageData) {

    int[] output = new int[3];
    for (int channel = 0; channel < 3; channel++) {
      int maxV = Integer.MIN_VALUE;
      for (int row = 0; row < imageData.length; row++) {
        for (int col = 0; col < imageData[0].length; col++) {

          if (imageData[row][col][channel] >= maxV) {
            maxV = imageData[row][col][channel];
          }
        }
      }
      output[channel] = maxV;
    }
    return output;
  }

  /**
   * It returns an array that stores minimum values for each channel of the given image data.
   *
   * @param imageData a three dimensional array
   * @return an array
   */
  private int[] minValue(int[][][] imageData) {
    int[] output = new int[3];
    for (int channel = 0; channel < 3; channel++) {
      int minV = Integer.MAX_VALUE;
      for (int row = 0; row < imageData.length; row++) {
        for (int col = 0; col < imageData[0].length; col++) {

          if (imageData[row][col][channel] <= minV) {
            minV = imageData[row][col][channel];
          }
        }
      }
      output[channel] = minV;
    }
    return output;
  }

  @Override
  public void highlightEdgeGrey() {
    int[][][] tempData = edgeDetectionHelper(this.rgb);
    int[] maxValue = maxValue(tempData);
    int[] minValue = minValue(tempData);
    if (Arrays.equals(maxValue, minValue)) {
      return;
    }
    //    System.out.println(maxValue);
    //    System.out.println(minValue);
    for (int row = 0; row < tempData.length; row++) {
      for (int col = 0; col < tempData[0].length; col++) {
        for (int channel = 0; channel < 3; channel++) {
          tempData[row][col][channel] =
              ((tempData[row][col][channel] - minValue[channel]) * 255)
                  / (maxValue[channel] - minValue[channel]);
        }
      }
    }
    this.rgb = tempData;
    imageGreyScale();
  }

  @Override
  public void generateCheckerboard(int nsps, int size) {
    if (size < nsps || nsps <= 0 || size <= 0) {
      throw new IllegalArgumentException(
          "can't generate a checkerboard with this" + " size, and the number of squares");
    }
    int windowWidth = size / nsps;
    int windowHeight = size / nsps;
    int extraCol = size % nsps;
    int extraRow = size % nsps;
    int rowNumber = 0;
    int colNumber = 0;
    int[][][] answer = new int[size][size][3];
    RBGColor black = new RBGColor(0, 0, 0);
    RBGColor white = new RBGColor(255, 255, 255);
    boolean drawBlack = true;

    while (rowNumber < size || colNumber < size) {
      if (extraRow > 0) {
        windowHeight = (size / nsps) + 1;
        extraRow -= 1;
      } else {
        windowHeight = (size / nsps);
      }
      boolean temp = drawBlack;
      for (int row = rowNumber; row < rowNumber + windowHeight; row++) {
        colNumber = 0;
        extraCol = size % nsps;
        while (colNumber < size) {
          if (extraCol > 0) {
            extraCol -= 1;
            windowWidth = (size / nsps) + 1;
          } else {
            windowWidth = (size / nsps);
          }

          for (int col = colNumber; col < colNumber + windowWidth; col++) {
            if (drawBlack) {
              answer[row][col][0] = black.getChannelOne();
              answer[row][col][1] = black.getChannelTwo();
              answer[row][col][2] = black.getChannelThree();
            } else {
              answer[row][col][0] = white.getChannelOne();
              answer[row][col][1] = white.getChannelTwo();
              answer[row][col][2] = white.getChannelThree();
            }
          }
          drawBlack = !drawBlack;
          colNumber = colNumber + windowWidth;
        }

        drawBlack = temp;
      }
      rowNumber = rowNumber + windowHeight;

      drawBlack = !drawBlack;
    }

    //    store();

    this.rgb = answer;
    //    return answer;
  }

  @Override
  public void loadImage(String inputFile) throws IOException {
    this.rgb = ImageUtilities.readImage(inputFile);
  }

  @Override
  public void storeImage(String outputFile) throws IOException {
    //    System.out.println("output " + outputFile);
    ImageUtilities.writeImage(this.rgb, this.getWidth(), this.getHeight(), outputFile);
  }

  @Override
  public void generateFranceFlag(int height) {
    int width = (int) (1.5 * height);
    if ((double) width / (double) height != 1.5) {
      throw new IllegalArgumentException("width/height != 1.5");
    }

    if (height < 3) {
      throw new IllegalArgumentException("FranceFlag needs bigger size");
    }
    int[][][] answer = new int[height][width][3];
    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        if (col < width / 3) {
          answer[row][col][0] = 0;
          answer[row][col][1] = 0;
          answer[row][col][2] = 255;
        } else if (col >= width / 3 && col < (2 * width) / 3) {
          answer[row][col][0] = 255;
          answer[row][col][1] = 255;
          answer[row][col][2] = 255;
        } else {
          answer[row][col][0] = 255;
          answer[row][col][1] = 0;
          answer[row][col][2] = 0;
        }
      }
    }

    //    store();

    this.rgb = answer;
    //    return answer;
  }

  @Override
  public void generateSwitzerlandFlag(int height) {
    int width = (int) (1.0 * height);
    //    if ((double) width / (double) height != 2.0 / 2.0) {
    //      throw new IllegalArgumentException("SwitzerlandFlag proportion is not correct");
    //    }
    if (height < 32) {
      throw new IllegalArgumentException("SwitzerlandFlag needs bigger size");
    }

    int[][][] answer = new int[height][width][3];
    RBGColor white = new RBGColor(255, 255, 255);
    RBGColor red = new RBGColor(255, 0, 0);
    int numRowForColor = height / 32;
    int needToAddLast = height % 32;
    int rowNumber = 0;
    int rth = 0;
    int rowl = -1;
    while (rth < 32) {

      if (needToAddLast > 0) {
        rowl = numRowForColor + 1;
        needToAddLast -= 1;
      } else {
        rowl = numRowForColor;
      }
      int ll = rowNumber + rowl;
      while (rowNumber < ll) {
        for (int col = 0; col < width; col++) {
          if (rth >= 6 && rth < 13 && col >= (13 * width / 32) && col < (19 * width / 32)) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }
          if (rth >= 13 && rth < 19 && col >= (6 * width / 32) && col < (26 * width / 32)) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }
          if (rth >= 19 && rth < 26 && col >= (13 * width / 32) && col < (19 * width / 32)) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }

          answer[rowNumber][col][0] = red.getChannelOne();
          answer[rowNumber][col][1] = red.getChannelTwo();
          answer[rowNumber][col][2] = red.getChannelThree();
        }
        rowNumber += 1;
      }
      rth += 1;
    }

    //    store();

    this.rgb = answer;
    //    return answer;
  }

  @Override
  public void generateNorwayFlag(int height) {
    int width = (int) ((11.0 / 8.0) * height);
    if ((double) width / (double) height != 11.0 / 8.0) {
      throw new IllegalArgumentException("width/height != 11/8");
    }
    if (height < 16) {
      throw new IllegalArgumentException("NorwayFlag proportion is wrong");
    }
    int[][][] answer = new int[height][width][3];
    RBGColor blue = new RBGColor(0, 0, 255);
    RBGColor white = new RBGColor(255, 255, 255);
    RBGColor red = new RBGColor(255, 0, 0);
    int numRowForColor = height / 16;
    int needToAddLast = height % 16;
    int rowNumber = 0;
    int rth = 0;
    int rowl = 1;
    RBGColor colorToDraw = red;
    while (rth < 16) {

      if (needToAddLast > 0) {
        rowl = numRowForColor + 1;
        needToAddLast -= 1;
      } else {
        rowl = numRowForColor;
      }
      int ll = rowNumber + rowl;
      while (rowNumber < ll) {
        for (int col = 0; col < width; col++) {
          if (((col >= (6 * width / 22.0)) && (col < (7.0 * width / 22.0)))
              || ((col >= (9 * width / 22.0)) && (col < (10.0 * width / 22.0)))) {
            if (rth < 6 || rth >= 10) {
              answer[rowNumber][col][0] = white.getChannelOne();
              answer[rowNumber][col][1] = white.getChannelTwo();
              answer[rowNumber][col][2] = white.getChannelThree();
              continue;
            }
          }
          if (((col >= 7.0 * width / 22.0) && (col < 9.0 * width / 22.0))) {
            if (rth < 6 || rth >= 10) {
              answer[rowNumber][col][0] = blue.getChannelOne();
              answer[rowNumber][col][1] = blue.getChannelTwo();
              answer[rowNumber][col][2] = blue.getChannelThree();
              continue;
            }

            if (rth == 6 || rth == 9) {
              answer[rowNumber][col][0] = blue.getChannelOne();
              answer[rowNumber][col][1] = blue.getChannelTwo();
              answer[rowNumber][col][2] = blue.getChannelThree();
              continue;
            }
          }

          answer[rowNumber][col][0] = colorToDraw.getChannelOne();
          answer[rowNumber][col][1] = colorToDraw.getChannelTwo();
          answer[rowNumber][col][2] = colorToDraw.getChannelThree();
        }
        rowNumber += 1;
      }
      rth += 1;
      if (rth < 6 || rth >= 10) {
        colorToDraw = red;
      }
      if ((rth == 6) || (rth == 9)) {
        colorToDraw = white;
      }
      if ((rth == 7) || (rth == 8)) {
        colorToDraw = blue;
      }
    }

    //    store();

    this.rgb = answer;
    //    return answer;
  }

  @Override
  public void generateGreeceFlag(int height) {
    int width = (int) (1.5 * height);

    if ((double) width / (double) height != 1.5) {
      throw new IllegalArgumentException("width/height != 1.5");
    }
    if (height < 9) {
      throw new IllegalArgumentException("GreeceFlag needs bigger size");
    }
    int[][][] answer = new int[height][width][3];
    RBGColor blue = new RBGColor(0, 0, 255);
    RBGColor white = new RBGColor(255, 255, 255);
    int numRowForColor = height / 9;
    int needToAddLast = height % 9;
    int rowNumber = 0;
    int rth = 0;
    int rowl = -1;
    boolean drawBlue = true;

    while (rth < 9) {

      if (needToAddLast > 0) {
        rowl = numRowForColor + 1;
        needToAddLast -= 1;
      } else {
        rowl = numRowForColor;
      }
      int ll = rowNumber + rowl;
      while (rowNumber < ll) {
        for (int col = 0; col < width; col++) {
          if (rth == 0
              && drawBlue
              && (col >= (2.0 * width / 15.0))
              && (col < (3.0 * width / 15.0))) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }

          if (rth == 1 && !drawBlue && (col < (2 * width / 15.0))) {
            answer[rowNumber][col][0] = blue.getChannelOne();
            answer[rowNumber][col][1] = blue.getChannelTwo();
            answer[rowNumber][col][2] = blue.getChannelThree();
            continue;
          }

          if (rth == 1 && !drawBlue && col >= (3 * width / 15.0) && col < (5 * width / 15.0)) {
            answer[rowNumber][col][0] = blue.getChannelOne();
            answer[rowNumber][col][1] = blue.getChannelTwo();
            answer[rowNumber][col][2] = blue.getChannelThree();
            continue;
          }
          if (rth == 2 && drawBlue && col < (5 * width / 15.0)) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }
          if (rth == 3 && !drawBlue && (col < (2 * width / 15.0))) {

            answer[rowNumber][col][0] = blue.getChannelOne();
            answer[rowNumber][col][1] = blue.getChannelTwo();
            answer[rowNumber][col][2] = blue.getChannelThree();
            continue;
          }

          if (rth == 3 && !drawBlue && col >= (3 * width / 15.0) && col < (5 * width / 15.0)) {
            answer[rowNumber][col][0] = blue.getChannelOne();
            answer[rowNumber][col][1] = blue.getChannelTwo();
            answer[rowNumber][col][2] = blue.getChannelThree();
            continue;
          }
          if (rth == 4 && drawBlue && (col >= (2 * width / 15.0) && col < (3 * width / 15.0))) {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
            continue;
          }

          if (drawBlue) {
            answer[rowNumber][col][0] = blue.getChannelOne();
            answer[rowNumber][col][1] = blue.getChannelTwo();
            answer[rowNumber][col][2] = blue.getChannelThree();

          } else {
            answer[rowNumber][col][0] = white.getChannelOne();
            answer[rowNumber][col][1] = white.getChannelTwo();
            answer[rowNumber][col][2] = white.getChannelThree();
          }
        }
        rowNumber += 1;
      }
      drawBlue = !drawBlue;
      rth += 1;
    }

    //    store();

    this.rgb = answer;
    //    return answer;
  }

  //
  //  @Override
  //  public void store() {
  //    if (undoStack.empty()
  //        || !Arrays.deepEquals(
  //            this.copy(rgb, rgb.length, rgb[0].length, rgb[0][0].length), this.undoStack.peek()))
  // {
  //      this.undoStack.push(this.copy(rgb, rgb.length, rgb[0].length, rgb[0][0].length));
  //    }
  //  }

  //  @Override
  //  public void undo() {
  //    if (!undoStack.empty()) {
  //      this.redoStack.push(this.copy(rgb, rgb.length, rgb[0].length, rgb[0][0].length));
  //
  //      this.rgb = this.undoStack.pop();
  //    } else {
  //      throw new IllegalArgumentException("can't go back anymore");
  //    }
  //  }

  //  @Override
  //  public void redo() {
  //    if (!this.redoStack.empty()) {
  //      this.undoStack.push(this.copy(rgb, rgb.length, rgb[0].length, rgb[0][0].length));
  //      this.rgb = this.redoStack.pop();
  //    } else {
  //      throw new IllegalArgumentException("can't go forward anymore");
  //    }
  //  }
  //
  private void checkoutModify(int[][][] data) {
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[0].length; j++) {
        for (int c = 0; c < data[0][0].length; c++) {
          if (data[i][j][c] > 255) {
            data[i][j][c] = 255;
          }
          if (data[i][j][c] < 0) {
            data[i][j][c] = 0;
          }
        }
      }
    }
  }

  @Override
  public void imageBlur() {
    double[][] filter = new double[3][3];
    filter[0][0] = 1.0 / 16.0;
    filter[0][1] = 1.0 / 8.0;
    filter[0][2] = 1.0 / 16.0;
    filter[1][0] = 1.0 / 8.0;
    filter[1][1] = 1.0 / 4.0;
    filter[1][2] = 1.0 / 8.0;
    filter[2][0] = 1.0 / 16.0;
    filter[2][1] = 1.0 / 8.0;
    filter[2][2] = 1.0 / 16.0;

    //    store();

    this.rgb = ImageOperation2D.generalFilterAlgorithm(this.rgb, filter);
    checkoutModify(this.rgb);
    //    return this.getRgb();
  }

  @Override
  public void imageSharpening() {
    double[][] filter = {
      {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}
    };
    //    store();
    this.rgb = ImageOperation2D.generalFilterAlgorithm(this.rgb, filter);
    checkoutModify(this.rgb);
    //    return this.getRgb();
  }

  @Override
  public void imageGreyScale() {
    double[][] matrix = {
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722}
    };

    //    store();
    this.rgb = ImageOperation2D.generalColoringAlgorithm(this.rgb, matrix);
    //    return this.getRgb();
  }

  @Override
  public void imageSepiaTone() {
    double[][] matrix = {
      {0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168},
      {0.272, 0.534, 0.131}
    };

    //    store();
    this.rgb = ImageOperation2D.generalColoringAlgorithm(this.rgb, matrix);
    //    return this.getRgb();
  }

  @Override
  public void imageDithering() {
    imageGreyScale();
    double[] vector = {(7.0 / 16.0), (3.0 / 16.0), (5.0 / 16.0), (1.0 / 16.0)};
    this.rgb = ImageOperation2D.imageDithering(this.rgb, vector);
  }

  @Override
  public int getHeight() {
    return this.rgb.length;
  }

  @Override
  public int getWidth() {
    return this.rgb[0].length;
  }

  @Override
  public BufferedImage getData() {
    return ImageUtilities.toBufferImage(ImageOperation2D.copy(rgb, getHeight(), getWidth(),
            3));
  }
}
