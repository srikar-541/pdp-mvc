package point;

/**
 * This class is a 2D implementation of  the interface Point. It represents
 * a 2D point which has Cartesian coordinates (x,y). It offers methods which
 * gives operations on the points like adding, distance from another point etc.
 */
public class TwoDPoint {

  private int x;
  private int y;

  /**
   * This creates a new 2D point object with x and y co ordinates passed to this constructor.
   *
   * @param x The x co ordinate of the new 2D object.
   * @param y The y co ordinate of the new 2D object.
   */
  public TwoDPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * This method gets the x co ordinate of the current 2D object.
   *
   * @return x co ordinate of the current 2D object.
   */

  public int getX() {
    return this.x;
  }

  /**
   * This method gets the y co ordinate of the current 2D object.
   *
   * @return y co ordinate of the current 2D object.
   */

  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return this.x + "," + this.getY();
  }
}
