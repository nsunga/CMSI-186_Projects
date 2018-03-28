import java.util.ArrayList;

public class SoccerSim {
  public static final double RADIUS = 4.45;
  public static final double DIAMETER = 2 * RADIUS;

  public static boolean at_rest(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      if (soccer_balls.get(i).get_dx() != 0.0) { return true; }
      if (soccer_balls.get(i).get_dy() != 0.0) { return true; }
    }
    System.out.println("No collisions");
    return false;
  }

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

  public static void change_position(ArrayList<Ball> soccer_balls) {
    for (int i = 0; i < soccer_balls.size(); i++) {
      soccer_balls.get(i).move();
    }
  }

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

    change_position(soccer_balls);
  }
}
