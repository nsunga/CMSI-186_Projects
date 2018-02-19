/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson, Nick Sunga
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
import java.util.Map;
import java.util.HashMap;

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
    if (dieIndex < 0 || dieIndex > this.ds.length) { throw new IllegalArgumentException(); }

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
  * Two sets are identical if both have the same number of dice, same number
  * of sides, have the same total value for all dice in the sets, and
  * both sets have the same occurrences of the number of pips. The occurrences
  * do not have to be in order
  * @return  tru iff this set is identical to the set passed as an argument
  */
  public boolean isIdentical( DiceSet ds ) {
    Map<String, Integer> this_rolls_and_occurrences = new HashMap<String, Integer>();
    Map<String, Integer> other_ds_rolls_and_occurrences = new HashMap<String, Integer>();

    if (this.count == ds.count && this.sides == ds.sides && this.sum() == ds.sum()) {
      for (int i = 0; i < this.count; i++) {
        if (this_rolls_and_occurrences.containsKey(this.ds[i].toString())) {
          Integer value = this_rolls_and_occurrences.get(this.ds[i].toString());
          this_rolls_and_occurrences.replace(this.ds[i].toString(), value + 1);
        } else {
          this_rolls_and_occurrences.put(this.ds[i].toString(), new Integer(this.ds[i].getValue()));
        }

        if (other_ds_rolls_and_occurrences.containsKey(ds.ds[i].toString())) {
          Integer value = other_ds_rolls_and_occurrences.get(ds.ds[i].toString());
          other_ds_rolls_and_occurrences.replace(ds.ds[i].toString(), value + 1);
        } else {
          other_ds_rolls_and_occurrences.put(this.ds[i].toString(), new Integer(ds.ds[i].getValue()));
        }
      }

      for(Map.Entry<String, Integer> entry : this_rolls_and_occurrences.entrySet()) {
        String key = entry.getKey();
        Integer value = entry.getValue();
        if (!other_ds_rolls_and_occurrences.containsKey(key)) { return false; }
        else if (other_ds_rolls_and_occurrences.get(key) != value) { return false; }
      }

      return true;
    } else {
      return false;
    }
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

    System.out.println("\nTESTS for isIdentical():");
    try {
      DiceSet temp_set = new DiceSet(5, 6);
      DiceSet other_set = new DiceSet(5, 6);
      System.out.println("Two unrolled and equal sets should be true: " + temp_set.isIdentical(other_set));
    } catch(Exception e) { System.out.println(e + " caught"); }
    try {
      DiceSet temp_set = new DiceSet(5, 6);
      DiceSet other_set = new DiceSet(5, 6);
      temp_set.roll();
      other_set.roll();
      System.out.println("Two rolled and equal sets can be true or false: " + temp_set.isIdentical(other_set));
    } catch(Exception e) { System.out.println(e + " caught"); }
  }

}
