package control.commands;

/**
 * This class holds the enums required for this application.
 */
public enum FilterEnum {

  Blur(Constants.BLUR_KERNEL),
  Sepia(Constants.SEPIA_KERNEL),
  GreyScale(Constants.GREYSCALE_KERNEL),
  Sharpen(Constants.SHARPEN_KERNEL);

  private double[][] kernel;

  FilterEnum(double[][] kernel) {
    this.kernel = kernel;
  }

  public static double[][] getKernel(FilterEnum filterType) {
    return filterType.kernel;
  }
}
