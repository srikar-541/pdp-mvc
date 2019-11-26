package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import control.ImageGenerationCommand;
import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import control.commands.Manipulation.Dither;
import control.commands.Manipulation.Filter;
import control.commands.Manipulation.Mosaic;
import model.ImageModel;

public class ImageProcessorController implements Controller {

  private Readable readable;
  private final ImageModel model;

  public ImageProcessorController(Readable readable, ImageModel model) {
    this.readable = readable;
    this.model = model;
  }

  @Override
  public void process() {
    Map<String, Function<Scanner, ImageGenerationCommand>> generationKnownCommands =
            setupGenerationKnownCommands();
    Map<String, Function<Scanner, ImageManipulationCommand>> manipulationKnownCommands =
            setupManipulationKnownCommands();

    Scanner scanner = new Scanner(readable);
    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();
      if (command.equals("load")) {
        BufferedImage image = readImage(scanner.nextLine());
        model.loadImage(image);
      } else if (command.equals("save")) {
        writeImage(model.getImage(), scanner.nextLine());
      } else {
        if (generationKnownCommands.containsKey(command) ||
                manipulationKnownCommands.containsKey(command)) {
          if (generationKnownCommands.containsKey(command)) {
            /*int width = Integer.parseInt(commands.get(i)[1]);
            int height = Integer.parseInt(commands.get(i)[2]);
            model.resetCanvas(width, height);*/
            generationKnownCommands.get(command).apply(scanner).process(model);
          } else {
            manipulationKnownCommands.get(command).apply(scanner).process(model);
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

  private Map<String, Function<Scanner, ImageGenerationCommand>> setupGenerationKnownCommands() {
    Map<String, Function<Scanner,
            ImageGenerationCommand>> generationKnownCommands = new HashMap<>();
    generationKnownCommands.put("Switzerland", scanner -> new Switzerland(scanner.nextInt()));
    generationKnownCommands.put("France", scanner -> new FranceFlag(scanner.nextInt()));
    generationKnownCommands.put("Horizontal Rainbow", scanner -> new HorizontalRainbow(
            scanner.nextInt(), scanner.nextInt()));
    generationKnownCommands.put("Vertical Rainbow", scanner -> new
            VerticalRainbow(scanner.nextInt(), scanner.nextInt()));
    generationKnownCommands.put("CheckeredBoard", scanner ->
            new CheckeredBoard(scanner.nextInt()));
    generationKnownCommands.put("Greece", scanner ->
            new GreeceFlag(scanner.nextInt(), scanner.nextInt()));
    return generationKnownCommands;
  }

  private Map<String, Function<Scanner, ImageManipulationCommand>> setupManipulationKnownCommands() {
    Map<String, Function<Scanner, ImageManipulationCommand>>
            manipulationKnownCommands = new HashMap<>();
    manipulationKnownCommands.put("mosaic", scanner -> new Mosaic(scanner.nextInt()));
    manipulationKnownCommands.put("dither", scanner -> new Dither());
    manipulationKnownCommands.put("sepia", scanner -> new Filter(FilterEnum.Sepia));
    manipulationKnownCommands.put("greyscale", scanner -> new Filter(FilterEnum.GreyScale));
    manipulationKnownCommands.put("blur", scanner -> new Filter(FilterEnum.Blur));
    manipulationKnownCommands.put("sharpen", scanner -> new Filter(FilterEnum.Sharpen));

    return manipulationKnownCommands;
  }
}
