package model;

import java.awt.image.BufferedImage;

/**
 * This interface is used to manipulate an image. Manipulating an image includes taking in a filter
 * operation or applying color filters. Filtering is done by applying convolution on a given image
 * matrix. Transforming is done by matrix multiplication of the current image matrix and matrix of
 * the filter. Future enhancements to Image Generations can be made by extending this interface and
 * having own implementation.
 */
public interface ImageManipulation {

  /**
   * This function transforms an image by applying a color filter on an image which is represented
   * as a matrix. It applies this filter on the current image matrix and updates the matrix.
   *
   * @param transformer The matrix specific to the color filter is passed to this method.
   */
  void transform(double[][] transformer);

  /**
   * This function transforms an image by applying a scaling filter on an image which is passed as a
   * matrix. It applies this filter on the current image matrix and updates the matrix.
   *
   * @param filter This filter is the matrix which has to be convoluted with the current image
   *               matrix. This is also called as kernel.
   */
  void filter(double[][] filter);

  /**
   * This method gets the image in the form of bufferedimage which can be stored in any format in
   * the model implementation.
   *
   * @return BufferedImage representation of the image.
   */
  BufferedImage getImage();

  /**
   * This method gives an image a “stained glass window” effect. It randomly takes a set of points
   * called seeds and then creates a cluster of pixels for each seed. Then the color of each pixel
   * in the image is replaced with the average color of its cluster.
   *
   * @param seed number of seed points in the image to be selected.
   */
  void mosaic(int seed);

  /**
   * This operation breaks down a black and white image into an image that is made of dots from just
   * black and white colors.
   */
  void dither();

  /**
   * This method loads a buffered image into the model and sets it up for image modifications.
   *
   * @param image BufferedImage that has to be loaded into the model.
   */
  void loadImage(BufferedImage image);
}
