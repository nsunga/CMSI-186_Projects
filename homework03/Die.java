/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson, Nick Sunga
 *  Date          :  2017-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Die {

  /**
  * private instance data
  */
  private int sides;
  private int pips;
  private final int MINIMUM_SIDES = 4;


  // public constructor:
  /**
  * constructor
  * @param nSides int value containing the number of sides to build on THIS Die
  * @throws       IllegalArgumentException
  * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
  */
  public Die( int nSides ) {
    if(nSides < MINIMUM_SIDES) { throw new IllegalArgumentException(); } else { this.sides = nSides; }
  }

  /**
  * Roll THIS die and return the result
  * @return  integer value of the result of the roll, randomly selected
  */
  public int roll() {
    this.pips = (int)(Math.random() * this.sides) + 1;
    return this.pips;
  }

  /**
  * Get the value of THIS die to return to the caller; note that the way
  *  the count is determined is left as a design decision to the programmer
  *  For example, what about a four-sided die - which face is considered its
  *  "value"?
  * @return the pip count of THIS die instance
  */
  public int getValue() {
    return this.pips;
  }

  /**
  * @param  int  the number of sides to set/reset for this Die instance
  * @return      The new number of sides, in case anyone is looking
  * @throws      IllegalArgumentException
  */
  public void setSides( int sides ) {
    if(sides < MINIMUM_SIDES) { throw new IllegalArgumentException(); } else { this.sides = sides; }
  }

  /**
  * Public Instance method that returns a String representation of THIS die instance
  * @return String representation of this Die
  */
  public String toString() {
    return "[" + this.pips + "]";
  }

  /**
  * Class-wide method that returns a String representation of THIS die instance
  * @return String representation of this Die
  */
  public static String toString( Die d ) {
    return d.toString();
  }

  /**
  * A little test main to check things out
  */
  public static void main( String[] args ) {
    System.out.println("\nTESTS for invalid Die(some_int) input:");
    try { System.out.print("new Die(3) should throw exception: "); new Die(3); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { System.out.print("new Die(2) should throw exception: "); new Die(2); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { System.out.print("new Die(1) should throw exception: "); new Die(1); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { System.out.print("new Die(0) should throw exception: "); new Die(0); }
    catch(Exception e) { System.out.println(e + " caught"); }

    System.out.println("\nTESTS for valid roll() returns:");
    System.out.println("Roll value for new Die(4) should be between 1-4: " + new Die(4).roll());
    System.out.println("Roll value for new Die(10) should be between 1-10: " + new Die(10).roll());
    System.out.println("Roll value for new Die(20) should be between 1-20: " + new Die(20).roll());
    System.out.println("Roll value for new Die(50) should be between 1-50: " + new Die(50).roll());

    System.out.println("\nTESTS if getValue() is equal to the value of a previous roll() call:");
    try{ Die temp_die = new Die(4); System.out.println("getValue() equal to previous roll(): " + (temp_die.roll() == temp_die.getValue() ? "true" : "false")); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try{ Die temp_die = new Die(10); System.out.println("getValue() equal to previous roll(): " + (temp_die.roll() == temp_die.getValue() ? "true" : "false")); }
    catch(Exception e) { System.out.println(e + " caught"); }

    System.out.println("\nTESTS for invalid setSides(some_int) input:");
    try { Die temp_die = new Die(4); System.out.print("temp_die.setSides(2): "); temp_die.setSides(2); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { Die temp_die = new Die(4); System.out.print("temp_die.setSides(3): "); temp_die.setSides(2); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { Die temp_die = new Die(4); System.out.print("temp_die.setSides(1): "); temp_die.setSides(2); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { Die temp_die = new Die(4); System.out.print("temp_die.setSides(0): "); temp_die.setSides(2); }
    catch(Exception e) { System.out.println(e + " caught"); }

    System.out.println("\nTESTS for Static and public instance toString():");
    try { Die temp_die = new Die(4); temp_die.roll(); System.out.println("temp_die.toString(): " + temp_die); }
    catch(Exception e) { System.out.println(e + " caught"); }
    try { Die temp_die = new Die(4); temp_die.roll(); System.out.println("Die.toString(temp_die): " + Die.toString(temp_die)); }
    catch(Exception e) { System.out.println(e + " caught"); }
  }

}
