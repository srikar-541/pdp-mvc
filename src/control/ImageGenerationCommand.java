package control;

import model.ImageGenerator;

/**
 * This is an interface which allows a model to execute the commands specific to a command design
 * pattern. It contains all the macros which can be for future enhancements.  Commands implementing
 * this interface can produce Images.
 */
public interface ImageGenerationCommand {
  /**
   * This method is used to tell the model to do an operation specific to the implementation.
   *
   * @param model ImageGenerator model which contains functionality specific to the implementation.
   */
  void process(ImageGenerator model);
}
