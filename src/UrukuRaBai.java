import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.Controller;
import controller.ImageProcessorController;
import model.ModelFactory;
import model.ModelFactoryImpl;

public class UrukuRaBai {

  public static void main(String[] args) throws FileNotFoundException {
    String filePath = args[0];
    Readable readable = new FileReader(filePath);
    ModelFactory factory = new ModelFactoryImpl();
    Controller controller = new ImageProcessorController(readable, factory);
    controller.process();
  }
}

