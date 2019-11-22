package control.commands.Manipulation;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Dither implements ImageProcessingCommand {

  /**
   * This method is used to tell the model to do an operation specific to the implementation.
   *
   * @param model Model interface which contains the functionality specific to the implementation.
   */
  @Override
  public void process(ImageModel model) {
    double[][] transform = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    model.transform(transform);
    model.dither();
  }
}
