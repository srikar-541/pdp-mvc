package control.commands.Manipulation;

import control.ImageManipulationCommand;
import model.ImageManipulation;

public class Mosaic implements ImageManipulationCommand {
  private final int seeds;

  public Mosaic(int seeds) {
    this.seeds = seeds;
  }

  @Override
  public void process(ImageManipulation model) {
    model.mosaic(this.seeds);
  }
}

