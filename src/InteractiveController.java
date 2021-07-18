import java.awt.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

/**
 * It represents the controller of the GUI view {@link ImageView}. It implements the Features
 * interface. It is the controller in MVC pattern and it has control of those features
 * (functionality like blur ).
 */
public class InteractiveController implements Features {
  private ImageModelInterface model;
  private ViewInterface view;

  /**
   * It constructs the controller with given ImageModelInterface.
   *
   * @param m the ImageModelInterface
   */
  public InteractiveController(ImageModelInterface m) {
    model = m;
  }

  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  public void setView(ViewInterface v) throws Exception {
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  @Override
  public void highlightEdgeGrey() {
    try {
      model.highlightEdgeGrey();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageBlur() {
    try {
      model.imageBlur();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageSharpening() {
    try {
      model.imageSharpening();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageGreyScale() {
    try {
      model.imageGreyScale();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageSepiaTone() {
    try {
      model.imageSepiaTone();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageDithering() {
    try {
      model.imageDithering();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateCheckerboard() {
    try {
      String nsps = view.userInput("Give the number of squares per side");
      String size = view.userInput("Give the size of the checkerboard");

      model.generateCheckerboard(Integer.parseInt(nsps), Integer.parseInt(size));
      IViewModel iv = (IViewModel) model;

      view.setImage(iv);

    } catch (Exception e) {
      System.out.println(123);
      System.out.println(e);
      view.handleError(e);
    }
  }

  @Override
  public void loadImage() {
    try {
      String inputFIle = view.userChosen();
      if (inputFIle == null) {
        return;
      }
      model.loadImage(inputFIle);
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void storeImage() {
    try {
      String outputFile = view.userStoreAddress("Give the file name with suffix like png");
      if (outputFile == null) {
        return;
      }
      model.storeImage(outputFile);
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateRainbowStrips(boolean horizontal) {
    try {

      String customHeight =
          (horizontal)
              ? view.userInput("Give the height of rainbow and " +
                  "the height should be greater 7")
              : view.userInput("Give the height of rainbow and the height " +
                  "should be positive");
      String customWidth =
          (horizontal)
              ? view.userInput("Give the width of rainbow " +
                  "and the width should be positive")
              : view.userInput("Give the width of rainbow and the " +
                  "width should be greater 7");
      model.generateRainbowStrips(
          Integer.parseInt(customHeight), Integer.parseInt(customWidth), horizontal);
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void imageMosaicing() {
    try {
      String seedNumber = view.userInput("Give the seed number for Mosaics");
      model.imageMosaicing(Integer.parseInt(seedNumber));
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void histogramEqualization() {
    try {
      model.histogramEqualization();
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void removeRedEye(int startColumn, int endColumn, int startRow, int endRow) {
    try {
      int leftTopX = Math.min(startColumn, endColumn);
      int leftTopY = Math.min(startRow, endRow);
      int rightBotX = Math.max(startColumn, endColumn);
      int rightBotY = Math.max(startRow, endRow);
      System.out.println("lefttopx " + leftTopX);
      System.out.println("lefttopeY " + leftTopY);
      System.out.println("rightbotx " + rightBotX);
      System.out.println("rightbotY " + rightBotY);
      model.removeRedEye(leftTopX, rightBotX, leftTopY, rightBotY);
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateFranceFlag() {
    try {
      String height = view.userInput("Give the correct height of France flag");
      model.generateFranceFlag(Integer.parseInt(height));
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateSwitzerlandFlag() {
    try {
      String height = view.userInput("Give the correct height of Switzerland flag");
      model.generateSwitzerlandFlag(Integer.parseInt(height));
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateNorwayFlag() {
    try {
      String height = view.userInput("Give the correct height of Norway flag");
      model.generateNorwayFlag(Integer.parseInt(height));
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void generateGreeceFlag() {
    try {
      String height = view.userInput("Give the correct height of Greece flag");
      model.generateGreeceFlag(Integer.parseInt(height));
      IViewModel iv = (IViewModel) model;
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void runScript() {
    try {
      String userText = view.userInputFromTextArea();
      Readable ed = new StringReader(userText);

      ImageControllerInterface controller = new ImageController(ed);
      controller.getControl((ImageModelInterface) new ImageModel());
      view.cleanScript();
      view.showSuccess();

    } catch (Exception ex) {
      view.handleError(ex);
    }
  }

  @Override
  public void selectScript() {
    try {
      String pathFile = view.userChosen();
      if (pathFile == null) {
        return;
      }
      File file = new File(pathFile);
      InputStream inputStream = null;

      inputStream = new FileInputStream(file);

      InputStreamReader inputStreamReader =
          new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      ImageControllerInterface controller = new ImageController(inputStreamReader);
      controller.getControl((ImageModelInterface) new ImageModel());
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void addBorder() {
    try {
      String borderSize = this.view.userInput("Give the size of border");
      Color color = this.view.userColorChoose();

      IViewModel iv = new BorderDecorator((IViewModel) model, Integer.parseInt(borderSize), color);
      view.setImage(iv);
    } catch (Exception e) {
      view.handleError(e);
    }
  }

  @Override
  public void addSticker() {
    try {
      Point selectedPoint = view.userClickPoint();
      String inputFIle = view.userChosen();
      Color color = this.view.userColorChoose();
      if (inputFIle == null) {
        return;
      }
      BufferedImage stickerImage = ImageIO.read(new FileInputStream(inputFIle));

      IViewModel iv =
          new StickerDecorator(
              (IViewModel) model, stickerImage, color, selectedPoint.x, selectedPoint.y);
      view.setImage(iv);

    } catch (Exception ex) {
      view.handleError(ex);
    }
  }

  @Override
  public void addMeme() {
    try {
      String st1 = this.view.userInput("Give the text on top");
      String st2 = this.view.userInput("Give the text on bottom");
      IViewModel iv = new MemeDecorator((IViewModel) model, st1, st2);
      view.setImage(iv);

    } catch (Exception ex) {
      view.handleError(ex);
    }
  }
}
