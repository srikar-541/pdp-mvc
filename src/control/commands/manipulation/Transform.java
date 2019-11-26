package control.commands.manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

/**
 * This command design class is a macro which is specific to image color transformation such as
 * Greyscaling, Sepia color transformation etc.
 */
public class Transform implements ImageManipulationCommand {

  private FilterEnum filterType;

  /**
   * This creates an Filtering command object of type specified passed as a parameter
   * to this method.
   * @param filterType   Filter specific enum type which is to be applied on a given image.
   *                     Enum of type Greyscale is to be sent if greyscaling is to be done.
   */
  public Transform(FilterEnum filterType) {
    this.filterType = filterType;
  }

  @Override
  public void process(ImageManipulation model) {
    model.transform(FilterEnum.getKernel(filterType));
  }
}
