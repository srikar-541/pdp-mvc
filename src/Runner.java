import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import control.ImageGenerationCommand;
import control.ImageManipulationCommand;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import control.commands.Manipulation.Blur;
import control.commands.Manipulation.GreyScale;
import control.commands.Manipulation.Sepia;
import control.commands.Manipulation.Sharpen;
import model.ImageGenerator;
import model.ImageManipulation;
import model.ImageProcessor;

public class Runner {

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

  public static void main(String [] args){
    Scanner s = new Scanner(System.in);
    System.out.println("Your options are load an image or generate an image");
    String option=s.nextLine();
    BufferedImage img=null;
    if(option.equals("load"))
    {
      load(s,img);
    }
    else if(option.equals("generate")) {
      generate(s);
    }
  }

  private static void load(Scanner s, BufferedImage img){
    if(img==null) {
      System.out.println("enter valid filepath for input image:");
//        filePath = s.nextLine();
      String filePath = "input/manhattan-small.png";
      System.out.println("your options are: sepia,greyscale,blur,sharpen,edge");
      img = readImage(filePath);
    }
    ImageManipulation model = new ImageProcessor(img);
    ImageManipulationCommand cmd = null;
    while (s.hasNext()) {
      String in = s.nextLine();
      switch (in) {
        case "q":
          System.out.println("Quitting");
          return;
        case "sepia":
          cmd = new Sepia();
          break;
        case "sharpen":
          cmd = new Sharpen();
          break;
        case "blur":
          cmd = new Blur();
          break;
        case "greyscale":
          cmd = new GreyScale();
          break;
        case "save":
          BufferedImage result=model.getImage();
          writeImage(result,"output/result.png");
          cmd=null;
          break;

      }
      if (cmd != null) {
        cmd.process(model);
        System.out.println(".....Done.");
        cmd = null;
      }
    }

  }

  private static void generate(Scanner s){
    ImageGenerator model = null;
    ImageGenerationCommand cmd = null;
    System.out.println("Your options are flags of France, Switzerland,Greece and Horizontal "
            + "Rainbow, Vertical Rainbow and CheckeredBoard");
    while (s.hasNext()) {
      String in = s.nextLine();
      switch (in) {
        case "q":
          System.out.println("Quitting");
          return;
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
          BufferedImage result=model.getImage();
          writeImage(result,"output/result.png");
          cmd=null;
          break;
        case "modify":
          BufferedImage img=model.getImage();
          load(s,img);
          return;
      }
      if (cmd != null) {
        cmd.process(model);
        System.out.println(".....Done.");
        cmd = null;
      }
    }
  }

}
