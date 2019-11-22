package control;

import model.ImageGenerator;


public interface ImageGenerationCommand {

  void process(ImageGenerator model);
}
