import java.awt.Color;
import java.awt.Point;

/**
 * The interface of GUI view interface. It has methods such as setFeatures, setImage, and so on.
 * It represents thew view in MVC pattern.
 */
public interface ViewInterface {


  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f) throws Exception;


  /**
   * Set the image that is showing what the model stores.
   * @param image a IViewModel that the model image data represents.
   */
  void setImage(IViewModel image);


  /**
   * Handle the error thrown during the interaction between the view and user.
   * @param ex the exception that is thrown
   */
  void handleError(Exception ex);


  /**
   * It cleans the script text area in the view.
   */
  void cleanScript();

  /**
   * It shows success dialog.
   */
  void showSuccess();

  /**
   * It returns the path of the chosen file.
   * file.
   * @return Null if user doesn't choose any file else the chosen file's path
   */
  String userChosen();

  /**
   *  It returns the user input String.
   * @param question the question that user will see.
   * @return null if user doesn't give any input else user's String input.
   */
  String userInput(String question);

  /**
   * User will enter a file name and choose a directory where they want to save the file
   * with the file name. It returns the file path including the file name that the user picks.
   * @param question The question that asks the file name.
   * @return A string that represents the file path.
   */
  String userStoreAddress(String question);

  /**
   * It returns the String in text area.
   * @return user's input from text area
   */
  String userInputFromTextArea();

  /**
   * It returns the color that the user chooses.
   * @return a color
   */
  Color userColorChoose();

  /**
   * It returns the point that the user clicks with mouse.
   * @return a point
   */
  Point userClickPoint();






}
