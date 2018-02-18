import java.util.Scanner;

public class HighRoll {
  private static int high_score = 5;
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

  public void do_operation(int opcode, DiceSet game_set) {
    Scanner scanner = new Scanner(System.in);

    try {
      switch (opcode) {
        case 1: game_set.roll();
                break;
        case 2: System.out.println("Enter an index");
                int input = scanner.nextInt();
                game_set.rollIndividual(input);
                break;
        case 3: System.out.println("SCORE: " + game_set.sum());
                break;
        case 4: high_score = game_set.sum();
                System.out.println("High score saved.");
                break;
        case 5: System.out.println("CURRENT HIGH SCORE: " + high_score);
                break;
      }
    } catch(Exception e) { throw e; }
  }

  public static void main(String[] args) {
    high_score = 10;
    try {
      String arg_zero = args[0];
      String arg_one = args[1];
      int number_of_dice = Integer.parseInt(arg_zero);
      int number_of_sides = Integer.parseInt(arg_one);
      DiceSet game_set = new DiceSet(number_of_dice, number_of_sides);
      HighRoll temp = new HighRoll();
      boolean continue_game = true;

      while (continue_game) {
        temp.menu();

        Scanner scanner = new Scanner(System.in);
        String input_value = scanner.next();
        int input = 0;

        if (input_value.equals("Q") || input_value.equals("q")) {
          continue_game = false;
          break;
        } else { input = Integer.parseInt(input_value); }

        temp.do_operation(input, game_set);
      }
      System.out.println("QUITTING GAME");
    } catch(Exception e) { System.out.println("Exception " + e); }
  }
}
