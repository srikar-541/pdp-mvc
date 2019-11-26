package control.commands;

/**
 * This class holds the standard constants required for this application such as the matrix
 * representation of the kernels.
 */
class ImageFilteringConstants {

  static final double[][] BLUR_KERNEL = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  static final double[][] SHARPEN_KERNEL = {
          {-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, 0.25, 1.0, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0.125}
  };

  static final double[][] GREYSCALE_KERNEL = {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
  };

  static final double[][] SEPIA_KERNEL = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };
}
