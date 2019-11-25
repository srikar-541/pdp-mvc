package control.commands.Manipulation;

import control.ImageManipulationCommand;
import model.ImageManipulation;

/**
 * This class is an macro which does greyscaling of images. It contains the kernel
 * specific to image greyscaling. The model applies this filter and the model stores the updated
 * image pixel.
 */
public class GreyScale implements ImageManipulationCommand {

  @Override
  public void process(ImageManipulation model) {
    double[][] transform = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    model.transform(transform);
  }
}
