import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.Controller;
import controller.ImageProcessorController;
import model.ImageProcessor;

/**
 * This class is the runner for our application. It reads input from the text file, creates the
 * controller object and relinquishes control to the controller's process method.
 */
public class UrukuRaBai {
  
  public static void main(String[] args) throws FileNotFoundException {
    String filePath = args[0];
    Readable readable = new FileReader(filePath);
    Controller controller = new ImageProcessorController(readable, new ImageProcessor());
    controller.process();
  }
}

