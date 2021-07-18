
import java.util.Scanner;

/**
 * It represents the controller in MVC pattern. It has a readable and a method called getControl. It
 * controls the image model based on its readable object.
 */
public class ImageController implements ImageControllerInterface {
  private final Readable in;

  /**
   * It constructs the controller with a given Readable object.
   *
   * @param in the Readable object.
   */
  public ImageController(Readable in) {
    this.in = in;
  }

  @Override
  public void getControl(ImageModelInterface iModel) throws Exception {
    Scanner scan = new Scanner(this.in);
    while (scan.hasNextLine()) {
      String[] words = scan.nextLine().split(" ");
      if (words.length == 2) {
        String filePath = words[1];
        switch (words[0]) {
          case "load":
            iModel.loadImage(filePath);
            continue;

          case "save":
            iModel.storeImage(filePath);
            continue;
          case "mosaics":
            iModel.imageMosaicing(Integer.parseInt(words[1]));
            continue;
          default:
            throw new IllegalArgumentException(
                "you give incorrect command input either on save image or load image or "
                    + "image mosaics");
        }
      } else if (words.length == 1) {
        switch (words[0]) {
          case "dither":
            iModel.imageDithering();
            continue;
          case "blur":
            iModel.imageBlur();
            continue;
          case "sharpen":
            iModel.imageSharpening();
            continue;
          case "greyscale":
            iModel.imageGreyScale();
            continue;
          case "sepia":
            iModel.imageSepiaTone();
            continue;
          case "highlightEdge":
            iModel.highlightEdgeGrey();
            continue;
          case "contrastEnhancement":
            iModel.histogramEqualization();
            continue;
          default:
            throw new IllegalArgumentException("you give incorrect command for modify images");
        }

      } else if (words.length == 4) {
        try {
          if (words[0].equals("rainbow")) {

            if (words[1].equals("vertical")) {
              iModel.generateRainbowStrips(
                  Integer.parseInt(words[2]), Integer.parseInt(words[3]), false);
              continue;
            } else if (words[1].equals("horizontal")) {
              iModel.generateRainbowStrips(
                  Integer.parseInt(words[2]), Integer.parseInt(words[3]), true);
              continue;
            } else {
              throw new IllegalArgumentException("you give incorrect command for rainbow");
            }
          }
        } catch (Exception e) {
          throw e;
          //          if (e.getMessage().equals("you give incorrect command for rainbow")) {
          //            throw e;
          //          }
          //          throw new IllegalArgumentException(
          //              "You might give incorrect input data for rainbow. "
          //                  + ""
          //                  + "Strip Width or Height should"
          //                  + "be greater than 7");
        }
      } else if (words.length == 3) {
        try {
          switch (words[0]) {
            case "checkerboard":
              iModel.generateCheckerboard(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
              continue;
            case "flag":
              if (words[1].equals("France")) {
                iModel.generateFranceFlag(Integer.parseInt(words[2]));
              } else if (words[1].equals("Greece")) {
                iModel.generateGreeceFlag(Integer.parseInt(words[2]));
              } else if (words[1].equals("Switzerland")) {
                iModel.generateSwitzerlandFlag(Integer.parseInt(words[2]));
              } else if (words[1].equals("Norway")) {
                iModel.generateNorwayFlag(Integer.parseInt(words[2]));
              } else {
                throw new IllegalArgumentException(words[1] + " flag doesnt' exist");
              }
              continue;
            default:
              throw new IllegalArgumentException(
                  "you give incorrect command for checkerboard or flags");
          }
        } catch (Exception e) {
          throw e;
          //          if (e.getMessage().contains("flag doesn't exist")
          //              || e.getMessage().contains("checkerboard or flags")) {
          //            throw e;
          //          }
          //          throw new IllegalArgumentException(
          //              "you might give incorrect input for generating flags or checkerboard.");
        }
      } else {
        throw new IllegalArgumentException("the number of arguments do not fit.");
      }
    }
  }
}
