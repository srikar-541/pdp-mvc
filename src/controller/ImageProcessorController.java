package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import control.ImageProcessingCommand;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import control.commands.Manipulation.Blur;
import control.commands.Manipulation.Dither;
import control.commands.Manipulation.GreyScale;
import control.commands.Manipulation.Mosaic;
import control.commands.Manipulation.Sepia;
import control.commands.Manipulation.Sharpen;
import model.ImageModel;
import model.ImageProcessor;

public class ImageProcessorController implements Controller {

  private Readable readable;

  public ImageProcessorController(Readable readable) {
    this.readable = readable;
  }

  @Override
  public void process() {
    List<String> commands = readData();
    ImageModel model = null;
    ImageProcessingCommand cmd = null;

    for (int i = 0; i < commands.size(); i++) {
      String line = commands.get(i);
      switch (line) {
        case "q":
          return;
        case "load":
          String filePath = commands.get(i + 1);
          i++;
          BufferedImage img = readImage(filePath);
          model = new ImageProcessor(img);
          break;
        case "mosaic":
          int seedCount = Integer.parseInt(commands.get(i + 1));
          i++;
          cmd = new Mosaic(seedCount);
          break;
        case "dither":
          cmd = new Dither();
          break;
        case "sepia":
          cmd = new Sepia();
          break;
        case "greyscale":
          cmd = new GreyScale();
          break;
        case "blur":
          cmd = new Blur();
          break;
        case "France":
          int width = Integer.parseInt(commands.get(i + 1));
          i++;
          model = new ImageProcessor(width, (2 * width) / 3);
          cmd = new FranceFlag(width);
          break;
        case "Switzerland":
          width = Integer.parseInt(commands.get(i + 1));
          i++;
          int height = width;
          model = new ImageProcessor(width, height);
          cmd = new Switzerland(width);
          break;
        case "sharpen":
          cmd = new Sharpen();
          break;
        case "Horizontal Rainbow":
          int thickness = Integer.parseInt(commands.get(i + 1));
          i++;
          int length = Integer.parseInt(commands.get(i + 1));
          i++;
          model = new ImageProcessor(length, thickness * 7);
          cmd = new HorizontalRainbow(length, thickness);
          break;
        case "Vertical Rainbow":
          int bandwidth = Integer.parseInt(commands.get(i + 1));
          i++;
          height = Integer.parseInt(commands.get(i + 1));
          i++;
          model = new ImageProcessor(bandwidth * 7, height);
          cmd = new VerticalRainbow(height, bandwidth);
          break;
        case "CheckeredBoard":
          int size = Integer.parseInt(commands.get(i + 1));
          i++;
          model = new ImageProcessor(8 * size, 8 * size);
          cmd = new CheckeredBoard(size);
          break;
        case "Greece":
          height = Integer.parseInt(commands.get(i + 1));
          i++;
          height = height / 18 * 18;
          width = 3 * height / 2;
          model = new ImageProcessor(width, height);
          cmd = new GreeceFlag(width, height);
          break;
        case "save":
          filePath = commands.get(i + 1);
          i++;
          BufferedImage result = model.getImage();
          writeImage(result, filePath);
          break;
        default:
          System.out.println("Invalid operation");
          break;
      }
      if (cmd != null) {
        cmd.process(model);
        System.out.println(".....Done.");
        cmd = null;
      }
    }
  }

  private List<String> readData() {
    Scanner scanner = new Scanner(readable);
    List<String> commands = new ArrayList<>();
    while (scanner.hasNextLine()) {
      commands.add(scanner.nextLine());
    }

    return commands;
  }

  private BufferedImage readImage(String filepath) {
    BufferedImage image = null;
    File f;
    try {
      f = new File(filepath);
      image = ImageIO.read(f);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return image;
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
