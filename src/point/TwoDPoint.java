package point;

/**
 * This class is a 2D implementation of  the interface Point.
 * It represents a 2D point which has Cartesian coordinates (x,y).
 * It offers methods which gives operations on the points like
 * adding, distance from another point etc.
 */
public class TwoDPoint {

  private int x;
  private int y;

  /**
   * This creates a new 2D point with x and y co originates pointing to the origin.
   */
  public TwoDPoint() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * This creates a new 2D point object with x and y co ordinates passed to this constructor.
   * @param     x The x co ordinate of the new 2D object.
   * @param     y The y co ordinate of the new 2D object.
   */
  public TwoDPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * This method gets the x co ordinate of the current 2D object.
   * @return    x co ordinate of the current 2D object.
   */

  public int getX() {
    return this.x;
  }

  /**
   * This method gets the y co ordinate of the current 2D object.
   * @return    y co ordinate of the current 2D object.
   */

  public int getY() {
    return this.y;
  }

  /**
   * This method computes the distance between this point and another point using the
   * euclidean distance formula.
   * @param     other the other point from which the Euclidean distance to this point has to be
   *              calculated.
   * @return    the euclidean distance of this point from another point.
   */

//  public int getEuclideanDistance(TwoDPoint other) {
//    TwoDPoint otherTwoD =  other;
//    return Math.sqrt(Math.pow(this.x - otherTwoD.getX(), 2) +
//            Math.pow(this.y - otherTwoD.getY(), 2));
//  }

  /**
   * Adds two points and returns the resulting point. The value of corresponding dimensions is
   * added to form a new point.
   * @param     other The point which has  to be added to this point.
   * @return    A result 2D point with x and y co ordinates summed up.
   */

  public TwoDPoint add(TwoDPoint other) {
    TwoDPoint p =  other;
    return new TwoDPoint(this.x + p.getX(), this.y + p.getY());
  }

  /**
   * Divides each dimension value of this point with a constant.
   *
   * @param divisor the value with which this point has to be divided.
   * @return new Point object resulting from dividing the x and y co ordinates by a constant.
   */

//  public TwoDPoint divideBy(double divisor) {
//    return new TwoDPoint(this.x / divisor, this.y / divisor);
//  }

  /**
   * Subtracts two points and returns the resulting point. The value of corresponding
   * dimensions is subtracted to form a new point.
   *
   * @param     other The point which has to be subtracted from this point.
   * @return    new Point resulting from subtracting the x and y co ordinates of two points.
   */

  public TwoDPoint subtract(TwoDPoint other) {
    TwoDPoint p =  other;
    return new TwoDPoint(this.x - p.getX(), this.y - p.getY());
  }

  /**
   * This method creates a new 2D object by squaring the x and y co ordinates of
   * the current 2D object.
   *
   * @return    A new 2D object with x and y co ordinates squared.
   */

  public TwoDPoint square() {
    return new TwoDPoint(this.x * this.x, this.y * this.y);
  }

  /**
   * This method multiplies the x and y co ordinates of the current point and
   * returns the double result.
   * @return     product of x and y co ordinates of current point.
   */

  public int multiplyDimensions() {
    return this.x * this.y;
  }

  @Override
  public String toString(){
    return this.x+","+this.getY();
  }
}
