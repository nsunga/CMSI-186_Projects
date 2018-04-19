/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

  public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
  public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
  public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
  public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
  public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
  public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
  public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
  public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
  public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
  public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
  public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
  public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
  public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
  public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
  public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
  private String internalValue = "";        // internal String representation of this BrobInt
  private byte   sign          = 0;         // "0" is positive, "1" is negative
  private String reversed      = "";        // the backwards version of the internal String representation
  private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
  *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
  *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
  *   for later use
  *  @param  value  String value to make into a BrobInt
  */
  public BrobInt( String value ) {
    super();
    this.internalValue = value;
    if (value.substring(0,1).equals("-")) {
      this.sign = 1;
      this.byteVersion = new byte[value.length() - 1];
      byteInitializer(value, 2);
    } else {
      this.byteVersion = new byte[value.length()];
      byteInitializer(value, 1);
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Initialize a BrobInt's bytes arr
  *  Note: static method
  *  @param  value         The value of the BrobInt
  *  @param  cond          Used to disregard value magnitude. No need to track neg or pos sign as a byte
  *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private void byteInitializer(String value, int cond) {
    int index = 0;
    for (int i = value.length(); i >= cond; i--) {
      this.byteVersion[index++] = Byte.parseByte(value.substring(i-1, i));
      this.reversed += value.substring(i-1, i);
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to validate that all the characters in the value are valid decimal digits
  *  @return  boolean  true if all digits are good
  *  @throws  IllegalArgumentException if something is hinky
  *  note that there is no return false, because of throwing the exception
  *  note also that this must check for the '+' and '-' sign digits
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean validateDigits() {
    for (int i = 0; i < this.byteVersion.length; i++) {
      if (this.byteVersion[i] < 0) { throw new IllegalArgumentException("\nBad Value"); }
      if (this.byteVersion[i] > 10) { throw new IllegalArgumentException("\nBad Value."); }
    }
    return true;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to reverse the value of this BrobInt
  *  @return BrobInt that is the reverse of the value of this BrobInt
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt reverser() {
    if (this.sign == 0) { return new BrobInt(this.reversed); }
    return new BrobInt("-" + this.reversed);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to reverse the value of a BrobIntk passed as argument
  *  Note: static method
  *  @param  gint         BrobInt to reverse its value
  *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static BrobInt reverser( BrobInt gint ) {
    if (gint.sign == 0) { return new BrobInt(gint.reversed); }
    return new BrobInt("-" + gint.reversed);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
  *  @param  gint         BrobInt to add to this
  *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt addByte( BrobInt gint ) {
    String brob_value = new String();

    if (gint.sign == this.sign) {
      brob_value = adderSameSign(gint);
      if (this.sign == 0 && gint.sign == 0) { return new BrobInt(brob_value); }
      else { return new BrobInt("-" + brob_value); }
    } else {
      if (this.compareTo(new BrobInt(gint.toString().substring(1))) == 0) { return ZERO; }
      else if (this.compareTo(gint) > 0) {
        brob_value = adder(this, gint);
        if (this.sign == 0) { return new BrobInt(brob_value); }
        else { return new BrobInt("-" + brob_value); }
      } else {
        brob_value = adder(gint, this);
        if (gint.sign == 0) { return new BrobInt(brob_value); }
        else { return new BrobInt("-" + brob_value); }
      }
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Private method that adds two BrobInts of different signs
  *
  *  @param  bigger         The bigger BrobInt
  *  @param  smaller        The smaller BrobInt
  *  @return String that should be the value of the new BrobInt
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static String adder(BrobInt bigger, BrobInt smaller) {
    String brob_value = new String();
    int additionsNeeded = bigger.byteVersion.length;

    for (int i = 0; i < additionsNeeded; i++){
      if (i > smaller.byteVersion.length-1) {
        if (bigger.byteVersion[i] == -1 && i + 1 < additionsNeeded) {
          brob_value = "9" + brob_value;
          bigger.byteVersion[i+1] = new Byte(Integer.toString(bigger.byteVersion[i+1] - 1));
        } else if (bigger.byteVersion[i] > 0) {
          brob_value = Integer.toString(bigger.byteVersion[i]) + brob_value;
        }
      } else if (smaller.byteVersion[i] > bigger.byteVersion[i]) {
        brob_value = Integer.toString(bigger.byteVersion[i] + 10 - smaller.byteVersion[i]) + brob_value;
        bigger.byteVersion[i+1] = new Byte(Integer.toString(bigger.byteVersion[i+1] - 1));
      } else {
        brob_value = Integer.toString(bigger.byteVersion[i] - smaller.byteVersion[i]) + brob_value;
      }
    }

    return brob_value;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Private method that adds two BrobInts of same signs
  *
  *  @param  gint         The other BrobInt added to this BrobInt
  *  @return String that should be the value of the new BrobInt
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private String adderSameSign(BrobInt gint) {
    String brob_value = new String();
    boolean carry = false;
    int additionsNeeded = Math.max(gint.byteVersion.length, this.byteVersion.length);

    for (int i = 0; i < additionsNeeded; i++) {
      if (gint.byteVersion.length - 1 < i) {
        if (carry) {
          if (this.byteVersion[i] + 1 > 9) {
            brob_value = Integer.toString((this.byteVersion[i] + 1) % 10) + brob_value;
            carry = true;
          } else {
            brob_value = Integer.toString(this.byteVersion[i] + 1) + brob_value;
            carry = false;
          }
        } else {
          brob_value = Integer.toString(this.byteVersion[i]) + brob_value;
        }
      } else if (this.byteVersion.length - 1 < i) {
        if (carry) {
          if (gint.byteVersion[i] + 1 > 9) {
            brob_value = Integer.toString((gint.byteVersion[i] + 1) % 10) + brob_value;
            carry = true;
          } else {
            brob_value = Integer.toString(gint.byteVersion[i] + 1) + brob_value;
            carry = false;
          }
        } else {
          brob_value = Integer.toString(gint.byteVersion[i]) + brob_value;
        }
      } else if (this.byteVersion[i] + gint.byteVersion[i] > 9) {
          if (carry) {
            brob_value = Integer.toString((this.byteVersion[i] + gint.byteVersion[i] + 1) % 10) + brob_value;
            carry = false;
          } else {
            brob_value = Integer.toString((this.byteVersion[i] + gint.byteVersion[i]) % 10) + brob_value;
          }
          carry = true;
      } else {
        if (carry) {
          if (this.byteVersion[i] + gint.byteVersion[i] + 1 > 9) {
            brob_value = Integer.toString((this.byteVersion[i] + gint.byteVersion[i] + 1) % 10) + brob_value;
            carry = true;
          } else {
            brob_value = Integer.toString(this.byteVersion[i] + gint.byteVersion[i] + 1) + brob_value;
            carry = false;
          }
        } else {
          brob_value = Integer.toString(this.byteVersion[i] + gint.byteVersion[i]) + brob_value;
        }
      }
    }

    if (carry) { brob_value = "1" + brob_value; }
    return brob_value;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
  *  @param  gint         BrobInt to subtract from this
  *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt subtractByte( BrobInt gint ) {
    if (gint.sign == 1) { return this.addByte(new BrobInt(gint.toString().substring(1))); }
    else { return this.addByte(new BrobInt("-" + gint.toString())); }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
  *  @param  gint         BrobInt to multiply by this
  *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt multiply( BrobInt gint ) {
    if (gint.internalValue.equals("0") || this.internalValue.equals("0")) { return ZERO; }
    else { return TEN; } // place holder case for now
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
  *  @param  gint         BrobInt to divide this by
  *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt divide( BrobInt gint ) {
    throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to get the remainder of division of this BrobInt by the one passed as argument
  *  @param  gint         BrobInt to divide this one by
  *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt remainder( BrobInt gint ) {
    throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to compare a BrobInt passed as argument to this BrobInt
  *  @param  gint  BrobInt to add to this
  *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
  *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
  *        THAT was easy.....
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt gint ) {
     if( internalValue.length() > gint.internalValue.length() ) {
        return 1;
     } else if( internalValue.length() < gint.internalValue.length() ) {
        return (-1);
     } else {
        for( int i = 0; i < internalValue.length(); i++ ) {
           Character a = new Character( internalValue.charAt(i) );
           Character b = new Character( gint.internalValue.charAt(i) );
           if( new Character(a).compareTo( new Character(b) ) > 0 ) {
              return 1;
           } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
              return (-1);
           }
        }
     }
     return 0;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to check if a BrobInt passed as argument is equal to this BrobInt
  *  @param  gint     BrobInt to compare to this
  *  @return boolean  that is true if they are equal and false otherwise
  *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
  *        also using the java String "equals()" method -- THAT was easy, too..........
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean equals( BrobInt gint ) {
    return (internalValue.equals( gint.toString() ));
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to return a BrobInt given a long value passed as argument
  *  @param  value         long type number to make into a BrobInt
  *  @return BrobInt  which is the BrobInt representation of the long
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static BrobInt valueOf( long value ) throws NumberFormatException {
    BrobInt gi = null;
    try {
      gi = new BrobInt( new Long( value ).toString() );
    }
    catch( NumberFormatException nfe ) {
      System.out.println( "\n  Sorry, the value must be numeric of type long." );
    }
    return gi;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to return a String representation of this BrobInt
  *  @return String  which is the String representation of this BrobInt
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public String toString() {
    String byteVersionOutput = "";
    for( int i = 0; i < byteVersion.length; i++ ) {
      byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
    }
    byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
    return internalValue;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to display an Array representation of this BrobInt as its bytes
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void toArray( byte[] d ) {
    System.out.println( Arrays.toString( d ) );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  the main method redirects the user to the test class
  *  @param  args  String array which contains command line arguments
  *  note:  we don't really care about these
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static void main( String[] args ) {
    System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
    System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

    System.exit( 0 );
  }
}
