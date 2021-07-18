import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * This class provides two general algorithms for ImageModel2D that are filter algorithm and
 * coloring algorithm.
 */
public class ImageOperation2D {

  /**
   * It is a general filter function with given filter that is two dimensional array and given image
   * data that is three dimensional array. It returns a three dimensional image data that has been
   * filtered with the filter.
   *
   * @param rgborigin original image data
   * @param filter the filter that is applied to the rgborigin
   * @return a three dimensional array, the new image data
   */
  public static int[][][] generalFilterAlgorithm(int[][][] rgborigin, double[][] filter) {
    int[][][] rgb = copy(rgborigin, rgborigin.length, rgborigin[0].length, rgborigin[0][0].length);
    int width = rgb[0].length;
    int height = rgb.length;
    int midpoint = filter.length / 2;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int channel = 0; channel < 3; channel++) {
          double value = 0;
          for (int filtery = 0; filtery < filter.length; filtery++) {
            for (int filterx = 0; filterx < filter[0].length; filterx++) {
              int realx = j - midpoint + filterx;
              int realy = i - midpoint + filtery;
              if (realx >= 0 && realy >= 0 && realx < width && realy < height) {

                value += filter[filtery][filterx] * rgborigin[realy][realx][channel];
              }
            }
          }

          rgb[i][j][channel] = (int) value;
        }
      }
    }

    return rgb;
  }

  /**
   * It creates a copy of given three dimensional array.
   *
   * @param origin the origin data
   * @param height the height
   * @param width the width
   * @param channel the channel number
   * @return a three dimensional array
   */
  public static int[][][] copy(int[][][] origin, int height, int width, int channel) {

    int[][][] output = new int[height][width][channel];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int c = 0; c < channel; c++) {
          output[i][j][c] = origin[i][j][c];
        }
      }
    }
    return output;
  }

  /**
   * It is general coloring method. It takes in a two dimensional colorVector and a given image data
   * (three dimensional array) and returns a image data that has been applied with colorVector.
   *
   * @param rgborigin the original image data
   * @param colorVector the color vector
   * @return a new image data
   */
  public static int[][][] generalColoringAlgorithm(int[][][] rgborigin, double[][] colorVector) {
    int[][][] rgb = copy(rgborigin, rgborigin.length, rgborigin[0].length, rgborigin[0][0].length);
    if (rgb[0][0].length != colorVector.length || rgb[0][0].length != colorVector[0].length) {
      throw new IllegalArgumentException("rgb channel number doesn't fit to colorVector size");
    } else {
      for (int channel = 0; channel < colorVector.length; channel++) {
        for (int row = 0; row < rgb.length; row++) {
          for (int col = 0; col < rgb[0].length; col++) {

            double[] tempColorOneDimensionVector = colorVector[channel];
            double value = -1;
            for (int i = 0; i < tempColorOneDimensionVector.length; i++) {
              value += (int) (tempColorOneDimensionVector[i] * rgb[row][col][i]);
            }
            if (value > 255) {
              value = 255;
            }
            if (value < 0) {
              value = 0;
            }
            rgb[row][col][channel] = (int) value;
          }
        }
      }
    }
    return rgb;
  }

  /**
   * It creates Dithering of the image with the given vector.
   *
   * @param rgborigin the origin image data
   * @param dVector the vector
   * @return a three dimensional array
   */
  public static int[][][] imageDithering(int[][][] rgborigin, double[] dVector) {
    int[][][] rgb = copy(rgborigin, rgborigin.length, rgborigin[0].length, rgborigin[0][0].length);
    int width = rgb[0].length;
    int height = rgb.length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < 3; channel++) {
          int oldColor = rgb[row][col][channel];
          int newColor = (oldColor > 255 - oldColor) ? 255 : 0;
          int error = oldColor - newColor;
          rgb[row][col][0] = newColor;
          rgb[row][col][1] = newColor;
          rgb[row][col][2] = newColor;
          if (col + 1 < width) {
            rgb[row][col + 1][channel] = (int) (rgb[row][col + 1][channel] + dVector[0] * error);
            //            rgb[row][col + 1][1] = (int) (rgb[row][col + 1][1] + DVector[0] * error);
            //            rgb[row][col + 1][2] = (int) (rgb[row][col + 1][2] + DVector[0] * error);
          }
          if (col - 1 >= 0 && row + 1 < height) {
            rgb[row + 1][col - 1][channel] =
                (int) (rgb[row + 1][col - 1][channel] + dVector[1] * error);
            //            rgb[row + 1][col - 1][1] =
            //                    (int) (rgb[row + 1][col - 1][1] + DVector[1] * error);
            //            rgb[row + 1][col - 1][2] =
            //                    (int) (rgb[row + 1][col - 1][2] + DVector[1] * error);
          }
          if (row + 1 < height) {
            rgb[row + 1][col][channel] = (int) (rgb[row + 1][col][channel] + dVector[2] * error);
            //            rgb[row + 1][col][1] = (int) (rgb[row + 1][col][1] + DVector[2] * error);
            //            rgb[row + 1][col][2] = (int) (rgb[row + 1][col][2] + DVector[2] * error);
          }
          if (col + 1 < width && row + 1 < height) {
            rgb[row + 1][col + 1][channel] =
                (int) (rgb[row + 1][col + 1][channel] + dVector[3] * error);
            //            rgb[row + 1][col + 1][1] =
            //                    (int) (rgb[row + 1][col + 1][1] + DVector[3] * error);
            //            rgb[row + 1][col + 1][2] =
            //                    (int) (rgb[row + 1][col + 1][2] + DVector[3] * error);
          }
        }
      }
    }
    return rgb;
  }

  /**
   * It generates some random points within an area according to the given np, height, and width.
   *
   * @param np the number of points that will be generated
   * @param height the height
   * @param width the width
   * @return list of points.
   */
  public static List<Point> randomNumberPixel(int np, int height, int width) {
    List<Point> output = new ArrayList<>();
    if (np > height * width) {
      throw new IllegalArgumentException("the number of seed is greater than the total pixel");
    }
    int t = 0;
    while (t < np) {
      Random rd = new Random();
      int randX = rd.nextInt(width);
      int randY = rd.nextInt(height);
      Point temp = new Point(randX, randY);

      output.add(temp);
      t++;
    }

    return output;
  }

  /**
   * It generates a histogram table corresponding to the image data and the level.
   *
   * @param imageData the image data
   * @param level the level
   * @return a map
   */
  public static TreeMap<Integer, Integer> histogramTable(int[][] imageData, int level) {
    TreeMap<Integer, Integer> output = new TreeMap<>();
    for (int height = 0; height < imageData.length; height++) {
      for (int width = 0; width < imageData[0].length; width++) {
        Integer temp = imageData[height][width];
        if (output.containsKey(temp)) {
          output.put(temp, output.get(temp) + 1);
        } else {
          output.put(temp, 1);
        }
      }
    }
    for (int i = 0; i < level; i++) {
      if (!output.containsKey(i)) {
        output.put(i, 0);
      }
    }
    //    System.out.println(output);
    return output;
  }

  /**
   * It generates a cumulative distribution map based on the given histogram.
   *
   * @param map1 the given histogram
   * @return a map that represents cumulative distribution map
   */
  public static TreeMap<Integer, Integer> cdf(TreeMap<Integer, Integer> map1) {
    TreeMap<Integer, Integer> output = new TreeMap<>();
    Integer previous = map1.firstKey();
    output.put(previous, map1.get(previous));
    //    System.out.println("abv");
    //    System.out.println(map1.get(previous));
    for (Integer i : map1.keySet()) {
      //      System.out.println(map1.get(map1.firstKey()));
      if (!i.equals(map1.firstKey())) {
        output.put(i, map1.get(i) + output.get(previous));
        previous = i;
      }
    }

    return output;
  }

  /**
   * It generates a idealized histogram.
   *
   * @param totalPixel the total number of pixel
   * @param level the level that usually refers to rgb maximum value
   * @return a map representing a histogram
   */
  public static TreeMap<Integer, Integer> idealizedHistogram(int totalPixel, int level) {
    int mostValue = totalPixel / level;
    int midValue = mostValue + 1;
    int mid = (level / 2);
    TreeMap<Integer, Integer> output = new TreeMap<>();
    int count = totalPixel % level;
    int s = mid - count / 2;
    int p = 0;
    while (count > 0) {
      output.put(s + p, midValue);
      p += 1;
      count -= 1;
    }

    for (int i = 0; i < level; i++) {
      if (!output.containsKey(i)) {
        output.put(i, mostValue);
      }
    }
    //    System.out.println(output);
    return output;
  }

  /**
   * Design the mapping comparing the cumulative frequency distribution of the idealized histogram
   * with the cumulative frequency distribution of the source image.
   *
   * @param cuf the cumulative frequency distribution of the image data histogram
   * @param cufeq the cumulative frequency distribution of the idealized histogram
   * @return
   */
  public static TreeMap<Integer, Integer> mapTo(
      TreeMap<Integer, Integer> cuf, TreeMap<Integer, Integer> cufeq) {
    TreeMap<Integer, Integer> output = new TreeMap<>();
    for (Integer i : cuf.keySet()) {
      int cufvalue = cuf.get(i);
      int difference = Integer.MAX_VALUE;
      int mapkey = -1;
      for (Integer ii : cufeq.keySet()) {
        if (Math.abs(cufeq.get(ii) - cufvalue) < difference) {
          difference = Math.abs(cufeq.get(ii) - cufvalue);
          mapkey = ii;
        }
      }

      output.put(i, mapkey);
    }
    return output;
  }

  //  /**
  //   * Calculate the correlation between two image data
  //   * @param imageData the image data
  //   * @param tempData the reference image data
  //   * @return a value represents the how similar they are
  //   */
  //  public static double correlation(int[][][] imageData, int[][][] tempData) {
  //    int output = 0;
  //    int[] averageImageData = new int[3];
  //    int[] averageTempData = new int[3];
  //    int[] tempImageDataMul = new int[3];
  //    int[] denominatorFirst = new int[3];
  //    int[] denominatorSecond = new int[3];
  //    double[] tempOut = new double[3];
  //    for (int channel = 0; channel < 3; channel++) {
  //
  //      for (int height = 0; height < imageData.length; height++) {
  //        for (int width = 0; width < imageData[0].length; width++) {
  //          averageImageData[channel] += imageData[height][width][channel];
  //          averageTempData[channel] += tempData[height][width][channel];
  //        }
  //      }
  //
  //      averageImageData[channel] /= imageData.length * imageData[0].length;
  //      averageTempData[channel] /= tempData.length * tempData[0].length;
  //
  //      for (int height = 0; height < imageData.length; height++) {
  //        for (int width = 0; width < imageData[0].length; width++) {
  //          denominatorFirst[channel] += Math.pow((tempData[height][width][channel]
  //                  - averageTempData[channel]),2);
  //          denominatorSecond[channel] += Math.pow((imageData[height][width][channel]
  //                  - averageImageData[channel]),2);
  //          tempImageDataMul[channel] +=
  //              (tempData[height][width][channel] - averageTempData[channel])
  //                  * (imageData[height][width][channel] - averageImageData[channel]);
  //        }
  //      }
  //      tempOut[channel] = tempImageDataMul[channel] / (Math.sqrt(denominatorFirst[channel]) *
  //              Math.sqrt(denominatorSecond[channel]));
  //
  //
  //
  //
  //    }
  //    return Math.sqrt(Math.pow(tempOut[0], 2) * 2 + Math.pow(tempOut[1], 2) +
  //    Math.pow(tempOut[2], 2));
  //  }

  //  public static TreeMap<Integer, Integer> equalizedV(
  //      TreeMap<Integer, Integer> map1, int width, int height, int level) {
  //    TreeMap<Integer, Integer> output = new TreeMap<>();
  //    Integer minCdf = map1.get(map1.firstKey());
  //    Integer firstKey = map1.firstKey();
  //
  //    for (Integer i : map1.keySet()) {
  //      if (i.equals(firstKey)) {
  //        output.put(i, 0);
  //      } else {
  //        double temp1 = map1.get(i) - minCdf;
  //        double temp2 = width * height - minCdf;
  ////        System.out.println((temp1/temp2) * 255.0);
  //        output.put(
  //            i,
  //                (int) Math.ceil(
  //                    ((temp1 / temp2)
  //                        * (level - 1))));
  //      }
  //    }
  //    return output;
  //  }
}
