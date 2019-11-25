package model;

import java.awt.image.BufferedImage;

public class ModelFactoryImpl implements ModelFactory{

  @Override
  public ImageManipulation getManipulation(BufferedImage image) {
    return new ImageProcessor(image);
  }

  @Override
  public ImageGenerator getGeneration( int width,int height) {
    return new ImageProcessor(width,height);
  }
}
