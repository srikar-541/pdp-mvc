import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;


import control.ImageGenerationCommand;
import control.ImageManipulationCommand;
import control.commands.Manipulation.Blur;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Manipulation.Dither;
import control.commands.Manipulation.GreyScale;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Manipulation.Mosaic;

import control.commands.Manipulation.Sepia;
import control.commands.Manipulation.Sharpen;
import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import model.ImageManipulation;
import model.ImageModel;
import model.ImageProcessor;

/**
 * This class runs the ImageProcessor application. It gives user options like loading an image,
 * filtering, coloring and generating an image.
 */
public class ProgramRunner {

  private static BufferedImage readImage(String filepath) {
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

  private static void writeImage(BufferedImage img, String filepath) {
    try {
      File output = new File(filepath);
      ImageIO.write(img, "png", output);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Image written to :" + filepath);
  }


  /**
   * This is the entry point of the application. User interaction code resides here.
   *
   * @param args Command Line Arguments
   */
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    ImageModel model = null;

    ImageManipulationCommand cmd = null;
    System.out.println("Enter an option: you either load or generate an image. Type q to quit.");
    System.out.println("your options are: sepia,greyscale,blur,sharpen,edge");
    System.out.println("You can also generate the following images:");
    System.out.println("The flags of France, Switzerland,Greece ");
    System.out.println("Horizontal Rainbow, Vertical Rainbow and CheckeredBoard");
    while (s.hasNext()) {
      String in = s.nextLine();
      try {
        switch (in) {
          case "q":
            return;
          case "load":
            System.out.println("Enter file path:");
            String filePath = s.nextLine();
            BufferedImage img = readImage(filePath);
            model = new ImageProcessor(img);
            System.out.println("Read image from " + filePath + " of : ");
            System.out.println("width: " + img.getWidth());
            System.out.println("height: " + img.getHeight());
            break;
          case "mosaic":
            int seedCount=s.nextInt();
            cmd=new Mosaic(seedCount);
            break;

          case "dither":
            cmd=new Dither();
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
            System.out.println("Specify width of the flag:");
            int width = s.nextInt();
            model = new ImageProcessor(width, (2 * width) / 3);
            cmd = new FranceFlag(width);
            break;
          case "Switzerland":
            System.out.println("Specify width of the flag:");
            width = s.nextInt();
            int height = width;
            model = new ImageProcessor(width, height);
            cmd = new Switzerland(width);
            break;
          case "sharpen":
            cmd = new Sharpen();
            break;

          case "Horizontal Rainbow":
            System.out.println("Specify thickness of one stripe:");
            int thickness = s.nextInt();
            System.out.println("Specify the length of a stripe:");
            int length = s.nextInt();
            model = new ImageProcessor(length, thickness * 7);
            cmd = new HorizontalRainbow(length, thickness);
            break;
          case "Vertical Rainbow":
            System.out.println("Specify the width of a stripe:");
            int bandwidth = s.nextInt();
            System.out.println("Specify the height of the stripe:");
            height = s.nextInt();
            model = new ImageProcessor(bandwidth * 7, height);
            cmd = new VerticalRainbow(height, bandwidth);
            break;
          case "CheckeredBoard":
            int size = s.nextInt();
            model = new ImageProcessor(8 * size, 8 * size);
            cmd = new CheckeredBoard(size);
            break;
          case "Greece":
            System.out.println("Specify the height of the flag:");
            height = s.nextInt();
            height = height / 18 * 18;
            width = 3 * height / 2;
            model = new ImageProcessor(width, height);
            cmd = new GreeceFlag(width, height);
            break;
          case "save":
            System.out.println("Enter file path:");
            filePath = s.nextLine();
            BufferedImage result=model.getImage();
            writeImage(result,filePath);
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
      } catch (InputMismatchException ime) {
        System.out.println("Bad length to " + in);
      }
    }
  }
}