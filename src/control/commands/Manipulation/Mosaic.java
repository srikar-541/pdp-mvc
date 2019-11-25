package control.commands.Manipulation;
import control.ImageManipulationCommand;
import model.ImageManipulation;

public class Mosaic implements ImageManipulationCommand {
  private final int seed;

  public Mosaic(int seed){
    this.seed=seed;
  }
  @Override
  public void process(ImageManipulation model) {
    model.mosaic(this.seed);
  }
}

