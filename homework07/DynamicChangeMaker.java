import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  // main ting
  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
    validateDenominations(denominations);
    validateTarget(target);

    Tuple[][] table = new Tuple[denominations.length][target + 1];
    Tuple combinations = new Tuple(denominations.length);
    int rows = denominations.length;
    int columns = target + 1;
    Arrays.sort(denominations);
    initColZero(table, denominations.length);

    return new Tuple(0);
  }

  // initialize col zero to zero tuple
  private static void initColZero(Tuple[][] table, int coins) {
    for (int i = 0; i < coins; i++) {
      table[i][0] = new Tuple(coins);
    }
  }

  // validate denominations
  private static void validateDenominations(int[] denominations) {
    boolean existingNegatives = checkForNegatives(denominations);
    boolean existingDuplicates = checkForDuplicates(denominations);
    if (existingDuplicates || existingNegatives) { throw new IllegalArgumentException(); }
  }

  // validate target
  private static void validateTarget(int target) {
    if (target < 0) { throw new IllegalArgumentException(); }
  }

  // checks for neg denominations
  private static boolean checkForNegatives(int[] denominations) {
    for (int i = 0; i < denominations.length; i++) {
      if (denominations[i] <= 0) { return true; }
    }

    return false;
  }

  // checks for duplicate denominations
  private static boolean checkForDuplicates(int[] denominations) {
    Set<Integer> coins = new HashSet<Integer>();

    for (int i = 0; i < denominations.length; i++) {
      if (!coins.contains(new Integer(denominations[i]))) {
        coins.add(new Integer(denominations[i]));
      } else { return true; }
    }

    return false;
  }

  public static void main(String[] args) {
    int[] denomination = {3, 2, 1};
    int target = -1;

    try {
      DynamicChangeMaker.makeChangeWithDynamicProgramming(denomination, target);
    } catch (IllegalArgumentException e) {
      System.out.println("BAD DATA");
    }
  }
}
