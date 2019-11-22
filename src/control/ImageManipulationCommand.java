package control;

import model.ImageManipulation;

/**
 * This is an interface which allows a model to execute the commands specific
 * to a command design pattern. It contains all the macros which can be for future
 * enhancements.
 */
public interface ImageManipulationCommand {

  /**
   * This method is used to tell the model to do an operation specific to the implementation.
   * @param     model Model interface which contains the functionality specific
   *                 to the implementation.
   */
  void process(ImageManipulation model);
}
