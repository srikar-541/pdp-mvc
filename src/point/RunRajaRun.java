/*
package point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import control.ImageGenerationCommand;
import control.ImageManipulationCommand;
import control.ImageProcessingCommand;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Manipulation.Dither;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Manipulation.Mosaic;

import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import model.ImageGenerator;
import model.ImageManipulation;
import model.ImageModel;
import model.ImageProcessor;
import model.ModelFactory;
import model.ModelFactoryImpl;

*/
/**
 * This class runs the ImageProcessor application. It gives user options like loading an image,
 * filtering, coloring and generating an image.
 *//*

public class RunRajaRun {

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

  */
/**
   * This is the entry point of the application. User interaction code resides here.
   *
   * @param args Command Line Arguments
   *//*

  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    ModelFactory f=new ModelFactoryImpl();
    ImageModel model = null;

    ImageManipulationCommand manipulationCommand=null;
    ImageGenerationCommand generationCommand=null;

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
//            String filePath = s.nextLine();
            String filePath="input/manhattan-small.png";
            BufferedImage img = readImage(filePath);
            model = f.getImageModel(img);
            System.out.println("Read image from " + filePath + " of : ");
            System.out.println("width: " + img.getWidth());
            System.out.println("height: " + img.getHeight());
            break;

          case "sepia":
            manipulationCommand = new Sepia();
            break;

          case "Switzerland":
            System.out.println("Specify width of the flag:");
            int width = s.nextInt();
            int height = width;
            model = new ImageProcessor(width, height);

            generationCommand = new Switzerland(width);
            break;

          case "save":
            System.out.println("Enter file path:");
//            filePath = s.nextLine();
            filePath="output.png";
            BufferedImage result=model.getImage();
            writeImage(result,filePath);
            break;
          default:
            System.out.println("Invalid operation");
            break;
        }
//        if(model==null){
//          System.out.println("first load or generate an image!!");
//          return;
//        }
        if (manipulationCommand != null) {
          manipulationCommand.process(model);
          System.out.println(".....Done.");
          manipulationCommand = null;
        }

        if (generationCommand!=null){
          generationCommand.process(model);
          System.out.println(".....Done.");
          generationCommand=null;
        }

      } catch (InputMismatchException ime) {
        System.out.println("Bad length to " + in);
      }
    }
  }
}
*/
