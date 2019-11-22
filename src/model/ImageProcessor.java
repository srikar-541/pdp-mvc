package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import point.TwoDPoint;

/**
 * This class is an implementation of the ImageModel. It implements the functionality of filtering,
 * transforming and image generation.
 */
public class ImageProcessor implements ImageModel {

  private int[][] reds;
  private int[][] greens;
  private int[][] blues;

  private final int height;
  private final int width;

  /**
   * This creates an object of type ImageProcessor by taking a BufferedImage as input. The image is
   * loaded by reading the individual r,g,b pixel values from a BufferedImage and setting them in
   * the blue, green and red matrices. This object is created when filtering/coloring operation is
   * to applied.
   *
   * @param image The original image on which operations of filtering, transforming are to be
   *              applied.
   */
  public ImageProcessor(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();

    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];

    loadImage(image);
  }

  private void loadImage(BufferedImage image) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int pixel = image.getRGB(j, i);
        Color color = new Color(pixel);
        reds[i][j] = color.getRed();
        greens[i][j] = color.getGreen();
        blues[i][j] = color.getBlue();
      }
    }
  }

  /**
   * This creates an object of type ImageProcessor by taking height and width as input and setting
   * the matrix of r,g,b to default. This constructor is used when images are to be generated.
   *
   * @param width  The width of the image to be generated.
   * @param height The height of the image to be generated.
   */
  public ImageProcessor(int width, int height) {
    this.width = width;
    this.height = height;
    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];
  }

  //This methods clamps color of red,blue,green. It sets the value to 0 if
  //the value passed is < 0 and 255 if the value passed is > 255.
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    return Math.min(255, value);
  }

  //The pixel value is returned by taking in the red,green,blue color values.
  private int pixelVal(int red, int green, int blue) {
    return (red << 16) | (green << 8) | blue;
  }

  @Override
  public BufferedImage getImage() {
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int pixel = pixelVal(this.reds[i][j], this.greens[i][j], this.blues[i][j]);

        result.setRGB(j, i, pixel);
      }
    }
    return result;
  }

  @Override
  public void transform(double[][] transformer) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = this.reds[i][j];
        int g = this.greens[i][j];
        int b = this.blues[i][j];

        this.reds[i][j] = clamp((int) Math.floor(r * transformer[0][0]
                + g * transformer[0][1] + b * transformer[0][2]));

        this.greens[i][j] = clamp((int) Math.floor(r * transformer[1][0]
                + g * transformer[1][1] + b * transformer[1][2]));

        this.blues[i][j] = clamp((int) Math.floor(r * transformer[2][0]
                + g * transformer[2][1] + b * transformer[2][2]));
      }
    }
  }

  @Override
  public void filter(double[][] filter) {
    this.reds = convolve(filter, this.reds);
    this.greens = convolve(filter, this.greens);
    this.blues = convolve(filter, this.blues);
  }

  @Override
  public void setRed(int x, int y, int red) {
    this.reds[y][x] = clamp(red);
  }

  @Override
  public void setGreen(int x, int y, int green) {
    this.greens[y][x] = clamp(green);
  }

  @Override
  public void setBlue(int x, int y, int blue) {
    this.blues[y][x] = clamp(blue);
  }

  @Override
  public void setPixel(int x, int y, int pixel) {
    Color color = new Color(pixel);
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();
    this.reds[x][y] = clamp(red);
    this.greens[x][y] = clamp(green);
    this.blues[x][y] = clamp(blue);
  }

  @Override
  public void drawVerticalLine(int x1, int y1, int length, Color lineColor) {
    int red = lineColor.getRed();
    int green = lineColor.getGreen();
    int blue = lineColor.getBlue();

    if (y1 + length > this.height) {
      throw new IllegalArgumentException("Illegal argument of length while line draw");
    }

    for (int j = y1; j < y1 + length; j++) {
      this.setBlue(x1, j, blue);
      this.setRed(x1, j, red);
      this.setGreen(x1, j, green);
    }
  }

  @Override
  public void drawHorizontalLine(int x1, int y1, int length, Color lineColor) {
    int red = lineColor.getRed();
    int green = lineColor.getGreen();
    int blue = lineColor.getBlue();

    if (x1 + length > this.width) {
      throw new IllegalArgumentException("Illegal argument of length while line draw");
    }

    for (int i = x1; i < x1 + length; i++) {
      this.setBlue(i, y1, blue);
      this.setRed(i, y1, red);
      this.setGreen(i, y1, green);
    }
  }

  @Override
  public void drawHorizontalBand(int x1, int y1, int x2, int y2, Color bandColor) {
    for (int i = 0; i <= Math.abs(y1 - y2); i++) {
      drawHorizontalLine(x1, i + y1, Math.abs(x2 - x1) + 1, bandColor);
    }
  }

  @Override
  public void drawVerticalBand(int x1, int y1, int x2, int y2, Color bandColor) {
    for (int i = 0; i <= Math.abs(x1 - x2); i++) {
      drawVerticalLine(i + x1, y1, Math.abs(y2 - y1) + 1, bandColor);
    }
  }

  private int[][] convolve(double[][] kernel, int[][] channel) {
    int kernelSize = kernel.length;
    int padding = (kernelSize - 1) / 2;
    double[][] paddedMatrix = padChannel(channel, padding, this.height, this.width);
    int[][] convolutedChannel = convolutionHelper(paddedMatrix, kernel, padding);
    return removePadding(convolutedChannel, padding);
  }

  private int[][] removePadding(int[][] convolutedMatrix, int padding) {
    int[][] noPadding = new int[convolutedMatrix.length - 2 * padding][convolutedMatrix[0]
            .length - 2 * padding];
    int end_i = convolutedMatrix.length - padding;
    int end_j = convolutedMatrix[0].length - padding;
    int x = 0;
    int y = 0;
    for (int i = padding; i < end_i; i++) {
      for (int j = padding; j < end_j; j++) {
        noPadding[x][y] = convolutedMatrix[i][j];
        y++;
      }
      y = 0;
      x++;
    }
    return noPadding;
  }

  private int[][] convolutionHelper(double[][] paddedMatrix, double[][] kernel, int padding) {
    int[][] convolutedChannel = new int[paddedMatrix.length][paddedMatrix[0].length];
    int kernelSize = kernel.length;
    int end_i = paddedMatrix.length - padding;
    int end_j = paddedMatrix[end_i - 1].length - padding;

    for (int i = padding; i < end_i; i++) {
      for (int j = padding; j < end_j; j++) {

        int selectIndexStartI = i - padding;
        int selectIndexStartJ = j - padding;

        int selectIndexEndI = i + padding + 1;
        int selectIndexEndJ = j + padding + 1;

        double[][] selectedMatrix = selectMatrix(selectIndexStartI, selectIndexEndI,
                selectIndexStartJ, selectIndexEndJ, kernelSize, paddedMatrix);
        convolutedChannel[i][j] = clamp((int) Math.round(convolutedValue(selectedMatrix, kernel)));

      }
    }
    return convolutedChannel;
  }

  private double[][] selectMatrix(int selectIndexStartI, int selectIndexEndI,
                                  int selectIndexStartJ, int selectIndexEndJ, int kernelSize,
                                  double[][] paddedMatrix) {
    double[][] selectedMatrix = new double[kernelSize][kernelSize];
    int index_i = 0;
    int index_j = 0;

    for (int x = selectIndexStartI; x < selectIndexEndI; x++) {
      for (int y = selectIndexStartJ; y < selectIndexEndJ; y++) {
        selectedMatrix[index_i][index_j] = paddedMatrix[x][y];
        index_j++;
      }
      index_j = 0;
      index_i++;
    }
    return selectedMatrix;
  }


  private double convolutedValue(double[][] selectedMatrix, double[][] kernel) {
    double sum = 0;
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        sum = sum + selectedMatrix[i][j] * kernel[i][j];
      }
    }
    return sum;
  }

  private double[][] padChannel(int[][] channel, int padding, int height, int width) {
    int channelWithPaddingHeight = height + 2 * padding;
    int channelWithPaddingWidth = width + 2 * padding;
    double[][] channelWithPadding = new double[channelWithPaddingHeight][channelWithPaddingWidth];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        channelWithPadding[i + padding][j + padding] = channel[i][j];
      }
    }
    return channelWithPadding;
  }

  @Override
  public void mosaic(int seed) {

    List<Integer> x=generateRandom(seed,this.height);
    List<Integer> y=generateRandom(seed,this.width);
    List<TwoDPoint> randomCenters=generatePoints(x,y);

    Map<TwoDPoint, List<TwoDPoint>> clusters = initializeClusters(randomCenters);
    List<TwoDPoint> allPoints=populate();
    assignCentersToPoints(allPoints, clusters);
    List< List<Integer> > rgbValues=generateAverageColors(clusters);
    setNewRGBValues(clusters,rgbValues);

  }

  private void setNewRGBValues(Map<TwoDPoint, List<TwoDPoint>> clusters, List<List<Integer>> rgbValues) {
    int j=0;
    for(Map.Entry<TwoDPoint,List<TwoDPoint>> entry: clusters.entrySet()){
      List<TwoDPoint> clusterPoints=entry.getValue();
      List<Integer> colors=rgbValues.get(j);
      j++;

      for (TwoDPoint point : clusterPoints) {
        int x = point.getX();
        int y = point.getY();

        this.reds[x][y] = colors.get(0);
        this.greens[x][y] = colors.get(1);
        this.blues[x][y] = colors.get(2);
      }
    }
  }

  private List<List<Integer>> generateAverageColors(Map<TwoDPoint, List<TwoDPoint>> clusters) {
    List<List<Integer>> allColors=new ArrayList<>();
    for(Map.Entry<TwoDPoint,List<TwoDPoint>> entry: clusters.entrySet()){
      List<TwoDPoint> clusterPoints=entry.getValue();
      clusterPoints.add(entry.getKey());
      List<Integer> averageColor=getAverageClusterColor(clusterPoints);
      allColors.add(averageColor);
    }
    return allColors;
  }

  private List<Integer> getAverageClusterColor(List<TwoDPoint> clusterPoints) {
    List<Integer> rgb=new ArrayList<>();

    List <Integer> reds=new ArrayList<>();
    List <Integer> greens=new ArrayList<>();
    List <Integer> blues=new ArrayList<>();
    for (TwoDPoint point:clusterPoints) {
      reds.add(this.reds[point.getX()][point.getY()]);
      greens.add(this.greens[point.getX()][point.getY()]);
      blues.add(this.blues[point.getX()][point.getY()]);
    }

    rgb.add( clamp(reds.stream().mapToInt(i->i).sum()/reds.size()));
    rgb.add( clamp(  greens.stream().mapToInt(i->i).sum()/greens.size()));
    rgb.add(clamp(blues.stream().mapToInt(i->i).sum()/blues.size()));
    return rgb;
  }

  private List<TwoDPoint> populate() {
    List<TwoDPoint> allPoints=new ArrayList<>();
    for(int i=0; i<this.height;i++){
      for(int j=0; j<this.width;j++){
        allPoints.add(new TwoDPoint(i,j));
      }
    }
    return allPoints;
  }

  private TwoDPoint getNewCenter(TwoDPoint point,Map<TwoDPoint, List<TwoDPoint>> clusters) {
    double minDistance = Double.POSITIVE_INFINITY;
    TwoDPoint newCenter = null;
    double distance;
    for (TwoDPoint center : clusters.keySet()) {
      distance = Math.sqrt( Math.pow(center.getX()-point.getX(),2)+Math.pow(center.getY()-point.getY(),2) );
      if (distance < minDistance) {
        minDistance = distance;
        newCenter = center;
      }
    }
    return newCenter;
  }

  private void assignCentersToPoints(List<TwoDPoint> points, Map<TwoDPoint, List<TwoDPoint>> clusters) {
    for (TwoDPoint point : points) {
      TwoDPoint center = getNewCenter(point,clusters);
      List<TwoDPoint> centerList = clusters.get(center);
      centerList.add(point);
      clusters.put(center, centerList);
    }
  }

  private Map<TwoDPoint, List<TwoDPoint>> initializeClusters(List<TwoDPoint> centers) {
    Map<TwoDPoint, List<TwoDPoint>> clusters = new LinkedHashMap<>();
    for (TwoDPoint center : centers) {
      clusters.put(center, new ArrayList<>());
    }
    return clusters;
  }

  private List<TwoDPoint> generatePoints(List<Integer> x, List<Integer> y) {
    List<TwoDPoint> points=new ArrayList<>();
    for(int i=0;i<x.size();i++){
      points.add( new TwoDPoint(x.get(i),y.get(i)));
    }
    return points;
  }

  private List<Integer> generateRandom(int n, int max){
    List<Integer> randomNum=new ArrayList<>();
    Random random = new Random();
    for(int i=0;i<n;i++){
      int r=random.nextInt(max);
      randomNum.add(r);
    }
    return randomNum;
  }

  @Override
  public void dither() {
    ditheringProcedure();
  }

  private void ditheringProcedure() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int oldColorRed = reds[i][j];
        int newColorRed = getNewColor(oldColorRed);
        int errorRed = oldColorRed - newColorRed;

        reds[i][j] = newColorRed;
        greens[i][j] = newColorRed;
        blues[i][j] = newColorRed;

        if (j < this.width - 1) {
          reds[i][j + 1] += Math.round(7.0/16 * errorRed);
          greens[i][j + 1] += Math.round(7.0/16 * errorRed);
          blues[i][j + 1] += Math.round(7.0/16 * errorRed);
        }
        if (i < this.height - 1 && j > 0) {
          reds[i + 1][j - 1] += Math.round(3.0/16 * errorRed);
          greens[i + 1][j - 1] += Math.round(3.0/16 * errorRed);
          blues[i + 1][j - 1] += Math.round(3.0/16 * errorRed);
        }
        if (i < this.height - 1) {
          reds[i + 1][j] += Math.round(5.0 /16 * errorRed);
          greens[i + 1][j] += Math.round(5.0 /16 * errorRed);
          blues[i + 1][j] += Math.round(5.0 /16 * errorRed);
        }
        if (i < this.height - 1 && j < this.width - 1) {
          reds[i + 1][j + 1] += Math.round(1.0 /16 * errorRed);
          greens[i + 1][j + 1] += Math.round(1.0 /16 * errorRed);
          blues[i + 1][j + 1] += Math.round(1.0 /16 * errorRed);
        }
      }
    }
  }

  private int getNewColor(int oldColor) {
    return oldColor < 128 ? 0 : 255;
  }

}
