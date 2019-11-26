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
   * Adds two points and returns the resulting point. The value of corresponding dimensions is
   * added to form a new point.
   * @param     other The point which has  to be added to this point.
   * @return    A result 2D point with x and y co ordinates summed up.
   */

  public TwoDPoint add(TwoDPoint other) {
    TwoDPoint p =  other;
    return new TwoDPoint(this.x + p.getX(), this.y + p.getY());
  }

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
