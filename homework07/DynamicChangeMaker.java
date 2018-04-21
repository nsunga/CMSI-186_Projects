import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
    validateDenominations(denominations);

    Tuple[][] table = new Tuple[denominations.length][target + 1];

    initColZero(table, denominations.length);

    System.out.println("col zero should all be zero: ");
    for (int i = 0; i < denominations.length; i++) {
      System.out.println(table[i][0]);
    }

    Tuple combinations = new Tuple(denominations.length);
    Arrays.sort(denominations);
    return new Tuple(0);
  }

  private static void initColZero(Tuple[][] table, int coins) {
    for (int i = 0; i < coins; i++) {
      table[i][0] = new Tuple(coins);
    }
  }

  private static void validateDenominations(int[] denominations) {
    boolean existingNegatives = checkForNegatives(denominations);
    boolean existingDuplicates = checkForDuplicates(denominations);
    if (existingDuplicates || existingNegatives) { throw new IllegalArgumentException(); }
  }

  private static boolean checkForNegatives(int[] denominations) {
    for (int i = 0; i < denominations.length; i++) {
      if (denominations[i] <= 0) { return true; }
    }

    return false;
  }

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
    int[] denomination = {3, 2, 1, 1};
    int target = 5;

    try {
      DynamicChangeMaker.makeChangeWithDynamicProgramming(denomination, target);
    } catch (IllegalArgumentException e) {
      System.out.println("BAD DATA");
    }
  }
}
