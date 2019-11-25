package model;

import java.awt.image.BufferedImage;

public class ModelFactoryImpl implements ModelFactory{


  @Override
  public ImageModel getImageModel(BufferedImage image) {
    return new ImageProcessor(image);
  }

  @Override
  public ImageModel getImageModel(int width, int height) {
    return new ImageProcessor(width,height);
  }
}
