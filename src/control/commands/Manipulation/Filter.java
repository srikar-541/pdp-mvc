package control.commands.Manipulation;

import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import model.ImageManipulation;

public class Filter implements ImageManipulationCommand {

  private FilterEnum filterType;

  public Filter(FilterEnum filterType) {
    this.filterType = filterType;
  }

  @Override
  public void process(ImageManipulation model) {
    model.filter(FilterEnum.getKernel(filterType));
  }
}
