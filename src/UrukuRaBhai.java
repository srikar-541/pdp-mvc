import java.io.FileNotFoundException;
import java.io.FileReader;

import control.Controller;
import control.ImageProcessorController;
import model.ImageProcessor;

/**
 * This class is the runner for our application. It creates the controller object
 * to which a file as an argument to the controller and relinquishes control to
 * the controller's process method.
 */
public class UrukuRaBhai {

  /**
   * This method is the runner for the image processing application.
   * It creates a controller object, passes the data source from which data is to be parsed
   * by the controller, creates a model object which contains image data.
   * @param     args Command line arguments.
   * @throws    FileNotFoundException Throws an exception if the file passed
   *                                  is not present.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = args[0];
    Readable readable = new FileReader(filePath);
    Controller controller = new ImageProcessorController(readable, new ImageProcessor());
    controller.process();
  }
}
