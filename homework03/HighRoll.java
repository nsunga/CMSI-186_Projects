import java.util.Scanner;

public class HighRoll {
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

  public static void main(String[] args) {
    try {
      String arg_zero = args[0];
      String arg_one = args[1];
      int number_of_dice = Integer.parseInt(arg_zero);
      int number_of_sides = Integer.parseInt(arg_one);
      DiceSet game_set = new DiceSet(number_of_dice, number_of_sides);
      HighRoll temp = new HighRoll();
      temp.menu();

      Scanner scanner = new Scanner(System.in);
      String input_value = scanner.next();
      int input = 0;

      if (input_value.equals("Q") || input_value.equals("q")) { System.exit(0); }
      else { input = Integer.parseInt(input_value); }

      System.out.println("input: " + input);
    } catch(Exception e) { System.out.println("Exception " + e); }
  }
}
