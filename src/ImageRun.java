import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** the main for taking input from the users. */
public class ImageRun {

  /**
   * the main function.
   *
   * @param args the arguments given by the users
   * @throws Exception throw exception if something wrong during processing
   */
  public static void main(String[] args) throws Exception {
    System.out.println(args.length);
    if (args.length != 1 && args.length != 2) {
      throw new IllegalArgumentException("command-line invalid1");
    } else if (args.length == 2 && !args[0].equals("-script")) {
      System.out.println(args[1]);
      throw new IllegalArgumentException("command-line invalid2");
    } else if (args.length == 1 && !args[0].equals("-interactive")) {
      throw new IllegalArgumentException("command-line invalid3");
    }
    if (args.length == 2) {
      File file = new File(args[1]);
      try {
        InputStream intputstream = new FileInputStream(file);
        //    InputStream intputstream = ImageRun1.class.getResourceAsStream(args[0]);
        //    System.out.println(intputstream);
        InputStreamReader inputStreamReader =
            new InputStreamReader(intputstream, StandardCharsets.UTF_8);
        ImageControllerInterface controller = new ImageController(inputStreamReader);

        controller.getControl((ImageModelInterface) new ImageModel());
        System.out.println("done");
      } catch (Exception e) {
        System.out.println("fail");
        e.printStackTrace();
      }
    } else {
      ImageModelInterface model = new ImageModel();
      InteractiveController controller1 = new InteractiveController(model);
      ViewInterface view = new ImageView("美图秀秀");
      controller1.setView(view);
    }
  }
}
