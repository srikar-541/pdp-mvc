package model;

import java.awt.image.BufferedImage;

public interface ModelFactory {

  ImageModel getImageModel(BufferedImage image);

  ImageModel getImageModel(int width, int height);
}
