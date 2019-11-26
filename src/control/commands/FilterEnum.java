package control.commands;

/**
 * This enum is a filter enum. It holds enums corresponding to color transformations/
 * image filtering operations that can be done an image.
 * Fields in this enum have a value of a 2D matrix called as kernel.
 */
public enum FilterEnum {
  /**
   * Enum of type Blur image filtering. It carries kernel corresponding to blurring.
   */
  Blur(ImageFilteringConstants.BLUR_KERNEL),
  /**
   * Enum of type Sepia color transformation. It carries kernel corresponding to
   * sepia transformation.
   */
  Sepia(ImageFilteringConstants.SEPIA_KERNEL),
  /**
   * Enum of type Greyscale color transformation. It carries kernel corresponding to
   * greyscale transformation.
   */
  GreyScale(ImageFilteringConstants.GREYSCALE_KERNEL),
  /**
   * Enum of type Sharpen image filtering. It carries kernel corresponding to sharpening
   * an image.
   */
  Sharpen(ImageFilteringConstants.SHARPEN_KERNEL);

  private double[][] kernel;

  FilterEnum(double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * This function is used to get the kernel corresponding to a particular Filter Type.
   * @param     filterType Enum for which value is to be returned.
   * @return    Kernel value of a particular FilterType.
   */
  public static double[][] getKernel(FilterEnum filterType) {
    return filterType.kernel;
  }
}
