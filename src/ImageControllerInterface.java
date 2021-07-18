import java.io.IOException;

/**
 * This represents the interface of controller in MVC pattern. It takes in users commands and calls
 * the model in getControl method.
 */
public interface ImageControllerInterface {
  /**
   * It gives the control to the controller.
   * @param iModel the model to use
   * @throws IOException if something goes wrong during taking inputs from users.
   */
  void getControl(ImageModelInterface iModel) throws Exception;
}
