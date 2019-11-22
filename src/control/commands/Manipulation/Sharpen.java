package control.commands.Manipulation;

import control.ImageManipulationCommand;
import model.ImageManipulation;
import model.ImageModel;

/**
 * This class is an macro which does sharpening of images. It contains the kernel
 * specific to image sharpening. The model applies this filter and the model stores the updated
 * image pixels.
 */
public class Sharpen implements ImageManipulationCommand {
  @Override
  public void process(ImageManipulation model) {
    double[][] sharp = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    model.filter(sharp);
  }
}
