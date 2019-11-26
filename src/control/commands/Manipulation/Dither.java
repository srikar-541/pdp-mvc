package control.commands.Manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

/**
 * This command class is a macro which is specific to image dither filtering.
 */
public class Dither implements ImageManipulationCommand {

  /**
   * This method has sequence of steps which are required to give dithering
   * filtering to an image.
   *
   * @param model the ImageModel object which exposes image manipulation operations on an image.
   */
  @Override
  public void process(ImageManipulation model) {
    model.transform(FilterEnum.getKernel(FilterEnum.GreyScale));
    model.dither();
  }
}
