package control.commands.Manipulation;
import control.ImageManipulationCommand;
import control.ImageProcessingCommand;
import model.ImageManipulation;
import model.ImageModel;

public class Mosaic implements ImageProcessingCommand {
  private final int seed;

  public Mosaic(int seed){
    this.seed=seed;
  }
  @Override
  public void process(ImageModel model) {
    model.mosaic(this.seed);
  }
}

