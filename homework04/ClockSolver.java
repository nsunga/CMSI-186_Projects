/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson, Nick Sunga
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
  *  Class field definintions go here
  */
  private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
  private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
  private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons
  public Clock clock; // used to find equal angles when solving ClockSolver
  /**
  *  Constructor
  *  This just calls the superclass constructor, which is "Object"
  */
  public ClockSolver() {
    super();
  }

  /**
  *  Method to handle all the input arguments from the command line
  *  this sets up the variables for the simulation
  * @param clock inputs
  */
  public void handleInitialArguments( String args[] ) {
  // args[0] specifies the angle for which you are looking
  //  your simulation will find all the angles in the 12-hour day at which those angles occur
  // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
  // you may want to consider using args[2] for an "angle window"
    Clock clock_validator = new Clock();
    System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
    if ( args.length == 0 ) {
       System.out.println( "   Sorry you must enter at least one argument\n" +
                           "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                           "   Please try again..........." );
       System.exit( 1 );
    } else if (args.length == 1) {
      try {
        clock = new Clock(clock_validator.validateAngleArg(args[0]));
      } catch (NumberFormatException e) {
        System.out.println("NOT A VALID ANGLE");
        System.exit(1);
      }
    } else if (args.length == 2) {
      if (clock_validator.validateTimeSliceArg(args[1]) == -1) {
        System.out.println("Not a valid time slice");
        System.exit(1);
      }
      try {
        clock = new Clock(clock_validator.validateAngleArg(args[0]), clock_validator.validateTimeSliceArg(args[1]));
      } catch (NumberFormatException e) {
        System.out.println("NOT A VALID ANGLE");
        System.exit(1);
      }
    } else {
      System.out.println("Too many arguments");
      System.exit(1);
    }
  }

  /**
  *  Method to return the defined EPSILON_VALUE
  * @param none
  * @return EPSILON_VALUE
  */
  public double get_epsilon() {
    return EPSILON_VALUE;
  }
  /**
  *  The main program starts here
  *  remember the constraints from the project description
  *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
  *  @param  args  String array of the arguments from the command line
  *                args[0] is the angle for which we are looking
  *                args[1] is the time slice; this is optional and defaults to 60 seconds
  */
  public static void main( String args[] ) {
    ClockSolver cse = new ClockSolver();
    cse.handleInitialArguments(args);
    double[] timeValues = new double[3];
    int number_of_angles = 0;
    double hand_angle_minus_angle;
    double angle_minus_hand_angle;

    while( cse.clock.getTotalSeconds() < 43200 ) {
      hand_angle_minus_angle = cse.clock.getHandAngle() - cse.clock.validateAngleArg(args[0]);
      angle_minus_hand_angle = cse.clock.validateAngleArg(args[0]) - cse.clock.getHandAngle();

      if (hand_angle_minus_angle <= cse.get_epsilon() && hand_angle_minus_angle >= 0 || angle_minus_hand_angle <= cse.get_epsilon() && angle_minus_hand_angle >= 0) {
        System.out.println(cse.clock);
        number_of_angles++;
      }
      cse.clock.tick();
    }

    if (number_of_angles == 0) { System.out.println("NO ANGLES FOUND"); }
    else { System.out.println("NUMBER OF ANGLES FOUND: " + number_of_angles); }
    System.exit( 0 );
  }
}
