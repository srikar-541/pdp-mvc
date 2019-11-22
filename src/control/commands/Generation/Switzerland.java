package control.commands.Generation;

import java.awt.Color;

import control.ImageGenerationCommand;
import model.ImageGenerator;

/**
 * This class is an macro which generates a Greece flag of a given width. The height
 * in a Switzerland flag equals to the width. Hence we accept only one parameter.
 */
public class Switzerland implements ImageGenerationCommand {
  private final int width;

  /**
   * This creates an object of type Switzerland flag by taking in parameter
   * of the width of the final flag to be generated.
   * @param width of the  flag.
   */
  public Switzerland(int width) {
    this.width = width;
  }

  @Override
  public void process(ImageGenerator model) {
    model.drawVerticalBand(0, 0, width - 1, width - 1, Color.RED);
    model.drawHorizontalBand((6 * width) / 32, (13 * width) / 32,
            (26 * width) / 32, (19 * width) / 32, Color.WHITE);
    model.drawVerticalBand((13 * width) / 32, (6 * width) / 32,
            (19 * width) / 32, (26 * width) / 32, Color.WHITE);
  }
}
