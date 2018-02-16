/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

/**
* private instance data
*/
private int count;
private int sides;
private Die[] ds = null;

  // public constructor:
  /**
  * constructor
  * @param  count int value containing total dice count
  * @param  sides int value containing the number of pips on each die
  * @throws IllegalArgumentException if one or both arguments don't make sense
  * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
  */
  public DiceSet( int count, int sides ) {
    if (count < 1 || sides < 4) { throw new IllegalArgumentException(); }

    ds = new Die[ count ];
    this.count = count;
    this.sides = sides;
    for (int i = 0; i < ds.length; i++) {
      ds[i] = new Die(sides);
    }
  }

  /**
  * @return the sum of all the dice values in the set
  */
  public int sum() {
    int sigma = 0;

    for (Die every_die : this.ds) {
      sigma += every_die.getValue();
    }

    return sigma;
  }

  /**
  * Randomly rolls all of the dice in this set
  *  NOTE: you will need to use one of the "toString()" methods to obtain
  *  the values of the dice in the set
  */
  public void roll() {
    for (Die every_die : this.ds) {
      every_die.roll();
    }
  }

  /**
  * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @return the integer value of the newly rolled die
  * @throws IllegalArgumentException if the index is out of range
  */
  public int rollIndividual( int dieIndex ) {
    if (dieIndex < this.ds.length || dieIndex > this.ds.length) { throw new IllegalArgumentException(); }

    return this.ds[dieIndex].roll();
  }

  /**
  * Gets the value of the die in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @throws IllegalArgumentException if the index is out of range
  */
  public int getIndividual( int dieIndex ) {
    if (dieIndex < this.ds.length || dieIndex > this.ds.length) { throw new IllegalArgumentException(); }

    return this.ds[dieIndex].getValue();
  }

  /**
  * @return Public Instance method that returns a String representation of the DiceSet instance
  */
  public String toString() {
    String result = "";

    for (Die every_die : this.ds) { result += every_die; }

    return result;
  }

  /**
  * @return Class-wide version of the preceding instance method
  */
  public static String toString( DiceSet ds ) {
    return ds.toString();
  }

  /**
  * @return  tru iff this set is identical to the set passed as an argument
  */
  public boolean isIdentical( DiceSet ds ) {
    /*
    * CASES: 1) same number of dice in both sets
    *  2) same as 1) with same number of sides
    *  3) same as 2) plus same total value for all dice in the sets
    *  4) same as 2) plus same values on pairs of dice, NOT in order
    *  5) same as 2) plus same values on pairs of dice, IN order
    */
    if (this.count == ds.count) { return true; }
    else if (this.count == ds.count && this.sides == ds.sides) { return true; }
    else { return false; }
  }
  /**
  * A little test main to check things out
  */
  public static void main( String[] args ) {
    System.out.println("\nTESTS for invalid DiceSet(some_int, other_int) input:");
    try {
      System.out.print("DiceSet(0, 4) should throw excep.: ");
      new DiceSet(0, 4);
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      System.out.print("DiceSet(3, 2) should throw excep.: ");
      new DiceSet(3, 2);
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      System.out.print("DiceSet(-10, -10) should throw excep.: ");
      new DiceSet(-10, -10);
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      System.out.print("DiceSet(0, 0) should throw excep.: ");
      new DiceSet(0, 0);
    } catch(Exception e) { System.out.println(e + " caught"); }

    System.out.println("\nTESTS for roll() outputs:");
    try {
      DiceSet temp_set = new DiceSet(5, 10);
      temp_set.roll();
      System.out.println("Values for DiceSet(5, 10) should be between 1-10: " + temp_set);
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      DiceSet temp_set = new DiceSet(5, 6);
      temp_set.roll();
      System.out.println("Values for DiceSet(5, 6) should be between 1-6: " + temp_set);
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      DiceSet temp_set = new DiceSet(3, 4);
      temp_set.roll();
      System.out.println("Values for DiceSet(3, 4) should be between 1-4: " + temp_set);
    } catch(Exception e) { System.out.println(e + " caught"); }
  }

}
