package control.commands.manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

/**
 * This command design class is a macro which is specific to image filtering such as
 * Blurring, Sharpening etc.
 */
public class Filter implements ImageManipulationCommand {

  private FilterEnum filterType;

  /**
   * This creates an Filtering command object of type specified passed as a parameter
   * to this method.
   * @param filterType   Filter specific enum type which is to be applied on a given image.
   *                     Enum of type Blur is to be sent if blurring is to be done.
   */
  public Filter(FilterEnum filterType) {
    this.filterType = filterType;
  }

  @Override
  public void process(ImageManipulation model) {
    model.filter(FilterEnum.getKernel(filterType));
  }
}
