package control.commands.Manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

public class Transform implements ImageManipulationCommand {

  private FilterEnum filterType;

  public Transform(FilterEnum filterType) {
    this.filterType = filterType;
  }

  @Override
  public void process(ImageManipulation model) {
    model.transform(FilterEnum.getKernel(filterType));
  }
}
