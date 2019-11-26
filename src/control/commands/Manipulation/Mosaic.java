package control.commands.Manipulation;

import control.ImageManipulationCommand;
import model.ImageManipulation;

/**
 * This command design class is a macro which is specific to mosaic image filtering.
 *
 */
public class Mosaic implements ImageManipulationCommand {
  private final int seeds;

  /**
   * This creates an object of type Mosaic image filtering. It accepts the number of seeds
   * that have to be considered for mosaicing.
   * @param     seeds Number of seeds/clusters to be selected from an image.
   */
  public Mosaic(int seeds) {
    this.seeds = seeds;
  }

  @Override
  public void process(ImageManipulation model) {
    model.mosaic(this.seeds);
  }
}

