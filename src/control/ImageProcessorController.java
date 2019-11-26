package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import control.Controller;
import control.ControllerConstants;
import model.ImageModel;

/**
 * This controller is the entry point for the Image Processing application. It has the data source from which the image
 * transformations to be done are present. It parses the data and calls the specific methods in the model using the
 * ImageModel object.
 */
public class ImageProcessorController implements Controller {

  private final Readable readable;
  private final ImageModel model;

  /**
   * @param readable The readable object which contains the sequence of the commands to be applied on an image model.
   * @param model    The ImageModel object on which the image transformations are to be done.
   */
  public ImageProcessorController(Readable readable, ImageModel model) {
    this.readable = readable;
    this.model = model;
  }

  @Override
  public void process() {
    Scanner scanner = new Scanner(readable);
    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();
      if (command.equals("load")) {
        BufferedImage image = readImage(scanner.nextLine());
        model.loadImage(image);
      } else if (command.equals("save")) {
        writeImage(model.getImage(), scanner.nextLine());
      } else {
        if (ControllerConstants.generationKnownCommands.containsKey(command)
                || ControllerConstants.manipulationKnownCommands.containsKey(command)) {
          if (ControllerConstants.generationKnownCommands.containsKey(command)) {
            ControllerConstants.generationKnownCommands.
                    get(command).apply(scanner).process(model);
          } else {
            ControllerConstants.manipulationKnownCommands.
                    get(command).apply(scanner).process(model);
          }
        }
      }
    }
  }

  private BufferedImage readImage(String filepath) {
    try {
      return ImageIO.read(new File(filepath));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  private void writeImage(BufferedImage img, String filepath) {
    try {
      File output = new File(filepath);
      ImageIO.write(img, "png", output);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Image written to :" + filepath);
  }
}
