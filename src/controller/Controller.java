package controller;

/**
 * This interface represents the controller for this application.
 * It makes calls to the model and view and transfers data back and forth.
 */
public interface Controller {

  /**
   * This is the entry point of the ImageProcessing application. It makes calls to the model to
   * process the given input.
   */
  void process();
}
