/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class that simulates a dice roll game.
 *  Author        :  Nick Sunga
 *  Date          :  2017-02-09
 *  Description   :  This class builds off of and uses Die.java and DiceSet.java. Running the main will
 *                   show a TUI to the user and its purpose is a dice game.
 *                   The user must run the code like so: java HighRoll <number of dice> <number of sides>.
 *                   Functions used:
 *                   public void menu();                                         // Displays the TUI game
 *                   public void do_operation(int opcode, DiceSet game_set);     // Performs operation on game_set based on opcode
 *                   public static void main(String[] args);                     // Instantiates HighRoll and runs the TUI game
 *  Warnings      :  None
 *  Exceptions    :  Catches IllegalArgumentException from DiceSet or Dice.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Scanner;

public class HighRoll {

  /**
  * private instance data
  */
  private int high_score = 0;

  /**
  * Displays the TUI game
  */
  public void menu() {
    System.out.println("*************************************");
    System.out.println("* 1. ROLL ALL DIE             (1)   *");
    System.out.println("* 2. ROLL A SINGLE DIE        (2)   *");
    System.out.println("* 3. CACLULATE SCORE          (3)   *");
    System.out.println("* 4. SAVE SCORE AS HIGH SCORE (4)   *");
    System.out.println("* 5. DISPLAY HIGH SCORE       (5)   *");
    System.out.println("* 6. QUIT                     (Q)   *");
    System.out.println("*************************************");
  }

  /**
  * Performs the operation listed in the TUI. If user quits game, it is handled
  * in main(). Either rolls all die, rolls an individual die, sums up the values
  * of the set, sets the high score, or prints the high score.
  * @param  opcode int of which operation to perform
  * @param  game_set DiceSet the user is playing with
  * @throws IllegalArgumentException which comes from DiceSet.java or Dice.java
  */
  public void do_operation(int opcode, DiceSet game_set) {
    Scanner scanner = new Scanner(System.in);

    try {
      switch (opcode) {
        case 1: game_set.roll();
                break;
        case 2: System.out.print("Enter die index for roll: ");
                int input = scanner.nextInt();
                System.out.println("Rolled a " + game_set.rollIndividual(input));
                break;
        case 3: System.out.println("SCORE: " + game_set.sum());
                break;
        case 4: if (game_set.sum() > this.high_score) {
                  this.high_score = game_set.sum();
                  System.out.println("High score saved.");
                  break;
                } else {
                  System.out.println("Sum: " + game_set.sum() + " <= High Score: " + this.high_score);
                  break;
                }
        case 5: System.out.println("CURRENT HIGH SCORE: " + this.high_score);
                break;
      }
    } catch(Exception e) { throw e; }
  }

  /**
  * Simulates HighRoll with a user. If user presses 'Q' or 'q', program finishes
  * @param String[] args: User must unsure that they enter in <number of dice> and <number of sides>
  */
  public static void main(String[] args) {
    try {
      String arg_zero = args[0];
      String arg_one = args[1];
      int number_of_dice = Integer.parseInt(arg_zero);
      int number_of_sides = Integer.parseInt(arg_one);
      DiceSet game_set = new DiceSet(number_of_dice, number_of_sides);
      HighRoll game = new HighRoll();
      boolean continue_game = true;

      while (continue_game) {
        game.menu();

        Scanner scanner = new Scanner(System.in);
        String input_value = scanner.next();
        int input = 0;

        if (input_value.equals("Q") || input_value.equals("q")) {
          continue_game = false;
          break;
        } else { input = Integer.parseInt(input_value); }

        game.do_operation(input, game_set);
      }
      System.out.println("QUITTING GAME");
    } catch(Exception e) { System.out.println("Exception " + e); }
  }
}
