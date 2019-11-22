package control.commands.Manipulation;
import control.ImageManipulationCommand;
import model.ImageManipulation;

public class Dither implements ImageManipulationCommand {

  @Override
  public void process(ImageManipulation model) {
    double[][] transform = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    model.transform(transform);
    model.dither();
  }

}
