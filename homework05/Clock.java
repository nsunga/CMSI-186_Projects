/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

public class Clock {
  /**
  *  Class field definintions go here
  */
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
  private static final double INVALID_ARGUMENT_VALUE = -1.0;
  private static final double MAXIMUM_DEGREE_VALUE = 360.0;
  private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
  private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
  private static final double MAXIMUM_TIME_SLICE_VALUE = 1800.0;

  private double time_slice;
  private double seconds;
  private double given_angle;
  /**
  *  Constructor goes here
  */
  public Clock() {
    this.time_slice = DEFAULT_TIME_SLICE_IN_SECONDS;
    this.given_angle = 0.0;
    this.seconds = 0.0;
  }

  /**
  *  Constructor
  * @param given_angle => angle for the clock
  */
  public Clock(double given_angle) {
    this.time_slice = DEFAULT_TIME_SLICE_IN_SECONDS;
    this.given_angle = given_angle;
    this.seconds = 0.0;
  }

  /**
  *  Constructor
  * @param given_angle => angle for the clock
  * @param time_slice => time slice for the clock
  */
  public Clock(double given_angle, double time_slice) {
    this.time_slice = time_slice;
    this.given_angle = given_angle;
    this.seconds = 0.0;
  }
  /**
  *  Methods go here
  *ex
  *  Method to calculate the next tick from the time increment
  *  @return double-precision value of the current clock tick
  */
  public double tick() {
    return this.seconds += this.time_slice;
  }

  /**
  *  Method to validate the angle argument
  *  @param   argValue  String from the main programs args[0] input
  *  @return  double-precision value of the argument
  *  @throws  NumberFormatException
  */
  public double validateAngleArg( String argValue ) throws NumberFormatException {
    double valid_angle = Double.parseDouble(argValue);

    if (valid_angle <= MAXIMUM_DEGREE_VALUE && valid_angle >= 0) { return valid_angle; }
    throw new NumberFormatException();
  }

  /**
  *  Method to validate the optional time slice argument
  *  @param  argValue  String from the main programs args[1] input
  *  @return double-precision value of the argument or -1.0 if invalid
  *  note: if the main program determines there IS no optional argument supplied,
  *         I have elected to have it substitute the string "60.0" and call this
  *         method anyhow.  That makes the main program code more uniform, but
  *         this is a DESIGN DECISION, not a requirement!
  *  note: remember that the time slice, if it is small will cause the simulation
  *         to take a VERY LONG TIME to complete!
  */
  public double validateTimeSliceArg( String argValue ) {
    double valid_time_slice = Double.parseDouble(argValue);

    if (valid_time_slice < MAXIMUM_TIME_SLICE_VALUE && valid_time_slice > 0) { return valid_time_slice; }
    return INVALID_ARGUMENT_VALUE;
  }

  /**
  *  Method to calculate and return the current position of the hour hand
  *  @return double-precision value of the hour hand location
  */
  public double getHourHandAngle() {
    return HOUR_HAND_DEGREES_PER_SECOND * this.seconds % 360;
  }

  /**
  *  Method to calculate and return the current position of the minute hand
  *  @return double-precision value of the minute hand location
  */
  public double getMinuteHandAngle() {
    return MINUTE_HAND_DEGREES_PER_SECOND * this.seconds % 360;
  }

  /**
  *  Method to calculate and return the angle between the hands
  *  @return double-precision value of the angle between the two hands
  */
  public double getHandAngle() {
    double hand_angle = Math.abs(this.getHourHandAngle() - this.getMinuteHandAngle());

    if (hand_angle > 180.0) { return 360.0 - hand_angle; }
    return hand_angle;
  }

  /**
  *  Method to fetch the total number of seconds
  *   we can use this to tell when 12 hours have elapsed
  *  @return double-precision value the total seconds private variable
  */
  public double getTotalSeconds() {
    return this.seconds;
  }

  /**
  *  Method to return a String representation of this clock
  *  @return String value of the current clock
  */
  public String toString() {
    return "CURRENT TIME => " + (int)this.seconds/3600 + ":" + (int)(this.seconds/60)%60 + ":" + this.seconds%60;
  }
  /**
  *  The main program starts here
  *  remember the constraints from the project description
  *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
  *  be sure to make LOTS of tests!!
  *  remember you are trying to BREAK your code, not just prove it works!
  */
  public static void main( String args[] ) {

    System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                        "--------------------------\n" );
    System.out.println( "  Creating a new clock: " );
    Clock clock = new Clock();
    System.out.println( "    New clock created: " + clock.toString() );
    System.out.println( "    Testing validateAngleArg()....");
    System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
    try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  15.0 degrees', expecting double value   15.0" );
    try { System.out.println( (15.0 == clock.validateAngleArg( "15.0" )) ? " - got 15.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  180.5 degrees', expecting double value   180.5" );
    try { System.out.println( (180.5 == clock.validateAngleArg( "180.5" )) ? " - got 180.5" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  360.1 degrees', expecting NFE" );
    try { System.out.println( (360.1 == clock.validateAngleArg( "360.1" )) ? " - got 360.1" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  270.12 degrees', expecting double value   270.12" );
    try { System.out.println( (270.12 == clock.validateAngleArg( "270.12" )) ? " - got 270.12" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

    System.out.println( "    Testing validateTimeSliceArg()....");
    System.out.print( "      sending '  1800 time slice', expecting double value   -1.0" );
    try { System.out.println( (-1.0 == clock.validateTimeSliceArg( "1800" )) ? " - got -1.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  1512.343 time slice', expecting double value   1512.343" );
    try { System.out.println( (1512.343 == clock.validateTimeSliceArg( "1512.343" )) ? " - got 1512.343" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  -123 time slice', expecting double value   -1.0" );
    try { System.out.println( (-1.0 == clock.validateTimeSliceArg( "-123" )) ? " - got -1.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  1274.09 time slice', expecting double value   1274.09" );
    try { System.out.println( (1274.09 == clock.validateTimeSliceArg( "1274.09" )) ? " - got 1274.09" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    System.out.print( "      sending '  43.8 time slice', expecting double value   43.8" );
    try { System.out.println( (43.8 == clock.validateTimeSliceArg( "43.8" )) ? " - got 43.8" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

    Clock clock_two = new Clock();
    System.out.println("12 hour clock cycle, Timeslice = 60");
    do {
      System.out.println(clock_two);
      System.out.println("HOUR HAND ANGLE => " + clock_two.getHourHandAngle());
      System.out.println("MINUTE HAND ANGLE => " + clock_two.getMinuteHandAngle());
      System.out.println("HAND ANGLE BETWEEN => " + clock_two.getHandAngle() + "\n");
      clock_two.tick();
    } while (clock_two.getTotalSeconds() < 43200);
  }
}
