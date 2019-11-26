package control.commands.Manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

public class Dither implements ImageManipulationCommand {

  /**
   * This method is used to tell the model to do an operation specific to the implementation.
   *
   * @param model Model interface which contains the functionality specific to the implementation.
   */
  @Override
  public void process(ImageManipulation model) {
    model.transform(FilterEnum.getKernel(FilterEnum.GreyScale));
    model.dither();
  }
}
