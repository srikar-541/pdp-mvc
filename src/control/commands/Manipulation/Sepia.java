package control.commands.Manipulation;

import control.ImageManipulationCommand;
import model.ImageManipulation;


/**
 * This class is an macro which does sepia color transformation on images. It contains the
 * sepia color transformation matrix. The model applies this filter and the model stores the
 * updated image pixels.
 */
public class Sepia implements ImageManipulationCommand {

  @Override
  public void process(ImageManipulation model) {
    double[][] transform = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    model.transform(transform);
  }
}
