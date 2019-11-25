package control;

import model.ImageManipulation;

public interface ImageManipulationCommand {

  void process(ImageManipulation model);
}
