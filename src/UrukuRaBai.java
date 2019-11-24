import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.Controller;
import controller.ImageProcessorController;

public class UrukuRaBai {

  public static void main(String[] args) throws FileNotFoundException {
    String filePath = args[0];
    Readable readable = new FileReader(filePath);
    Controller controller = new ImageProcessorController(readable);
    controller.process();
  }
}

