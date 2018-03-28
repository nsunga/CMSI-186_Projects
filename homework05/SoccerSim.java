/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @see
 *  @author       :  Nick Sunga
 *  Date written  :  2018-03-28
 *  Description   :  Provideds the implementation for the soccer ball collision simulator
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.ArrayList;

public class SoccerSim {
  public static final double RADIUS = 4.45;
  public static final double DIAMETER = 2 * RADIUS;

  /**
  *  If the balls are at rest, the simulation is done, no collision
  * @param soccer_balls: ArrayList of Balls
  */
  public static boolean done_simulating(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      if (soccer_balls.get(i).get_dx() != 0.0) { return true; }
      if (soccer_balls.get(i).get_dy() != 0.0) { return true; }
    }
    System.out.println("No collisions");
    return false;
  }

  /**
  *  Checks if collisions occurred
  * @param soccer_balls: ArrayList of Balls
  */
  public static boolean collision_simulator(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      for (int j = i + 1; j < soccer_balls.size(); j++) {
        double delta_x = soccer_balls.get(i).get_x_coord() - soccer_balls.get(j).get_x_coord();
        double delta_y = soccer_balls.get(i).get_y_coord() - soccer_balls.get(j).get_y_coord();
        double distance = Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2));

        if (distance < DIAMETER) {
          if (i == 0) { System.out.println("COLLISION => Pole and soccer ball " + j); }
          System.out.println("COLLISION => Soccer ball " + i + " and soccer ball " + j);
          return false;
        }
      }
    }

    return true;
  }

  /**
  *  Changes position of every soccer ball
  * @param soccer_balls: ArrayList of Balls
  */
  public static void change_position(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      soccer_balls.get(i).move();
    }
  }

  /**
  *  Prints out the coordinates and velocity of every soccer ball
  * @param soccer_balls: ArrayList of Balls
  */
  public static void get_soccer_info(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      if (i == 0) {
        System.out.println("Pole: coords( " +
          soccer_balls.get(i).get_x_coord() + ", " +
          soccer_balls.get(i).get_y_coord() + ") velocity(" +
          soccer_balls.get(i).get_dx() + ", " +
          soccer_balls.get(i).get_dy() + ")"
        );
      } else {
        System.out.println("Soccer ball " + i + " : coords( " +
          soccer_balls.get(i).get_x_coord() + ", " +
          soccer_balls.get(i).get_y_coord() + ") velocity(" +
          soccer_balls.get(i).get_dx() + ", " +
          soccer_balls.get(i).get_dy() + ")"
        );
      }
    }
  }

  /**
  *  Prints if the soccer ball is no longer in motion
  * @param soccer_balls: ArrayList of Balls
  */
  public static void at_rest(ArrayList<Ball> soccer_balls) {
    for (int i = 1; i < soccer_balls.size(); i++) {
      if (soccer_balls.get(i).get_dx() == 0.0 && soccer_balls.get(i).get_dy() == 0.0) {
        System.out.println("soccer ball " + i + " not in motion");
      }
    }
  }

  /**
    *  The main program starts here
    *  @param  args  String array of the arguments from the command line
    *                Every four arguments should pertain to a ball.
    *                Last arg pertains to the time_slice. If none given,
    *                default is specified in Ball.java
    */
  public static void main(String[] args) {
    Clock valid_clock;
    Double valid_time_slice = -1.0;

    if (args.length % 2 != 0) { valid_time_slice = Double.parseDouble(args[args.length - 1]); }
    if (valid_time_slice >= 0.0) { valid_clock = new Clock(valid_time_slice); }
    else { valid_clock = new Clock(); }

    ArrayList<Ball> soccer_balls = new ArrayList<Ball>();

    soccer_balls.add(new Ball(0, 0, 0, 0));

    int soccer_ball_data = args.length - 2;
    for (int i = 0; i < soccer_ball_data; i += 4) {
      double x_coord = Double.parseDouble(args[i]);
      double y_coord = Double.parseDouble(args[i + 1]);
      double dx = Double.parseDouble(args[i + 2]);
      double dy = Double.parseDouble(args[i + 3]);

      if (valid_time_slice >= 0.0) {
        Ball soccer_ball = new Ball(x_coord, y_coord, dx, dy, valid_time_slice);
        soccer_balls.add(soccer_ball);
      } else {
        Ball soccer_ball = new Ball(x_coord, y_coord, dx, dy);
        soccer_balls.add(soccer_ball);
      }
    }

    System.out.println(valid_clock.toString());
    valid_clock.tick();

    get_soccer_info(soccer_balls);

    while (done_simulating(soccer_balls) && collision_simulator(soccer_balls)) {
      System.out.println(valid_clock.toString());
      valid_clock.tick();

      change_position(soccer_balls);
      get_soccer_info(soccer_balls);
      at_rest(soccer_balls);
      System.out.println("\n");
    }
  }
}
