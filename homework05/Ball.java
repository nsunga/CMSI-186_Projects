/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  The implementation for a Ball
 *  @see
 *  @author       :  Nick Sunga
 *  Date written  :  2018-03-28
 *  Description   :  Provideds the implementation for the ball used in SoccerSim.java
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {
  private double x_coord;
  private double y_coord;
  private double dx;
  private double dy;
  private double time_slice;
  private static final double FRICTION = 0.99;

  /**
  *  Default Constructor
  */
  public Ball(double x_coord, double y_coord, double dx, double dy) {
    this.x_coord = x_coord;
    this.y_coord = y_coord;
    this.dx = dy;
    this.dy = dy;
    this.time_slice = 1;
  }

  /**
  *  Constructor if time slice given
  *  This just calls the superclass constructor, which is "Object"
  */
  public Ball(double x_coord, double y_coord, double dx, double dy, double time_slice) {
    this.x_coord = x_coord;
    this.y_coord = y_coord;
    this.dx = dy;
    this.dy = dy;
    this.time_slice = time_slice;
  }

  /**
  *  Changes coords and velocity of the ball
  */
  public void move() {
    this.x_coord += this.dx * this.time_slice;
    this.y_coord += this.dy * this.time_slice;
    if (Math.abs(this.dx) >= 1) { this.dx *= Math.pow(FRICTION, time_slice); }
    else { this.dx *= 0.0; }
    if (Math.abs(this.dy) >= 1) { this.dy *= Math.pow(FRICTION, time_slice); }
    else { this.dy *= 0.0; }
  }

  /**
  *  Getters for ball attributes
  */
  public double get_x_coord() { return this.x_coord; }
  public double get_y_coord() { return this.y_coord; }
  public double get_dx() { return this.dx; }
  public double get_dy() { return this.dy; }
  public double get_ts() { return this.time_slice; }
}
