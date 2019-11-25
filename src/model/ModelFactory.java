package model;

import java.awt.image.BufferedImage;

public interface ModelFactory {

  ImageManipulation getManipulation(BufferedImage image);

  ImageGenerator getGeneration(int width,int height);
}
