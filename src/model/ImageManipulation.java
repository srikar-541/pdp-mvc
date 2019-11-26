package model;

import java.awt.image.BufferedImage;

/**
 * This interface is used to manipulate an image. Manipulating an image includes taking in a filter operation or
 * applying color filters. Filtering is done by applying convolution on a given image matrix. Transforming is done by
 * matrix multiplication of the current image matrix and matrix of the filter. Future enhancements to Image Generations
 * can be made by extending this interface and having own implementation.
 */
public interface ImageManipulation {

  /**
   * This function transforms an image by applying a color filter on an image which is represented as a matrix. It
   * applies this filter on the current image matrix and updates the matrix.
   *
   * @param transformer The matrix specific to the color filter is passed to this method.
   */
  void transform(double[][] transformer);

  /**
   * This function transforms an image by applying a scaling filter on an image which is passed as a matrix. It applies
   * this filter on the current image matrix and updates the matrix.
   *
   * @param filter This filter is the matrix which has to be convoluted with the current image matrix. This is also
   *               called as kernel.
   */
  void filter(double[][] filter);

  /**
   * This method gets the image in the form of bufferedimage which can be stored
   * in any format in the model implementation.
   *
   * @return BufferedImage representation of the image.
   */
  BufferedImage getImage();

  void mosaic(int seed);

  void dither();

  void loadImage(BufferedImage image);
}
